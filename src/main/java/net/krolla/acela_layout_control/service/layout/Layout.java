package net.krolla.acela_layout_control.service.layout;

import net.krolla.acela_layout_control.bridge.BridgeInterface;
import net.krolla.acela_layout_control.bridge.MessageReceivedListener;
import net.krolla.acela_layout_control.bridge.command.*;
import net.krolla.acela_layout_control.bridge.response.*;
import net.krolla.acela_layout_control.model.Network;

public class Layout {
    private final Network network;
    private final BridgeInterface bridge;

    public Layout(Network network, BridgeInterface bridge) {
        this.network = network;
        this.bridge = bridge;

        setUpBridge();
    }

    private void setUpBridge() {
        bridge.setReceivedListener(new MessageReceivedListener() {
            @Override
            public void receivedMessage(BaseResponse response) {
                if (response instanceof NetworkOnlineResponse) { network.setIsOnline(true); }
                if (response instanceof NetworkOfflineResponse) { network.setIsOnline(false); }
                if (response instanceof CommunicationLostResponse) { network.setIsOnline(false); }
                if (response instanceof SensorStateChangeResponse) { readAllSensors(); }
            }
        });
    }

    public void onlineNetwork() { sendCommandToBridge(new NetworkOnlineCommand()); }

    public void offlineNetwork() { sendCommandToBridge(new NetworkOfflineCommand()); }

    public void pollNetwork() { sendCommandToBridge(new PollNetworkCommand()); }

    public void requestFirmwareVersion() { sendCommandToBridge(new ReadRevisionCommand());}

    public void readAllSensors() { sendCommandToBridge(new ReadAllSensorsCommand()); }

    private void sendCommandToBridge(BaseCommand command) {
        // if the network is not currently operating, and we aren't starting it, then ignore commands
        if ((!network.getIsOnline() || !bridge.isOpen()) && !(command instanceof NetworkOnlineCommand)) { return; }

        bridge.sendCommand(command);
    }
}

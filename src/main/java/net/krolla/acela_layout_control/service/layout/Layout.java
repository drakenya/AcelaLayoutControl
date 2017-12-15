package net.krolla.acela_layout_control.service.layout;

import net.krolla.acela_layout_control.bridge.BridgeInterface;
import net.krolla.acela_layout_control.bridge.MessageReceivedListener;
import net.krolla.acela_layout_control.bridge.command.BaseCommand;
import net.krolla.acela_layout_control.bridge.command.NetworkOfflineCommand;
import net.krolla.acela_layout_control.bridge.command.NetworkOnlineCommand;
import net.krolla.acela_layout_control.bridge.command.PollNetworkCommand;
import net.krolla.acela_layout_control.bridge.response.BaseResponse;
import net.krolla.acela_layout_control.model.Network;

public class Layout {
    private final Network network;
    private final BridgeInterface bridge;

    public Layout(Network network, BridgeInterface bridge) {
        this.network = network;
        this.bridge = bridge;

        setUpNetwork();
        setUpBridge();
    }

    private void setUpBridge() {
        bridge.setReceivedListener(new MessageReceivedListener() {
            @Override
            public void receivedMessage(BaseResponse response) {
                System.out.println("Received response: " + response);
            }
        });
    }

    private void setUpNetwork() {
        network.isOnlineProperty().addListener(((observable, oldValue, newValue) -> {
            System.out.println("HUH DO I NEED TO BE HERE?");
        }));
    }

    public void onlineNetwork() { sendCommandToBridge(new NetworkOnlineCommand()); }

    public void offlineNetwork() { sendCommandToBridge(new NetworkOfflineCommand()); }

    public void pollNetwork() { sendCommandToBridge(new PollNetworkCommand()); }

    private void sendCommandToBridge(BaseCommand command) {
        synchronized (this) {
            if (!network.getIsOnline() || !bridge.isOpen()) { return; }

            bridge.sendCommand(command);
        }
    }
}

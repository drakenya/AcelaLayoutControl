package net.krolla.acela_layout_control.view;

import net.krolla.acela_layout_control.model.Network;
import net.krolla.acela_layout_control.service.layout.Layout;

public class NetworkPresenter {
    private final Network network;
    private final NetworkView networkView;
    private final Layout layout;

    public NetworkPresenter(Network network, NetworkView networkView, Layout layout) {
        this.network = network;
        this.networkView = networkView;
        this.layout = layout;

        attachEvents();
    }

    private void attachEvents() {
        networkView.networkStatusBtn.setOnAction(e -> handleNetworkStatus());
        networkView.pollNetworkBtn.setOnAction(e -> layout.pollNetwork());
        networkView.requestFirmwareVersionBtn.setOnAction(e -> layout.requestFirmwareVersion());
        networkView.readAllSensorsBtn.setOnAction(e -> layout.readAllSensors());

        network.isOnlineProperty().addListener((observable, oldValue, newValue) -> networkView.updateStatusButton());
    }

    private void handleNetworkStatus() {
        if (!network.getIsOnline()) {
            layout.onlineNetwork();
        } else {
            layout.offlineNetwork();
        }
    }
}

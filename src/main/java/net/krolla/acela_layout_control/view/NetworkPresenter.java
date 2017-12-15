package net.krolla.acela_layout_control.view;

import net.krolla.acela_layout_control.model.Network;

public class NetworkPresenter {
    private final Network network;
    private final NetworkView networkView;

    public NetworkPresenter(Network network, NetworkView networkView) {
        this.network = network;
        this.networkView = networkView;

        attachEvents();
    }

    private void attachEvents() {
        networkView.networkStatusBtn.setOnAction(e -> handleNetworkStatus());
        networkView.pollNetworkBtn.setOnAction(e -> handlePollNetwork());

        network.isOnlineProperty().addListener((observable, oldValue, newValue) -> networkView.updateStatusButton());
    }

    private void handleNetworkStatus() {
        network.setIsOnline(!network.getIsOnline());
    }

    private void handlePollNetwork() {

    }
}

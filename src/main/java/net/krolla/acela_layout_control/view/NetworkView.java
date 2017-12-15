package net.krolla.acela_layout_control.view;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import net.krolla.acela_layout_control.model.Network;

public class NetworkView extends GridPane{
    private final Network network;

    Button networkStatusBtn = new Button("Network Status");
    Button pollNetworkBtn = new Button("Poll Network");
    Button requestFirmwareVersionBtn = new Button("Get Firmware Version");

    public void updateStatusButton() {
        if (network.getIsOnline()) {
            Platform.runLater(() -> networkStatusBtn.setText("Online"));
        } else {
            Platform.runLater(() -> networkStatusBtn.setText("Offline"));
        }
    }

    private void layoutForm() {
        this.setHgap(5);
        this.setVgap(5);

        this.add(networkStatusBtn, 1, 1);
        this.add(pollNetworkBtn, 2, 1);
        this.add(requestFirmwareVersionBtn, 2, 2);
    }

    public NetworkView(Network network) {
        this.network = network;

        updateStatusButton();
        layoutForm();
    }
}

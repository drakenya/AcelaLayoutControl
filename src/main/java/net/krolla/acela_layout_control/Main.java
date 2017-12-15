package net.krolla.acela_layout_control;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.krolla.acela_layout_control.bridge.Acela;
import net.krolla.acela_layout_control.model.Network;
import net.krolla.acela_layout_control.service.layout.Layout;
import net.krolla.acela_layout_control.view.NetworkPresenter;
import net.krolla.acela_layout_control.view.NetworkView;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Network network = new Network();
        NetworkView networkView = new NetworkView(network);
        networkView.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: blue;"
        );

        Acela acela = new Acela("cu.usbmodem1411");
        acela.open();

        Layout layout = new Layout(network, acela);

        Scene scene = new Scene(networkView);

        NetworkPresenter networkPresenter = new NetworkPresenter(network, networkView, layout);

        primaryStage.setOnCloseRequest(windowEvent -> acela.close());

        primaryStage.setScene(scene);
        primaryStage.setTitle("Layout Control");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

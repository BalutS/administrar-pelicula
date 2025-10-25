package org.unimag.app;

import javafx.application.Application;
import javafx.stage.Stage;
import org.unimag.controlador.SalidaControlador;
import org.unimag.vista.gestor.VistaAdmi;

public class Principal extends Application {

    private VistaAdmi vistaAdmi;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        vistaAdmi = new VistaAdmi(stage);

        stage.setTitle("Hallo :D");

        vistaAdmi.habilitarXCerrar(
                () -> {
                    SalidaControlador.verificar(stage);
                });

        stage.show();

    }
}

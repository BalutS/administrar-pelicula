package org.unimag.vista.gestor;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.unimag.recurso.constante.Configuracion;

public class VistaAdmi {

    private final Stage miEscenario;
    private final Scene miScene;
    private final Pane miPanelCuerpo;
    private final BorderPane miPanelPrincipal;

    public VistaAdmi(Stage esce) {
        miEscenario = esce;
        miPanelCuerpo = new Pane();
        miPanelPrincipal = new BorderPane();

        VistaCabecera cabecera = new VistaCabecera(esce, miPanelPrincipal, Configuracion.ALTO_CABECERA);

        miPanelPrincipal.setTop(cabecera);
        miPanelPrincipal.setCenter(miPanelCuerpo);

        miScene = new Scene(miPanelPrincipal, Configuracion.ANCHO_APP, Configuracion.ALTO_APP);
        miEscenario.setScene(miScene);

    }

    public void habilitarXCerrar(Runnable accion) {
        miEscenario.setOnCloseRequest(
                (e) -> {
                    e.consume();
                    accion.run();

                });
    }

}

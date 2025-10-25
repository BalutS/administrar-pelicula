package org.unimag.controlador.genero;

import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.unimag.vista.genero.VistaGeneroAdministrar;
import org.unimag.vista.genero.VistaGeneroListar;
import org.unimag.vista.genero.VistaGeneroCrear;

public class GeneroVistasControlador {

    public static StackPane crearGen(Stage esce, double anchito, double altito) {
        return new VistaGeneroCrear(esce, anchito, altito);
    }

    public static StackPane ListarGenero(Stage esce,
            double ancho, double alto) {
        return new VistaGeneroListar(esce, ancho, alto);
    }

    public static StackPane administrarGenero(Stage esce,
            double ancho, double alto) {
        return new VistaGeneroAdministrar(esce, ancho, alto);
    }
}

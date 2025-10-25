package org.unimag.controlador.pelicula;

import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.unimag.vista.pelicula.VistaPeliculaCrear;
import org.unimag.vista.pelicula.VistaPeliculaListar;

public class PeliculaVistasControlador {

    public static StackPane crearPelicula(Stage esce, double anchito, double altito) {
        return new VistaPeliculaCrear(esce, anchito, altito);
    }
    
    public static StackPane ListarPelicula(Stage esce,
            double ancho, double alto){
        return new VistaPeliculaListar(esce, ancho, alto);
    }
}

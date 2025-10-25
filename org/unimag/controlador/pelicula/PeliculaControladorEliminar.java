package org.unimag.controlador.pelicula;

import org.unimag.servicio.PeliculaServicio;

public class PeliculaControladorEliminar {

    public static Boolean borrar(int codigo) {
        Boolean correcto;
        PeliculaServicio peliculaServicio = new PeliculaServicio();
        correcto = peliculaServicio.deleteFrom(codigo);
        return correcto;
    }
}

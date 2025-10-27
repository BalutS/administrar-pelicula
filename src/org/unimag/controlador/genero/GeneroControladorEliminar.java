package org.unimag.controlador.genero;

import org.unimag.servicio.GeneroServicio;

public class GeneroControladorEliminar {

    public static Boolean borrar(int codigo) {
        Boolean correcto;
        GeneroServicio generoServicio = new GeneroServicio();
        correcto = generoServicio.deleteFrom(codigo);
        return correcto;
    }
}

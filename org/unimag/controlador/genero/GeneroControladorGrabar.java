package org.unimag.controlador.genero;

import org.unimag.dto.GeneroDto;
import org.unimag.servicio.GeneroServicio;

public class GeneroControladorGrabar {

    public static Boolean crearGenero(GeneroDto dto) {
        
        Boolean correcto = false;
        GeneroServicio generoServicio = new GeneroServicio();
        GeneroDto dtoRespuesta;
        dtoRespuesta = generoServicio.inserInto(dto, "");

        if (dtoRespuesta != null) {
            correcto = true;
        }

        return correcto;
    }
}

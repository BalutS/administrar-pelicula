package org.unimag.controlador.pelicula;

import org.unimag.dto.PeliculaDto;
import org.unimag.servicio.PeliculaServicio;

public class PeliculaControladorGrabar {

    public static Boolean crearPelicula(PeliculaDto dto) {
        
        Boolean correcto = false;
        PeliculaServicio peliculaServicio = new PeliculaServicio();
        PeliculaDto dtoRespuesta;
        dtoRespuesta = peliculaServicio.inserInto(dto, "");

        if (dtoRespuesta != null) {
            correcto = true;
        }

        return correcto;
    }
}

package org.unimag.controlador.pelicula;

import java.util.List;
import org.unimag.dto.PeliculaDto;
import org.unimag.servicio.PeliculaServicio;

public class PeliculaControladorListar {
    
    public static List<PeliculaDto> obtenerPelicula(){
        PeliculaServicio miDao = new PeliculaServicio();
        List<PeliculaDto> arreglo = miDao.selectFrom();
        
        return arreglo;
    }

    public static int obtenerCantidadPelicula(){
        PeliculaServicio miDao = new PeliculaServicio();
        int cantidad = miDao.numRows();
        return cantidad;
    }
}

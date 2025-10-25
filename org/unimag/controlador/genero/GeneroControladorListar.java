package org.unimag.controlador.genero;

import java.util.List;
import org.unimag.dto.GeneroDto;
import org.unimag.servicio.GeneroServicio;

public class GeneroControladorListar {

    public static List<GeneroDto> obtenerGeneros() {
        GeneroServicio miDao = new GeneroServicio();
        List<GeneroDto> arreglo = miDao.selectFrom();

        return arreglo;
    }

    public static List<GeneroDto> obtenerGenerosActivos() {
        GeneroServicio miDao = new GeneroServicio();
        List<GeneroDto> arreglo = miDao.selectFromWhereActivos();

        return arreglo;
    }

    public static int obtenerCantidadGeneros() {
        GeneroServicio miDao = new GeneroServicio();
        int cantidad = miDao.numRows();
        return cantidad;
    }
}

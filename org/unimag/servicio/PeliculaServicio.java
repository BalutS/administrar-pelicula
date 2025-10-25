package org.unimag.servicio;

import com.poo.persistence.NioFile;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.unimag.api.ApiOperacionBD;
import org.unimag.dto.GeneroDto;
import org.unimag.dto.PeliculaDto;
import org.unimag.modelo.Genero;
import org.unimag.modelo.Pelicula;
import org.unimag.recurso.constante.Persistencia;

public class PeliculaServicio implements ApiOperacionBD<PeliculaDto, Integer> {

    private NioFile miArchivo;
    private String nombrePersistencia;

    public PeliculaServicio() {
        nombrePersistencia = Persistencia.NOMBRE_PELICULA;
        try {
            miArchivo = new NioFile(nombrePersistencia);
        } catch (IOException ex) {
            Logger.getLogger(PeliculaServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getSerial() {
        int id = 0;

        try {
            id = miArchivo.ultimoCodigo() + 1;
        } catch (IOException ex) {
            Logger.getLogger(PeliculaServicio.class.getName()).log(Level.SEVERE, null, ex);
        }

        return id;
    }

    @Override
    public PeliculaDto inserInto(PeliculaDto dto, String ruta) {

        Genero objGenero = new Genero(dto.getGeneroPelicula().getIdGenero(),
                dto.getGeneroPelicula().getNombreGenero(),
                dto.getGeneroPelicula().getEstadoGenero(),
                dto.getGeneroPelicula().getCantidadPeliculaGenero()
        );

        Pelicula objPelicula = new Pelicula();

        objPelicula.setIdPelicula(getSerial());
        objPelicula.setNombrePelicula(dto.getNombrePelicula());
        objPelicula.setProtagonistaPelicula(dto.getProtagonistaPelicula());
        objPelicula.setGeneroPelicula(objGenero);
        objPelicula.setPresupuestoPelicula(dto.getPresupuestoPelicula());
        objPelicula.setRestriccionEdadPelicula(dto.getRestriccionEdadPelicula());

        String filaGrabar = objPelicula.getIdPelicula() + Persistencia.SEPARADOR_COLUMNAS
                + objPelicula.getNombrePelicula() + Persistencia.SEPARADOR_COLUMNAS
                + objPelicula.getProtagonistaPelicula() + Persistencia.SEPARADOR_COLUMNAS
                + objPelicula.getGeneroPelicula().getIdGenero() + Persistencia.SEPARADOR_COLUMNAS
                + BigDecimal.valueOf(objPelicula.getPresupuestoPelicula()).toPlainString() + Persistencia.SEPARADOR_COLUMNAS
                + objPelicula.getRestriccionEdadPelicula();

        if (miArchivo.agregarRegistro(filaGrabar)) {
            dto.setIdPelicula(objPelicula.getIdPelicula());
            return dto;
        }

        return null;
    }

    // Esta es la trampa más eficiente para sacar la cantidad de hijos de un papá
    // En otras palabras la cantidad de películas de un género
    // *************************************************************************
    public Map<Integer, Integer> peliculasPorGenero() {
        Map<Integer, Integer> arrCantidades = new HashMap<>();
        List<String> arregloDatos = miArchivo.obtenerDatos();

        for (String cadena : arregloDatos) {
            try {
                cadena = cadena.replace("@", "");
                String[] columnas = cadena.split(Persistencia.SEPARADOR_COLUMNAS);
                // Analiza que la única columna que uso es la del papá (idGenero)
                // Pilas es la columna del código del género
                int idGenero = Integer.parseInt(columnas[3].trim());
                // Y el trucazo es que cuento los idGenero, si no existe mi vale le pongo cero y si existe le sumo 1, breve
                arrCantidades.put(idGenero, arrCantidades.getOrDefault(idGenero, 0) + 1);

            } catch (NumberFormatException error) {
                Logger.getLogger(PeliculaServicio.class.getName()).log(Level.SEVERE, null, error);
            }
        }
        return arrCantidades;
    }
    // *************************************************************************

    @Override
    public List<PeliculaDto> selectFrom() {
        // Esta es la trampa para tener los generos completos en peliculas
        // *********************************************************************
        GeneroServicio generoServicio = new GeneroServicio();
        List<GeneroDto> arrGeneros = generoServicio.selectFrom();
        // *********************************************************************

        List<PeliculaDto> arregloPeliculaDtos = new ArrayList<>();
        List<String> arregloDatos = miArchivo.obtenerDatos();

        Double presupuesto;
        Boolean restriccion;
        String nombre, protagonista;
        Integer codigoPelicula, codigoGenero;

        for (String cadena : arregloDatos) {
            try {
                cadena = cadena.replace("@", "");
                String[] columnas = cadena.split(Persistencia.SEPARADOR_COLUMNAS);

                codigoPelicula = Integer.valueOf(columnas[0].trim());
                nombre = columnas[1].trim();
                protagonista = columnas[2].trim();
                codigoGenero = Integer.valueOf(columnas[3].trim());
                presupuesto = Double.valueOf(columnas[4].trim());
                restriccion = Boolean.valueOf(columnas[5].trim());

                PeliculaDto objPeliculaDto = new PeliculaDto();
                objPeliculaDto.setIdPelicula(codigoPelicula);
                objPeliculaDto.setNombrePelicula(nombre);
                objPeliculaDto.setProtagonistaPelicula(protagonista);
                objPeliculaDto.setPresupuestoPelicula(presupuesto);
                objPeliculaDto.setRestriccionEdadPelicula(restriccion);

                // *************************************************************
                objPeliculaDto.setGeneroPelicula(obtenerGeneroCompleto(codigoGenero, arrGeneros));
                // *************************************************************

                arregloPeliculaDtos.add(objPeliculaDto);
            } catch (NumberFormatException error) {
                Logger.getLogger(PeliculaServicio.class.getName()).log(Level.SEVERE, null, error);
            }
        }
        return arregloPeliculaDtos;
    }

    // Si ya tienes los géneros, podemos buscar el que necesitemos a lo easy peasy
    // *************************************************************************
    private GeneroDto obtenerGeneroCompleto(Integer codigoGenero, List<GeneroDto> arrGeneros) {
        for (GeneroDto generoExterno : arrGeneros) {
            if (Objects.equals(codigoGenero, generoExterno.getIdGenero())) {
                return generoExterno;
            }
        }
        return null;
    }
    // *************************************************************************

    @Override
    public int numRows() {
        int cantidad = 0;
        try {
            cantidad = miArchivo.cantidadFilas();

        } catch (IOException ex) {
            Logger.getLogger(PeliculaServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cantidad;
    }

    @Override
    public Boolean deleteFrom(Integer codigo) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public PeliculaDto getOne(Integer codigo) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public PeliculaDto updateSet(Integer codigo, PeliculaDto objeto, String ruta) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}

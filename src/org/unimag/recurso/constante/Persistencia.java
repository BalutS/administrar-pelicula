package org.unimag.recurso.constante;

import java.io.File;

public class Persistencia {

    public static final String RUTA_PROYECTO = System.getProperty("user.dir");
    public static final String NOMBRE_BASE_DATOS_GENERO = "Genero.txt";
    public static final String NOMBRE_BASE_DATOS_PELICULAS = "Pelicula.txt";
    public static final String NOMBRE_CARPETA = "miBaseDeDatos";
    public static final String NOMBRE_CARPETA_IMAGENES_EXTERNAS 
            = "lasFotos";
    public static final String NOMBRE_CARPETA_IMAGENES_INTERNAS
            = "/org/unimag/recurso/imagenes/";
    
    public static final String RUTA_FOTOS = RUTA_PROYECTO
            + Persistencia.SEPARADOR_CARPETAS
            +NOMBRE_CARPETA_IMAGENES_EXTERNAS;

    public static final String SEPARADOR_COLUMNAS = ";";
    public static final String SEPARADOR_CARPETAS = File.separator;

    public static final String NOMBRE_GENERO = RUTA_PROYECTO + SEPARADOR_CARPETAS
            + NOMBRE_CARPETA + SEPARADOR_CARPETAS + NOMBRE_BASE_DATOS_GENERO;

    public static final String NOMBRE_PELICULA = RUTA_PROYECTO + SEPARADOR_CARPETAS
            + NOMBRE_CARPETA + SEPARADOR_CARPETAS + NOMBRE_BASE_DATOS_PELICULAS;
}

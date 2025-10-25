package org.unimag.modelo;

public class Genero {

    private Integer idGenero;
    private String nombreGenero;
    private Boolean estadoGenero;
    private Short cantidadPeliculaGenero;

    public Genero() {
    }

    public Genero(Integer idGenero, String nombreGenero, Boolean estadoGenero, Short cantidadPeliculaGenero) {
        this.idGenero = idGenero;
        this.nombreGenero = nombreGenero;
        this.estadoGenero = estadoGenero;
        this.cantidadPeliculaGenero = cantidadPeliculaGenero;
    }

    

    public Integer getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(Integer idGenero) {
        this.idGenero = idGenero;
    }

    public String getNombreGenero() {
        return nombreGenero;
    }

    public void setNombreGenero(String nombreGenero) {
        this.nombreGenero = nombreGenero;
    }

    public Boolean getEstadoGenero() {
        return estadoGenero;
    }

    public void setEstadoGenero(Boolean estadoGenero) {
        this.estadoGenero = estadoGenero;
    }

    public Short getCantidadPeliculaGenero() {
        return cantidadPeliculaGenero;
    }

    public void setCantidadPeliculaGenero(Short cantidadPeliculaGenero) {
        this.cantidadPeliculaGenero = cantidadPeliculaGenero;
    }

    @Override
    public String toString() {
        return nombreGenero;
    }
    
    

   

}

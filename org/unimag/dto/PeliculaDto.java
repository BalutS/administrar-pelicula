package org.unimag.dto;

public class PeliculaDto {

    private Integer idPelicula;
    private String nombrePelicula;
    private String protagonistaPelicula;
    private GeneroDto generoPelicula;
    private Double presupuestoPelicula;
    private Boolean restriccionEdadPelicula;
    
    public PeliculaDto() {
    }

    public PeliculaDto(Integer idPelicula, String nombrePelicula, String protagonistaPelicula, GeneroDto generoPelicula, Double presupuestoPelicula, Boolean restriccionEdadPelicula, GeneroDto idGenero) {
        this.idPelicula = idPelicula;
        this.nombrePelicula = nombrePelicula;
        this.protagonistaPelicula = protagonistaPelicula;
        this.generoPelicula = generoPelicula;
        this.presupuestoPelicula = presupuestoPelicula;
        this.restriccionEdadPelicula = restriccionEdadPelicula;
    }

    

    public Integer getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(Integer idPelicula) {
        this.idPelicula = idPelicula;
    }

    public String getNombrePelicula() {
        return nombrePelicula;
    }

    public void setNombrePelicula(String nombrePelicula) {
        this.nombrePelicula = nombrePelicula;
    }

    public String getProtagonistaPelicula() {
        return protagonistaPelicula;
    }

    public void setProtagonistaPelicula(String protagonistaPelicula) {
        this.protagonistaPelicula = protagonistaPelicula;
    }

    public GeneroDto getGeneroPelicula() {
        return generoPelicula;
    }

    public void setGeneroPelicula(GeneroDto generoPelicula) {
        this.generoPelicula = generoPelicula;
    }

    public Double getPresupuestoPelicula() {
        return presupuestoPelicula;
    }

    public void setPresupuestoPelicula(Double presupuestoPelicula) {
        this.presupuestoPelicula = presupuestoPelicula;
    }

    public Boolean getRestriccionEdadPelicula() {
        return restriccionEdadPelicula;
    }

    public void setRestriccionEdadPelicula(Boolean restriccionEdadPelicula) {
        this.restriccionEdadPelicula = restriccionEdadPelicula;
    }

}

    
   


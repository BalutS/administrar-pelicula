package org.unimag.modelo;

public class Pelicula {

    private Integer idPelicula;
    private String nombrePelicula;
    private String protagonistaPelicula;
    private Genero generoPelicula;
    private Double presupuestoPelicula;
    private Boolean restriccionEdadPelicula;

    public Pelicula() {
    }

    public Pelicula(Integer idPelicula, String nombrePelicula, String protagonistaPelicula, Genero generoPelicula, Double presupuestoPelicula, Boolean restriccionEdadPelicula) {
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

    public Genero getGeneroPelicula() {
        return generoPelicula;
    }

    public void setGeneroPelicula(Genero generoPelicula) {
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

    @Override
    public String toString() {
        return "Pelicula:" 
                + "idPelicula = " + idPelicula 
                + "nombrePelicula = " + nombrePelicula 
                + "protagonistaPelicula = " + protagonistaPelicula 
                + "generoPelicula = " + generoPelicula 
                + "presupuestoPelicula = " + presupuestoPelicula 
                + "restriccionEdadPelicula = " + restriccionEdadPelicula;
    }
    
    

    
    

    
}

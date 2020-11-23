package edu.upc.dsa.models;

import com.sun.org.apache.xpath.internal.operations.Bool;

public class Muestra {


    String idMuestra;
    String idClinico;
    String idPersona;
    int fechaExtraccion;
    String idLab;
    Boolean positivo;
    String comentario;

    public Muestra() {
    }

    public Muestra(String idMuestra, String idClinico, String idPersona, int fechaExtraccion, String idLab) {
        this.idMuestra = idMuestra;
        this.idClinico = idClinico;
        this.idPersona = idPersona;
        this.fechaExtraccion = fechaExtraccion;
        this.idLab = idLab;
    }

    public String getIdMuestra() {
        return idMuestra;
    }

    public void setIdMuestra(String idMuestra) {
        this.idMuestra = idMuestra;
    }

    public String getIdClinico() {
        return idClinico;
    }

    public void setIdClinico(String idClinico) {
        this.idClinico = idClinico;
    }

    public String getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(String idPersona) {
        this.idPersona = idPersona;
    }

    public int getFechaExtraccion() {
        return fechaExtraccion;
    }

    public void setFechaExtraccion(int fechaExtraccion) {
        this.fechaExtraccion = fechaExtraccion;
    }

    public String getIdLab() {
        return idLab;
    }

    public void setIdLab(String idLab) {
        this.idLab = idLab;
    }

    public Boolean getPositivo() {
        return positivo;
    }

    public void setPositivo(Boolean positivo) {
        this.positivo = positivo;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    @Override
    public String toString() {
        return "Muestra [idMuestra = " + idMuestra + ", idClinico = " + idClinico + ", idPersona = " + idPersona + ", fechaExtraccion = " + fechaExtraccion + ", idLab = " + idLab + "]";
    }
}


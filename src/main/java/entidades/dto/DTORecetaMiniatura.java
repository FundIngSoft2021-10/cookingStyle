package entidades.dto;

import entidades.modelo.Categoria;
import entidades.modelo.Chef;

import java.util.List;

public class DTORecetaMiniatura {
    private int idReceta;
    private String nombreReceta;
    private String linkImagen;
    private Chef autor;

    public DTORecetaMiniatura(int idReceta, String nombreReceta, String linkImagen, Chef autor) {
        this.idReceta = idReceta;
        this.nombreReceta = nombreReceta;
        this.linkImagen = linkImagen;
        this.autor = autor;
    }

    public DTORecetaMiniatura() {
    }

    public int getIdReceta() {
        return idReceta;
    }

    public void setIdReceta(int idReceta) {
        this.idReceta = idReceta;
    }

    public String getNombreReceta() {
        return nombreReceta;
    }

    public void setNombreReceta(String nombreReceta) {
        this.nombreReceta = nombreReceta;
    }

    public String getLinkImagen() {
        return linkImagen;
    }

    public void setLinkImagen(String linkImagen) {
        this.linkImagen = linkImagen;
    }

    public Chef getAutor() {
        return autor;
    }

    public void setAutor(Chef autor) {
        this.autor = autor;
    }
}

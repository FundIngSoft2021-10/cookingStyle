package entidades.dto;

import entidades.modelo.Categoria;
import entidades.modelo.Chef;

import java.math.BigInteger;
import java.util.List;

public class DTORecetaMiniatura {
    private BigInteger idReceta;
    private String nombreReceta;
    private String linkImagen;
    private Chef autor;
    private boolean encontrado;
    private int idCategoria;

    public DTORecetaMiniatura(BigInteger idReceta, String nombreReceta, String linkImagen, Chef autor) {
        this.idReceta = idReceta;
        this.nombreReceta = nombreReceta;
        this.linkImagen = linkImagen;
        this.autor = autor;
    }

    public DTORecetaMiniatura() {
    }

    public BigInteger getIdReceta() {
        return idReceta;
    }

    public void setIdReceta(BigInteger idReceta) {
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

    public boolean isEncontrado() {
        return encontrado;
    }

    public void setEncontrado(boolean encontrado) {
        this.encontrado = encontrado;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }
}

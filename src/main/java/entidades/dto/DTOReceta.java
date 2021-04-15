package entidades.dto;

import entidades.modelo.Categoria;
import entidades.modelo.Chef;
import entidades.modelo.Receta;

public class DTOReceta {

    private Receta receta;
    private Chef autor;
    private Categoria categoria;

    public DTOReceta(Receta receta, Chef autor, Categoria categoria) {
        this.receta = receta;
        this.autor = autor;
        this.categoria = categoria;
    }

    public Receta getReceta() {
        return receta;
    }

    public void setReceta(Receta receta) {
        this.receta = receta;
    }

    public Chef getAutor() {
        return autor;
    }

    public void setAutor(Chef autor) {
        this.autor = autor;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}

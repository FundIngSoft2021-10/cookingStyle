package entidades.dto;

import entidades.modelo.Categoria;
import entidades.modelo.Chef;
import entidades.modelo.Receta;

public class DTOReceta {
    private Receta receta;
    private Chef autor;
    private boolean encotrado;

    public DTOReceta() {
    }

    public DTOReceta(Receta receta, Chef autor) {
        this.receta = receta;
        this.autor = autor;
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

    public boolean isEncotrado() {
        return encotrado;
    }

    public void setEncotrado(boolean encotrado) {
        this.encotrado = encotrado;
    }
}

package entidades.dto;

import entidades.modelo.Chef;
import entidades.modelo.Receta;

public class DTOReceta {
    private Receta receta;
    private Chef autor;
    private String mensaje;
    private boolean encontrado;

    public DTOReceta() {
    }

    public DTOReceta(Receta receta, Chef autor) {
        this.receta = receta;
        this.autor = autor;
    }

    public DTOReceta(Receta receta, Chef autor, String mensaje) {
        this.receta = receta;
        this.autor = autor;
        this.mensaje = mensaje;
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

    public boolean isEncontrado() {
        return encontrado;
    }

    public void setEncontrado(boolean encontrado) {
        this.encontrado = encontrado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}

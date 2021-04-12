package entidades.modelo;

import java.util.List;

public class ListaFavoritos {
    private String nombre;
    private String descripicion;
    private List<Receta> recetasFavoritas;

    public ListaFavoritos(String nombre, String descripicion, List<Receta> recetasFavoritas) {
        this.nombre = nombre;
        this.descripicion = descripicion;
        this.recetasFavoritas = recetasFavoritas;
    }

    public ListaFavoritos() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripicion() {
        return descripicion;
    }

    public void setDescripicion(String descripicion) {
        this.descripicion = descripicion;
    }

    public List<Receta> getRecetasFavoritas() {
        return recetasFavoritas;
    }

    public void setRecetasFavoritas(List<Receta> recetasFavoritas) {
        this.recetasFavoritas = recetasFavoritas;
    }
}

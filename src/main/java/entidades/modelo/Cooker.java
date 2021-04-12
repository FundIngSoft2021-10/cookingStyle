package entidades.modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Cooker extends Usuario {
    private List<ListaFavoritos> listasFavoritos;

    public Cooker(long idUsuario, String nombreUsuario, String correo, Date fechaCreacion, String nombre, String apellido) {
        super(idUsuario, nombreUsuario, correo, fechaCreacion, nombre, apellido);
        this.listasFavoritos = new ArrayList<>();
    }

    public Cooker() {
    }

    public List<ListaFavoritos> getListasFavoritos() {
        return listasFavoritos;
    }

    public void setListasFavoritos(List<ListaFavoritos> listasFavoritos) {
        this.listasFavoritos = listasFavoritos;
    }
}

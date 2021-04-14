package entidades.modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Cooker extends Usuario {
    private List<ListaFavoritos> listasFavoritos;
    private List<Cita> citas;

    public Cooker(int idUsuario, String nombreUsuario, String correo, Date fechaCreacion, String nombre, String apellido, List<ListaFavoritos> listasFavoritos, List<Cita> citas) {
        super(idUsuario, nombreUsuario, correo, fechaCreacion, nombre, apellido);
        this.listasFavoritos = listasFavoritos;
        this.citas = citas;
    }

    public Cooker(int idUsuario, String nombreUsuario, String correo, Date fechaCreacion, String nombre, String apellido) {
        super(idUsuario, nombreUsuario, correo, fechaCreacion, nombre, apellido);
        this.citas = new ArrayList<>();
        this.listasFavoritos = new ArrayList<>();
    }

    public Cooker() {
        this.citas = new ArrayList<>();
        this.listasFavoritos = new ArrayList<>();
    }

    public List<ListaFavoritos> getListasFavoritos() {
        return listasFavoritos;
    }

    public void setListasFavoritos(List<ListaFavoritos> listasFavoritos) {
        this.listasFavoritos = listasFavoritos;
    }

    public List<Cita> getCitas() {
        return citas;
    }

    public void setCitas(List<Cita> citas) {
        this.citas = citas;
    }
}

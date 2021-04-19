package entidades.modelo;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Cooker extends Usuario {
    private List<ListaFavoritos> listasFavoritos;
    private List<Cita> citas;

    public Cooker(BigInteger idUsuario, String nombreUsuario, Date fechaCreacion, String nombre, List<ListaFavoritos> listasFavoritos, List<Cita> citas) {
        super(idUsuario, nombreUsuario, fechaCreacion, nombre);
        this.listasFavoritos = listasFavoritos;
        this.citas = citas;
    }

    public Cooker(BigInteger idUsuario, String nombreUsuario, Date fechaCreacion, String nombre) {
        super(idUsuario, nombreUsuario, fechaCreacion, nombre);
        this.citas = new ArrayList<>();
        this.listasFavoritos = new ArrayList<>();
    }

    public Cooker(BigInteger bigInteger, String s, Date date, String sa, List<ListaFavoritos> listaFavoritos) {
        this.citas = new ArrayList<>();
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

    public List<Cita> getCitas() {
        return citas;
    }

    public void setCitas(List<Cita> citas) {
        this.citas = citas;
    }
}

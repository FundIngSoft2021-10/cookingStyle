package entidades.modelo;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Chef extends Usuario {
    private List<Receta> recetas;
    private Calendario calendario;
    private List<Cita> citas;

    public Chef(BigInteger idUsuario, String nombreUsuario, Date fechaCreacion, String nombre, List<Receta> recetas, Calendario calendario, List<Cita> citas) {
        super(idUsuario, nombreUsuario, fechaCreacion, nombre);
        this.recetas = recetas;
        this.calendario = calendario;
        this.citas = citas;
    }

    public Chef(BigInteger idUsuario, String nombreUsuario, Date fechaCreacion, String nombre) {
        super(idUsuario, nombreUsuario, fechaCreacion, nombre);
        this.citas = new ArrayList<>();
        this.recetas = new ArrayList<>();
    }

    public Chef() {
        this.citas = new ArrayList<>();
        this.recetas = new ArrayList<>();
    }

    public List<Receta> getRecetas() {
        return recetas;
    }

    public void setRecetas(List<Receta> recetas) {
        this.recetas = recetas;
    }

    public Calendario getCalendario() {
        return calendario;
    }

    public void setCalendario(Calendario calendario) {
        this.calendario = calendario;
    }

    public List<Cita> getCitas() {
        return citas;
    }

    public void setCitas(List<Cita> citas) {
        this.citas = citas;
    }
}

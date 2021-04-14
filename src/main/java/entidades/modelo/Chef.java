package entidades.modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Chef extends Usuario {
    private List<Receta> recetas;
    private Calendario calendario;
    private List<Cita> citas;

    public Chef(int idUsuario, String nombreUsuario, String correo, Date fechaCreacion, String nombre, String apellido, List<Receta> recetas, Calendario calendario, List<Cita> citas) {
        super(idUsuario, nombreUsuario, correo, fechaCreacion, nombre, apellido);
        this.recetas = recetas;
        this.calendario = calendario;
        this.citas = citas;
    }

    public Chef(int idUsuario, String nombreUsuario, String correo, Date fechaCreacion, String nombre, String apellido) {
        super(idUsuario, nombreUsuario, correo, fechaCreacion, nombre, apellido);
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

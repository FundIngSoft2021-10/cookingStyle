package entidades.modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Chef extends Usuario {
    private List<Receta> recetas;
    private Calendario calendario;

    public Chef(long idUsuario, String nombreUsuario, String correo, Date fechaCreacion, String nombre, String apellido) {
        super(idUsuario, nombreUsuario, correo, fechaCreacion, nombre, apellido);
        this.recetas = new ArrayList<>();
    }

    public Chef() {
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
}

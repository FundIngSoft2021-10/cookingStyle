package entidades.dto;

import entidades.modelo.Calendario;
import entidades.modelo.Chef;

import java.util.List;

public class DTOAgendaChef {
    private boolean estado;
    private String mensaje;
    private Chef chef;
    private Calendario Calendario;

    public DTOAgendaChef() {
    }

    public DTOAgendaChef(boolean estado, String mensaje, Chef chef, entidades.modelo.Calendario calendario) {
        this.estado = estado;
        this.mensaje = mensaje;
        this.chef = chef;
        Calendario = calendario;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Chef getChef() {
        return chef;
    }

    public void setChef(Chef chef) {
        this.chef = chef;
    }

    public entidades.modelo.Calendario getCalendario() {
        return Calendario;
    }

    public void setCalendario(entidades.modelo.Calendario calendario) {
        Calendario = calendario;
    }
}

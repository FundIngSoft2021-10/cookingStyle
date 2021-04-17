package entidades.dto;

import entidades.modelo.Cita;

import java.util.List;

public class DTOAgendaChef {
    private boolean estado;
    private String mensaje;
    private List<Cita> Citas;

    public DTOAgendaChef(){

    }

    public DTOAgendaChef(boolean estado, String mensaje, List<Cita> citas) {
        this.estado = estado;
        this.mensaje = mensaje;
        Citas = citas;
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

    public List<Cita> getCitas() {
        return Citas;
    }

    public void setCitas(List<Cita> citas) {
        Citas = citas;
    }
}

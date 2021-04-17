package entidades.dto;

import entidades.modelo.Chef;
import entidades.modelo.Cita;

import java.util.List;

public class DTOCitasChef {
    private boolean estado;
    private String mensaje;
    private Chef chef;
    private List<Cita> Citas;

    public DTOCitasChef(){

    }

    public DTOCitasChef(boolean estado, String mensaje, Chef chef, List<Cita> citas) {
        this.estado = estado;
        this.mensaje = mensaje;
        this.chef = chef;
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

    public Chef getChef() {
        return chef;
    }

    public void setChef(Chef chef) {
        this.chef = chef;
    }
}

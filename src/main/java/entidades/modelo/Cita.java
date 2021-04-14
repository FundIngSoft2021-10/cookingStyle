package entidades.modelo;

import java.util.Date;

public class Cita {
    private Date fecha;
    private Bloque bloque;
    private Cooker cooker;
    private Chef chef;

    public Cita(Date fecha, Bloque bloque, Cooker cooker, Chef chef) {
        this.fecha = fecha;
        this.bloque = bloque;
        this.cooker = cooker;
        this.chef = chef;
    }

    public Cita() {
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Cooker getCooker() {
        return cooker;
    }

    public void setCooker(Cooker cooker) {
        this.cooker = cooker;
    }

    public Chef getChef() {
        return chef;
    }

    public void setChef(Chef chef) {
        this.chef = chef;
    }

    public Bloque getBloque() {
        return bloque;
    }

    public void setBloque(Bloque bloque) {
        this.bloque = bloque;
    }
}

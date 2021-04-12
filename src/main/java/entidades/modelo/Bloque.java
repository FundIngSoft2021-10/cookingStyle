package entidades.modelo;

public class Bloque {
    private Dia dia;
    private int hora;
    private boolean disponible;

    public Bloque(Dia dia, int hora, boolean disponible) {
        this.dia = dia;
        this.hora = hora;
        this.disponible = disponible;
    }

    public Bloque() {
    }

    public Dia getDia() {
        return dia;
    }

    public void setDia(Dia dia) {
        this.dia = dia;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}

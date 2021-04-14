package entidades.modelo;

public class Bloque {
    private Dia dia;
    private int hora;

    public Bloque(Dia dia, int hora) {
        this.dia = dia;
        this.hora = hora;
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
}

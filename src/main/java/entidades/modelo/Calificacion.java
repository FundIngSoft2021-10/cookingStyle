package entidades.modelo;

public class Calificacion {
    private float valor;
    private Cooker usuario;

    public Calificacion(float valor, Cooker usuario) {
        this.valor = valor;
        this.usuario = usuario;
    }

    public Calificacion() {
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public Cooker getUsuario() {
        return usuario;
    }

    public void setUsuario(Cooker usuario) {
        this.usuario = usuario;
    }
}

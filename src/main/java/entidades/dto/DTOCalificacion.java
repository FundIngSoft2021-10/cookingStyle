package entidades.dto;

public class DTOCalificacion {
    private float valor;
    private boolean estado;
    private String mensaje;

    public DTOCalificacion(float valor, boolean estado, String mensaje) {
        this.valor = valor;
        this.estado = estado;
        this.mensaje = mensaje;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
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
}

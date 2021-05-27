package entidades.dto;

public class DTOCalificacion {
    private int valor;
    private boolean estado;
    private String mensaje;

    public DTOCalificacion(int valor, boolean estado, String mensaje) {
        this.valor = valor;
        this.estado = estado;
        this.mensaje = mensaje;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
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

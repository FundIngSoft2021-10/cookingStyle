package entidades.dto;

public class DTOExito {
    private boolean estado;
    private String mensaje;

    public DTOExito(boolean estado, String mensaje) {
        this.estado = estado;
        this.mensaje = mensaje;
    }

    public DTOExito() {
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

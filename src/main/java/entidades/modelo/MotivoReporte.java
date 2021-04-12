package entidades.modelo;

public class MotivoReporte {
    private int idMotivo;
    private String nombre;
    private String descripcion;

    public MotivoReporte(int idMotivo, String nombre, String descripcion) {
        this.idMotivo = idMotivo;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public MotivoReporte() {
    }

    public int getIdMotivo() {
        return idMotivo;
    }

    public void setIdMotivo(int idMotivo) {
        this.idMotivo = idMotivo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

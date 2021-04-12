package entidades.modelo;

import java.util.Date;

public class Usuario {
    private long idUsuario;
    private String nombreUsuario;
    private String correo;
    private Date fechaCreacion;
    private String nombre;
    private String apellido;
    private CredencialesUsuario credenciales;

    public Usuario(long idUsuario, String nombreUsuario, String correo, Date fechaCreacion,
                   String nombre, String apellido) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.correo = correo;
        this.fechaCreacion = fechaCreacion;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Usuario() {
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public CredencialesUsuario getCredenciales() {
        return credenciales;
    }

    public void setCredenciales(CredencialesUsuario credenciales) {
        this.credenciales = credenciales;
    }
}

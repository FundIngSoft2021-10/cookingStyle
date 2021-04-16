package entidades.modelo;

import java.math.BigInteger;
import java.util.Date;

public class Usuario {
    private BigInteger idUsuario;
    private String nombreUsuario;
    private Date fechaCreacion;
    private String nombre;
    private CredencialesUsuario credenciales;

    public Usuario(BigInteger idUsuario, String nombreUsuario, Date fechaCreacion,
                   String nombre) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.fechaCreacion = fechaCreacion;
        this.nombre = nombre;
    }

    public Usuario() {
    }

    public BigInteger getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(BigInteger idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
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

    public CredencialesUsuario getCredenciales() {
        return credenciales;
    }

    public void setCredenciales(CredencialesUsuario credenciales) {
        this.credenciales = credenciales;
    }
}

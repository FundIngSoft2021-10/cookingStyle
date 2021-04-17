package entidades.dto;

import entidades.modelo.CredencialesUsuario;

import java.math.BigInteger;

public class DTOCredencialesBD {
    private boolean existe;
    private BigInteger idUsuario;
    private CredencialesUsuario credenciales;

    public DTOCredencialesBD(boolean existe, BigInteger idUsuario, CredencialesUsuario credenciales) {
        this.existe = existe;
        this.idUsuario = idUsuario;
        this.credenciales = credenciales;
    }

    public DTOCredencialesBD() {
    }

    public boolean isExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }

    public BigInteger getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(BigInteger idUsuario) {
        this.idUsuario = idUsuario;
    }

    public CredencialesUsuario getCredenciales() {
        return credenciales;
    }

    public void setCredenciales(CredencialesUsuario credenciales) {
        this.credenciales = credenciales;
    }
}

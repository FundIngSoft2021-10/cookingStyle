package entidades.modelo;

public class CredencialesUsuario {
    private String correo;
    private String salt;
    private String hash;

    public CredencialesUsuario(String correo, String salt, String hash) {
        this.correo = correo;
        this.salt = salt;
        this.hash = hash;
    }

    public CredencialesUsuario() {
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}

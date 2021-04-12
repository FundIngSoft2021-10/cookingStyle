package entidades.modelo;

import java.util.Date;

public class Admin extends Usuario {
    public Admin(long idUsuario, String nombreUsuario, String correo, Date fechaCreacion, String nombre, String apellido) {
        super(idUsuario, nombreUsuario, correo, fechaCreacion, nombre, apellido);
    }

    public Admin() {
    }
}

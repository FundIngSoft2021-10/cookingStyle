package entidades.modelo;

import java.math.BigInteger;
import java.util.Date;

public class Admin extends Usuario {
    public Admin(BigInteger idUsuario, String nombreUsuario, Date fechaCreacion, String nombre) {
        super(idUsuario, nombreUsuario, fechaCreacion, nombre);
    }

    public Admin() {
    }
}

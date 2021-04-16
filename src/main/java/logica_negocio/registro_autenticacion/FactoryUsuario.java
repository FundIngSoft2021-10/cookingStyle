package logica_negocio.registro_autenticacion;

import entidades.modelo.*;
import logica_negocio.utilidad.ControladorUtilidad;
import logica_negocio.utilidad.IControladorUtilidad;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;

public class FactoryUsuario {
    public FactoryUsuario() {
    }

    public Usuario crearUsuario(TipoUsuario tipo, String nombreUsuario, String nombre) {
        Usuario usuario;

        if (tipo == TipoUsuario.ADMIN) {
            usuario = new Admin();
        } else if (tipo == TipoUsuario.COOKER) {
            usuario = new Cooker();
            ((Cooker) usuario).getListasFavoritos().add(new ListaFavoritos(1, "Todas las recetas",
                    "Todas mis recetas favoritas", new ArrayList<>()));
        } else if (tipo == TipoUsuario.CHEF) {
            usuario = new Chef();
            ((Chef) usuario).setCalendario(new Calendario());
        } else {
            usuario = new Usuario();
        }

        IControladorUtilidad utilidad = new ControladorUtilidad();
        usuario.setIdUsuario(utilidad.generarRandomBigInt(new BigInteger("1000000000000"), new BigInteger("10000000000000")));
        usuario.setFechaCreacion(new Date());
        usuario.setNombreUsuario(nombreUsuario);
        usuario.setNombre(nombre);

        return usuario;
    }
}

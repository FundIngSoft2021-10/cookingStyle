package logica_negocio.registro_autenticacion;

import acceso_datos.consultas_bd.*;
import entidades.modelo.*;
import logica_negocio.utilidad.*;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class FactoryUsuario {
    private TipoUsuario tipo;

    public FactoryUsuario(TipoUsuario tipo) {
        this.tipo = tipo;
    }

    /**
     * Crea un usuario abstracto (Admin, Cooker o Chef), de acuerdo al tipo de Factory
     * @param nombreUsuario el nombre de usuario del objeto
     * @param nombre el nombre del objeto
     * @return el objeto {@link entidades.modelo.Usuario} inicializado abstractamente
     * @throws SQLException arroja excepción si no logra conectarse a la base de datos
     */
    public Usuario crearUsuario(String nombreUsuario, String nombre) throws SQLException {
        Usuario usuario = new Usuario();

        if (this.tipo == TipoUsuario.ADMIN) {
            usuario = new Admin();
        } else if (this.tipo == TipoUsuario.COOKER) {
            usuario = new Cooker();
            ((Cooker) usuario).getListasFavoritos().add(new ListaFavoritos(1, "Todas las recetas",
                    "Todas mis recetas favoritas", new ArrayList<>()));
        } else if (this.tipo == TipoUsuario.CHEF) {
            usuario = new Chef();
            ((Chef) usuario).setCalendario(new Calendario());
        }

        usuario.setIdUsuario(this.generarIdUsuarioUnico());
        usuario.setFechaCreacion(new Date());
        usuario.setNombreUsuario(nombreUsuario);
        usuario.setNombre(nombre);

        return usuario;
    }

    /**
     * Genera un idUsuario único en la base de datos del Sistema
     * @return el idUsuario generado
     * @throws SQLException arroja excepción si no logra conectarse a la base de datos
     */
    private BigInteger generarIdUsuarioUnico() throws SQLException {
        IControladorUtilidad utilidad = new ControladorUtilidad();
        IControladorCBDRegAut controlBD = new ControladorCBDRegAut();
        BigInteger idUsuario;
        boolean existe = true;

        do {
            idUsuario = utilidad.generarRandomBigInt(new BigInteger("1000000000000"), new BigInteger("10000000000000"));
            existe = controlBD.existeUsuario(idUsuario);
        } while (existe);

        return idUsuario;
    }
}

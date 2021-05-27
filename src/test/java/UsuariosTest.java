import entidades.dto.DTOAutenticacion;
import entidades.dto.DTOExito;
import entidades.modelo.TipoUsuario;
import logica_negocio.perfiles.ControladorPerfiles;
import logica_negocio.registro_autenticacion.ControladorRegAut;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

public class UsuariosTest {

    static ControladorPerfiles perfiles;
    static ControladorRegAut registro;

    @BeforeAll
    static void setUp(){
        perfiles = new ControladorPerfiles();
        registro = new ControladorRegAut();
    }

    @Test
    public void eliminarPerfilCookerTest(){
        registro.registrarUsuario(TipoUsuario.COOKER, "dmarroyo@gmail.com", "RosaNives", "DmArroyo", "Damaris");
        DTOAutenticacion usuario = registro.autenticarUsuario(TipoUsuario.COOKER, "dmarroyo@gmail.com", "RosaNives");

        DTOExito exito = perfiles.eliminarPerfil(usuario.getUsuario().getIdUsuario());

        assertTrue(exito.isEstado());
    }

    @Test
    public void eliminarPerfilChefTest(){
        registro.registrarUsuario(TipoUsuario.CHEF, "luisg@gmail.com", "Alpina", "luisg", "Luis Bermudez");
        DTOAutenticacion usuario = registro.autenticarUsuario(TipoUsuario.CHEF, "luisg@gmail.com", "Alpina");

        DTOExito exito = perfiles.eliminarPerfil(usuario.getUsuario().getIdUsuario());

        assertTrue(exito.isEstado());
    }

    @Test
    public void eliminarPerfilAdmnTest(){
        registro.registrarUsuario(TipoUsuario.ADMIN, "ame@gmail.com", "Adriana", "Ame", "Amelia");
        DTOAutenticacion usuario = registro.autenticarUsuario(TipoUsuario.CHEF, "ame@gmail.com", "Adriana");

        DTOExito exito = perfiles.eliminarPerfil(usuario.getUsuario().getIdUsuario());

        assertTrue(exito.isEstado());
    }

    @Test
    public void eliminarPerfilInexistTest(){
        DTOExito exito = perfiles.eliminarPerfil(new BigInteger("-1"));
        assertTrue(exito.isEstado());
    }



}

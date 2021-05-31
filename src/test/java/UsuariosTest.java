import entidades.dto.DTOAutenticacion;
import entidades.dto.DTOExito;
import entidades.dto.DTOPerfil;
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
    public void autenticarCookerTest(){
        DTOAutenticacion autenticacion = registro.autenticarUsuario(TipoUsuario.COOKER,"test.cooker","123");
        assertTrue(autenticacion.isEstado());
    }
    @Test
    public void autenticarChefTest(){
        DTOAutenticacion autenticacion = registro.autenticarUsuario(TipoUsuario.CHEF,"test.chef","123");
        assertTrue(autenticacion.isEstado());
    }
    @Test
    public void autenticarAdminTest(){
        DTOAutenticacion autenticacion = registro.autenticarUsuario(TipoUsuario.ADMIN,"test.admin","123");
        assertTrue(autenticacion.isEstado());
    }
    @Test
    public void autenticarCookerFalsoTest(){
        DTOAutenticacion autenticacion = registro.autenticarUsuario(TipoUsuario.COOKER,"test.cooker","hola123");
        assertFalse(autenticacion.isEstado());
    }
    @Test
    public void autenticarChefFalsoTest(){
        DTOAutenticacion autenticacion = registro.autenticarUsuario(TipoUsuario.CHEF,"test.chef","hola123");
        assertFalse(autenticacion.isEstado());
    }
    @Test
    public void autenticarAdminFalsoTest(){
        DTOAutenticacion autenticacion = registro.autenticarUsuario(TipoUsuario.ADMIN,"test.admin","hola123");
        assertFalse(autenticacion.isEstado());
    }
    @Test
    public void crearPerfilAdminTest(){
        registro.registrarUsuario(TipoUsuario.ADMIN,"arianaG1@gmail.com","unicornios11","AriGrande","Ariana Grande");
        DTOAutenticacion usuario = registro.autenticarUsuario(TipoUsuario.CHEF,"arianaG1@gmail.com","unicornios11");

        DTOExito exito = perfiles.verificarPerfil(usuario.getUsuario().getIdUsuario());

        assertTrue(exito.isEstado());
    }
    @Test
    public void crearPerfilChefTest(){
        registro.registrarUsuario(TipoUsuario.CHEF,"sartoben@gmail.com","GardfieldLasana","Sartoben","Santiago Vasquez");
        DTOAutenticacion usuario = registro.autenticarUsuario(TipoUsuario.CHEF,"sartoben@gmail.com","GardfieldLasana");

        DTOExito exito = perfiles.verificarPerfil(usuario.getUsuario().getIdUsuario());

        assertTrue(exito.isEstado());
    }
    @Test
    public void crearPerfilCookerTest(){
        registro.registrarUsuario(TipoUsuario.COOKER,"mateoSarmiento@gmail.com","slimeRancher2","DevoraPlatillos","Mateo");
        DTOAutenticacion usuario = registro.autenticarUsuario(TipoUsuario.CHEF,"mateoSarmiento@gmail.com","slimeRancher2");

        DTOExito exito = perfiles.verificarPerfil(usuario.getUsuario().getIdUsuario());

        assertTrue(exito.isEstado());
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
        DTOAutenticacion usuario = registro.autenticarUsuario(TipoUsuario.ADMIN, "ame@gmail.com", "Adriana");

        DTOExito exito = perfiles.eliminarPerfil(usuario.getUsuario().getIdUsuario());

        assertTrue(exito.isEstado());
    }

    @Test
    public void eliminarPerfilInexistTest(){
        DTOExito exito = perfiles.eliminarPerfil(new BigInteger("-1"));
        assertTrue(exito.isEstado());
    }

    @Test
    public void modificarPerfilCookerTest()
    {
        registro.registrarUsuario(TipoUsuario.COOKER, "naizaquej3@gmail.com", "RosaNives", "Jess_4", "Jessica");
        DTOAutenticacion usuario = registro.autenticarUsuario(TipoUsuario.COOKER, "naizaquej3@gmail.com", "RosaNives");

        DTOPerfil perfilModificado = perfiles.modificarPerfil(usuario.getUsuario(), "nombre", "Sara");
        assertTrue(perfilModificado.isEstado());
    }

    @Test
    public void modificarPerfilChefTest()
    {
        registro.registrarUsuario(TipoUsuario.CHEF, "collante@gmail.com", "Lupita", "Cam84", "Camila Collante");
        DTOAutenticacion usuario = registro.autenticarUsuario(TipoUsuario.CHEF, "collante@gmail.com", "Lupita");

        DTOPerfil perfilModificado = perfiles.modificarPerfil(usuario.getUsuario(), "nombreUsuario", "Camila42");
        assertTrue(perfilModificado.isEstado());
    }

}

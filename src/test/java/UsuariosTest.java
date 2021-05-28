import entidades.dto.DTOAutenticacion;
import entidades.dto.DTOExito;
import entidades.modelo.TipoUsuario;
import logica_negocio.perfiles.ControladorPerfiles;
import logica_negocio.registro_autenticacion.ControladorRegAut;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
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
    public void eliminarPerfilTest(){
        registro.registrarUsuario(TipoUsuario.COOKER, "dmarroyo@gmail.com", "RosaNives", "DmArroyo", "Damaris");
        DTOAutenticacion usuario = registro.autenticarUsuario(TipoUsuario.COOKER, "dmarroyo@gmail.com", "RosaNives");

        DTOExito exito = perfiles.eliminarPerfil(usuario.getUsuario().getIdUsuario());

        assertTrue(exito.isEstado());
    }

}

import entidades.dto.DTOExito;
import entidades.dto.DTOReceta;
import entidades.modelo.Cooker;
import entidades.dto.DTOAutenticacion;
import entidades.modelo.*;
import logica_negocio.recetas.ControladorRecetasChef;
import logica_negocio.recetas.ControladorRecetasCooker;
import logica_negocio.perfiles.ControladorPerfiles;
import logica_negocio.registro_autenticacion.ControladorRegAut;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RecetasTest {
    static ControladorRecetasCooker controlCooker;
    static ControladorRecetasChef controlChef;
    static ControladorPerfiles perfiles;
    static ControladorRegAut registro;

    @BeforeAll
    static void setUp(){
        controlCooker = new ControladorRecetasCooker();
        controlChef = new ControladorRecetasChef();
        perfiles = new ControladorPerfiles();
        registro = new ControladorRegAut();
        registro.registrarUsuario(TipoUsuario.CHEF, "dmarroyo@gmail.com", "RosaNives", "DmArroyo", "Damaris");
        DTOAutenticacion usuario = registro.autenticarUsuario(TipoUsuario.CHEF, "dmarroyo@gmail.com", "RosaNives");
        Chef elChef= new Chef(usuario.getUsuario().getIdUsuario(), usuario.getUsuario().getNombreUsuario(),usuario.getUsuario().getFechaCreacion(),usuario.getUsuario().getNombre());
        controlChef = new ControladorRecetasChef(elChef);

    }

    @Test
    public void añadirRecetaFavoritaTest() {
        controlCooker.setCooker(new Cooker(BigInteger.valueOf(14), "CamCollan", new Date(2021-4-20), "Camila Collante"));
        DTOExito exito = controlCooker.agregarRecetaListaFavoritos(BigInteger.valueOf(1001));
        assertTrue(exito.isEstado());
    }

    @Test
    public void añadirRecetaFavoritaListaTest() {
        controlCooker.setCooker(new Cooker(BigInteger.valueOf(14), "CamCollan", new Date(2021-4-20), "Camila Collante"));
        DTOExito exito = controlCooker.agregarRecetaListaFavoritos(BigInteger.valueOf(1019),1);
        assertTrue(exito.isEstado());
    }

    @Test
    public void añadirRecetaFavoritaListasTest() {
        List<Integer> ids =new ArrayList<>();
        ids.add(1);
        ids.add(3);
        controlCooker.setCooker(new Cooker(BigInteger.valueOf(14), "CamCollan", new Date(2021-4-20), "Camila Collante"));
        DTOExito exito = controlCooker.agregarRecetaListaFavoritos(BigInteger.valueOf(1011),ids);
        assertTrue(exito.isEstado());
    }

    @Test
    public void modificarNombreRecetaTest(){
        DTOReceta receta = controlChef.modificarNombre("Lasagna de pollo", BigInteger.valueOf(1001));
        assertTrue(receta.isEncontrado());
    }

    @Test
    public void modificarDescripcionRecetaTest(){
        DTOReceta receta = controlChef.modificarDecripcion("Deliciosa receta extranjera para tus antojos fit.", BigInteger.valueOf(1003));
        assertTrue(receta.isEncontrado());
    }

    @Test
    public void modificarLinkVideoRecetaTest(){
        DTOReceta receta = controlChef.modificarLinkVideo("https://www.youtube.com/embed/_B9CiqKJYR8", BigInteger.valueOf(1000));
        assertTrue(receta.isEncontrado());
    }

    @Test
    public void modificarLinkImagenRecetaTest() {
        DTOReceta receta = controlChef.modificarLinkImg("https://img-global.cpcdn.com/recipes/95ff8997e90fe28e/751x532cq70/churrasco-tipo-cubano-foto-principal.jpg", BigInteger.valueOf(1004));
        assertTrue(receta.isEncontrado());
    }

    @Test
    public void eliminarRecetaTest(){
        List<LineaIngrediente> ingredients = new ArrayList<>();
        List<Categoria> categorias = new ArrayList<>();
        List<PasoReceta> pasosreceta = new ArrayList<>();
        List<Calificacion> calificaciones = new ArrayList<>();
        List<Reporte> reportes = new ArrayList<>();
        BigInteger bigI = new BigInteger("1025");
        Receta laReceta = new Receta(bigI,"Pizza de prueba","La magnifica descripcion de una receta de pizza de prueba",true,"https://www.youtube.com/watch?v=eovGDyel9d8",TipoVideo.YOUTUBE,true,"https://images-gmi-pmc.edge-generalmills.com/e1c318a2-f4c3-4854-8b54-a087015c47a9.jpg",ingredients,pasosreceta,categorias,calificaciones, reportes);

        //crear receta
        controlChef.subirReceta(laReceta.getNombre(),laReceta.getDescripcion(),laReceta.getLinkVideo(),laReceta.getTipoVideo(),laReceta.getLinkImagen(),laReceta.getLineasIngrediente(),laReceta.getCategorias(),laReceta.getPasosReceta());

        DTOReceta recetaa = controlChef.eliminarReceta(laReceta.getIdReceta());

        assertNotNull(recetaa);
    }

}
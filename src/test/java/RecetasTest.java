import entidades.dto.DTOExito;
import entidades.dto.DTOReceta;
import entidades.modelo.Cooker;
import entidades.dto.DTOAutenticacion;
import entidades.modelo.*;
import logica_negocio.recetas.ControladorRecetasChef;
import logica_negocio.recetas.ControladorRecetasCooker;
import logica_negocio.perfiles.ControladorPerfiles;
import logica_negocio.recetas.IControladorRecetasChef;
import logica_negocio.registro_autenticacion.ControladorRegAut;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Duration;
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
    }

    @Test
    public void subirRecetaTest(){
        List<LineaIngrediente> ingredientes = new ArrayList<>();
        List<Categoria> categorias = new ArrayList<>();
        List<PasoReceta> pasosReceta = new ArrayList<>();
        DTOAutenticacion usuario = registro.autenticarUsuario(TipoUsuario.CHEF, "testchef", "123");
        IControladorRecetasChef controlAux = new ControladorRecetasChef((Chef) usuario.getUsuario());

        DTOExito exito = controlAux.subirReceta("Hamburguesa", "Deliciosa hamburguesa extravagante.", "https://www.youtube.com/watch?v=e9X3r5bxWzQ", TipoVideo.YOUTUBE, "https://i.blogs.es/6b1775/hamburguesa_rec/450_1000.jpg", ingredientes, categorias, pasosReceta );
        assertTrue(exito.isEstado());
    }

    @Test
    public void subirRecetaFailTest(){
        List<LineaIngrediente> ingredientes = new ArrayList<>();
        List<Categoria> categorias = new ArrayList<>();
        List<PasoReceta> pasosReceta = new ArrayList<>();
        DTOAutenticacion usuario = registro.autenticarUsuario(TipoUsuario.CHEF, "testchef", "123");
        IControladorRecetasChef controlAux = new ControladorRecetasChef((Chef) usuario.getUsuario());

        DTOExito exito = controlAux.subirReceta("Pizza", "Deliciosa pizza extravagante.", "watch?v=e9X3r5bxWzQ", TipoVideo.YOUTUBE, "https://i.blogs.es/6b1775/hamburguesa_rec/450_1000", ingredientes, categorias, pasosReceta );
        assertFalse(exito.isEstado());
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

        DTOAutenticacion usuario = registro.autenticarUsuario(TipoUsuario.CHEF, "testchef", "123");
        IControladorRecetasChef controlAux = new ControladorRecetasChef((Chef) usuario.getUsuario());

        List<LineaIngrediente> ingredients = new ArrayList<>();
        List<Categoria> categorias = new ArrayList<>();
        List<PasoReceta> pasosreceta = new ArrayList<>();
        List<Calificacion> calificaciones = new ArrayList<>();
        List<Reporte> reportes = new ArrayList<>();
        BigInteger bigI = new BigInteger("1025");
        Receta laReceta = new Receta(bigI,"Pizza de prueba","La magnifica descripcion de una receta de pizza de prueba",true,"https://www.youtube.com/watch?v=eovGDyel9d8",TipoVideo.YOUTUBE,true,"https://images-gmi-pmc.edge-generalmills.com/e1c318a2-f4c3-4854-8b54-a087015c47a9.jpg",ingredients,pasosreceta,categorias,calificaciones, reportes);

        //crear receta
        controlAux.subirReceta(laReceta.getNombre(),laReceta.getDescripcion(),laReceta.getLinkVideo(),laReceta.getTipoVideo(),laReceta.getLinkImagen(),laReceta.getLineasIngrediente(),laReceta.getCategorias(),laReceta.getPasosReceta());

        DTOReceta recetaa = controlAux.eliminarReceta(laReceta.getIdReceta());

        assertNotNull(recetaa);
    }
    @Test
    public void timeoutBuscarRecetaTest() {
        assertTimeout(Duration.ofSeconds(1), () -> controlCooker.buscarReceta("Pollo"));
    }

    @Test
    public void timeoutBuscarRecetaPorChefTest() {
        assertTimeout(Duration.ofSeconds(1), () -> controlCooker.buscarReceta("Alejandro"));
    }
    @Test
    public void timeoutBuscarRecetaPorCategoria() {
        assertTimeout(Duration.ofSeconds(1), () -> controlCooker.buscarReceta("Pescado"));
    }
    @Test
    public void calificarRecetaTest() {

        DTOAutenticacion usuario = registro.autenticarUsuario(TipoUsuario.CHEF, "testchef", "123");
        IControladorRecetasChef controlAux = new ControladorRecetasChef((Chef) usuario.getUsuario());
        controlCooker.setCooker(new Cooker(BigInteger.valueOf(14), "CamCollan", new Date(2021-4-20), "Camila Collante"));

        // Crear Receta
        List<LineaIngrediente> ingredientes = new ArrayList<>();
        List<Categoria> categorias = new ArrayList<>();
        List<PasoReceta> pasos = new ArrayList<>();
        List<Calificacion> calificaciones = new ArrayList<>();
        List<Reporte> reportes = new ArrayList<>();
        BigInteger bigI = new BigInteger("1022");
        Receta receta = new Receta(bigI,"Pasta Inglesa","La pasta inglesa más deliciosa",true,"https://www.youtube.com/watch?v=eovGDyel9d8",TipoVideo.YOUTUBE,true,"https://images-gmi-pmc.edge-generalmills.com/e1c318a2-f4c3-4854-8b54-a087015c47a9.jpg",ingredientes,pasos,categorias,calificaciones, reportes);
        controlAux.subirReceta(receta.getNombre(),receta.getDescripcion(),receta.getLinkVideo(),receta.getTipoVideo(),receta.getLinkImagen(),receta.getLineasIngrediente(),receta.getCategorias(),receta.getPasosReceta());

        DTOExito exito = controlCooker.calificarReceta(receta, 5);
        assertTrue(exito.isEstado());
    }

    @Test
    public void timeoutSubirRecetaTest(){
        List<LineaIngrediente> ingredientes = new ArrayList<>();
        List<Categoria> categorias = new ArrayList<>();
        List<PasoReceta> pasosReceta = new ArrayList<>();
        DTOAutenticacion usuario = registro.autenticarUsuario(TipoUsuario.CHEF, "testchef", "123");
        IControladorRecetasChef controlAux = new ControladorRecetasChef((Chef) usuario.getUsuario());
        assertTimeout(Duration.ofSeconds(2), () -> controlAux.subirReceta("Hamburguesa", "Deliciosa hamburguesa extravagante.", "https://www.youtube.com/watch?v=e9X3r5bxWzQ", TipoVideo.YOUTUBE, "https://i.blogs.es/6b1775/hamburguesa_rec/450_1000.jpg", ingredientes, categorias, pasosReceta ));
    }

    @Test
    public void reportarRecetaTest() {

        DTOAutenticacion usuario = registro.autenticarUsuario(TipoUsuario.CHEF, "testchef", "123");
        IControladorRecetasChef controlAux = new ControladorRecetasChef((Chef) usuario.getUsuario());
        controlCooker.setCooker(new Cooker(BigInteger.valueOf(14), "CamCollan", new Date(2021-4-20), "Camila Collante"));
        Reporte reporte = new Reporte(controlCooker.getCooker(),new MotivoReporte(67,"Malo", "La receta es mala"),new Date("30/05/2021"), false);

        // Crear Receta
        List<LineaIngrediente> ingredientes = new ArrayList<>();
        List<Categoria> categorias = new ArrayList<>();
        List<PasoReceta> pasos = new ArrayList<>();
        List<Calificacion> calificaciones = new ArrayList<>();
        List<Reporte> reportes = new ArrayList<>();
        BigInteger bigI = new BigInteger("1022");
        Receta receta = new Receta(bigI,"Pasta Bolognesa","La pasta bolognesa más deliciosa",true,"https://www.youtube.com/watch?v=eovGDyel9d8",TipoVideo.YOUTUBE,true,"https://images-gmi-pmc.edge-generalmills.com/e1c318a2-f4c3-4854-8b54-a087015c47a9.jpg",ingredientes,pasos,categorias,calificaciones, reportes);
        controlAux.subirReceta(receta.getNombre(),receta.getDescripcion(),receta.getLinkVideo(),receta.getTipoVideo(),receta.getLinkImagen(),receta.getLineasIngrediente(),receta.getCategorias(),receta.getPasosReceta());

        DTOExito exito = controlCooker.reportarReceta(receta,reporte);
        assertTrue(exito.isEstado());
    }
    @Test
    public void calificarChefTest() {

        DTOAutenticacion usuario = registro.autenticarUsuario(TipoUsuario.CHEF, "testchef", "123");
        IControladorRecetasChef controlAux = new ControladorRecetasChef((Chef) usuario.getUsuario());
        controlCooker.setCooker(new Cooker(BigInteger.valueOf(14), "CamCollan", new Date(2021-4-20), "Camila Collante"));

        // Crear Receta

        DTOExito exito = controlCooker.calificarChef((Chef)usuario.getUsuario(), 5);
        assertTrue(exito.isEstado());
    }

}
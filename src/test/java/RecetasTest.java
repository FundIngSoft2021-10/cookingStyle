import entidades.dto.DTOExito;
import entidades.modelo.Cooker;
import logica_negocio.recetas.ControladorRecetasChef;
import logica_negocio.recetas.ControladorRecetasCooker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RecetasTest {
    static ControladorRecetasCooker controlCooker;
    static ControladorRecetasChef controlChef;

    @BeforeAll
    static void setUp(){
        controlCooker = new ControladorRecetasCooker();
        controlChef = new ControladorRecetasChef();
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
}

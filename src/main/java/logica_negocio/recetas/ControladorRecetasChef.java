package logica_negocio.recetas;

import acceso_datos.consultas_bd.ControladorCBDRecetas;
import acceso_datos.consultas_bd.IControladorCBDRecetas;
import acceso_datos.persistencia_bd.ControladorPBDRecetasChef;
import acceso_datos.persistencia_bd.IControladorPBDRecetasChef;
import entidades.dto.DTOReceta;
import entidades.modelo.*;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;

public class ControladorRecetasChef implements IControladorRecetasChef {

    private IControladorPBDRecetasChef controladorPBD;
    private IControladorCBDRecetas controladorCBD;
    public ControladorRecetasChef() {
        controladorPBD = new ControladorPBDRecetasChef();
        controladorCBD = new ControladorCBDRecetas();
    }

    //DTO para mostrar mensaje.
    public void subirReceta (String nombre, String descripcion, String link_video, int tipovideo, String link_imagen, List<LineaIngrediente> ingredientes, List<Categoria> categorias, List<PasoReceta> pasosReceta) throws SQLException {

    }


    //Validar que la URL de un link exista
    public boolean validarUrl (String url){
        if (url.contains("https://www.")){
            try{
                (new URL(url)).openStream().close();
                return true;
            } catch (IOException e){
            }
        } else {
            url = "https://www." + url;
            try{
                (new URL(url)).openStream().close();
                return true;
            } catch (IOException e){
            }
        }
        return false;
    }


    //Validar que el link del video corresponda al tipo de video escogido.

    @Override
    public DTOReceta modificarNombre(String nuevoNombre, BigInteger idReceta){
        DTOReceta receta = new DTOReceta();
        try {
            boolean cambiado = this.controladorPBD.modificarNombreReceta(nuevoNombre, idReceta);
            Receta recetaRec = this.controladorCBD.buscarRecetas(idReceta);
            Chef chef = this.controladorCBD.consultaRecetaXChef(idReceta);
            receta.setReceta(recetaRec);
            receta.setAutor(chef);
            receta.setEncotrado(true);
        } catch (SQLException sqlException) {
            receta.setEncotrado(false);
        }

        return receta;
    }

    @Override
    public DTOReceta modificarDecripcion(String nuevaDesc, BigInteger idReceta){
        DTOReceta receta = new DTOReceta();
        try {
            receta.setEncotrado(this.controladorPBD.modificarDescrReceta(nuevaDesc, idReceta));
            Receta recetaRec = this.controladorCBD.buscarRecetas(idReceta);
            Chef chef = this.controladorCBD.consultaRecetaXChef(idReceta);
            receta.setReceta(recetaRec);
            receta.setAutor(chef);
        } catch (SQLException sqlException) {
            receta.setEncotrado(false);
        }

        return receta;
    }
}

package logica_negocio.recetas;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import acceso_datos.consultas_bd.ControladorBDRecetasCooker;
import acceso_datos.consultas_bd.IControladorBDRecetasCooker;
import entidades.dto.DTOListaFavoritos;
import entidades.dto.DTOReceta;
import entidades.dto.DTORecetaMiniatura;
import entidades.modelo.ListaFavoritos;
import entidades.modelo.Receta;
import entidades.modelo.Cooker;

public class ControladorRecetasCooker implements IControladorRecetasCooker{

    private Cooker cooker;
    IControladorBDRecetasCooker controladorBDRecetasCooker = new ControladorBDRecetasCooker();

    public ControladorRecetasCooker() {

    }

    public ControladorRecetasCooker(Cooker cooker) {
        this.cooker = cooker;
    }

    public Cooker getCooker() {
        return cooker;
    }

    public void setCooker(Cooker cooker) {
        this.cooker = cooker;
    }

    //Métodos Funcionalidad Crear Lista Favoritos


    /**
     * Creación de una lista de recetas favorita específica con una receta inicial
     * @param nombreLista nombre de la lista de favoritos
     * @param descripcion decripcion de la lista
     * @param id_receta ide de la receta a añiadir a la lista
     * @return true si se creo con éxito, false si no se creó
     */
    @Override
    public boolean crearListaFavoritos(String nombreLista, String descripcion, int id_receta) throws SQLException {

        try {

            //Se obtiene el id de la nueva lista
            int id = cooker.getListasFavoritos().size() + 1;
            //Obtengo de la bace de datos la receta correspondiente al id_receta
            Receta receta = controladorBDRecetasCooker.buscarRecetas(id_receta);
            //Se añade la receta a una lista de recetas
            List<Receta> listaReceta = new ArrayList<>();
            listaReceta.add(receta);

            //Se crea la lista de recetas
            ListaFavoritos listaFavoritos = new ListaFavoritos(id, nombreLista, descripcion, listaReceta);
            //Se añade la lista a la lista de favoritos del cooker
            cooker.getListasFavoritos().add(listaFavoritos);

            //Se crea un DTO de lista favoritos para enviar a la base de datos
            DTOListaFavoritos listaEnviar = new DTOListaFavoritos(cooker, listaFavoritos);

            //Se recibe un booleano que indica si se agregó con exito la nueva lista a la base de datos
            boolean exito = controladorBDRecetasCooker.crearListaFavoritosConReceta(listaEnviar);

            //La nueva receta favorita, se añade a la lista de favoritos general
            for(ListaFavoritos listaFavoritos1 : cooker.getListasFavoritos()){
                if(listaFavoritos1.getIdListaFavoritos() == 1){
                    listaFavoritos1.getRecetasFavoritas().add(receta);
                    controladorBDRecetasCooker.insertarRecetaListaFavoritos(id_receta, 1, cooker.getIdUsuario());
                }
            }
            return exito;

        } catch (SQLException sqle){
            throw sqle;
        }

    }

    /**
     * Creación de una lista de recetas favorita específica sin una receta inicial
     * @param nombreLista nombre de la lista de favoritos
     * @param descripcion decripcion de la lista
     * @return true si se creo con éxito, false si no se creó
     */
    @Override
    public boolean crearListaFavoritos(String nombreLista, String descripcion) throws SQLException {
        try {

            //Se obtiene el id de la nueva lista
            int id = cooker.getListasFavoritos().size() + 1;

            System.out.println(id);
            //Se crea una lista de recetas
            List<Receta> listaRecetas = new ArrayList<>();

            //Se crea la lista de recetas
            ListaFavoritos listaFavoritos = new ListaFavoritos(id, nombreLista, descripcion, listaRecetas);
            //Se añade la lista a la lista de favoritos del cooker
            cooker.getListasFavoritos().add(listaFavoritos);

            //Se crea un DTO de lista favoritos para enviar a la base de datos
            DTOListaFavoritos listaEnviar = new DTOListaFavoritos(cooker, listaFavoritos);

            System.out.println(listaEnviar.getCooker().getIdUsuario());

            boolean exito = controladorBDRecetasCooker.crearListaFavoritos(listaEnviar);

            return  exito;

        } catch (SQLException sqle){
            throw sqle;
        }
    }

    /**
     * Agrega una receta a una lista de favoritos determinada
     * @param receta receta a agregar
     * @param idLista id de la lista a la que se le va a agregar la receta
     * @return true si se agregó con éxito, false si no
     */
    @Override
    public boolean agregarRecetaListaFavoritos(DTOReceta receta, int idLista) throws SQLException {

        try {
            boolean exito = controladorBDRecetasCooker.insertarRecetaListaFavoritos(receta.getReceta().getIdReceta(), idLista, cooker.getIdUsuario());
            return exito;
        } catch (SQLException sqle){
            throw sqle;
        }
    }
}

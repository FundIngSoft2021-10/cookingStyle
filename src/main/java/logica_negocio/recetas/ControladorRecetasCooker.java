package logica_negocio.recetas;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import acceso_datos.consultas_bd.ControladorCBDRecetasCooker;
import acceso_datos.consultas_bd.IControladorCBDRecetasCooker;
import acceso_datos.persistencia_bd.ControladorPBDRecetasCooker;
import acceso_datos.persistencia_bd.IControladorPBDRecetasCooker;
import entidades.dto.DTOListaFavoritos;
import entidades.dto.DTOReceta;
import entidades.modelo.ListaFavoritos;
import entidades.modelo.Receta;
import entidades.modelo.Cooker;

public class ControladorRecetasCooker implements IControladorRecetasCooker{

    private Cooker cooker;
    IControladorCBDRecetasCooker controladorCBDRecetasCooker = new ControladorCBDRecetasCooker();
    IControladorPBDRecetasCooker controladorPBDRecetasCooker = new ControladorPBDRecetasCooker();
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

    @Override
    public boolean existeRecetaListaFavoritos(int idLista, BigInteger idReceta) throws SQLException{

        return controladorCBDRecetasCooker.recetaEnListaRecetas(idLista, idReceta, cooker.getIdUsuario());

    }

    /**
     * Creación de una lista de recetas favorita específica con una receta inicial
     * @param nombreLista nombre de la lista de favoritos
     * @param descripcion decripcion de la lista
     * @param id_receta ide de la receta a añiadir a la lista
     * @return la lista creada
     * @throws SQLException
     */
    @Override
    public DTOListaFavoritos crearListaFavoritos(String nombreLista, String descripcion, BigInteger id_receta) throws SQLException{

        DTOListaFavoritos listaEnviar;
        try {

            //Se obtiene el tamaño de la lista de listaFavoritos
            int tamArreglo = cooker.getListasFavoritos().size();

            //Se crea el id de la nueva lista
            int id = cooker.getListasFavoritos().get(tamArreglo-1).getIdListaFavoritos() + 1;

            //Obtengo de la bace de datos la receta correspondiente al id_receta
            Receta receta = controladorCBDRecetasCooker.buscarRecetas(id_receta);

            //Se añade la receta a una lista de recetas
            List<Receta> listaReceta = new ArrayList<>();
            listaReceta.add(receta);

            //Se crea la lista de recetas
            ListaFavoritos listaFavoritos = new ListaFavoritos(id, nombreLista, descripcion, listaReceta);


            //Se crea un DTO de lista favoritos para enviar a la base de datos
            listaEnviar = new DTOListaFavoritos(cooker, listaFavoritos);

            //Se recibe un booleano que indica si se agregó con exito la nueva lista a la base de datos
            boolean exito = controladorPBDRecetasCooker.crearListaFavoritosConReceta(listaEnviar);

            //Mensaje de creación
            if(exito){
                listaEnviar.setMensaje("¡Lista creada con éxito!");
            } else {
                listaEnviar.setMensaje("No se pudo crear la lista. ");
            }

            //Si la receta no se ha añadido a la lista de favoritos general, se añade
            if(!controladorCBDRecetasCooker.recetaEnListaRecetas(1, id_receta, cooker.getIdUsuario())) {
                controladorPBDRecetasCooker.insertarRecetaListaFavoritos(id_receta, 1, cooker.getIdUsuario());
            }


        } catch (SQLException sqle){
            throw sqle;
        }
        return listaEnviar;
    }

    /**
     * Creación de una lista de recetas favorita específica sin una receta inicial
     * @param nombreLista nombre de la lista de favoritos
     * @param descripcion decripcion de la lista
     * @return la lista creada
     * @throws SQLException
     */
    @Override
    public DTOListaFavoritos crearListaFavoritos(String nombreLista, String descripcion) throws SQLException {


        try {

            //Se obtiene el tamaño de la lista de listaFavoritos
            int tamArreglo = cooker.getListasFavoritos().size();

            //Se crea el id de la nueva lista
            int id = cooker.getListasFavoritos().get(tamArreglo-1).getIdListaFavoritos() + 1;

            //Se añade la receta a una lista de recetas
            List<Receta> listaReceta = new ArrayList<>();

            //Se crea la lista de recetas
            ListaFavoritos listaFavoritos = new ListaFavoritos(id, nombreLista, descripcion, listaReceta);


            //Se crea un DTO de lista favoritos para enviar a la base de datos
            DTOListaFavoritos listaEnviar = new DTOListaFavoritos(cooker, listaFavoritos);

            //Se recibe un booleano que indica si se agregó con exito la nueva lista a la base de datos
            boolean exito = controladorPBDRecetasCooker.crearListaFavoritosConReceta(listaEnviar);

            //Mensaje de creación
            if(exito){
                listaEnviar.setMensaje("¡Lista creada con éxito!");
            } else {
                listaEnviar.setMensaje("No se pudo crear la lista. ");
            }

            return listaEnviar;

        } catch (SQLException sqle){
            throw sqle;
        }
    }

    /**
     * Agrega una receta a una lista de favoritos determinada
     * @param idreceta id de la receta a agregar
     * @param idLista id de la lista a la que se le va a agregar la receta
     * @return true si se agregó con éxito, false si no
     * @throws SQLException
     */
    @Override
    public boolean agregarRecetaListaFavoritos(BigInteger idreceta, int idLista) throws SQLException {

        boolean agregado = false;
        try {
            //Agrega la receta a la lista
            if(!controladorCBDRecetasCooker.recetaEnListaRecetas(idLista, idreceta, cooker.getIdUsuario())){
                agregado = controladorPBDRecetasCooker.insertarRecetaListaFavoritos(idreceta, idLista, cooker.getIdUsuario());
            }

            //Agrega la receta a la lista general
            if(!controladorCBDRecetasCooker.recetaEnListaRecetas(1, idreceta, cooker.getIdUsuario())){
                controladorPBDRecetasCooker.insertarRecetaListaFavoritos(idreceta, 1, cooker.getIdUsuario());
            }
            return  agregado;
        } catch (SQLException sqle){
            throw sqle;
        }
    }

    /**
     * Dodo una lista de favoritos, agrega la receta a todas las listas
     * @param idreceta id de la receta a agregar
     * @param idsLista lista con los id's de las listas
     * @return una lista de DTOListaFavoritos con las listas actualizadas
     * @throws SQLException
     */
    @Override
    public List<DTOListaFavoritos> agregarRecetaListaFavoritos(BigInteger idreceta, List<Integer> idsLista) throws SQLException {
        List<DTOListaFavoritos> agregados = new ArrayList<>();
        boolean agregado;
        try {

            //Agrego en todas las listas la receta
            for (int idLista : idsLista) {
                agregado = false;
                DTOListaFavoritos listaAgregar = new DTOListaFavoritos(cooker, controladorCBDRecetasCooker.consultaListaFavoritos(idLista));

                if(!controladorCBDRecetasCooker.recetaEnListaRecetas(idLista, idreceta, cooker.getIdUsuario())){
                    agregado = controladorPBDRecetasCooker.insertarRecetaListaFavoritos(idreceta, idLista, cooker.getIdUsuario());
                }

                listaAgregar.setAgregado(agregado);
                agregados.add(listaAgregar);
            }

            //Agrego la receta a la lista general
            if(!controladorCBDRecetasCooker.recetaEnListaRecetas(1, idreceta, cooker.getIdUsuario())){
                controladorPBDRecetasCooker.insertarRecetaListaFavoritos(idreceta, 1, cooker.getIdUsuario());
            }

            return agregados;
        } catch (SQLException sqle) {
            throw sqle;
        }

    }

    // Buscar Recetas Nombre
    @Override
    public List<DTOReceta> buscarRecetasNombre (String nombre) throws SQLException {
        List<DTOReceta> recetasEncontradas;
        try{
            recetasEncontradas = this.controladorCBDRecetasCooker.buscarRecetas(nombre);
            return recetasEncontradas;
        } catch (SQLException sqle){
            throw sqle;
        }
    }

    // Buscar Recetas Categoria
    @Override
    public List<DTOReceta> buscarRecetasCategoria (String categoria) throws SQLException {
        List<DTOReceta> recetasEncontradas;
        int idcategoria;
        idcategoria = this.controladorCBDRecetasCooker.consultaIdCategoria(categoria);
        try {
            recetasEncontradas = this.controladorCBDRecetasCooker.buscarRecetasCategoria(idcategoria);
            return recetasEncontradas;
        } catch (SQLException sqle){
            throw sqle;
        }
    }

    // Buscar Recetas Ingrediente
    @Override
    public List<DTOReceta> buscarRecetasIngrediente (String nom_ingrediente) throws SQLException{
        List<DTOReceta> recetasEncontradas = new ArrayList<>();
        List<DTOReceta> recetasEncontradasIngrediente;
        List<Integer> idsingredientes;
        idsingredientes = this.controladorCBDRecetasCooker.consultaIdsIngrediente(nom_ingrediente);
        try {
            for (Integer actual : idsingredientes){
                recetasEncontradasIngrediente = this.controladorCBDRecetasCooker.buscarRecetasIngrediente(actual);
                recetasEncontradas.addAll(recetasEncontradasIngrediente);
            }
            return recetasEncontradas;
        } catch (SQLException sqle){
            throw sqle;
        }
    }

    //Buscar Recetas Chef
    @Override
    public List<DTOReceta> buscarRecetasChef (String nom_chef) throws SQLException{
        List<DTOReceta> recetasEncontradas = new ArrayList<>();
        List<DTOReceta> recetasEncontradasChef;
        List<BigInteger> idsChefs;
        idsChefs = this.controladorCBDRecetasCooker.consultaIdsChef(nom_chef);
        try {
            for (BigInteger actual : idsChefs){
                recetasEncontradasChef = this.controladorCBDRecetasCooker.buscarRecetasChef(actual);
                recetasEncontradas.addAll(recetasEncontradasChef);
            }
            return recetasEncontradas;
        } catch (SQLException sqle){
            throw sqle;
        }
    }
}

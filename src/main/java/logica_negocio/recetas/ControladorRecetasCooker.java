package logica_negocio.recetas;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import acceso_datos.consultas_bd.ControladorCBDRecetasCooker;
import acceso_datos.consultas_bd.IControladorCBDRecetasCooker;
import acceso_datos.persistencia_bd.ControladorPBDRecetasCooker;
import acceso_datos.persistencia_bd.IControladorPBDRecetasCooker;
import entidades.dto.*;
import entidades.modelo.*;

public class ControladorRecetasCooker implements IControladorRecetasCooker{

    private Cooker cooker;
    private IControladorCBDRecetasCooker controladorCBDRecetasCooker ;
    private IControladorPBDRecetasCooker controladorPBDRecetasCooker;

    public ControladorRecetasCooker(Cooker cooker) {
        this.controladorCBDRecetasCooker = new ControladorCBDRecetasCooker();
        this.controladorPBDRecetasCooker = new ControladorPBDRecetasCooker();
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
     * @inheritDoc
     */
    @Override
    public DTOListaFavoritos crearListaFavoritos(String nombreLista, String descripcion, BigInteger id_receta){

        DTOListaFavoritos listaEnviar = null;
        try {

            //Se obtiene el tamaño de la lista de listaFavoritos
            int tamArreglo = this.cooker.getListasFavoritos().size();

            //Se crea el id de la nueva lista
            int id = this.cooker.getListasFavoritos().get(tamArreglo-1).getIdListaFavoritos() + 1;

            //Obtengo de la bace de datos la receta correspondiente al id_receta
            Receta receta = this.controladorCBDRecetasCooker.buscarRecetas(id_receta);

            //Se añade la receta a una lista de recetas
            List<Receta> listaReceta = new ArrayList<>();
            listaReceta.add(receta);

            //Se crea la lista de recetas
            ListaFavoritos listaFavoritos = new ListaFavoritos(id, nombreLista, descripcion, listaReceta);


            //Se crea un DTO de lista favoritos para enviar a la base de datos
            listaEnviar = new DTOListaFavoritos(this.cooker, listaFavoritos);

            //Se recibe un booleano que indica si se agregó con exito la nueva lista a la base de datos
            boolean exito = this.controladorPBDRecetasCooker.crearListaFavoritosConReceta(listaEnviar);

            //Mensaje de creación
            if(exito){
                listaEnviar.setMensaje("¡Lista creada con éxito!");
            }

            //Si la receta no se ha añadido a la lista de favoritos general, se añade
            if(!this.controladorCBDRecetasCooker.recetaEnListaRecetas(1, id_receta, this.cooker.getIdUsuario())) {
                this.controladorPBDRecetasCooker.insertarRecetaListaFavoritos(id_receta, 1, this.cooker.getIdUsuario());
            }


        } catch (SQLException sqle){
            listaEnviar.setMensaje("Error en la base de  datos " + sqle.getMessage());
        }

        return listaEnviar;
    }

    /**
     * @inheritDoc
     */
    @Override
    public DTOListaFavoritos crearListaFavoritos(String nombreLista, String descripcion) {

        DTOListaFavoritos listaEnviar = null;
        try {

            //Se obtiene el tamaño de la lista de listaFavoritos
            int tamArreglo = this.cooker.getListasFavoritos().size();

            //Se crea el id de la nueva lista
            int id = this.cooker.getListasFavoritos().get(tamArreglo-1).getIdListaFavoritos() + 1;

            //Se añade la receta a una lista de recetas
            List<Receta> listaReceta = new ArrayList<>();

            //Se crea la lista de recetas
            ListaFavoritos listaFavoritos = new ListaFavoritos(id, nombreLista, descripcion, listaReceta);


            //Se crea un DTO de lista favoritos para enviar a la base de datos
            listaEnviar = new DTOListaFavoritos(this.cooker, listaFavoritos);

            //Se recibe un booleano que indica si se agregó con exito la nueva lista a la base de datos
            boolean exito = this.controladorPBDRecetasCooker.crearListaFavoritos(listaEnviar);

            //Mensaje de creación
            if(exito){
                listaEnviar.setMensaje("¡Lista creada con éxito!");
            }

        } catch (SQLException sqle){
            listaEnviar.setMensaje("Error en la base de  datos " + sqle.getMessage());
        }
        return listaEnviar;
    }

    /**
     * @inheritDoc
     */
    @Override
    public DTOExito agregarRecetaListaFavoritos(BigInteger idreceta, int idLista){

        DTOExito exito = new DTOExito();
        boolean agregado = false;
        try {
            //Agrega la receta a la lista
            if(!this.controladorCBDRecetasCooker.recetaEnListaRecetas(idLista, idreceta, this.cooker.getIdUsuario())){
                agregado = this.controladorPBDRecetasCooker.insertarRecetaListaFavoritos(idreceta, idLista, this.cooker.getIdUsuario());
            }

            //Agrega la receta a la lista general
            if(!this.controladorCBDRecetasCooker.recetaEnListaRecetas(1, idreceta, this.cooker.getIdUsuario())){
                this.controladorPBDRecetasCooker.insertarRecetaListaFavoritos(idreceta, 1, this.cooker.getIdUsuario());
            }

            exito.setEstado(agregado);

            if(agregado){
                exito.setMensaje("¡La receta ha sido agregada a la lsita con éxito");
            }

        } catch (SQLException sqle){
            exito.setEstado(false);
            exito.setMensaje("Error en la base de datos " + sqle.getMessage());
        }
        return  exito;
    }

    /**
     * @inheritDoc
     */
    @Override
    public DTOExito agregarRecetaListaFavoritos(BigInteger idreceta, List<Integer> idsLista) {
        List<DTOListaFavoritos> agregados = new ArrayList<>();
        DTOExito exito = new DTOExito();
        boolean agregado;
        try {

            //Agrego en todas las listas la receta
            for (int idLista : idsLista) {
                agregado = false;
                DTOListaFavoritos listaAgregar = new DTOListaFavoritos(this.cooker, this.controladorCBDRecetasCooker.consultaListaFavoritos(idLista));

                if(!this.controladorCBDRecetasCooker.recetaEnListaRecetas(idLista, idreceta, this.cooker.getIdUsuario())){
                    agregado = this.controladorPBDRecetasCooker.insertarRecetaListaFavoritos(idreceta, idLista, this.cooker.getIdUsuario());
                }

                listaAgregar.setAgregado(agregado);
                agregados.add(listaAgregar);
            }

            for (DTOListaFavoritos listaFavoritos : agregados){
                if(listaFavoritos.isAgregado()){
                    exito.setEstado(listaFavoritos.isAgregado());
                    exito.setMensaje("¡Se agrego la receta a las listas indicadas!");
                }
            }
            //Agrego la receta a la lista general
            if(!this.controladorCBDRecetasCooker.recetaEnListaRecetas(1, idreceta, this.cooker.getIdUsuario())){
                this.controladorPBDRecetasCooker.insertarRecetaListaFavoritos(idreceta, 1, this.cooker.getIdUsuario());
            }


        } catch (SQLException sqle) {
            exito.setEstado(false);
            exito.setMensaje("Error en la base de datos: " + sqle.getMessage());
        }
        return exito;

    }

    /**
     * @inheritDoc
     */
    @Override
    public List<DTOReceta> buscarRecetasNombre (String nombre){
        List<DTOReceta> recetasEncontradas;
        try{
            recetasEncontradas = this.controladorCBDRecetasCooker.buscarRecetas(nombre);

        } catch (SQLException sqle){
            recetasEncontradas = null;
        }
        return recetasEncontradas;
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<DTOReceta> buscarRecetasCategoria (String categoria) {
        List<DTOReceta> recetasEncontradas;
        int idcategoria;
        try {
            idcategoria = this.controladorCBDRecetasCooker.consultaIdCategoria(categoria);
            recetasEncontradas = this.controladorCBDRecetasCooker.buscarRecetasCategoria(idcategoria);

        } catch (SQLException sqle){
            recetasEncontradas = null;
        }
        return recetasEncontradas;
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<DTOReceta> buscarRecetasIngrediente (String nom_ingrediente) {
        List<DTOReceta> recetasEncontradas = new ArrayList<>();
        List<DTOReceta> recetasEncontradasIngrediente;
        List<Integer> idsingredientes;

        try {
            idsingredientes = this.controladorCBDRecetasCooker.consultaIdsIngrediente(nom_ingrediente);
            for (Integer actual : idsingredientes){
                recetasEncontradasIngrediente = this.controladorCBDRecetasCooker.buscarRecetasIngrediente(actual);
                recetasEncontradas.addAll(recetasEncontradasIngrediente);
            }

        } catch (SQLException sqle){
            recetasEncontradas = null;
        }
        return recetasEncontradas;
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<DTOReceta> buscarRecetasChef (String nom_chef){
        List<DTOReceta> recetasEncontradas = new ArrayList<>();
        List<DTOReceta> recetasEncontradasChef;
        List<BigInteger> idsChefs;

        try {
            idsChefs = this.controladorCBDRecetasCooker.consultaIdsChef(nom_chef);
            for (BigInteger actual : idsChefs){
                recetasEncontradasChef = this.controladorCBDRecetasCooker.buscarRecetasChef(actual);
                recetasEncontradas.addAll(recetasEncontradasChef);
            }

        } catch (SQLException sqle){
            recetasEncontradas = null;
        }
        return recetasEncontradas;
    }

    /**
     * @inheritDoc
     */
    @Override
    public DTORecetaMiniatura miniaturaRecetas(BigInteger idReceta){

        DTORecetaMiniatura recetaMiniatura = new DTORecetaMiniatura();
        try {
            Receta receta = this.controladorCBDRecetasCooker.buscarRecetas(idReceta);
            Chef chef = this.controladorCBDRecetasCooker.consultaRecetaXChef(idReceta);


            if(receta != null && chef != null){
                recetaMiniatura = new DTORecetaMiniatura(idReceta, receta.getNombre(), receta.getLinkImagen(), chef);
                recetaMiniatura.setEncontrado(true);
            }


        } catch (SQLException sqle){
            recetaMiniatura.setEncontrado(false);
        }
        return recetaMiniatura;
    }

    /**
     * @inheritDoc
     */
    @Override
    public DTORecetasMiniaturaCategoria miniaturaRecetasCategoria(int idCategoria) {

        DTORecetasMiniaturaCategoria miniaturas = new DTORecetasMiniaturaCategoria();
        try {
            List<DTOReceta> recetas = controladorCBDRecetasCooker.buscarRecetasCategoria(idCategoria);
            Categoria categoria = controladorCBDRecetasCooker.consultaCategoria(idCategoria);
            List<DTORecetaMiniatura> listaMiniaturas = new ArrayList<>();

            for(DTOReceta receta : recetas){
                DTORecetaMiniatura minRecibida = miniaturaRecetas(receta.getReceta().getIdReceta());
                listaMiniaturas.add(minRecibida);
            }

            miniaturas.setCategoria(categoria);
            miniaturas.setRecetasCategoria(listaMiniaturas);
            miniaturas.setEncontrada(true);

        } catch (SQLException sqle){
            miniaturas.setEncontrada(false);
        }
        return miniaturas;
    }



    /**
     * @inheritDoc
     */
    @Override
    public DTOReceta mostrarReceta(BigInteger idReceta) {

        DTOReceta recetaMostrar = new DTOReceta();
        try {
            Receta receta = controladorCBDRecetasCooker.buscarRecetas(idReceta);
            Chef chef = controladorCBDRecetasCooker.consultaRecetaXChef(idReceta);
            recetaMostrar = new DTOReceta(receta, chef);
            recetaMostrar.setEncotrado(true);
        } catch (SQLException sqle){
            recetaMostrar.setEncotrado(false);
        }
        return recetaMostrar;
    }
}

package logica_negocio.recetas;

import acceso_datos.consultas_bd.ControladorCBDRecetasCooker;
import acceso_datos.consultas_bd.IControladorCBDRecetasCooker;
import acceso_datos.persistencia_bd.ControladorPBDRecetasCooker;
import acceso_datos.persistencia_bd.IControladorPBDRecetasCooker;
import entidades.dto.*;
import entidades.modelo.*;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ControladorRecetasCooker implements IControladorRecetasCooker {
    private Cooker cooker;
    private IControladorCBDRecetasCooker controlCBD;
    private IControladorPBDRecetasCooker controlPBD;

    public ControladorRecetasCooker(Cooker cooker) {
        this.controlCBD = new ControladorCBDRecetasCooker();
        this.controlPBD = new ControladorPBDRecetasCooker();
        this.cooker = cooker;
    }

    public ControladorRecetasCooker(){
        this.controlCBD = new ControladorCBDRecetasCooker();
        this.controlPBD = new ControladorPBDRecetasCooker();
    }

    public Cooker getCooker() {
        return cooker;
    }

    public void setCooker(Cooker cooker) {
        this.cooker = cooker;
    }

    // Métodos Funcionalidad Crear Lista Favoritos

    /**
     * @inheritDoc
     */
    @Override
    public DTOListaFavoritos crearListaFavoritos(String nombreLista, String descripcion, BigInteger id_receta) {

        DTOListaFavoritos listaEnviar = null;
        try {

            //Se obtiene el tamaño de la lista de listaFavoritos
            int tamArreglo = this.cooker.getListasFavoritos().size();

            //Se crea el id de la nueva lista
            int id = this.cooker.getListasFavoritos().get(tamArreglo - 1).getIdListaFavoritos() + 1;

            //Obtengo de la bace de datos la receta correspondiente al id_receta
            Receta receta = this.controlCBD.buscarRecetas(id_receta);

            //Se añade la receta a una lista de recetas
            List<Receta> listaReceta = new ArrayList<>();
            listaReceta.add(receta);

            //Se crea la lista de recetas
            ListaFavoritos listaFavoritos = new ListaFavoritos(id, nombreLista, descripcion, listaReceta);


            //Se crea un DTO de lista favoritos para enviar a la base de datos
            listaEnviar = new DTOListaFavoritos(this.cooker, listaFavoritos);

            //Se recibe un booleano que indica si se agregó con exito la nueva lista a la base de datos
            boolean exito = this.controlPBD.crearListaFavoritosConReceta(listaEnviar);

            //Mensaje de creación
            if (exito) {
                listaEnviar.setMensaje("¡Lista creada con éxito!");
            }

            //Si la receta no se ha añadido a la lista de favoritos general, se añade
            if (!this.controlCBD.recetaEnListaRecetas(1, id_receta, this.cooker.getIdUsuario())) {
                this.controlPBD.insertarRecetaListaFavoritos(id_receta, 1, this.cooker.getIdUsuario());
            }


        } catch (SQLException sqle) {
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
            int id = this.cooker.getListasFavoritos().get(tamArreglo - 1).getIdListaFavoritos() + 1;

            //Se añade la receta a una lista de recetas
            List<Receta> listaReceta = new ArrayList<>();

            //Se crea la lista de recetas
            ListaFavoritos listaFavoritos = new ListaFavoritos(id, nombreLista, descripcion, listaReceta);


            //Se crea un DTO de lista favoritos para enviar a la base de datos
            listaEnviar = new DTOListaFavoritos(this.cooker, listaFavoritos);

            //Se recibe un booleano que indica si se agregó con exito la nueva lista a la base de datos
            boolean exito = this.controlPBD.crearListaFavoritos(listaEnviar);

            //Mensaje de creación
            if (exito) {
                listaEnviar.setMensaje("¡Lista creada con éxito!");
            }

        } catch (SQLException sqle) {
            listaEnviar.setMensaje("Error en la base de  datos " + sqle.getMessage());
        }
        return listaEnviar;
    }

    @Override
    public DTOExito agregarRecetaListaFavoritos(BigInteger idreceta) {

        DTOExito exito = new DTOExito();
        boolean agregado = false;
        try {

            //Agrega la receta a la lista general
            if (!this.controlCBD.recetaEnListaRecetas(1, idreceta, this.cooker.getIdUsuario())) {
                agregado = this.controlPBD.insertarRecetaListaFavoritos(idreceta, 1, this.cooker.getIdUsuario());
            }

            exito.setEstado(agregado);

            if (agregado) {
                exito.setMensaje("¡La receta ha sido agregada a la lista con éxito!");
            } else {
                exito.setMensaje("¡La receta ya está en la lista!");
            }

        } catch (SQLException sqle) {
            exito.setEstado(false);
            exito.setMensaje("Error en la base de datos " + sqle.getMessage());
        }
        return exito;
    }

    /**
     * @inheritDoc
     */
    @Override
    public DTOExito agregarRecetaListaFavoritos(BigInteger idreceta, int idLista) {

        DTOExito exito = new DTOExito();
        boolean agregado = false;
        try {
            //Agrega la receta a la lista
            if (!this.controlCBD.recetaEnListaRecetas(idLista, idreceta, this.cooker.getIdUsuario())) {
                agregado = this.controlPBD.insertarRecetaListaFavoritos(idreceta, idLista, this.cooker.getIdUsuario());
            }

            //Agrega la receta a la lista general
            if (!this.controlCBD.recetaEnListaRecetas(1, idreceta, this.cooker.getIdUsuario())) {
                this.controlPBD.insertarRecetaListaFavoritos(idreceta, 1, this.cooker.getIdUsuario());
            }

            exito.setEstado(agregado);

            if (agregado) {
                exito.setMensaje("¡La receta ha sido agregada a la lista con éxito!");
            } else {
                exito.setMensaje("¡La receta ya está en la lista!");
            }

        } catch (SQLException sqle) {
            exito.setEstado(false);
            exito.setMensaje("Error en la base de datos " + sqle.getMessage());
        }
        return exito;
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
                DTOListaFavoritos listaAgregar = new DTOListaFavoritos(this.cooker, this.controlCBD.consultaListaFavoritos(idLista));

                if (!this.controlCBD.recetaEnListaRecetas(idLista, idreceta, this.cooker.getIdUsuario())) {
                    agregado = this.controlPBD.insertarRecetaListaFavoritos(idreceta, idLista, this.cooker.getIdUsuario());
                }

                listaAgregar.setAgregado(agregado);
                agregados.add(listaAgregar);
            }

            for (DTOListaFavoritos listaFavoritos : agregados) {
                if (listaFavoritos.isAgregado()) {
                    exito.setEstado(listaFavoritos.isAgregado());
                    exito.setMensaje("¡Se agrego la receta a las listas indicadas!");
                }
            }
            //Agrego la receta a la lista general
            if (!this.controlCBD.recetaEnListaRecetas(1, idreceta, this.cooker.getIdUsuario())) {
                this.controlPBD.insertarRecetaListaFavoritos(idreceta, 1, this.cooker.getIdUsuario());
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
    public List<DTORecetaMiniatura> buscarRecetasNombre(String nombre) {
        List<DTORecetaMiniatura> recetasEncontradas;
        try {
            recetasEncontradas = this.controlCBD.buscarRecetas(nombre);

        } catch (SQLException sqle) {
            recetasEncontradas = null;
        }
        return recetasEncontradas;
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<DTORecetaMiniatura> buscarRecetasCategoria(String categoria) {
        List<DTORecetaMiniatura> recetasEncontradas;

        try {
            recetasEncontradas = this.controlCBD.consultaIdCategoria(categoria);

        } catch (SQLException sqle) {
            recetasEncontradas = null;
        }
        return recetasEncontradas;
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<DTORecetaMiniatura> buscarRecetasIngrediente(String nom_ingrediente) {
        List<DTORecetaMiniatura> recetasEncontradas = new ArrayList<>();

        try {
            recetasEncontradas = this.controlCBD.consultaIdsIngrediente(nom_ingrediente);


        } catch (SQLException sqle) {
            recetasEncontradas = null;
        }
        return recetasEncontradas;
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<DTORecetaMiniatura> buscarRecetasChef(String nom_chef) {
        List<DTORecetaMiniatura> recetasEncontradas = new ArrayList<>();

        try {
            recetasEncontradas = this.controlCBD.consultaIdsChef(nom_chef);

        } catch (SQLException sqle) {
            recetasEncontradas = null;
        }
        return recetasEncontradas;
    }

    @Override
    public List<DTORecetaMiniatura> buscarReceta(String busqueda) {
        List<DTORecetaMiniatura> resultados = new ArrayList<>();
        boolean esta = false;

        List<DTORecetaMiniatura> busquedaNombre = buscarRecetasNombre(busqueda);
        if (busquedaNombre != null) {
            resultados.addAll(busquedaNombre);
        }

        List<DTORecetaMiniatura> busquedaCategoria = buscarRecetasCategoria(busqueda);
        if (busquedaCategoria != null) {
            for (DTORecetaMiniatura miniaturaAdd : busquedaCategoria) {
                for (DTORecetaMiniatura miniaturaCom : resultados) {
                    if (miniaturaCom.getIdReceta().equals(miniaturaAdd.getIdReceta())) {
                        esta = true;
                    }
                }
                if (!esta) {
                    resultados.add(miniaturaAdd);
                }
                esta = false;
            }
        }


        List<DTORecetaMiniatura> busquedaIngredientes = buscarRecetasIngrediente(busqueda);
        if (busquedaIngredientes != null) {
            for (DTORecetaMiniatura miniaturaAdd : busquedaIngredientes) {
                for (DTORecetaMiniatura miniaturaCom : resultados) {
                    if (miniaturaCom.getIdReceta().equals(miniaturaAdd.getIdReceta())) {
                        esta = true;
                    }
                }
                if (!esta) {
                    resultados.add(miniaturaAdd);
                }
                esta = false;
            }
        }


        List<DTORecetaMiniatura> busquedaChefs = buscarRecetasChef(busqueda);
        if (busquedaChefs != null) {
            for (DTORecetaMiniatura miniaturaAdd : busquedaChefs) {
                for (DTORecetaMiniatura miniaturaCom : resultados) {
                    if (miniaturaCom.getIdReceta().equals(miniaturaAdd.getIdReceta())) {
                        esta = true;
                    }
                }
                if (!esta) {
                    resultados.add(miniaturaAdd);
                }
                esta = false;
            }
        }

        return resultados;
    }

    @Override
    public DTOReceta buscarReceta(BigInteger idreceta) {
        DTOReceta receta = new DTOReceta();
        try {
            Receta rec = this.controlCBD.buscarRecetas(idreceta);
            Chef chef = this.controlCBD.consultaRecetaXChef(idreceta);
            receta.setReceta(rec);
            receta.setAutor(chef);
        } catch (SQLException sqlException) {
            receta = null;
        }
        return receta;
    }

    /**
     * @inheritDoc
     */
    @Override
    public DTORecetaMiniatura miniaturaRecetas(BigInteger idReceta) {
        DTORecetaMiniatura recetaMiniatura = new DTORecetaMiniatura();

        try {
            Receta receta = this.controlCBD.buscarRecetas(idReceta);
            Chef chef = this.controlCBD.consultaRecetaXChef(idReceta);

            if (receta != null && chef != null) {
                recetaMiniatura = new DTORecetaMiniatura(idReceta, receta.getNombre(), receta.getLinkImagen(), chef);

            }
        } catch (SQLException sqle) {

        }
        return recetaMiniatura;
    }

    /**
     * Convierte un DTOReceta en un DTORecetaMiniatura
     *
     * @param receta la receta
     * @return la miniatura con la información
     */
    private DTORecetaMiniatura crearMiniaturaReceta(DTOReceta receta) {
        return new DTORecetaMiniatura(receta.getReceta().getIdReceta(),
                receta.getReceta().getNombre(), receta.getReceta().getLinkImagen(), receta.getAutor());
    }

    /**
     * Convierte la lista de DTOReceta que recibe en una lista de DTORecetaMiniatura
     *
     * @param recetas la lista de recetas
     * @return la miniatura con la informaación
     */
    private List<DTORecetaMiniatura> crearMiniaturasRecetas(List<DTOReceta> recetas) {
        List<DTORecetaMiniatura> miniaturas = new ArrayList<>();

        for (DTOReceta receta : recetas) {
            miniaturas.add(this.crearMiniaturaReceta(receta));
        }

        return miniaturas;
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<DTORecetaMiniatura> buscarMiniaturaRecetaCategoria(int idCategoria) {
        List<DTORecetaMiniatura> miniaturas = new ArrayList<>();

        try {
            miniaturas = this.controlCBD.buscarRecetasCategoria(idCategoria);
        } catch (SQLException sqle) {
        }

        return miniaturas;
    }

    @Override
    public List<Categoria> buscarCategorias() {
        List<Categoria> categorias = new ArrayList<>();

        try {
            categorias = this.controlCBD.consultarCategorias();
        } catch (SQLException sqle) {
        }

        return categorias;
    }

    @Override
    public List<DTORecetasMiniaturaCategoria> buscarMiniaturasRecetasCategoria() {
        List<DTORecetasMiniaturaCategoria> miniaturasCategoria = new ArrayList<>();
        List<Categoria> categorias = this.buscarCategorias();

        for (Categoria categoria : categorias) {
            List<DTORecetaMiniatura> miniaturas = this.buscarMiniaturaRecetaCategoria(categoria.getIdCategoria());
            if (miniaturas.size() > 0) {
                miniaturasCategoria.add(new DTORecetasMiniaturaCategoria(categoria, miniaturas));
            }
        }

        return miniaturasCategoria;
    }

    @Override
    public List<LineaIngrediente> ingredientesxReceta(BigInteger idReceta) {
        List<LineaIngrediente> ingredientes;
        try {
            ingredientes = this.controlCBD.consultaLineaIngrediente(idReceta);
        } catch (SQLException sqlException) {
            ingredientes = null;
        }
        return ingredientes;
    }

    /**
     * @inheritDoc
     */
    @Override
    public DTOReceta mostrarReceta(BigInteger idReceta) {

        DTOReceta recetaMostrar = new DTOReceta();
        try {
            Receta receta = controlCBD.buscarRecetas(idReceta);
            Chef chef = controlCBD.consultaRecetaXChef(idReceta);
            recetaMostrar = new DTOReceta(receta, chef);
            recetaMostrar.setEncontrado(true);
        } catch (SQLException sqle) {
            recetaMostrar.setEncontrado(false);
        }
        return recetaMostrar;
    }

    @Override
    public List<DTOReceta> recetasChef(BigInteger idChef){
        List<DTOReceta> recetas = new ArrayList<>();
        try {
            recetas.addAll(controlCBD.buscarRecetasChef(idChef));
        } catch (SQLException sqle){
            recetas = null;
        }
        return  recetas;
    }

    /**
     * @inheritDoc
     */
    @Override
    public DTOExito calificarChef(Chef chef, int calificacion){
        try {
            this.controlPBD.calificarChef(chef.getIdUsuario(), this.cooker.getIdUsuario(), calificacion);
        } catch (SQLException e) {
            return new DTOExito(false, "Error en la base de datos; " + e.getMessage());
        }
        return new DTOExito(true, "La calificación fue añadida con éxito");
    }

    /**
     * @inheritDoc
     *
     */
    @Override
    public DTOExito calificarReceta(Receta receta, int calificacion){
        try {
            this.controlPBD.calificarReceta(receta.getIdReceta(), this.cooker.getIdUsuario(), calificacion);
        } catch (SQLException e) {
            return new DTOExito(false, "Error en la base de datos; " + e.getMessage());
        }
        return new DTOExito(true, "La calificación fue añadida con éxito");
    }

    @Override
    public DTOCalificacion promedioCalificacionReceta (Receta receta){

        try {
            List<Integer> calificaciones = this.controlCBD.listaCalificacionReceta(receta.getIdReceta());
            float promedio = 0;
            for(Integer valor: calificaciones){
                promedio +=  valor;
            }

            promedio = promedio/calificaciones.size();

            return new DTOCalificacion(promedio, true, "Promedio calculado");

        } catch (SQLException e) {
            return new DTOCalificacion(0, false, "Error en la base de datos; " + e.getMessage());
        }
    }

    @Override
    public DTOCalificacion promedioCalificacionChef (Chef chef){

        try {
            List<Integer> calificaciones = this.controlCBD.listaCalificacionChef(chef.getIdUsuario());
            float promedio = 0;
            for(Integer valor: calificaciones){
                promedio +=  valor;
            }

            promedio = promedio/calificaciones.size();

            return new DTOCalificacion(promedio, true, "Promedio calculado");

        } catch (SQLException e) {
            return new DTOCalificacion(0, false, "Error en la base de datos; " + e.getMessage());
        }
    }
    @Override
    public DTOExito eliminarCalificacion (Receta receta){
        try {
            this.controlPBD.eliminarCalificacion(receta.getIdReceta(), this.cooker.getIdUsuario());
        } catch (SQLException e) {
            return new DTOExito(false, "Error en la base de datos; " + e.getMessage());
        }
        return new DTOExito(true, "La calificación fue eliminada con éxito");
    }

    private int mayor(List<Integer> ids){
        int mayor = 0;

        for(Integer in : ids){
            if(in > mayor){
                mayor = in;
            }
        }
        return mayor;
    }

    /**
     * @inheritDoc
     */
    public DTOExito reportarReceta(Receta receta, Reporte reporte){

        boolean exito;
        try {
            List<Integer> ids = controlCBD.listarReportes();
            int idReporte = mayor(ids) + 1;
            exito = controlPBD.reportarReceta(receta.getIdReceta(), cooker.getIdUsuario(), reporte.getMotivo().getIdMotivo(), idReporte);

        } catch (SQLException e) {
            return new DTOExito(false, "Error en la base de datos; " + e.getMessage());
        }
        return new DTOExito(exito, "La receta se reportó");
    }

    @Override
    public List<DTORecetaMiniatura> recetasListaFavoritos(){

        try {
            return this.controlCBD.idsListaFavoritos(this.cooker.getIdUsuario());
        } catch (SQLException e) {
            return null;
        }
    }
}

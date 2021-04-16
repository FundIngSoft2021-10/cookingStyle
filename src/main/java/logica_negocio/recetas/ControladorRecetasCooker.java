package logica_negocio.recetas;

import java.util.ArrayList;
import java.util.List;

import acceso_datos.consultas_bd.ControladorBDRecetasCooker;
import entidades.dto.DTOListaFavoritos;
import entidades.dto.DTOReceta;
import entidades.dto.DTORecetaMiniatura;
import entidades.modelo.ListaFavoritos;
import entidades.modelo.Receta;
import entidades.modelo.Cooker;

public class ControladorRecetasCooker implements IControladorRecetasCooker{

    private Cooker cooker;
    ControladorBDRecetasCooker controladorBDRecetasCooker = new ControladorBDRecetasCooker();

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
    public boolean crearListaFavoritos(String nombreLista, String descripcion, int id_receta) {

        try {

            //Se obtiene el id de la nueva lista
            int id = cooker.getListasFavoritos().size() + 1;
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


            boolean exito = controladorBDRecetasCooker.crearListaFavoritosConReceta(listaEnviar);

            //La nueva receta favorita, se añade a la lista de favoritos general
            for(ListaFavoritos listaFavoritos1 : cooker.getListasFavoritos()){
                if(listaFavoritos1.getIdListaFavoritos() == 1){
                    listaFavoritos1.getRecetasFavoritas().add(receta);
                }
            }
            return exito;

        } catch (Exception e){
            return false;
        }

    }

    /**
     * Creación de una lista de recetas favorita específica sin una receta inicial
     * @param nombreLista nombre de la lista de favoritos
     * @param descripcion decripcion de la lista
     * @return true si se creo con éxito, false si no se creó
     */
    @Override
    public boolean crearListaFavoritos(String nombreLista, String descripcion) {
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

            boolean exito = controladorBDRecetasCooker.crearListaFavoritosConReceta(listaEnviar);

            System.out.println("Exito");

        } catch (Exception e){
            return false;
        }
        return false;
    }

    @Override
    public boolean agregarRecetaListaFavoritos(DTOReceta receta, int idLista) {
        try{
            //AQUÍ ESTARÍA LA LLAMADA A LA BASE DE DATOS
            //DTOReceta =
            ListaFavoritos listaFavoritos= new ListaFavoritos();
            Receta recetaAgregar = receta.getReceta();

            listaFavoritos.getRecetasFavoritas().add(recetaAgregar);
        }catch (Exception e){
            return false;
        }
        return false;
    }
}

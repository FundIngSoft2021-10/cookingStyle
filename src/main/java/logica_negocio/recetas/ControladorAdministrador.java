package logica_negocio.recetas;

import acceso_datos.consultas_bd.ControladorCBDAdministrador;
import acceso_datos.consultas_bd.ControladorCBDRecetas;
import acceso_datos.consultas_bd.IControladorCBDRecetas;
import acceso_datos.persistencia_bd.ControladorPBDRecetasChef;
import acceso_datos.persistencia_bd.IControladorPBDRecetasChef;
import entidades.dto.DTOReceta;
import entidades.modelo.Receta;

import java.math.BigInteger;
import java.sql.SQLException;


public class ControladorAdministrador implements IControladorAdministrador {
    private IControladorCBDRecetas controlCBDRecetas;
    private IControladorPBDRecetasChef controlPersistenciaBD;

    public ControladorAdministrador() {
        this.controlCBDRecetas = new ControladorCBDRecetas();
        this.controlPersistenciaBD = new ControladorPBDRecetasChef();
    }
    @Override
    public DTOReceta eliminarVideoInapropiado(BigInteger idreceta){
        DTOReceta receta = new DTOReceta();

        try{
            Receta rec = this.controlCBDRecetas.buscarRecetas(idreceta);
            receta.setEncontrado(true);
            receta.setReceta(rec);
            receta.setAutor(this.controlCBDRecetas.consultaRecetaXChef(idreceta));
            if(this.controlPersistenciaBD.eliminarReceta(idreceta)){
                receta.setMensaje("la receta ha sido eliminada exitosamente");
            }
            else{
                receta.setMensaje("la receta no se pudo eliminar");
            }
        }catch (SQLException sqlException){
            receta.setMensaje("fallo en la base de datos");
            receta.setReceta(null);
            receta.setAutor(null);
            receta.setEncontrado(false);
        }
        return receta;

    }
}

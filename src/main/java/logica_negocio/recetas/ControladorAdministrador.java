package logica_negocio.recetas;

import acceso_datos.consultas_bd.ControladorCBDAdministrador;
import acceso_datos.consultas_bd.ControladorCBDRecetas;
import acceso_datos.consultas_bd.IControladorCBDAdministrador;
import acceso_datos.consultas_bd.IControladorCBDRecetas;
import acceso_datos.persistencia_bd.ControladorPBDAdministrador;
import acceso_datos.persistencia_bd.ControladorPBDRecetasChef;
import acceso_datos.persistencia_bd.IControladorPBDAdministrador;
import acceso_datos.persistencia_bd.IControladorPBDRecetasChef;
import entidades.dto.DTOExito;
import entidades.dto.DTOReceta;
import entidades.modelo.Receta;
import entidades.modelo.Reporte;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ControladorAdministrador implements IControladorAdministrador {
    private IControladorCBDRecetas controlCBDRecetas;
    private IControladorPBDAdministrador controlPBDAdmin;
    private IControladorPBDRecetasChef controlPersistenciaBD;
    private IControladorCBDAdministrador controlCBDAdministrador;

    public ControladorAdministrador() {
        this.controlCBDRecetas = new ControladorCBDRecetas();
        this.controlPersistenciaBD = new ControladorPBDRecetasChef();
        this.controlCBDAdministrador = new ControladorCBDAdministrador();
        this.controlPBDAdmin = new ControladorPBDAdministrador();
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

    @Override
    public List<Reporte> revisarReportes (){
        List<Reporte> reportesEncontrados;
        try {
            reportesEncontrados = controlCBDAdministrador.revisarReportes();
        } catch (SQLException e) {
            reportesEncontrados = null;
        }
        return  reportesEncontrados;
    }

    @Override
    public DTOExito eliminarReporte(int idReporte){
        try {
            this.controlPBDAdmin.eliminarReporte(idReporte);
            return new DTOExito(true, "Reporte eliminado");
        } catch (SQLException e) {
            return new DTOExito(false, "Error en la base de datos; " + e.getMessage());
        }
    }
}

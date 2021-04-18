package logica_negocio.citas;

import acceso_datos.consultas_bd.IControladorCBDCitas;
import acceso_datos.consultas_bd.IControladorCBDRegAut;
import acceso_datos.persistencia_bd.IControladorPBDCitas;
import entidades.dto.*;
import entidades.modelo.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class ControladorCitas implements IControladorCitas {
    private IControladorCBDCitas controlConsultaBD;
    private IControladorPBDCitas controlPersistenciaBD;

    @Override
    public DTOAgendaChef crearAgendaChef(Chef chef, List<Dia> dias, List<Integer> horas){
    ListIterator it =dias.listIterator();
    ListIterator it2 =horas.listIterator();
    List<Bloque> bloques = new ArrayList<>();

    while (it.hasNext()){
    Bloque bloque = new Bloque();
    bloque.setDia((Dia) it.next());
    bloque.setHora((int) it2.next());
    bloques.add(bloque);
    }
    try{
        this.controlPersistenciaBD.crearAgendaChef(chef,bloques);
    } catch(SQLException e){
        return new DTOAgendaChef(false,"Error en la base de datos" + e.getMessage(),null,null);
    }
    Calendario calendario = new Calendario(bloques);
    return new DTOAgendaChef(true, "Agenda creada exitosamente",chef,calendario);
    }
    @Override
    public DTOAgendaChef consultarDisponibilidadChef(Chef chef){
        List<Bloque> bloques = new ArrayList<>();
        Calendario calendario;
        try{
            calendario =this.controlConsultaBD.buscarCalendarioChef(chef);
        } catch (SQLException e){
            return new DTOAgendaChef(false, "Error en al base de datos"+ e.getMessage(),null,null);
        }
        return new DTOAgendaChef(true,"Agenda del chef",chef,calendario);
    }
    /*
    //IMPORTANTE, NO BORRAR, CODIGO DE CREACIÓN DE LAS CITAS DEL CHEF

    DTOCitasChef crearCitasChef(Chef chef, List<Date>fechas, List<Bloque>bloques, List<Cooker>cookers){
        List<Cita> Citas = new ArrayList<>();
        ListIterator it =fechas.listIterator();
        ListIterator it2 =bloques.listIterator();
        ListIterator it3 =cookers.listIterator();
        boolean existe;
        while(it.hasNext()) {
            Cita cita = new Cita();
            Date date = (Date) it.next();
            cita.setFecha(date);
            Bloque bloque = (Bloque) it2.next();
            cita.setBloque(bloque);
            Cooker cooker = (Cooker) it3.next();
            try{
                existe =this.controlConsultaBD.existeNombreUsuario(cooker.getNombreUsuario());
                if(!existe){
                    return new DTOCitasChef(false,"el cooker no se encuentra en la base de datos",chef,null);
                }
            }
            catch(SQLException e){
                    return new DTOCitasChef(false,"Error en la base de datos"+ e.getMessage(),chef,null);
            }
            cita.setCooker(cooker);
            cita.setChef(chef);
            Citas.add(cita);
        }
        //PERSISTIR INFORMACIÓN
        return new DTOCitasChef(true,"la creación de la agenda fue exitosa",chef,Citas);

    }

 */
}



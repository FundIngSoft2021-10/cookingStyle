package logica_negocio.citas;

import acceso_datos.consultas_bd.ControladorCBDCitas;
import acceso_datos.consultas_bd.IControladorCBDCitas;
import acceso_datos.persistencia_bd.ControladorPBDCitas;
import acceso_datos.persistencia_bd.IControladorPBDCitas;
import entidades.dto.DTOAgendaChef;
import entidades.dto.DTOCorreo;
import entidades.modelo.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ControladorCitas implements IControladorCitas {
    private IControladorCBDCitas controlCBD;
    private IControladorPBDCitas controlPBD;

    public ControladorCitas() {
        this.controlCBD = new ControladorCBDCitas();
        this.controlPBD = new ControladorPBDCitas();
    }

    @Override
    public DTOAgendaChef crearAgendaChef(Chef chef, List<Bloque> bloques) {
        try {
            this.controlPBD.crearAgendaChef(chef.getIdUsuario(), bloques);
        } catch (SQLException e) {
            return new DTOAgendaChef(false, "Error en la base de datos; " + e.getMessage(), null, null);
        }

        Calendario calendario = new Calendario(bloques);
        return new DTOAgendaChef(true, "Agenda creada exitosamente", chef, calendario);
    }

    @Override
    public DTOAgendaChef consultarAgendaChef(Chef chef) {
        List<Bloque> bloques = new ArrayList<>();
        Calendario calendario;

        try {
            calendario = this.controlCBD.buscarCalendarioChef(chef);
        } catch (SQLException e) {
            return new DTOAgendaChef(false, "Error en al base de datos; " + e.getMessage(), null, null);
        }

        return new DTOAgendaChef(true, "Agenda del chef", chef, calendario);
    }

    /**
     * @inheritDoc
     */
    @Override
    public DTOCorreo correoUsuario(Usuario usuario){
        String correo;

        try {
            correo = this.controlCBD.getCorreo(usuario.getIdUsuario());
        } catch (SQLException e) {
            return new DTOCorreo(false, "Error en la base de datos; " + e.getMessage(), null);
        }

        return new DTOCorreo(true, "Correo del usuario", correo);
    }

    /*
    TODO: Implementar crear las citas del chef
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



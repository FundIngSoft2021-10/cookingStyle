package logica_negocio.citas;

import acceso_datos.consultas_bd.IControladorCBDRegAut;
import acceso_datos.persistencia_bd.IControladorPBDRegAut;

import acceso_datos.consultas_bd.*;
import acceso_datos.persistencia_bd.*;
import entidades.dto.*;
import entidades.modelo.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

public class ControladorCitas implements iControladorCitas  {
    private IControladorCBDCitas controlConsultaBD;
    private IControladorPBDCitas controlPersistenciaBD;

    DTOAgendaChef crearAgendaChef (Chef chef, List<Date>fechas,List<Bloque>bloques,List<Cooker>cookers){
        List<Cita> Citas = new ArrayList<>();
        ListIterator it =fechas.listIterator();
        ListIterator it2 =bloques.listIterator();
        ListIterator it3 =cookers.listIterator();
        while(it.hasNext()) {
            Cita cita = new Cita();
            Date date = (Date) it.next();
            cita.setFecha(date);
            Bloque bloque = (Bloque) it2.next();
            cita.setBloque(bloque);
            Cooker cooker = (Cooker) it3.next();
            cita.setCooker(cooker);
            cita.setChef(chef);
            Citas.add(cita);

        }
        return null;
    }
}



package acceso_datos.consultas_bd;

import acceso_datos.conexion_bd.ControladorBDConexion;
import entidades.modelo.Bloque;
import entidades.modelo.Calendario;
import entidades.modelo.Chef;
import entidades.modelo.Dia;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ControladorCBDCitas implements IControladorCBDCitas {
    ControladorBDConexion controladorBDConexion;
    Connection conexion;

    public ControladorCBDCitas() {
        controladorBDConexion = new ControladorBDConexion();
        conexion = controladorBDConexion.conectarMySQL();
    }
    @Override
    public Calendario buscarCalendarioChef(Chef chef) throws SQLException{
        String consulta = "SELECT * FROM bloque where Chef_idUsuario = ?";

        try{
            PreparedStatement stmt =conexion.prepareStatement(consulta);
            stmt.setBigDecimal(1,new BigDecimal(chef.getIdUsuario()));
            List<Bloque> bloques = new ArrayList<>();
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Bloque bloque = new Bloque();
                int num =rs.getInt("dia");
                Dia dia;
                if (num == 1){

                    dia=Dia.LUNES;
                    bloque.setDia(dia);
                }
                if (num == 2){

                    dia=Dia.MARTES;
                    bloque.setDia(dia);
                }
                if (num == 3){

                    dia=Dia.MIERCOLES;
                    bloque.setDia(dia);
                }
                if (num == 4){

                    dia=Dia.JUEVES;
                    bloque.setDia(dia);
                }
                if (num == 5){

                    dia=Dia.VIERNES;
                    bloque.setDia(dia);
                }
                if (num == 6){

                    dia=Dia.SABADO;
                    bloque.setDia(dia);
                }
                if (num == 7) {

                    dia = Dia.DOMINGO;
                    bloque.setDia(dia);
                }
                bloque.setHora(rs.getInt("hora"));
                bloques.add(bloque);
            }
            if(bloques.size()>=1){
                return new Calendario(bloques);
            }

        }catch(SQLException sqle){
            throw sqle;
        }
    return null;
    }



}

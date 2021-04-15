package logica_negocio.recetas;

import java.util.ArrayList;
import java.util.List;

import entidades.dto.DTOReceta;
import entidades.dto.DTORecetaMiniatura;
import entidades.modelo.Receta;
import entidades.modelo.Cooker;

public class ControladorRecetasCooker implements IControladorRecetasCooker{

    private Cooker cooker;

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

    public DTOReceta verReceta(DTORecetaMiniatura dtoRecetaMiniatura){
        
        DTOReceta dtoReceta = new DTOReceta();
        return dtoReceta;
    }

}

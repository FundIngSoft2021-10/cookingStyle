package entidades.dto;

import entidades.modelo.LineaIngrediente;
import entidades.modelo.Receta;

public class DTOLineaIngrediente {
    private Receta receta;
    private LineaIngrediente lineaIngrediente;

    public DTOLineaIngrediente(Receta receta, LineaIngrediente lineaIngrediente) {
        this.receta = receta;
        this.lineaIngrediente=lineaIngrediente;
    }

    public Receta getReceta() {
        return receta;
    }

    public void setReceta(Receta receta) {
        this.receta = receta;
    }

    public LineaIngrediente getLineaIngrediente() {
        return lineaIngrediente;
    }

    public void setLineaIngrediente(LineaIngrediente lineaIngrediente) {
        this.lineaIngrediente = lineaIngrediente;
    }
}

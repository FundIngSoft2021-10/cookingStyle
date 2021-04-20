package entidades.dto;

import entidades.modelo.Ingrediente;

public class DTOIngrediente {
    private Ingrediente ingrediente;

    public DTOIngrediente(Ingrediente ingrediente) {
        this.ingrediente = ingrediente;
    }

    public Ingrediente getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(Ingrediente ingrediente) {
        this.ingrediente = ingrediente;
    }
}

package entidades.modelo;

public class LineaIngrediente {
    private Ingrediente ingrediente;
    private float cantidad;
    private Medida medida;

    public LineaIngrediente(Ingrediente ingrediente, float cantidad, Medida medida) {
        this.ingrediente = ingrediente;
        this.cantidad = cantidad;
        this.medida = medida;
    }

    public LineaIngrediente() {
    }

    public Ingrediente getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(Ingrediente ingrediente) {
        this.ingrediente = ingrediente;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    public Medida getMedida() {
        return medida;
    }

    public void setMedida(Medida medida) {
        this.medida = medida;
    }
}

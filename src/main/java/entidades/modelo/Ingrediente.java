package entidades.modelo;

public class Ingrediente {
    private int idIngrediente;
    private String nombre;

    public Ingrediente(int idIngrediente, String nombre) {
        this.idIngrediente = idIngrediente;
        this.nombre = nombre;
    }

    public Ingrediente() {
    }

    public int getIdIngrediente() {
        return idIngrediente;
    }

    public void setIdIngrediente(int idIngrediente) {
        this.idIngrediente = idIngrediente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

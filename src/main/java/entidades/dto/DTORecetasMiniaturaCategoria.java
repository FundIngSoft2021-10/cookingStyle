package entidades.dto;

import entidades.modelo.Categoria;

import java.util.List;
import java.util.Map;

public class DTORecetasMiniaturaCategoria {
    Categoria categoria;
    List<DTORecetaMiniatura> recetasCategoria;

    public DTORecetasMiniaturaCategoria(Categoria categoria, List<DTORecetaMiniatura> recetasCategoria) {
        this.categoria = categoria;
        this.recetasCategoria = recetasCategoria;
    }

    public DTORecetasMiniaturaCategoria() {
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<DTORecetaMiniatura> getRecetasCategoria() {
        return recetasCategoria;
    }

    public void setRecetasCategoria(List<DTORecetaMiniatura> recetasCategoria) {
        this.recetasCategoria = recetasCategoria;
    }
}


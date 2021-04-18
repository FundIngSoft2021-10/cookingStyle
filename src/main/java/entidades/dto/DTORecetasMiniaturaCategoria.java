package entidades.dto;

import entidades.modelo.Categoria;

import java.util.List;
import java.util.Map;

public class DTORecetasMiniaturaCategoria {
    private Categoria categoria;
    private List<DTORecetaMiniatura> recetasCategoria;
    private boolean encontrada;

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

    public boolean isEncontrada() {
        return encontrada;
    }

    public void setEncontrada(boolean encontrada) {
        this.encontrada = encontrada;
    }
}


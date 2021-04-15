package entidades.dto;

import entidades.modelo.Categoria;

import java.util.List;
import java.util.Map;

public class DTOPantallaInicioCooker {
    Map<Integer, Categoria> categorias;
    List<DTORecetasMiniaturaCategoria> categoriasMiniatura;

    public DTOPantallaInicioCooker(Map<Integer, Categoria> categorias, List<DTORecetasMiniaturaCategoria> categoriasMiniatura) {
        this.categorias = categorias;
        this.categoriasMiniatura = categoriasMiniatura;
    }

    public DTOPantallaInicioCooker() {
    }

    public Map<Integer, Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(Map<Integer, Categoria> categorias) {
        this.categorias = categorias;
    }

    public List<DTORecetasMiniaturaCategoria> getCategoriasMiniatura() {
        return categoriasMiniatura;
    }

    public void setCategoriasMiniatura(List<DTORecetasMiniaturaCategoria> categoriasMiniatura) {
        this.categoriasMiniatura = categoriasMiniatura;
    }
}

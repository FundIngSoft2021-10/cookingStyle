package entidades.dto;

import entidades.modelo.Cooker;
import entidades.modelo.ListaFavoritos;

public class DTOListaFavoritos {
    private Cooker cooker;
    private ListaFavoritos listaFavoritos;

    public DTOListaFavoritos(Cooker cooker, ListaFavoritos listaFavoritos) {
        this.cooker = cooker;
        this.listaFavoritos = listaFavoritos;
    }

    public Cooker getCooker() {
        return cooker;
    }

    public void setCooker(Cooker cooker) {
        this.cooker = cooker;
    }

    public ListaFavoritos getListaFavoritos() {
        return listaFavoritos;
    }

    public void setListaFavoritos(ListaFavoritos listaFavoritos) {
        this.listaFavoritos = listaFavoritos;
    }
}

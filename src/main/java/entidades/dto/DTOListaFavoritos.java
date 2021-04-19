package entidades.dto;

import entidades.modelo.Cooker;
import entidades.modelo.ListaFavoritos;

public class DTOListaFavoritos {
    private Cooker cooker;
    private ListaFavoritos listaFavoritos;
    private boolean agregado;
    private String mensaje;

    public DTOListaFavoritos(Cooker cooker, ListaFavoritos listaFavoritos) {
        this.cooker = cooker;
        this.listaFavoritos = listaFavoritos;
    }

    public DTOListaFavoritos() {
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

    public boolean isAgregado() {
        return agregado;
    }

    public void setAgregado(boolean agregado) {
        this.agregado = agregado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}

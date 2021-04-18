package entidades.dto;

import entidades.modelo.*;

import java.util.Stack;

public class DTOSesion {
    private TipoUsuario tipoUsuario;
    private Usuario usuario;
    private Stack<Pantalla> historial;

    public DTOSesion(TipoUsuario tipoUsuario, Usuario usuario, Stack<Pantalla> historial) {
        this.tipoUsuario = tipoUsuario;
        this.usuario = usuario;
        this.historial = historial;
    }

    public DTOSesion(TipoUsuario tipoUsuario, Usuario usuario) {
        this.tipoUsuario = tipoUsuario;
        this.usuario = usuario;
        this.historial = new Stack<>();
    }

    public DTOSesion() {
        this.historial = new Stack<>();
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Stack<Pantalla> getHistorial() {
        return historial;
    }

    public void setHistorial(Stack<Pantalla> historial) {
        this.historial = historial;
    }

    // Métodos

    public void agregarPantalla(Pantalla pantalla) {
        this.historial.push(pantalla);
    }

    public Pantalla pantallaActual() {
        return this.historial.peek();
    }

    public Pantalla volverPantalla() {
        this.historial.pop();           // Eliminar la pantalla actual
        return this.historial.pop();    // Pop a la pantalla que se está regresando
    }
}

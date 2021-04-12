package entidades.modelo;

public class PasoReceta {
    private int numero;
    private String texto;
    private boolean tieneImagen;
    private String linkImagen;

    public PasoReceta(int numero, String texto, boolean tieneImagen) {
        this.numero = numero;
        this.texto = texto;
        this.tieneImagen = tieneImagen;
    }

    public PasoReceta() {
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public boolean isTieneImagen() {
        return tieneImagen;
    }

    public void setTieneImagen(boolean tieneImagen) {
        this.tieneImagen = tieneImagen;
    }

    public String getLinkImagen() {
        return linkImagen;
    }

    public void setLinkImagen(String linkImagen) {
        this.linkImagen = linkImagen;
    }
}

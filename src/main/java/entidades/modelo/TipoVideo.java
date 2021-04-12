package entidades.modelo;

public enum TipoVideo {
    YOUTUBE(1),
    VIMEO(2);

    private final int valor;

    TipoVideo(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return this.valor;
    }
}

package entidades.modelo;

public enum TipoUsuario {
    ADMIN(1),
    COOKER(2),
    CHEF(3);

    private final int tipo;

    TipoUsuario(int tipo) {
        this.tipo = tipo;
    }

    public int getTipo() {
        return this.tipo;
    }
}

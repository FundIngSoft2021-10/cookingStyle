package entidades.modelo;

import java.util.HashMap;
import java.util.Map;

public enum Dia {
    LUNES(1),
    MARTES(2),
    MIERCOLES(3),
    JUEVES(4),
    VIERNES(5),
    SABADO(6),
    DOMINGO(7);

    private int valor;

    Dia(int valor) {
        this.valor = valor;
    }

    private static Map<Integer, Dia> map = new HashMap<Integer, Dia>();
    static {
        for (Dia dia : Dia.values()) {
            map.put(dia.valor, dia);
        }
    }

    public static Dia valueOf(int valor) {
        return map.get(valor);
    }

    public int getValor() {
        return this.valor;
    }
}

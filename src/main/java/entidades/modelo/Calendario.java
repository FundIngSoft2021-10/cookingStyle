package entidades.modelo;

import java.util.ArrayList;
import java.util.List;

public class Calendario {
    private List<Bloque> bloques;

    public Calendario(List<Bloque> bloques) {
        this.bloques = bloques;
    }

    public Calendario() {
    }

    public List<Bloque> getBloques() {
        return bloques;
    }

    public void setBloques(List<Bloque> bloques) {
        this.bloques = bloques;
    }

    // Métodos

    public List<Bloque> getBloquesDisponibles() {
        List<Bloque> bloquesDisponibles = new ArrayList<>();

        for (Bloque bloque : this.bloques) {
            if (bloque.isDisponible()) {
                bloquesDisponibles.add(bloque);
            }
        }

        return bloquesDisponibles;
    }
}

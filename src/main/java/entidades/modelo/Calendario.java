package entidades.modelo;

import java.util.ArrayList;
import java.util.List;

public class Calendario {
    private List<Bloque> bloques;

    public Calendario(List<Bloque> bloques) {
        this.bloques = bloques;
    }

    public Calendario() {
        this.bloques = new ArrayList<>();
    }

    public List<Bloque> getBloques() {
        return bloques;
    }

    public void setBloques(List<Bloque> bloques) {
        this.bloques = bloques;
    }
}

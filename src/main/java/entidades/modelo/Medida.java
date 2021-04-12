package entidades.modelo;

public enum Medida {
    MILIGRAMO {
        @Override
        public String toString() {
            return "Miligramo";
        }

        public String abv() {
            return "mg";
        }
    },

    GRAMO {
        @Override
        public String toString() {
            return "Gramo";
        }

        public String abv() {
            return "gr";
        }
    },

    KILOGRAMO {
        @Override
        public String toString() {
            return "Kilogramo";
        }

        public String abv() {
            return "kg";
        }
    },

    MILILITRO {
        @Override
        public String toString() {
            return "Mililitro";
        }

        public String abv() {
            return "ml";
        }
    },

    LITRO {
        @Override
        public String toString() {
            return "Litro";
        }

        public String abv() {
            return "l";
        }
    },

    UNIDAD {
        @Override
        public String toString() {
            return "Unidad";
        }

        public String toStringPlural() {
            return this.toString() + "es";
        }

        public String abv() {
            return "und";
        }
    };

    public String toStringPlural() {
        return this.toString() + "s";
    }
}

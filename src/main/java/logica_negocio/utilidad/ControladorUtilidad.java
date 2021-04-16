package logica_negocio.utilidad;

import java.math.BigInteger;
import java.util.Random;

public class ControladorUtilidad implements IControladorUtilidad {
    public ControladorUtilidad() {
    }

    /**
     * @inheritDoc
     */
    @Override
    public BigInteger generarRandomBigInt(BigInteger min, BigInteger max) {
        BigInteger rango = max.subtract(min);
        Random rnd = new Random();

        BigInteger numero = new BigInteger(max.bitLength(), rnd);
        if (numero.compareTo(min) < 0)
            numero = numero.add(min);
        if (numero.compareTo(rango) >= 0)
            numero = numero.mod(rango).add(min);

        return numero;
    }
}

package logica_negocio.utilidad;

import java.math.BigInteger;

public interface IControladorUtilidad {
    /**
     * Genera un número BigInteger al azar, acotado en un rango
     * @param min el límite inferior del número al azar a generar
     * @param max el límite superior del número al azar a generar
     * @return el número BigInteger generado
     */
    public BigInteger generarRandomBigInt(BigInteger min, BigInteger max);
}

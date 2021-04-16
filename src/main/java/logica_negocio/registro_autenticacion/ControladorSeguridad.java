package logica_negocio.registro_autenticacion;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class ControladorSeguridad implements IControladorSeguridad {
    /**
     * @inheritDoc
     */
    @Override
    public String generarHash(String password, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        int iteraciones = 1000;
        char[] chars = password.toCharArray();

        PBEKeySpec spec = new PBEKeySpec(chars, salt, iteraciones, 64 * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = skf.generateSecret(spec).getEncoded();
        return toHex(hash);
    }

    /**
     * @inheritDoc
     */
    @Override
    public byte[] generarSalt() throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }

    /**
     * Convierte un número representado en bytes a hex
     * @param arreglo el arreglo de bytes a pasar a hex
     * @return el String en hex
     */
    @Override
    public String toHex(byte[] arreglo) {
        BigInteger bi = new BigInteger(1, arreglo);
        String hex = bi.toString(16);
        int longitudRelleno = (arreglo.length * 2) - hex.length();
        if (longitudRelleno > 0) {
            return String.format("%0" + longitudRelleno + "d", 0) + hex;
        } else {
            return hex;
        }
    }
}

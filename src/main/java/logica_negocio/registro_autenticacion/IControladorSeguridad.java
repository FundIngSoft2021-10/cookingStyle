package logica_negocio.registro_autenticacion;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public interface IControladorSeguridad {
    /**
     * Hashea una contraseña (con salt) mediante el algoritmo de encriptación PBKFD2, proporcionado por JavaX
     * @param password la contraseña a hashear
     * @param salt el salt de la contraseña
     * @return el hash generado
     * @throws NoSuchAlgorithmException ver también {@link javax.crypto.SecretKeyFactory#getInstance(String) getInstance}
     * @throws InvalidKeySpecException ver también {@link javax.crypto.SecretKeyFactory#getInstance(String) getInstance}
     */
    public String generarHash(String password, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException;

    /**
     * Genera una salt al azar de manera segura (mediante SHA1PRNG) de 16 bytes
     * @return el salt generado
     * @throws NoSuchAlgorithmException ver también {@link java.security.SecureRandom#getInstance(String) getInstance}
     */
    public byte[] generarSalt() throws NoSuchAlgorithmException;

    /**
     * Verifica si una contraseña + salt generan el mismo hash de comparación
     * @param password la contraseña a probar
     * @param salt el salt de la contraseña
     * @param hash el hash de comparación
     * @return true, las contraseñas coinciden; false, no coinciden
     */
    boolean validarPassword(String password, String salt, String hash) throws InvalidKeySpecException, NoSuchAlgorithmException;

    /**
     * Convierte un número representado en bytes a hex
     * @param arreglo el arreglo de bytes a pasar a hex
     * @return el número en hex
     */
    public String toHex(byte[] arreglo);

    /**
     * Convierte un número representado en hex a bytes
     * @param hex el número en hex
     * @return el número en bytes
     */
    public byte[] fromHex(String hex);
}

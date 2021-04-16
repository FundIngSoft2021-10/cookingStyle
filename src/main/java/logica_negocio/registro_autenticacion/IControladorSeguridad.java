package logica_negocio.registro_autenticacion;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public interface IControladorSeguridad {
    /**
     * Encripta una contraseña (con salt) mediante el algoritmo de encriptación PBKFD2, proporcionado por JavaX
     * @param password la contraseña a encriptar
     * @param salt el salt de la contraseña
     * @return el hash encriptado
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
     * Convierte un número representado en bytes a hex
     * @param arreglo el arreglo de bytes a pasar a hex
     * @return el String en hex
     */
    public String toHex(byte[] arreglo);
}

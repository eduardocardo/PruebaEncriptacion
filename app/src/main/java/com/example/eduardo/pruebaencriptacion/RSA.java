package com.example.eduardo.pruebaencriptacion;

import android.util.Base64;
import android.util.Log;

import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.Security;
import java.security.spec.RSAKeyGenParameterSpec;

import javax.crypto.Cipher;

/**
 * Created by Eduardo on 25/03/2016.
 */
public class RSA {

    static {
        Security.insertProviderAt(new org.spongycastle.jce.provider.BouncyCastleProvider(), 1);
    }
    //indica el tamaño maximo de la key para el sistema encriptacion RSA
    private static final int KEY_SIZE = 1024;

    /**
     * Metodo que genera un keypair con algoritmo de encriptacion RSA
     * @return un Keypair
     */
    public static KeyPair generate() {
        try {
            SecureRandom random = new SecureRandom();
            RSAKeyGenParameterSpec spec = new RSAKeyGenParameterSpec(KEY_SIZE, RSAKeyGenParameterSpec.F4);
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA", "SC");
            generator.initialize(spec, random);
            return generator.generateKeyPair();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo que se encarga de encriptar un texto pasado por parametro segun la clave pública también
     * pasado por parametro
     * @param publicKey es la clave que indicara como se encripta el texto
     * @param toBeCiphred es el texto a encriptar
     * @return un texto encriptado en formato String
     */
    public static String encrypt(Key publicKey, String toBeCiphred) {
        try {
            Cipher rsaCipher = Cipher.getInstance("RSA", "SC");
            rsaCipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] text = toBeCiphred.getBytes("UTF-8");
            String encodedText = Base64.encodeToString(rsaCipher.doFinal(text), Base64.DEFAULT);
            return encodedText ;
        } catch (Exception e) {
            Log.e("WARNING", "Error while encrypting data: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo que se encarga de desencriptar un texto pasado por parámetro según la clave privada pasada
     * también por parametro
     * @param privateKey es la clave que indicara como se desencripta el texto
     * @param encryptedText es el texto a desencriptar
     * @return un texto desecriptado en formato String
     */
    public static String decrypt(Key privateKey, String encryptedText) {
        String decodeText= "";
        try {
            Cipher rsaCipher = Cipher.getInstance("RSA", "SC");
            rsaCipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] encodedText = encryptedText.getBytes("UTF-8");
            decodeText=Base64.encodeToString(rsaCipher.doFinal(encodedText),Base64.DEFAULT);
            return decodeText;

        } catch (Exception e) {
            Log.e("WARNING", "Error while decrypting data: " + e.getMessage());
            throw new RuntimeException(e);
        }

    }
}

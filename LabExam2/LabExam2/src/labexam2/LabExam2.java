package labexam2;

import java.io.*;
import java.security.*;
import javax.crypto.Cipher;
 
public class LabExam2 {
    public static void main(String[] args) throws Exception{
        KeyStore keyStoreObject = KeyStore.getInstance("JKS");
        FileInputStream fileInputStreamObject = new FileInputStream("C:\\Users\\Admin\\Desktop\\2014A7PS0035\\SOCLab2.keystore");
        keyStoreObject.load(fileInputStreamObject, "abcdefgh12".toCharArray());
        
        // Get the certificate
        java.security.cert.Certificate certificateObject = keyStoreObject.getCertificate("LabExam2");
        System.out.println("Certificate = " + certificateObject.toString());
        
        // Get the public key from the certificate
        System.out.println("Public Key = " + certificateObject.getPublicKey());
        
        // Get the private key from the keystore
        Key privateKeyObject = (PrivateKey)keyStoreObject.getKey("LabExam2", "mnopqrst".toCharArray());
        System.out.println("Private Key = " + privateKeyObject.toString());       
       
        // Perform encryption using the public key
        String toBeEncrypted = "SOC Labs are interesting";
        Cipher cipherObject = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipherObject.init(Cipher.ENCRYPT_MODE, certificateObject.getPublicKey());
        byte[] encryptedMessageBytes = cipherObject.doFinal(toBeEncrypted.getBytes());
        String encryptedMessage = new String(encryptedMessageBytes, "UTF8");
        System.out.println("Encrypted message: "+encryptedMessage);
        
        
        // Perform decryption using the private key
        cipherObject.init(Cipher.DECRYPT_MODE, privateKeyObject);
        byte[] decryptedMessageBytes = cipherObject.doFinal(encryptedMessageBytes);
        String decryptedMessage = new String(decryptedMessageBytes, "UTF8");
        System.out.println("Decrypted message: "+decryptedMessage);
    }    
}

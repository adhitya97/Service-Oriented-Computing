package symmetrickeyencryptionmodule;

import javax.crypto.*;

public class SymmetricKeyEncryptionModule {
    public static void main(String[] args) throws Exception{ 
        KeyGenerator keyGeneratorObject = KeyGenerator.getInstance("DES");
        
        // Specify size of the key
        keyGeneratorObject.init(56);
        
        // Cipher object
        SecretKey secretKeyObject = keyGeneratorObject.generateKey();
        
        // ECB is a block cipher mode.
        // Keysize for the scheme is 56 bits.
        Cipher cipherObject = Cipher.getInstance("DES/ECB/PKCS5Padding");             
        
        String toBeEncrypted = "PotatoTomato";
        System.out.println("Plaintext message - "+toBeEncrypted);
        
        // Encrypt the string
        cipherObject.init(Cipher.ENCRYPT_MODE, secretKeyObject);  
        byte[] encryptedMessageBytes = cipherObject.doFinal(toBeEncrypted.getBytes("UTF8"));
        String encryptedMessage = new String(encryptedMessageBytes, "UTF8");
        
        System.out.println("Encrypted message - "+encryptedMessage);        
        
        // Decrypt the same string
        cipherObject.init(Cipher.DECRYPT_MODE, secretKeyObject);
        byte[] decryptedMessageBytes = cipherObject.doFinal(encryptedMessageBytes);
        String decryptedMessage = new String(decryptedMessageBytes, "UTF8");
        
        System.out.println("Decrypted message - "+decryptedMessage);
    }    
}

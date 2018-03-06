package publickeyencryptionmodule;

import java.security.*;
import javax.crypto.*;

public class PublicKeyEncryptionModule {
    public static void main(String[] args) throws Exception{
        KeyPairGenerator keyPairGeneratorObject = KeyPairGenerator.getInstance("RSA");
        
        KeyPair keyPairObject = keyPairGeneratorObject.genKeyPair();
        
        // ECB is a block cipher mode.
        // Keysize for the scheme is (1024, 2048) bits.
        Cipher cipherObject = Cipher.getInstance("RSA/ECB/PKCS1Padding");
                
        String toBeEncrypted = "PotatoTomato";
        System.out.println("Plaintext message - "+toBeEncrypted);
        
        // Encrypt the string using the public key
        cipherObject.init(Cipher.ENCRYPT_MODE, keyPairObject.getPublic());  
        byte[] encryptedMessageBytes = cipherObject.doFinal(toBeEncrypted.getBytes("UTF8"));
        String encryptedMessage = new String(encryptedMessageBytes, "UTF8");
        
        System.out.println("Encrypted message - "+encryptedMessage);        
        
        // Decrypt the same string using the private key
        cipherObject.init(Cipher.DECRYPT_MODE, keyPairObject.getPrivate());
        byte[] decryptedMessageBytes = cipherObject.doFinal(encryptedMessageBytes);
        String decryptedMessage = new String(decryptedMessageBytes, "UTF8");
        
        System.out.println("Decrypted message - "+decryptedMessage);
        
       
    }    
}

package digitalsigning;

import java.security.*;
import javax.crypto.*;


public class DigitalSigning{
    public static void main(String[] args) throws Exception{
        String messageToSend = "Potato Tomato 1232";
        System.out.println("Message: " + messageToSend);
        
        // Create a digest object
        MessageDigest digestObject = MessageDigest.getInstance("SHA");
        byte[] messageDigest = digestObject.digest(messageToSend.getBytes("UTF8"));
        
        System.out.println("Message digest (sender): " + new String(messageDigest, "UTF8"));
               
        // Perform digital signature of the digest
        KeyPairGenerator keyPairGeneratorObject = KeyPairGenerator.getInstance("RSA");
        
        KeyPair keyPairObject = keyPairGeneratorObject.generateKeyPair();
        
        Cipher cipherObject = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        
        // To perform digital signing, we have to make use the private key of the sender
        cipherObject.init(Cipher.ENCRYPT_MODE, keyPairObject.getPrivate());
        byte[] encryptedDigestBytes = cipherObject.doFinal(messageDigest);
        
        String encryptedMessage = new String(encryptedDigestBytes, "UTF8");
        System.out.println("Encrypted message digest: "+encryptedMessage);  
        
        
        // Now, assume that the receiver has received MESSAGE + DIGEST       
        
        // On the receiver's side, we have to decrypt the message received.
        cipherObject.init(Cipher.DECRYPT_MODE, keyPairObject.getPublic());
        byte[] decryptedDigestBytes = cipherObject.doFinal(encryptedDigestBytes);
        
        String decryptedMessage = new String(decryptedDigestBytes, "UTF8");        
        System.out.println("Message digest (receiver): "+decryptedMessage);
        
        // We calculate the digest using the message, on the receiver's side,
        // and check if the digest matches the decrypted digest.
        String messageReceived = "Potato Tomato 1232";
        MessageDigest digestObjectReceiver = MessageDigest.getInstance("SHA");
        byte[] messageDigestReceiver = digestObjectReceiver.digest(messageReceived.getBytes("UTF8"));
        
        System.out.println("Message digest calculated by the receiver: " + new String(messageDigestReceiver, "UTF8"));
        
        if (decryptedMessage.equals(new String(messageDigestReceiver, "UTF8"))){
            System.out.println("Message integrity is upheld");
        }
        else{
            System.out.println("Message has been tampered");
        }
       

    }    
}

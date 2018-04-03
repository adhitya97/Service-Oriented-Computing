package keystorehandler;

import java.io.*;
import java.security.*;
import javax.crypto.Cipher;

public class KeyStoreHandler {
    public static void main(String[] args) throws Exception{
        KeyStore keyStoreObject = KeyStore.getInstance("JKS");
        
        // Get the public key from the certificate
        FileInputStream fileInputStreamObject = new FileInputStream("C:\\Users\\Admin\\Desktop\\2014A7PS0035U\\Lab8\\KeyStore1.keystore");
        keyStoreObject.load(fileInputStreamObject, "123456".toCharArray());        
        java.security.cert.Certificate certificateObject = keyStoreObject.getCertificate("business");
        System.out.println("Public Key = " + certificateObject.getPublicKey());               
        
        // Get the private key from the keystore
        Key privateKeyObject = (PrivateKey)keyStoreObject.getKey("business", "123456".toCharArray());
        System.out.println("Private Key = " + privateKeyObject.toString());
        
        // Encrypt a message/file using the private key
        String inputFilePath = "C:\\Users\\Admin\\Desktop\\2014A7PS0035U\\Lab8\\FileToEncrypt.txt";
        FileReader fileReaderObject = new FileReader(inputFilePath);
        BufferedReader bufferedReaderObject = new BufferedReader(fileReaderObject);
        String inputMessage = "", inputLine = null;
        while ((inputLine = bufferedReaderObject.readLine()) != null){
            if (inputMessage != "")
                inputMessage += " "+ inputLine;
            else
                inputMessage += inputLine;
        }
        System.out.println("Message to be transferred: " + inputMessage);
        bufferedReaderObject.close(); 
        Cipher cipherObject = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipherObject.init(Cipher.ENCRYPT_MODE, privateKeyObject);
        byte[] encryptedMessageBytes = cipherObject.doFinal(inputMessage.getBytes());
        String encryptedMessage = new String(encryptedMessageBytes, "UTF8");
        System.out.println("Encrypted message: "+encryptedMessage);
        
        // Store the encrypted message into a file
        FileWriter fileWriterObject = new FileWriter("C:\\Users\\Admin\\Desktop\\2014A7PS0035U\\Lab8\\EncryptedFile.txt");
        BufferedWriter bufferedWriterObject = new BufferedWriter(fileWriterObject);
        bufferedWriterObject.write(encryptedMessage);
        bufferedWriterObject.close();
        
        
        // And then, read the encrypted file.
        FileReader encryptedFileReaderObject = new FileReader("C:\\Users\\Admin\\Desktop\\2014A7PS0035U\\Lab8\\EncryptedFile.txt");
        BufferedReader encryptedBufferedReaderObject = new BufferedReader(encryptedFileReaderObject);
        String encryptedInputMessage = ""; 
        inputLine = null;
        while ((inputLine = encryptedBufferedReaderObject.readLine()) != null){
//            if (encryptedInputMessage != "")
//                encryptedInputMessage += " "+ inputLine;
//            else
                encryptedInputMessage += inputLine;
        }
        System.out.println("Encrypted Message as read from file: " + encryptedInputMessage);
        encryptedBufferedReaderObject.close(); 
        
        // Decrypt the message using the public key
        cipherObject.init(Cipher.DECRYPT_MODE, certificateObject.getPublicKey());
        byte[] decryptedMessageBytes = cipherObject.doFinal(encryptedInputMessage.getBytes(), 0, inputMessage.length());
        String decryptedMessage = new String(decryptedMessageBytes, "UTF8");
        System.out.println("Decrypted message: "+decryptedMessage);
    }    
}

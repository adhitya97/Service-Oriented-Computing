package sslclient;

import java.io.*;
import java.net.*;
import java.security.*;
import javax.net.ssl.*;

public class SSLClient {
    public static void main(String[] args) throws Exception{
        int port = 8080;
        String host = "localhost";
        
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextInt();
        
        KeyStore clientKeyStore = KeyStore.getInstance("JKS");
        clientKeyStore.load(new FileInputStream("client.private"), "clientpw".toCharArray());

        KeyStore serverKeyStore = KeyStore.getInstance("JKS");
        serverKeyStore.load(new FileInputStream("server.public"), "public".toCharArray());

        KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
        kmf.init(clientKeyStore, "clientpw".toCharArray());

        TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
        tmf.init(serverKeyStore);
        
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), secureRandom);
        
        SSLSocketFactory sf = sslContext.getSocketFactory();
        SSLSocket socket = (SSLSocket)sf.createSocket( host, port );
        
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        
        BufferedReader stdIn = new BufferedReader(
                                   new InputStreamReader(System.in));
	String userInput;

        System.out.print ("input: ");
	while ((userInput = stdIn.readLine()) != null) {
	    out.println(userInput);
            
            if (userInput.equals("bye"))
                break;
	}

	out.close();
	in.close();
	stdIn.close();
	socket.close(); 
    }    
}

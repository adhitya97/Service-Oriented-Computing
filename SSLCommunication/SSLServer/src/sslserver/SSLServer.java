package sslserver;

import java.io.*;
import java.net.*;
import java.security.*;
import javax.net.ssl.*;

public class SSLServer {
    public static void main(String[] args) throws Exception{
        int port = 8080;
        String host = "localhost";

        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextInt();

        KeyStore serverKeyStore = KeyStore.getInstance("JKS");
        serverKeyStore.load(new FileInputStream("server.private"), "serverpw".toCharArray());

        KeyStore clientKeyStore = KeyStore.getInstance("JKS");
        clientKeyStore.load(new FileInputStream("client.public"), "public".toCharArray());

        KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
        kmf.init(serverKeyStore, "serverpw".toCharArray());

        TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
        tmf.init(clientKeyStore);

        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), secureRandom);

        SSLServerSocketFactory sf = sslContext.getServerSocketFactory();
        SSLServerSocket ss = (SSLServerSocket) sf.createServerSocket(port);
        ss.setNeedClientAuth(true);

        System.out.println( "Listening on port "+port+"..." );
        Socket socket = ss.accept();
        System.out.println( "Got connection from "+socket );
        
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        
        String inputLine;
        while ((inputLine = in.readLine()) != null) 
         { 
          System.out.println ("Server: " + inputLine); 
          out.println(inputLine); 
          
          if (inputLine.equals("bye"))
              break;
         } 

        out.close(); 
        in.close(); 
        socket.close();
        ss.close();
    }    
}

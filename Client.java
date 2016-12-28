import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.*;
import java.util.Scanner;
public class Client {
  public static void main(String[] bob){
      int port =4200;
      try {
         System.out.println("Connecting to " + "hamza baiga" + " on port " + port);
         Socket client = new Socket("192.168.8.101", port);
         Scanner _scan = new Scanner(System.in);
         System.out.println("Just connected to " + client.getRemoteSocketAddress());
         OutputStream outToServer = client.getOutputStream();
         DataOutputStream out = new DataOutputStream(outToServer);
         while(true){
            
            out.writeUTF(_scan.nextLine() + client.getLocalSocketAddress());
            InputStream inFromServer = client.getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);
            
            System.out.println("Server says " + in.readUTF());
            if(false){
              break;
              }
         }
         try {
         client.close();
           
         } catch (Exception e) {
            System.out.println(e);
         }
      }catch(IOException e) {
         e.printStackTrace();
      }

    }
}
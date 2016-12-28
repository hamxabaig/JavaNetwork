import java.net.*;
import java.util.Scanner;
import java.io.*;

public class Server extends Thread {
    private ServerSocket serverSocket;

    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(1000000);
    }

    public void run() {
        System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "...");
        Socket server = null;
        try {
            server = serverSocket.accept();
        } catch (Exception e) {
            System.out.println(e);
            System.exit(0);
        }

        System.out.println("Just connected to " + server.getRemoteSocketAddress());
        Scanner _s = new Scanner(System.in);
        while (true) {
            try {
                DataInputStream in = new DataInputStream(server.getInputStream());
                System.out.println(in.readUTF());
                DataOutputStream out = new DataOutputStream(server.getOutputStream());
                out.writeUTF(_s.nextLine() + server.getLocalSocketAddress());

            } catch (SocketTimeoutException s) {
                System.out.println("Socket timed out!");
                break;
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }

        }
        try {
            server.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        //  int port = Integer.parseInt(args[0]);
        try {
            Thread t = new Server(4200);
            t.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
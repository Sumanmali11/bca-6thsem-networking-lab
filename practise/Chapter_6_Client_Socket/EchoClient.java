package practise.Chapter_6_Client_Socket;

import java.net.*;
import java.io.*;

public class EchoClient {
    public static void main(String[] args) {
        String hostname = "localhost";
        if (args.length > 0) {
            hostname = args[0];
        }

        PrintWriter out = null;
        BufferedReader networkIn = null;
        try {
            Socket socket = new Socket(hostname, 80);
            networkIn = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
            BufferedReader userIn = new BufferedReader(
                new InputStreamReader(System.in));
            out = new PrintWriter(socket.getOutputStream());
            System.out.println("Connected to Echo Server");
            while (true) {
                String theLine = userIn.readLine();
                if (theLine.equals("."))
                    break;
                out.println(theLine);
                out.flush();
                System.out.println(networkIn.readLine());
            }
            socket.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (networkIn != null)
                    networkIn.close();
                if (out != null)
                    out.close();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
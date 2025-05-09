package practise.Chapter_7_Server_Socket;

import java.net.*;
import java.io.*;

public class DaytimeClient {
    public static void main(String[] args) {
        String hostname = "localhost";
        if (args.length > 0) {
            hostname = args[0];
        }

        PrintWriter out = null;
        BufferedReader networkIn = null;
        try {
            Socket socket = new Socket(hostname, 3000);
            networkIn = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());
            System.out.println("Connected to Echo Server");
            while (true) {
                String theLine = networkIn.readLine();
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
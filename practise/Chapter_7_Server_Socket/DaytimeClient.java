import java.net.*;
import java.io.*;

public class DaytimeClient {
    public static void main(String[] args) {
        String hostname = "localhost";
        if (args.length > 0) {
            hostname = args[0];
        }

        BufferedReader networkIn = null;
        try {
            Socket socket = new Socket(hostname, 3000);
            OutputStream out = socket.getOutputStream();
            Writer writer = new OutputStreamWriter(out, "UTF-8");
            writer = new BufferedWriter(writer);
            System.out.println("Connected to Echo Server");

            out.println(writer);
            out.flush();
            System.out.println(networkIn.readLine());
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
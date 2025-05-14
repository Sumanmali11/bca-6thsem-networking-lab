import java.net.*;
import java.io.*;

public class DaytimeClient {
    public static void main(String[] args) {
        String hostname = "localhost";
        if (args.length > 0) {
            hostname = args[0];
        }

        try (
                Socket socket = new Socket(hostname, 3000);
                BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader serverIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));) {
            String inputLine;
            System.out.println("Type messages to send to the server (type 'stop' to quit):");

            while ((inputLine = userInput.readLine()) != null) {
                out.println(inputLine); 

                if ("stop".equalsIgnoreCase(inputLine.trim())) {
                    break;
                }

                String response = serverIn.readLine();
                System.out.println("Server: " + response);
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }

    }
}
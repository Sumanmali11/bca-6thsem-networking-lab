import java.net.*;
import java.io.*;

public class DaytimeClient {
    public static void main(String[] args) {
        String hostname = "localhost";
        if (args.length > 0) {
            hostname = args[0];
        }

        try {
            Socket daytime = new Socket(hostname, 3000);
            System.out.println("Connection established");
            daytime.setSoTimeout(2000);
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(daytime.getInputStream())
            );
            System.out.println("Results : " + reader.readLine());

            daytime.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } 
    }
}
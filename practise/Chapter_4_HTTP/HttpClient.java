import java.net.*;
import java.io.*;

public class HttpClient{
    public static void main(String[] args) throws IOException{
        String host = "example.com";
        int port = 80;

        Socket socket = new Socket(host, port);
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        out.println("GET /index.html HTTP/1.0");
        out.println();
        out.flush();

        String line;
        while((line = reader.readLine())!= null){
            System.out.println(line);
        }
        reader.close();
        out.close();
    }
}
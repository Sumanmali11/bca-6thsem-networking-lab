package practise.Chapter_6_Client_Socket;
import java.io.*;
import java.net.*;

public class DictClass {
    public static final String SERVER = "dict.org";
    public static final int PORT = 2628;
    public static final int TIMEOUT = 15000;

    public static void main(String[] args) {
        Socket socket = null;
        try {
            socket = new Socket(SERVER, PORT);
            socket.setSoTimeout(TIMEOUT);
            OutputStream out = socket.getOutputStream();
            Writer writer = new OutputStreamWriter(out, "UTF-8");
            writer = new BufferedWriter(writer);
            InputStream in = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            for (String word : args) {
                define(word, writer, reader);
            }
            writer.write("quit\r\n");
            writer.flush();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
            }
        }
    }

    static void define(String word, Writer writer, BufferedReader reader)
            throws IOException, UnsupportedEncodingException {
        writer.write("DEFINE eng-lat " + word + "\r\n");
        writer.flush();

        for (String line = reader.readLine(); line != null; line = reader.readLine()) {
            if (line.startsWith("250 ")) {
                return;
            } else if (line.startsWith("552 ")) {
                System.out.println("No defination found for " + word);
                return;
            } else if (line.matches("\\d\\d\\d .*"))
                continue;
            else if (line.trim().equals("."))
                continue;
            else
                System.out.println(line);
        }
    }
}
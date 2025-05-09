package practise.Chapter_6_Client_Socket;

import java.io.*;
import java.net.*;

public class LowPortsScanner {
    public static void main(String[] args) {
        String host = "localhost";
        if (args.length > 0) {
            host = args[0];
        }
        for (int i = 1; i < 1024; i++) {
            try {
                Socket socket = new Socket(host, i);
                System.out.println("There is a server on port " + i + " of " + host);
            } catch (UnknownHostException e) {

            } catch (IOException e) {
            }
        }
    }
}

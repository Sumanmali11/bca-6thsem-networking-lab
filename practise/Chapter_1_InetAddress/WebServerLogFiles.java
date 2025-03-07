import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.InetAddress;

public class WebServerLogFiles {
    public static void main(String[] args) {
        String path = "./webserver.log";
        try (FileInputStream fin = new FileInputStream(path);
                Reader in = new InputStreamReader(fin);
                BufferedReader bin = new BufferedReader(in);) {
            for (String entry = bin.readLine(); entry != null; entry = bin.readLine()) {
                System.out.println("Inside the For");
                int index = entry.indexOf(' ');
                String ip = entry.substring(0, index);
                String theRest = entry.substring(index);

                try {
                    InetAddress address = InetAddress.getByName(ip);
                    System.out.println(address.getHostName() + theRest);

                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
            System.out.println("Finished reading the file");

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
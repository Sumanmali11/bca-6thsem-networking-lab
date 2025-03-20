import java.io.*;
import java.net.*;

public class readingData {
    public static void main(String[] args) {
        try {
            URL url = new URI("https://apptechnologies.co").toURL();
            System.out.println("******************************");
            InputStream inputStream = url.openStream();
            Reader reader = new InputStreamReader(inputStream);
            int counter;
            while((counter = reader.read()) != -1){
                System.out.print((char)counter);
            }
            System.out.println("******************************");
        } catch (URISyntaxException e) {
            System.err.println(e.getMessage());
        } catch (MalformedURLException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
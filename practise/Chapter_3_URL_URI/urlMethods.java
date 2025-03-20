import java.net.*;

public class urlMethods {
    public static void main(String[] args) {
        try {
            URL url = new URI("https://search.brave.com/search?q=sample+url&source=desktop#about").toURL();
            System.out.println("Protocol : " + url.getProtocol());
            System.out.println("Host : " + url.getHost());
            System.out.println("Port : " + url.getPort());
            System.out.println("File : " + url.getFile());
            System.out.println("Authority : " + url.getAuthority());
            System.out.println("Query : " + url.getQuery());
            System.out.println("DefaultPort : " + url.getDefaultPort());
            System.out.println("Ref : " + url.getRef());
            System.out.println("URI : " + url.toURI());
            System.out.println("String Conversion : " + url.toString());

        } catch (URISyntaxException e) {
            System.err.println(e.getMessage());
        } catch (MalformedURLException e) {
            System.err.println(e.getMessage());
        }
    }
}
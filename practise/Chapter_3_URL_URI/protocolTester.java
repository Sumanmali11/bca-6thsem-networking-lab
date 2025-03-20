import java.net.*;

public class protocolTester {
    public static void main(String[] args) {
        String host = "apptechnologies.co";
        String file = "/contact";
        String[] schemes = { "http", "https", "ftp", "telnet", "mailto",
                "file", "ldap", "gopher", "rmi", "jndi", "jar",
                "doc", "netdoc", "nfs", "verbatim", "finger", "daytime",
                "systemresource" };
        for (int i = 0; i < schemes.length; i++) {
            try {
                new URI(schemes[i], host, file).toURL();
                System.out.println(schemes[i] + " is supported!");
            } catch (URISyntaxException e) {
                System.err.println(e.getMessage());
            } catch (MalformedURLException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
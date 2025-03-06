import java.net.*;

public class NetworkAddressByName {
    public static void main(String[] args) {
        try {
            InetAddress address = InetAddress.getByName("www.facebook.com");
            System.out.println(address);
            System.out.println(address.getHostAddress());
            System.out.println(address.getHostName());
            System.out.println(address.getCanonicalHostName());
            System.out.println(address.getAddress());
        } catch (UnknownHostException e) {
            System.out.println("Error Caught: " + e.getMessage());
        }
    }
}
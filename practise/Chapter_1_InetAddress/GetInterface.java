import java.net.*;

public class GetInterface {
    public static void main(String[] args) {
        try {
            InetAddress address = InetAddress.getByName("localhost");
            NetworkInterface ni = NetworkInterface.getByInetAddress(address);
            if (ni == null) {
                System.out.println("No interface");
            } else {
                System.out.println(ni);
            }
            NetworkInterface nui = NetworkInterface.getByName("eth0");
            if (nui == null) {
                System.out.println("No interface: eth0");
            } else {
                System.out.println(nui);
            }
        } catch (SocketException e) {
            System.out.println("Error Caught: " + e.getMessage());
        } catch (UnknownHostException e) {
            System.out.println("Error Caught: " + e.getMessage());
        }
    }
}
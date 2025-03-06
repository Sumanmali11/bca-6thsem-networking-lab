import java.net.*;

class VersionChecker {
    public static void main(String[] args) {
        try {
            InetAddress address = InetAddress.getByName("www.apptechnologies.co");
            byte[] add = address.getAddress();
            if (add.length == 4)
                System.out.println("This is IP version 4");
            else
                System.out.println("This is version 6 IP");
        } catch (UnknownHostException e) {
            System.out.println("Error Caught: " + e.getMessage());
        }
    }
}

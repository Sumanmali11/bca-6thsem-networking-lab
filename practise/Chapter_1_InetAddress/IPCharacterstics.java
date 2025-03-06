import java.net.*;

public class IPCharacterstics {
    public static void main(String[] args) {
        try {
            InetAddress address = InetAddress.getByName("www.apptechnologies.co");
            if (address.isAnyLocalAddress()) {
                System.out.println(address + " is a isAnyLocalAddress");
            }
            if (address.isLoopbackAddress()) {
                System.out.println(address + " is a isLoopbackAddress");
            }
            if (address.isLinkLocalAddress()) {
                System.out.println(address + " is a isLinkLocalAddress");
            } else if (address.isSiteLocalAddress()) {
                System.out.println(address + " is a isSiteLocalAddress");
            } else {
                System.out.println(address + " is a global Addresss");
            }

            if (address.isMulticastAddress()) {
                if (address.isMCGlobal()) {
                    System.out.println(address + " is a isMCGlobal");
                } else if (address.isMCOrgLocal()) {
                    System.out.println(address + " is a isMCOrgLocal");
                } else if (address.isMCSiteLocal()) {
                    System.out.println(address + " is a isMCSiteLocal");
                } else if (address.isMCLinkLocal()) {
                    System.out.println(address + " is a isMCLinkLocal");
                } else if (address.isMCNodeLocal()) {
                    System.out.println(address + " is a isMCNodeLocal");
                } else {
                    System.out.println(address + " is a unknown Multicast");
                }
            } else {
                System.out.println(address + " is a unicast");

            }
        } catch (UnknownHostException e) {
            System.out.println("Error Caught: " + e.getMessage());
        }
    }
}

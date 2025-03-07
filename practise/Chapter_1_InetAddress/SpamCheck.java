import java.net.*;

public class SpamCheck {

    public static final String BLACKHOLE = "sbl.spamhaus.org/sbl";

    public static void main(String[] args) throws UnknownHostException {
        String arg = "zoho.com";
        // for (String arg : args) {
        System.out.println(arg);
        if (isSpammer(arg))
            System.out.println(arg + " is a known spammer.");
        else
            System.out.println(arg + " appears legitimate.");
        // }
    }

    private static boolean isSpammer(String arg) {
        try {
            InetAddress address = InetAddress.getByName(arg);
            byte[] quad = address.getAddress();
            String query = BLACKHOLE;
            System.out.println(quad);
            System.out.println(address.getHostAddress());
            System.out.println(query);

            // It Reverses the IP of the given address and then concat with the BLACKHOLE
            for (byte octet : quad) {
                int unsignedByte = octet < 0 ? octet + 256 : octet;
                query = unsignedByte + "." + query;
            }
            System.out.println(query);
            System.out.println(InetAddress.getByName(query));
            InetAddress.getByName(query);
            return true; // If resolved, it's a spammer
        } catch (UnknownHostException e) {
            return false; // Not found in blacklist, considered legitimate
        }
    }
}
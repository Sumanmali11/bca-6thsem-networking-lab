import java.net.MulticastSocket;
import java.net.InetAddress;
import java.net.DatagramPacket;

public class MulticastClient {
    public static void main(String[] args) throws Exception {
        MulticastSocket socket = new MulticastSocket(8888);
        InetAddress group = InetAddress.getByName("230.0.0.1");
        socket.joinGroup(group);
        byte[] buffer = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);
        System.out.println("Received: " + new String(packet.getData()));
        socket.leaveGroup(group);
        socket.close();
    }
}

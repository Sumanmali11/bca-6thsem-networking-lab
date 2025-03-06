import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class HelloServer extends UnicastRemoteObject implements HelloService {
    public HelloServer() throws RemoteException {}

    public String sayHello() throws RemoteException {
        return "Hello from RMI Server!";
    }

    public static void main(String[] args) throws Exception {
        Naming.rebind("HelloService", new HelloServer());
        System.out.println("Server is running...");
    }
}

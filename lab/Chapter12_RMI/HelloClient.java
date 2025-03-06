import java.rmi.Naming;

public class HelloClient {
    public static void main(String[] args) throws Exception {
        HelloService service = (HelloService) Naming.lookup("rmi://localhost/HelloService");
        System.out.println("Server says: " + service.sayHello());
    }
}

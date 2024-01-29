import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

class NetworkInterfaces {

    public static void main(String[] args) throws SocketException {
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        while(interfaces.hasMoreElements()){
            NetworkInterface networkInterface = interfaces.nextElement();
            Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
            System.out.println("Name: " + networkInterface.getName());
            System.out.println("Display Name: " + networkInterface.getDisplayName());
            while(inetAddresses.hasMoreElements()){
                System.out.println(inetAddresses.nextElement().getHostAddress());
            }
            System.out.println();
        }
    }
}
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.List;

/*
 * Server that listens on the Ipv6 link local address used
 * by the Bluetooth interface
 */
class Server {

    public static void main(String[] args) throws IOException {
                
        // Get name of the interface for Bluetooth
        List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
        String bluetoothInterface = "";
        for(int i = 0; i < interfaces.size(); i++){
            if(interfaces.get(i).getDisplayName().equals("Bluetooth Device (Personal Area Network)")){
                bluetoothInterface = interfaces.get(i).getName();
            }
        }
        NetworkInterface bluetooth;
        InetAddress address;
        try{
            bluetooth = NetworkInterface.getByName(bluetoothInterface); // bluetooth
            address = bluetooth.getInetAddresses().nextElement();
        } catch(Exception e){
            System.out.println("Bluetooth not found, please turn on");
            return;
        }
        System.out.println("Bluetooth interface: " + bluetoothInterface);

        ServerSocket socket = new ServerSocket();

        // Server port is configurable, if changed, should be updated in client as well
        int port = 60000;
        System.out.println("Listending on bluetooth link local address: " + address.getHostAddress());
        socket.bind(new InetSocketAddress(address, port));
        
        while(true){
            try {
                System.out.println("Waiting for connection..");
                Socket client = socket.accept();
                System.out.println("Client: " + client.getInetAddress());
                
                // Read message from client
                DataInputStream in = new DataInputStream((client.getInputStream()));
                String line;
                while((line = in.readUTF()) != null){
                    System.out.println(line);
                    System.out.flush();
                }
            } catch (EOFException e){
                // End of message from client
            }    
            System.out.println("Client disconnected");        
        }    
    }
}
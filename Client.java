
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.util.Collections;
import java.util.List;

class Client {

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

        String addr = address.getHostAddress();

        // Server runs at port 60000
        int port = 60000;
        Socket socket = new Socket(addr, port);
        System.out.println("Client socket: (" + socket.getLocalAddress() + ", " + socket.getLocalPort() + ")");
        System.out.println("Connected to server");
        
        // Sending a hello world message to the server
        DataOutputStream out = new DataOutputStream((socket.getOutputStream()));
        String line = "Hello World!";
        out.writeUTF(line);
        socket.close();
    }
}

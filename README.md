### Server and Client

Bluetooth in Windows is treated as a network interface, which is associated with an Ipv6 link local address.
This is the Java code for setting up a server at the Bluetooth interface's link local Ipv6 address and connecting to it from a client.

### Run

Run the server and client in different terminals
```
javac Server.java  
java Server
```

```
javac Client.java
java Client
```

### Note
1. For Windows OS only
2. The file ```NetworkInterfaces.java``` displays the list of network interfaces, their names, display names and IP addresses associated with them.
3. We assume the display name of Bluetooth is ```Bluetooth Device (Personal Area Network)``` on Windows OS (checked this on Windows 10 and 11), if not change the same (to what is displayed by NetworkInterfaces.java) in the code before run.
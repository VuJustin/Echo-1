package echo;

import java.net.Socket;

/*
To run this via console & run proxy handler & port and server name:
java echo.ProxyServer echo.ProxyHandler 5555 6666
*/

public class ProxyServer extends Server {
    protected Correspondent peer;
    String peerHost;
    int peerPort;

    public ProxyServer(int myPort, String service, int peerPort, String peerHost) {
        super(myPort,service);
        this.peerHost = peerHost;
        this.peerPort = peerPort;
    }

    public RequestHandler makeHandler(Socket s) {
        // make a proxy handler and call initPeer
        RequestHandler handler = super.makeHandler(s);
        ((ProxyHandler)handler).initPeer(peerHost, peerPort);
        return handler;
    }
    public static void main(String[] args) {
        int port = 5555;
        int peerPort = 6666;
        String peerHost = "localhost";
        String service = "echo.ProxyHandler";

        if (1 <= args.length) {
            service = args[0];
        }
        if (2 <= args.length) {
            peerPort = Integer.parseInt(args[1]);
        }
        if (3 <= args.length) {
            port = Integer.parseInt(args[2]);
        }
        if (4 <= args.length) {
            peerHost = args[3];
        }
        Server server = new ProxyServer(port, service, peerPort, peerHost);
        System.out.println("Proxy Server");
        System.out.println("Service: " + service);
        System.out.println("Peer Port: " + peerPort);
        System.out.println("Port: " + port);
        server.listen();
    }
}




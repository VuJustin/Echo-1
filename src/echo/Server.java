package echo;

import java.io.*;
import java.net.*;
/*
* To Run via console: java echo.Server
* To run via MathHandler: java echo.Server math.MathHandler
* */
public class Server {

    protected ServerSocket mySocket;
    protected int myPort;
    public static boolean DEBUG = true;
    protected Class<?> handlerType;

    public Server(int port, String handlerType) {
        try {
            myPort = port;
            mySocket = new ServerSocket(myPort);
            this.handlerType = (Class.forName(handlerType));
        } catch(Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        } // catch
    }


    public void listen() {
        while(true) {
            // accept a connection, mySocket.accept(), returns a brand-new socket to connect it to the client, Blocks until a request comes into the client
            //Socket s = mySocket.accept();
            Socket s;
            try {
                s = mySocket.accept();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            // make handler, calls the makeHandler
            RequestHandler slave = makeHandler(s);
            // start handler in its own thread, handlers are runnable, create the thread and start the thread
            Thread myThread = new Thread(slave);
            myThread.start();
        } // while
    }

    public RequestHandler makeHandler(Socket s) {
        // handler = a new instance of handlerType
        RequestHandler handler = null;
        try {
            // handler = (RequestHandler) handlerType.newInstance();
            handler = (RequestHandler) handlerType.newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        //    use: try { handlerType.getDeclaredConstructor().newInstance() } catch ...
        // set handler's socket to s
        handler.setSocket(s);
        // return handler
        return handler;
    }



    public static void main(String[] args) {
        int port = 5555;
        String service = "echo.RequestHandler";
        if (1 <= args.length) {
            service = args[0];
        }
        if (2 <= args.length) {
            port = Integer.parseInt(args[1]);
        }
        Server server = new Server(port, service);
        System.out.println("Server");
        System.out.println("Service: " + service);
        System.out.println("Port: " + port);
        server.listen();
    }
}

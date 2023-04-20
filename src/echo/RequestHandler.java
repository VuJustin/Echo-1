package echo;

import java.net.Socket;

public class RequestHandler extends Correspondent implements Runnable {
    protected boolean active; // response can set to false to terminate thread
    public RequestHandler(Socket s) {
        super(s);
        active = true;
    }
    public RequestHandler() {
        super();
        active = true;
    }
    // override in a subclass:
    protected String response(String msg) throws Exception {
        return "echo: " + msg;
    }
    // any housekeeping can be done by an override of this:
    protected void shutDown() {
        if (Server.DEBUG) System.out.println("handler shutting down");
    }
    public void run() {
        while(active) {
            try {
                // receive request, look at correspondant code, there is method
                String request = receive();
                System.out.println("Server recieved request "+ request);
                if(request.equals("quit")) {
                    shutDown();
                    break;
                }
                // send response
                send(response(request));
                System.out.println("Server sent response "+ response(request));
                // sleep, agents can do that (Ask?)
                Thread.sleep(5);
            } catch(Exception e) {
                send(e.getMessage() + "... ending session");
                break;
            }
        }
        // close
        close();
    }
}

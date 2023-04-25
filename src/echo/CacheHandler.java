package echo;

import java.net.Socket;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CacheHandler extends ProxyHandler {

    private static SaveTable cache = new SaveTable();

    public CacheHandler(Socket sock){
        super(sock);
    }

    public CacheHandler(){
        super();
    }

    protected String response(String msg) throws Exception {
        String response = "";
        // Searching for the keyword to see if made before
        response = cache.get(msg);
        // If made before
        if(response != null){
            System.out.println("Request has be made before");
            // no need to do anything else, response will already have value
        }
        // If not made before
        if(response == null){
            System.out.println("Request has not been made before, making request");
            // Send out the message to calculate it
            peer.send(msg);
            // Place into the cache to store this value to remember it
            cache.put(msg, peer.receive());
            response = cache.get(msg);
        }
        return response;
    }
}

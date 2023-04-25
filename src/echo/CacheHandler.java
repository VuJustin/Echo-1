package echo;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CacheHandler extends ProxyHandler {

    private static Cache cache = new Cache();

    private String search(String request) {
        // Locks the shared resource
        synchronized (cache) {
            return cache.get(request);
        }
    }
    private void update(String request, String response){
        synchronized (cache){
            cache.put(request,response);
        }
    }
}

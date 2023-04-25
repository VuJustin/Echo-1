package echo;
import java.util.*;
import java.net.*;
public class SecurityHandler extends ProxyHandler {
    private static Map<String, String> users = new HashMap<>();
    private Boolean login;
    public SecurityHandler(){
        super();
        login = false;
    }
    private String search(String request) {
        // Locks the shared resource
        synchronized (users) {
            return users.get(request);
        }
    }

    private void update(String request, String response){
        synchronized (users){
            users.put(request,response);
        }
    }

    protected String response (String request) throws Exception{
        String result = "";
        if(login){
            result = super.response(request);
        }
        else{
            String[] list = request.split("\\s+");
        }

        return result;
    }

}

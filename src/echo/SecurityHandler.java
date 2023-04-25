package echo;
import java.util.*;
import java.net.*;
public class SecurityHandler extends ProxyHandler {
    private static SaveTable users = new SaveTable();
    // To see if user logged in
    private Boolean login;
    // Constructor
    public SecurityHandler(){
        super();
        // Automatically set login status false
        login = false;
    }
    protected String response (String request) throws Exception{
        String result = "";
        // If login is currently false
        if(login == false){
            // Split up the request
            // list[0] = keyword (new, login)
            // list[1] = username
            // list[2] = password
            String[] list = request.split("\\s+");
            // If trying to create a new User
            if(list[0].equalsIgnoreCase("new")){
                // check to see if there is already an existing username
                String check = users.get(list[1]);
                // There is already a user with that username
                if(check != null){
                    result = "There is a user with that name already";
                }
                // There is not a user with that username
                else{
                    // update userbase with new user
                    users.put(list[1], list[2]);
                    result = "User created";
                }
            }
            // If logging in as a existing User
            else if(list[0].equalsIgnoreCase("login")){
                //Check to see if that user exists
                String check = users.get(list[1]);
                // If user does exist
                if(check != null){
                    // If the user password matches
                    if(check.equalsIgnoreCase(list[2])){
                        result = "Login Successful";
                        login = true;
                    }
                    else{
                        result = "Incorrect password";
                    }
                }
                // If user doesn't exist
                else {
                    result = "No known user by that username";
                }
            }
            // If it doesn't equal anything
            else{
                result = "Login Please";
            }
        }
        // If login is true
        else{
            //send message to peer & get result
            result = super.response(request);
        }
        return result;
    }

}

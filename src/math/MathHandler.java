package math;
import echo.*;
import java.net.*;
public class MathHandler extends RequestHandler{
    public MathHandler(Socket sock){
        super(sock);
    }
    public MathHandler(){
        super();
    }

    protected String response(String msg) throws Exception {
        String set[] = msg.split("\\s+");
        double result = 0;
        String response = "";
        if(set[0].equalsIgnoreCase("add")){
            for(int i = 1; i < set.length; i++){
                try{
                    result += Double.parseDouble(set[i]);
                } catch (NumberFormatException e) {
                    response = "Unknown variable: " + set[i];
                    return response;
                }
            }
            response = String.valueOf(result);
        } else if (set[0].equalsIgnoreCase("mul")) {
            for(int i = 1; i < set.length; i++){
                try{
                    if(i == 1)
                     result = Double.parseDouble(set[i]);
                    else
                        result *= Double.parseDouble(set[i]);
                } catch (NumberFormatException e){
                    response = "Unknown variable: " + set[i];
                    return response;
                }
            }
            response = String.valueOf(result);
        } else if (set[0].equalsIgnoreCase("sub")) {
            for(int i = 1; i < set.length; i++){
                try{
                    if(i == 1)
                        result = Double.parseDouble(set[i]);
                    else
                        result -= Double.parseDouble(set[i]);
                }catch (NumberFormatException e){
                    response = "Unknown variable: " + set[i];
                    return response;
                }
            }
            response = String.valueOf(result);
        } else if (set[0].equalsIgnoreCase("div")) {
            for(int i = 1; i < set.length; i++){
                try{
                    if(i == 1)
                        result = Double.parseDouble(set[i]);
                    else
                        result /= Double.parseDouble(set[i]);
                } catch (NumberFormatException e){
                    response = "Unknown variable: " + set[i];
                    return response;
                }
            }
            response = String.valueOf(result);
        } else if (set[0].equalsIgnoreCase("help")) {
            response = "Operations: [ add | mul | sub | div ], type quit to quit";
        } else {
            response = "Unknown Command: " + set[0];
            return response;
        }
        return response;
    }
}

package teamf.controller;

public class GlobalController {
    
    public static  String getCorrispondingErrorMessage(ServerError se){
        if(se == ServerError.WrongData){
            return "Incorrect username or password";
        }
        if(se == ServerError.ServerNotFound){
            return "Server not found, are you connected to internet?";
        }
        if(se == ServerError.OtherError){
            return "An error occured";
        }
        System.out.println(" in getcrooris " + se);
        return "";
    }
}

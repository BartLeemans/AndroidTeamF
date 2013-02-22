package teamf.controller;

import android.os.Message;
import android.widget.Toast;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import teamf.model.User;

import java.util.ArrayList;
import java.util.List;


public class ServerCaller {

    private User receivedUser;
    private List<Message> messagesList;
    private List<String> usernames;
    private static final String ipAddress = "10.0.2.2:8080";
    private RestTemplate restTemplate = new RestTemplate();
    
    private List<HttpMessageConverter<?>> messageConverters;

    private static ServerCaller instance = null;

    private ServerCaller(){
        messageConverters = new ArrayList<HttpMessageConverter<?>>();
        messageConverters.add(new FormHttpMessageConverter());
        messageConverters.add(new StringHttpMessageConverter());
        messageConverters.add(new MappingJacksonHttpMessageConverter());
        restTemplate.setMessageConverters(messageConverters); 
    }

    private static void createMessageService(){
        if(instance == null){
            instance = new ServerCaller();
        }
    }

    public static ServerCaller getInstance(){
        if(instance == null){
            createMessageService();
        }
        return instance;
    }

    public ServerError getAllUsernames() {
        try {

         usernames = restTemplate.getForObject("http://" + ipAddress + "ProjectTeamF-1.0/user/getUsers", List.class);

        } catch (ResourceAccessException rae) {
            return ServerError.ServerNotFound;
        } catch (HttpServerErrorException hsee) {
            return ServerError.WrongData;
        } catch (Exception e) {
            return ServerError.OtherError;
        }
        return ServerError.NoError;
    }

    public User getReceivedUser() {
           return receivedUser;
    }

    public ServerError login(String username, String password) {
           User user = new User();
           user.setUsername(username);
           user.setPassword(password);

           try {
               receivedUser = restTemplate.postForObject("http://" + ipAddress + "/ProjectTeamF-1.0/service/login", user, User.class);
           } catch (ResourceAccessException rae) {
               receivedUser = null;
               return ServerError.ServerNotFound;
           } catch (HttpServerErrorException hsee) {
               receivedUser = null;
               return ServerError.WrongData;
           } catch(RestClientException rce){
               receivedUser = null;
               return ServerError.WrongData;
           } catch (Exception e) {
               System.out.println("error " + e);
               receivedUser = null;
               return ServerError.OtherError;
           }
           return ServerError.NoError;
       }
}



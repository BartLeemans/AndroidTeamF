package teamf.controller;

import android.os.Message;
import org.springframework.http.*;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import teamf.controller.methods.*;
import teamf.model.Chat;
import teamf.model.Trip;
import teamf.model.User;

import java.util.*;
import java.util.concurrent.ExecutionException;


public class ServerCaller {
    private User receivedUser;
    private List<Chat> chatList;
    private List<String> usernames = new ArrayList<String>();

    String test = "";

    private User currentUser;



    private static final String ipAddress = "192.168.43.176:8080";
    private RestTemplate restTemplate = new RestTemplate();
    private List<HttpMessageConverter<?>> messageConverters;
    private List<Trip> trips;
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

   /* chat */

    public List<Chat> getChatList() {
        return chatList;
    }

    public void setChatList(List<Chat> chatList) {
        this.chatList = chatList;
    }

    public void setCurrentUser(User u){
        currentUser = u;
    }
    public User getCurrentUser(){
        return currentUser;
    }


    public ServerError addChat(String msg, int trip ) {
        String URL = "http://"+ipAddress+"/ProjectTeamF-1.0/android/add.json";

        MultiValueMap<String, String> mvm = new LinkedMultiValueMap<String, String>();
        mvm.add("msg", msg);
        mvm.add("trip", String.valueOf(trip));
        mvm.add("userid", String.valueOf(1));

        restTemplate.postForLocation(URL, mvm);
        return ServerError.NoError;
    }

    public static class ChatlistTest extends ArrayList<Chat>{ }

    public ServerError getChats(int tripid, int lastid) {
       /* chatList = new ArrayList<Chat>();
        chatList = restTemplate.getForObject("http://" + ipAddress + "/ProjectTeamF-1.0/chat/getChat.json?trip=1", ArrayList.class);*/

        String URL = "http://" + ipAddress + "/ProjectTeamF-1.0/chat/getChat.json?trip=" + tripid + "&lastId=" + lastid;

        getChatList gcl = new getChatList();
        gcl.execute(URL);
        try {
            chatList = gcl.get();
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (ExecutionException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return ServerError.NoError;
    }
    /* end chat */


    public void setReceivedUser(User receivedUser) {
        this.receivedUser = receivedUser;
    }

    public static ServerCaller getInstance(){
        if(instance == null){
            createMessageService();
        }
        return instance;
    }

    public ServerError getAllUsernames() {
        try {
            usernames = restTemplate.getForObject("http://" + ipAddress + "/ProjectTeamF-1.0/service/getUsernames.json", ArrayList.class);

        } catch (ResourceAccessException rae) {
            return ServerError.ServerNotFound;
        } catch (HttpServerErrorException hsee) {
            return ServerError.WrongData;
        } catch (Exception e) {
            return ServerError.OtherError;
        }
        return ServerError.NoError;
    }

    public List<String> getUsernames() {
        return usernames;
    }

    public User getReceivedUser() {
        return receivedUser;
    }


    public String getTest() {
        return test;
    }


    public ServerError login(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);


        try {
            String URL = "http://"+ipAddress+"/ProjectTeamF-1.0/service/login.json";
            Object[] params = new Object[]{URL,user};
            login log = new login();
            log.execute(params);
            receivedUser = log.get();
            String u = "";
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
    public ServerError listTrips(){
        try {
            String URL = "http://"+ipAddress+"/ProjectTeamF-1.0/service/getOpenTrips.json";

            getTripList gtl = new getTripList();
            gtl.execute(URL);
            trips =  gtl.get();


            String u = "";
            //new login().execute(params);
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
    public ServerError getTripsUser(User u) {
        try {
            String URL = "http://" + ipAddress + "/ProjectTeamF-1.0/service/getTripsUser.json";
            Object[] params = new Object[]{URL,u};
            getTripsUser log = new getTripsUser();
            trips = log.execute(params).get();

        } catch (ResourceAccessException rae) {
            test=rae.getMessage();
            return ServerError.ServerNotFound;
        } catch (HttpServerErrorException hsee) {
            test=hsee.getMessage();
            return ServerError.WrongData;
        } catch (Exception e) {
            test=e.getMessage();
            return ServerError.OtherError;
        }
        return ServerError.NoError;
    }

    public List<Trip> getTrips(){
        return trips;
    }


    public String pakTest(){
        return test;
    }
}



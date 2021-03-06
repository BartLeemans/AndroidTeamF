package teamf.controller;

import android.os.Message;
import com.google.android.gms.maps.model.LatLng;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import teamf.controller.methods.*;
import teamf.controller.methods.chat.chat;
import teamf.controller.methods.chat.getChatList;
import teamf.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class ServerCaller {

    private String test;
    private User currentUser;
    private List<Message> messagesList;
    private List<String> usernames = new ArrayList<String>();
    private List<Chat> chatList;

    public List<BroadcastMessage> getBroadcastList() {
        return broadcastList;
    }

    public void setBroadcastList(List<BroadcastMessage> broadcastList) {
        this.broadcastList = broadcastList;
    }

    private List<BroadcastMessage> broadcastList;

    //10.0.2.2
    //192.168.173.1
    //private static final String ipAddress = "10.0.2.2:8080";
    private static final String ipAddress = "192.168.43.176:8080";
    private RestTemplate restTemplate = new RestTemplate();

    private List<HttpMessageConverter<?>> messageConverters;

    private List<Trip> trips;
    private List<StopPlaats> stops;
    private List<String> vragen;

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
    public List<Chat> getChatList() {
        return chatList;
    }

    public void setChatList(List<Chat> chatList) {
        this.chatList = chatList;
    }


    public ServerError addChat(String msg, int trip ) {

        String URL = "http://" + ipAddress + "/ProjectTeamF-1.0/android/add.json";
        Object[] params = new Object[]{URL,msg,trip,restTemplate};
        chat c = new chat();
        c.execute(params);
        return ServerError.NoError;
    }

    public ServerError getChats(int tripid, int lastid) {
       /* chatList = new ArrayList<Chat>();
chatList = restTemplate.getForObject("http://" + ipAddress + "/ProjectTeamF-1.0/chat/getChat.json?trip=1", ArrayList.class);*/

        String URL = "http://" + ipAddress + "/ProjectTeamF-1.0/chat/getChat.json?trip=" + tripid + "&lastId=" + lastid;

        getChatList gcl = new getChatList();
        gcl.execute(URL);
        try {
            chatList = gcl.get();
        } catch (InterruptedException e) {
            e.printStackTrace(); //To change body of catch statement use File | Settings | File Templates.
        } catch (ExecutionException e) {
            e.printStackTrace(); //To change body of catch statement use File | Settings | File Templates.
        }

        return ServerError.NoError;
    }

    public ServerError getBroadcasts(int tripid) {
        String URL = "http://" + ipAddress + "/ProjectTeamF-1.0/broadcast/getMessages.json?trip=" + tripid;

        getBroadcastList gcl = new getBroadcastList();
        gcl.execute(URL);
        try {
            broadcastList = gcl.get();
        } catch (InterruptedException e) {
            e.printStackTrace(); //To change body of catch statement use File | Settings | File Templates.
        } catch (ExecutionException e) {
            e.printStackTrace(); //To change body of catch statement use File | Settings | File Templates.
        }
        return ServerError.NoError;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public void sendCurLoc(Double lat, Double lng,Integer userid,Integer tripid){
        String URL = "http://"+ipAddress+"/ProjectTeamF-1.0/service/updatePosition.json";
        Object[] params = new Object[]{URL,lng,lat,userid,tripid};
        sendCurLoc scl = new sendCurLoc();
        scl.execute(params);
    }

    public List<String> getLocOthers(Integer userid,Integer tripid){
        String URL = "http://"+ipAddress+"/ProjectTeamF-1.0/service/getPositions.json";
        Object[] params = new Object[]{URL,tripid,userid};
        getLocOthers glo = new getLocOthers();
        glo.execute(params);
        try {
            return glo.get();
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (ExecutionException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;
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

    public User getCurrentUser() {
        return currentUser;
    }

    public void setTest(Object o){
        test =  (String)o;
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
            currentUser = log.get();
            String u = "";
        } catch (ResourceAccessException rae) {
            currentUser = null;
            return ServerError.ServerNotFound;
        } catch (HttpServerErrorException hsee) {
            currentUser = null;
            return ServerError.WrongData;
        } catch(RestClientException rce){
            currentUser = null;
            return ServerError.WrongData;
        } catch (Exception e) {
            System.out.println("error " + e);
            currentUser = null;
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
            currentUser = null;
            return ServerError.ServerNotFound;
        } catch (HttpServerErrorException hsee) {
            currentUser = null;
            return ServerError.WrongData;
        } catch(RestClientException rce){
            currentUser = null;
            return ServerError.WrongData;
        } catch (Exception e) {
            System.out.println("error " + e);
            currentUser = null;
            return ServerError.OtherError;
        }

        return ServerError.NoError;
    }
    public ServerError getTripsUser(User u) {
        try {
            String URL = "http://" + ipAddress + "/ProjectTeamF-1.0/service/getTripsUser.json";
            Object[] params = new Object[]{URL,u};
            getTripsUser gtu = new getTripsUser();
            trips = gtu.execute(params).get();
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

    public ServerError getStopsTrip(Integer tripid) {
        try {
            String URL = "http://" + ipAddress + "/ProjectTeamF-1.0/service/getStopUser.json";
            Object[] params = new Object[]{URL,tripid};
            getStopTrip gst = new getStopTrip();
            stops = gst.execute(params).get();
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
    public ServerError getVraagStop(Integer stopid) {
        try {
            String URL = "http://" + ipAddress + "/ProjectTeamF-1.0/service/getVraagStop.json";
            Object[] params = new Object[]{URL,stopid};
            getVragenStop gvs = new getVragenStop();
            vragen = gvs.execute(params).get();
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

    public List<Trip> getOpenTrips(){
        return trips;
    }


    public String pakTest(){
        return test;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public List<StopPlaats> getStops(){
        return stops;
    }

    public List<String> getVragen(){
        return vragen;
    }
}



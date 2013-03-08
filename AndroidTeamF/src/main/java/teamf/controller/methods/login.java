package teamf.controller.methods;

import android.os.AsyncTask;
import com.google.gson.Gson;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import teamf.model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class login extends AsyncTask<Object[],Integer,User> {

    @Override
    protected User doInBackground(Object[]... params) {
        String message = "";

       /* try{
            List<MediaType> mediaTypes = new ArrayList<MediaType>();
            mediaTypes.add(MediaType.APPLICATION_JSON);
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(mediaTypes);
            Object o = params[0][1];
            User u = (User)o;
            HttpEntity<User> httpEntity = new HttpEntity<User>(u, headers);
            List<HttpMessageConverter<?>> messageConverters;
            messageConverters = new ArrayList<HttpMessageConverter<?>>();
            messageConverters.add(new FormHttpMessageConverter());
            messageConverters.add(new StringHttpMessageConverter());
            messageConverters.add(new MappingJacksonHttpMessageConverter());

            RestTemplate restTemplate = new RestTemplate();

            restTemplate.setMessageConverters(messageConverters);

            MultiValueMap<String,String> mvn = new LinkedMultiValueMap<String, String>();
            mvn.add("username","test");


            User user = restTemplate.postForObject(params[0][0].toString(),mvn,User.class);
            //  ResponseEntity<User> userEnt = restTemplate.exchange(params[0][0].toString(), HttpMethod.POST, httpEntity, User.class);
            //User user = userEnt.getBody();
            String s = user.getUsername();
            return user;

        }catch(Exception e){
            message = e.getMessage();
            return null;
        }   :*/
        String url = params[0][0].toString();
        MultiValueMap<String, String> mvm = new LinkedMultiValueMap<String, String>();
        mvm.add("uname", "test");
        mvm.add("pw", "test");
        RestTemplate restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        messageConverters.add(new FormHttpMessageConverter());
        messageConverters.add(new StringHttpMessageConverter());
        restTemplate.setMessageConverters(messageConverters);
        String nieuwe = restTemplate.postForObject(url, mvm, String.class);
        String gebruiker = null;
        try {
            JSONObject jsonObject = new JSONObject(nieuwe);
           gebruiker=  jsonObject.get("androidUser").toString();
        } catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }


        ObjectMapper mapper = new ObjectMapper();
        Gson gson = new Gson();

        User test = new User();
        test.setUsername("bla");
        test.setPassword("aze");
        test.setZipcode("test");
        test.setEmail("test");
        test.setDateOfBirth(new Date("10/10/10"));
        test.setFirstName("test");
        test.setLastName("test");
        test.setNumber(null);


        String bla = gson.toJson(test);
        User testUser = gson.fromJson(bla,User.class);

        User u =  gson.fromJson(gebruiker,User.class);
        String s = nieuwe;
        return null;
    }
}
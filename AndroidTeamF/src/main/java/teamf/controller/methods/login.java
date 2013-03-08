package teamf.controller.methods;

import android.os.AsyncTask;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import teamf.controller.ServerCaller;
import teamf.model.User;

import java.util.ArrayList;
import java.util.List;


public class login extends AsyncTask<Object[],Integer,User> {

    @Override
    protected User doInBackground(Object[]... params) {
        String message = "";

        try{
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
        }

    }
}
package teamf.controller.methods;

import android.os.AsyncTask;
import org.springframework.http.*;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import teamf.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Jeroen
 * Date: 27/02/13
 * Time: 21:08
 * To change this template use File | Settings | File Templates.
 */
public class getUserName extends AsyncTask<String,Integer,String> {

    @Override
    protected String doInBackground(String... strings) {
        String test ="";
        List<MediaType> mediaTypes = new ArrayList<MediaType>();
        mediaTypes.add(MediaType.APPLICATION_JSON);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(mediaTypes);
        HttpEntity<User> httpEntity = new HttpEntity<User>(null, headers);
        List<HttpMessageConverter<?>> messageConverters;
        messageConverters = new ArrayList<HttpMessageConverter<?>>();
        messageConverters.add(new FormHttpMessageConverter());
        messageConverters.add(new StringHttpMessageConverter());
        messageConverters.add(new MappingJacksonHttpMessageConverter());

        try{
        RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> users = restTemplate.exchange(strings[0], HttpMethod.GET, httpEntity, String.class);
            String userss = users.getBody();
            test=userss;


        }catch(Exception e) {
            test=e.getMessage();

        }
        return test;

    }

}

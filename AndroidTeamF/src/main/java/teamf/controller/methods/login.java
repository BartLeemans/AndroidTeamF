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

/**
 * Created with IntelliJ IDEA.
 * User: Jeroen
 * Date: 28/02/13
 * Time: 17:13
 * To change this template use File | Settings | File Templates.
 */
public class login extends AsyncTask<Object[],Integer,String> {

    @Override
    protected String doInBackground(Object[]... params) {

        String message = "";

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
        try{
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<User> userEnt = restTemplate.exchange(params[0][0].toString(), HttpMethod.POST, httpEntity, User.class);
            User user = userEnt.getBody();
        }catch(Exception e){
            message = e.getMessage();
        }

        return message;
    }
}

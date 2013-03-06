package teamf.controller.methods;

import android.os.AsyncTask;
import org.springframework.http.*;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import teamf.model.User;

import java.util.ArrayList;
import java.util.Arrays;
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

        Object o = params[0][1];
        User u = (User)o;
       try{
           HttpHeaders headers = new HttpHeaders();
               headers.setContentType(MediaType.APPLICATION_JSON);
           HttpEntity httpEntity = new HttpEntity(null, headers);
           List<HttpMessageConverter<?>> messageConverters;
           messageConverters = new ArrayList<HttpMessageConverter<?>>();
           messageConverters.add(new MappingJacksonHttpMessageConverter());

           RestTemplate restTemplate = new RestTemplate();
           restTemplate.setMessageConverters(messageConverters);
        ResponseEntity<User> response = restTemplate.exchange(params[0][0].toString(),HttpMethod.POST ,httpEntity,User.class);
        message = response.getBody().getUsername();
         return message;
        }catch(Exception e){
           message = e.getMessage();
           return message;
        }

    }
}

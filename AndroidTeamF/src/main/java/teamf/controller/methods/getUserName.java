package teamf.controller.methods;

import android.os.AsyncTask;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import teamf.controller.ServerCaller;
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


        try{
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
            final String url = strings[0];
            test = restTemplate.getForObject(url,String.class);
        }catch(Exception e) {
            test=e.getMessage();
        }
        return test;

    }

}

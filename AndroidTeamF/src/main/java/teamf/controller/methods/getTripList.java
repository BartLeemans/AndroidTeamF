package teamf.controller.methods;

import android.os.AsyncTask;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import teamf.model.Trip;
import teamf.model.User;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Jorne
 * Date: 9/03/13
 * Time: 15:12
 * To change this template use File | Settings | File Templates.
 */
public class getTripList extends AsyncTask<String,Integer,List<Trip>> {

    @Override
    protected List<Trip> doInBackground(String... params) {


        List<Trip> trips=new ArrayList<Trip>();
        String error;


        try{
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
            List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
            messageConverters.add(new FormHttpMessageConverter());
            messageConverters.add(new StringHttpMessageConverter());
            restTemplate.setMessageConverters(messageConverters);
            final String url = params[0];

            Type collectionType = new TypeToken<Trip[]>(){}.getType();

            Gson gson = new Gson();

            Trip[] u =  gson.fromJson(restTemplate.getForObject(url,String.class),collectionType);

            trips = Arrays.asList(u);
        }catch(Exception e) {
            error=e.getMessage();
        }
        return trips;
    }
}

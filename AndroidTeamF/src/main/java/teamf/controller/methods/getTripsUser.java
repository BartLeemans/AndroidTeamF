package teamf.controller.methods;

import android.os.AsyncTask;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import teamf.model.Trip;
import teamf.model.User;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Jeroen
 * Date: 5/03/13
 * Time: 13:37
 * To change this template use File | Settings | File Templates.
 */
public class getTripsUser extends AsyncTask<Object[],Integer,List<Trip>> {
    @Override
    protected List<Trip> doInBackground(Object[]... params) {

        String message="";

        List<Trip> trips = new ArrayList<Trip>();

        try {

            String url = params[0][0].toString();
        User u = (User)params[0][1];
        MultiValueMap<String, String> mvm = new LinkedMultiValueMap<String, String>();

        mvm.add("userid",1+"");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        messageConverters.add(new FormHttpMessageConverter());
        messageConverters.add(new StringHttpMessageConverter());
        restTemplate.setMessageConverters(messageConverters);

            Type collectionType = new TypeToken<Trip[]>(){}.getType();
            Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new JsonDateDeserializer()).create();


            JSONObject json = new JSONObject(restTemplate.postForObject(url,mvm,String.class));

            Trip[] tripsArray =  gson.fromJson(json.get("tripList").toString(),collectionType);
            trips = Arrays.asList(tripsArray);
        } catch (Exception e) {
            message=e.getMessage();
        }

        return trips;

    }
}

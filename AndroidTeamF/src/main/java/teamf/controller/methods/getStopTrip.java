package teamf.controller.methods;

import android.os.AsyncTask;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.json.JSONObject;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import teamf.model.StopPlaats;
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
 * Date: 14/03/13
 * Time: 17:55
 * To change this template use File | Settings | File Templates.
 */
public class getStopTrip extends AsyncTask<Object[], Integer, List<StopPlaats>> {
    @Override
    protected List<StopPlaats> doInBackground(Object[]... params) {
        List<StopPlaats> stops = new ArrayList<StopPlaats>();

        try {
            String url = params[0][0].toString();
            String tripid = params[0][1].toString();
            MultiValueMap<String, String> mvm = new LinkedMultiValueMap<String, String>();
            mvm.add("tripid",tripid);
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
            List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
            messageConverters.add(new FormHttpMessageConverter());
            messageConverters.add(new StringHttpMessageConverter());
            restTemplate.setMessageConverters(messageConverters);

            Type collectionType = new TypeToken<StopPlaats[]>(){}.getType();
            Gson gson = new Gson();


            JSONObject json = new JSONObject(restTemplate.postForObject(url,mvm,String.class));

            StopPlaats[] stopsArray =  gson.fromJson(json.get("stopPlaatsList").toString(),collectionType);
            stops = Arrays.asList(stopsArray);
        } catch (Exception e) {
            String message=e.getMessage();
        }

        return stops;
    }
}

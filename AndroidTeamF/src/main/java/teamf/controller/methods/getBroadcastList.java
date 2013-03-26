package teamf.controller.methods;

import android.os.AsyncTask;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import teamf.model.BroadcastMessage;
import teamf.model.Chat;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Jerre
 * Date: 26/03/13
 * Time: 11:57
 * To change this template use File | Settings | File Templates.
 */
public class getBroadcastList extends AsyncTask<String, Integer, List<BroadcastMessage>> {

    @Override
    protected List<BroadcastMessage> doInBackground(String... params) {
        List<BroadcastMessage> msgs = new ArrayList<BroadcastMessage>();
        String error;

        try {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
            List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
            messageConverters.add(new FormHttpMessageConverter());
            messageConverters.add(new StringHttpMessageConverter());
            restTemplate.setMessageConverters(messageConverters);
            final String url = params[0];

            Type collectionType = new TypeToken<BroadcastMessage[]>() {}.getType();
            //Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new JsonDateDeserializer()).create();
             Gson gson = new Gson();
            BroadcastMessage[] u = gson.fromJson(restTemplate.getForObject(url, String.class), collectionType);

            msgs = Arrays.asList(u);
        } catch (Exception e) {
            error = e.getMessage();
        }
        return msgs;
    }
}

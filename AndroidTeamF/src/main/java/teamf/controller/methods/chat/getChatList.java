package teamf.controller.methods.chat;

import android.os.AsyncTask;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import teamf.controller.methods.JsonDateDeserializer;
import teamf.model.Chat;
import teamf.model.Trip;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Jerre
 * Date: 12/03/13
 * Time: 23:50
 * To change this template use File | Settings | File Templates.
 */
public class getChatList extends AsyncTask<String, Integer, List<Chat>> {
    @Override
    protected List<Chat> doInBackground(String... params) {


        List<Chat> chats = new ArrayList<Chat>();
        String error;

        try {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
            List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
            messageConverters.add(new FormHttpMessageConverter());
            messageConverters.add(new StringHttpMessageConverter());
            restTemplate.setMessageConverters(messageConverters);
            final String url = params[0];

            Type collectionType = new TypeToken<Chat[]>() {}.getType();
            Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new JsonDateDeserializer()).create();
            Chat[] u = gson.fromJson(restTemplate.getForObject(url, String.class), collectionType);

            chats = Arrays.asList(u);
        } catch (Exception e) {
            error = e.getMessage();
        }
        return chats;
    }
}

package teamf.controller.methods;

import android.os.AsyncTask;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import teamf.model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class login extends AsyncTask<Object[],Integer,User> {

    @Override
    protected User doInBackground(Object[]... params) {


        User paramUser = (User) params[0][1];
        String url = params[0][0].toString();
        MultiValueMap<String, String> mvm = new LinkedMultiValueMap<String, String>();
        mvm.add("username", paramUser.getUsername());
        mvm.add("password", paramUser.getPassword());
        RestTemplate restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        messageConverters.add(new FormHttpMessageConverter());
        messageConverters.add(new StringHttpMessageConverter());
        restTemplate.setMessageConverters(messageConverters);
        String nieuwe = restTemplate.postForObject(url, mvm, String.class);

        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new JsonDateDeserializer()).create();

        User u =  gson.fromJson(nieuwe,User.class);
        return u;
    }
}
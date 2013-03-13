package teamf.controller.methods;

import android.os.AsyncTask;
import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import teamf.model.User;

import java.util.ArrayList;
import java.util.List;


public class login extends AsyncTask<Object[],Integer,User> {

    @Override
    protected User doInBackground(Object[]... params) {
        String message = "";
        User u=new User();

        String url = params[0][0].toString();
        User uu = (User)params[0][1];
        MultiValueMap<String, String> mvm = new LinkedMultiValueMap<String, String>();
        mvm.add("uname", uu.getUsername());
        mvm.add("pw", uu.getPassword());
        RestTemplate restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        messageConverters.add(new FormHttpMessageConverter());
        messageConverters.add(new StringHttpMessageConverter());
        restTemplate.setMessageConverters(messageConverters);

        String gebruiker = null;
        try {
            Gson gson = new Gson();
            JSONObject json = new JSONObject(restTemplate.postForObject(url,mvm,String.class));

            u =  gson.fromJson(json.get("user").toString(),User.class);

        } catch (Exception e) {
            message = e.getMessage();
        }


        return u;
    }
}
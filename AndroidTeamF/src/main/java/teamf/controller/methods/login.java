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


        String url = params[0][0].toString();
        MultiValueMap<String, String> mvm = new LinkedMultiValueMap<String, String>();
        mvm.add("uname", "test");
        mvm.add("pw", "test");
        RestTemplate restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        messageConverters.add(new FormHttpMessageConverter());
        messageConverters.add(new StringHttpMessageConverter());
        restTemplate.setMessageConverters(messageConverters);
        String nieuwe = restTemplate.postForObject(url, mvm, String.class);
        String gebruiker = null;
        try {
            JSONObject jsonObject = new JSONObject(nieuwe);
          // gebruiker=  jsonObject.get("androidUser").toString();
           gebruiker=  jsonObject.get("user").toString();
        } catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }


        Gson gson = new Gson();

        /*User test = new User();
        test.setUsername("bla");
        test.setPassword("aze");
        test.setZipcode("test");
        test.setEmail("test");
        test.setDateOfBirth(new Date("10/10/10"));
        test.setFirstName("test");
        test.setLastName("test");
        test.setNumber(null); */


       // String bla = gson.toJson(test);
       // User testUser = gson.fromJson(bla,User.class);

        User u =  gson.fromJson(gebruiker,User.class);
        String s = nieuwe;
        return u;
    }
}
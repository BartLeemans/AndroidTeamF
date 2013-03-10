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
import teamf.model.Trip;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Jorne
 * Date: 9/03/13
 * Time: 15:12
 * To change this template use File | Settings | File Templates.
 */
public class getTripList extends AsyncTask<Object[],Integer,List<Trip>> {

    @Override
    protected List<Trip> doInBackground(Object[]... params) {


        String url = params[0][0].toString();
        MultiValueMap<String, String> mvm = new LinkedMultiValueMap<String, String>();
        mvm.add("uname", "test");
        mvm.add("pw", "test");
        RestTemplate restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        messageConverters.add(new FormHttpMessageConverter());
        messageConverters.add(new StringHttpMessageConverter());
        restTemplate.setMessageConverters(messageConverters);
        String jsonFromWebserver = restTemplate.postForObject(url, mvm, String.class);


        String triplijst = null;
        try {
            JSONObject jsonObject = new JSONObject(jsonFromWebserver);
            // gebruiker=  jsonObject.get("androidUser").toString();
            triplijst=  jsonObject.get("tripList").toString();
        } catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        Gson gson = new Gson();
       // Type listType = new TypeToken<List<Trip>>(){}.getType();

       // List<Trip> trips =  gson.fromJson(triplijst,listType);

        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}

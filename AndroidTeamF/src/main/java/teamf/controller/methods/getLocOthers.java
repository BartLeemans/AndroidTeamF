package teamf.controller.methods;

import android.os.AsyncTask;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.JSONObject;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import teamf.model.Trip;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Jeroen
 * Date: 25/03/13
 * Time: 13:54
 * To change this template use File | Settings | File Templates.
 */
public class getLocOthers  extends AsyncTask<Object[],Integer,List<String>> {
    @Override
    protected List<String> doInBackground(Object[]... objects) {




        String url = (String)objects[0][0];
        Integer tripid = (Integer)objects[0][1];
        Integer userid = (Integer)objects[0][2];

        MultiValueMap<String, String> mvm = new LinkedMultiValueMap<String, String>();

        mvm.add("userid",userid+"");
        mvm.add("tripid",tripid+"");

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        messageConverters.add(new FormHttpMessageConverter());
        messageConverters.add(new StringHttpMessageConverter());
        restTemplate.setMessageConverters(messageConverters);


        Type collectionType = new TypeToken<String[]>() {}.getType();
        Gson gson = new Gson();
        String[] u = gson.fromJson(restTemplate.postForObject(url,mvm, String.class), collectionType);
        return Arrays.asList(u);


    }
}

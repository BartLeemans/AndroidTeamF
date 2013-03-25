package teamf.controller.methods;

import android.os.AsyncTask;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Jeroen
 * Date: 25/03/13
 * Time: 12:34
 * To change this template use File | Settings | File Templates.
 */
public class sendCurLoc extends AsyncTask<Object[],Integer,Boolean> {
    @Override
    protected Boolean doInBackground(Object[]... objects) {
        try {

            String url = (String)objects[0][0];
            Double lng = (Double)objects[0][1];
            Double lat = (Double)objects[0][2];
            Integer userid = (Integer)objects[0][3];
            Integer tripid = (Integer)objects[0][4];

            MultiValueMap<String, String> mvm = new LinkedMultiValueMap<String, String>();

            mvm.add("lng",lng+"");
            mvm.add("lat",lat+"");
            mvm.add("userid",userid+"");
            mvm.add("tripid",tripid+"");

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
            List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
            messageConverters.add(new FormHttpMessageConverter());
            messageConverters.add(new StringHttpMessageConverter());
            restTemplate.setMessageConverters(messageConverters);

           restTemplate.postForObject(url,mvm,String.class);

    } catch (Exception e) {
           return false;
        }
        return true;
    }
}
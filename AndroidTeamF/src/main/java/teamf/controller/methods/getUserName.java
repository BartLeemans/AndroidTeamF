package teamf.controller.methods;

import android.os.AsyncTask;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import teamf.model.Trip;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Jeroen
 * Date: 27/02/13
 * Time: 21:08
 * To change this template use File | Settings | File Templates.
 */
public class getUserName extends AsyncTask<String,Integer,List<Trip>> {

    @Override
    protected List<Trip> doInBackground(String... strings) {
        List<Trip> trips=new ArrayList<Trip>();
        String error;


        try{
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
            final String url = strings[0];
            trips = restTemplate.getForObject(url,List.class);
        }catch(Exception e) {
            error=e.getMessage();
        }
        return trips;

    }

}

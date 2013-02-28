package teamf.controller.methods;

import android.os.AsyncTask;
import org.springframework.web.client.RestTemplate;
import teamf.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Jeroen
 * Date: 27/02/13
 * Time: 21:08
 * To change this template use File | Settings | File Templates.
 */
public class getUserName extends AsyncTask<String,Integer,String> {

    @Override
    protected String doInBackground(String... strings) {
        String test ="";

        try{
        RestTemplate restTemplate = new RestTemplate();
        ArrayList<User> testje = restTemplate.getForObject(strings[0], ArrayList.class);
            Map zooi = (Map)testje.get(0);
            User u = new User();

          List troep = new ArrayList<Object>(zooi.values());

           test = troep.get(1).toString();
        }catch(Exception e) {
            test=e.getMessage();

        }
        return test;

    }

}

package teamf.controller.methods.chat;

import android.os.AsyncTask;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import teamf.model.Chat;

/**
 * Created with IntelliJ IDEA.
 * User: Jerre
 * Date: 14/03/13
 * Time: 14:35
 * To change this template use File | Settings | File Templates.
 */
public class chat extends AsyncTask<Object[],Integer, Chat > {
    @Override
    protected Chat doInBackground(Object[]... params) {

        String URL = params[0][0].toString();
        String msg = params[0][1].toString();
        String trip = params[0][2].toString();
        RestTemplate r = (RestTemplate) params[0][3];

        MultiValueMap<String, String> mvm = new LinkedMultiValueMap<String, String>();
        mvm.add("msg", msg);
        mvm.add("trip", String.valueOf(trip));
        mvm.add("userid", String.valueOf(1));

        r.postForLocation(URL, mvm);
        return null;
    }
}

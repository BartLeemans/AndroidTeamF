package teamf.controller.methods;

import android.os.AsyncTask;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import teamf.controller.ServerCaller;
import teamf.model.User;


public class login extends AsyncTask<Object[], Void, User> {
    public ServerCaller sc = ServerCaller.getInstance();
    public User body;

    @Override
    protected User doInBackground(Object[]... params) {
        String message = "";
        try {
            HttpHeaders requestHeaders = new HttpHeaders();

            requestHeaders.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<User> _entity = new HttpEntity<User>((User)params[0][1], requestHeaders);
            RestTemplate templ = new RestTemplate();
            templ.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
            templ.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
            ResponseEntity<User> _response = templ.postForEntity(params[0][0].toString(), _entity, User.class); //null here in order there wasn't http converter errors because response type String and [text/html] for JSON are not compatible;
            body = _response.getBody();
            return null;

        } catch (Exception e) {
            message = e.getMessage();
            return null;
        }
    }

    @Override
    protected void onPostExecute(User aVoid) {
        super.onPostExecute(aVoid);
        sc.setReceivedUser(body);
    }
}
package teamf.view;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import com.project.TeamFAndroid.R;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import teamf.controller.ServerCaller;
import teamf.model.User;

import java.io.IOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Jeroen
 * Date: 14/02/13
 * Time: 15:14
 * To change this template use File | Settings | File Templates.
 */
public class Menu extends Activity {

    private List<HttpMessageConverter<?>> messageConverters;
    private RestTemplate restTemplate = new RestTemplate();
    private static final String ipAddress = "10.0.2.2";
    private List<User> usernames;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        TextView username = (TextView)findViewById(R.id.usernametekst);
        //test
        String test="";

        try
        {

            usernames = restTemplate.getForObject("http://" + ipAddress + "ProjectTeamF-1.0/user/getUsers", List.class);
            User u = usernames.get(0);
            Toast.makeText(getApplicationContext(),"lelele",Toast.LENGTH_LONG).show();

        }catch(Exception e){
            e.printStackTrace();
        }
        Toast.makeText(getApplicationContext(), test, Toast.LENGTH_LONG).show();


    }}

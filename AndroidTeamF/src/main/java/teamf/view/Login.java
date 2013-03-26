package teamf.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.project.TeamFAndroid.R;
import teamf.controller.ServerCaller;
import teamf.controller.ServerError;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Login extends Activity {
    private ServerCaller serverCaller;
    String userName, passWord;

    EditText username, password;
    Button login,main;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        TextView txt = (TextView) findViewById(R.id.login_title);
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/headliner45.ttf");
        txt.setTypeface(font);

        instantiateElements();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();    //To change body of overridden methods use File | Settings | File Templates.
    }

    private void instantiateElements() {
        login = (Button) findViewById(R.id.login);
        main = (Button) findViewById((R.id.main));

        serverCaller = ServerCaller.getInstance();

        main.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent mainPage = new Intent(Login.this,Main.class);
                startActivity(mainPage);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                username = (EditText) findViewById(R.id.username);
                password = (EditText) findViewById(R.id.password);


                ServerError se = serverCaller.login(username.getText().toString(),password.getText().toString());
                if(se == ServerError.NoError){


                    Intent intent = new Intent(Login.this, Trips.class);
                    startActivity(intent);

                }else{
                    Toast.makeText(getApplicationContext(), "Error logging in", Toast.LENGTH_SHORT).show();
                }
                //se = serverCaller.listTrips();
                //List<Trip> openTrips = new ArrayList<Trip>(serverCaller.getOpenTrips());
                //Toast.makeText(getApplicationContext(), serverCaller.getReceivedUser().getUsername(), Toast.LENGTH_SHORT).show();
            }});

    }
}

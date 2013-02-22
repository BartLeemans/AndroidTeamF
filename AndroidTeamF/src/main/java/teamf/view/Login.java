package teamf.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.project.TeamFAndroid.R;
import teamf.controller.GlobalController;
import teamf.controller.ServerCaller;
import teamf.controller.ServerError;

public class Login extends Activity {
    private ServerCaller serverCaller;
    String userName, passWord;

    EditText username, password;
    Button login;

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
        serverCaller = ServerCaller.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                username = (EditText) findViewById(R.id.username);
                password = (EditText) findViewById(R.id.password);

                if (username.getText().toString().length() == 0 && password.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please fill in your username and password", Toast.LENGTH_SHORT).show();
                } else if (username.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Fill in your username", Toast.LENGTH_SHORT).show();
                } else if (password.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Fill in your password", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), username.getText(), Toast.LENGTH_SHORT).show();
                    ServerError se = serverCaller.login(username.getText().toString(), password.getText().toString());

                    if (serverCaller.getReceivedUser().getUsername().length() > 0 && se == ServerError.NoError) {
                        Intent intent = new Intent(Login.this, Menu.class);
                        startActivity(intent);
                    } else if (serverCaller.getReceivedUser().getUsername().length() == 0) {
                        Toast.makeText(getApplicationContext(), "Your login credentials were wrong", Toast.LENGTH_SHORT).show();
                    } else {
                        String message = GlobalController.getCorrispondingErrorMessage(se);
                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}

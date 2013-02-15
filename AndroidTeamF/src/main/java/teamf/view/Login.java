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
import teamf.controller.ServerCaller;

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

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);

        login.setOnClickListener(loginListener);

    }

    private void instantiateElements() {
        login = (Button) findViewById(R.id.login);
        serverCaller = ServerCaller.getInstance();
    }

    private View.OnClickListener loginListener = new View.OnClickListener() {
        public void onClick(View v) {

            if (username.getText().toString().equals("Jerre") && password.getText().toString().equals("Dierckx")) {
                Toast.makeText(getApplicationContext(), "Login Successfully !!!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), Menu.class);
                EditText editText = (EditText) findViewById(R.id.username);
                String message = editText.getText().toString();
                startActivity(intent);
            } else
                Toast.makeText(getApplicationContext(), "Login Not Successful !!!", Toast.LENGTH_LONG).show();
        }
    };
}

package teamf.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import com.project.TeamFAndroid.R;
import teamf.controller.ServerCaller;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Jerre
 * Date: 12/03/13
 * Time: 14:37
 * To change this template use File | Settings | File Templates.
 */
public class Chat extends Activity {
    private ServerCaller se = ServerCaller.getInstance();
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat);


        Button btnChat = (Button) findViewById(R.id.btnChat);
        Button btnUpdate = (Button) findViewById(R.id.btnUpdate);

        //ScrollView scrollview = (ScrollView) findViewById(R.id.scrollviewChat);


        btnChat.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                EditText input = (EditText) findViewById(R.id.inputChat);
                se.addChat(input.getText().toString(), 1);
                input.setText("");
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            TextView tv = (TextView) findViewById(R.id.chatTextview);

            public void onClick(View view) {
                           String text = "";
                se.getChats(1);

                List<teamf.model.Chat> cl = se.getChatList();

                for(teamf.model.Chat c : cl) {
                  text += c.getUser().getUsername() + ": " + c.getMsg()  + "\n";
                }
                tv.setText(text);
               // se.getChatList(1);
                //String t = se.getTest();
               // tv.setText("derp");
            }
        });
    }

}

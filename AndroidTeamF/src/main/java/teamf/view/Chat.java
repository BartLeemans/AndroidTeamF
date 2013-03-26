package teamf.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.project.TeamFAndroid.R;
import teamf.controller.ServerCaller;
import teamf.model.Trip;

import java.util.ArrayList;
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
    private int lastChatId = 0;
    private Trip detail;

    List<String> chat = new ArrayList<String>();
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat);
        detail = (Trip)getIntent().getSerializableExtra("Trip");
        Button btnChat = (Button) findViewById(R.id.btnChat);

        getChatMsg();

        btnChat.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                EditText input = (EditText) findViewById(R.id.inputChat);
                se.addChat(input.getText().toString(), detail.getTripId());
                input.setText("");
            }
        });
    }

    private void getChatMsg() {
        ListView listView = (ListView) findViewById(R.id.chatListView);

        se.getChats(detail.getTripId(), lastChatId);  //parameter = trip ID
        List<teamf.model.Chat> cl = se.getChatList();
        if(cl != null) {
            for(teamf.model.Chat c : cl) {
                chat.add(/*"(" + c.getDate() + ")" +*/ c.getUser().getUsername() + ": " + c.getMsg());
                lastChatId = c.getChatID();
            }
            ArrayAdapter<String> arrayAdapt = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, android.R.id.text1, chat);
            listView.setAdapter(arrayAdapt);
        }
        listView.setSelection(listView.getCount() - 1);
    }

}

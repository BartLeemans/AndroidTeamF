package teamf.view;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.project.TeamFAndroid.R;
import teamf.controller.ServerCaller;
import teamf.model.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

/**
 * Created with IntelliJ IDEA.
 * User: Jerre
 * Date: 26/03/13
 * Time: 10:57
 * To change this template use File | Settings | File Templates.
 */
public class Messages extends Activity {
    private ServerCaller se = ServerCaller.getInstance();
    private Trip detail;

    List<String> msgs = new ArrayList<String>();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.messages);
        detail = (Trip)getIntent().getSerializableExtra("Trip");
        fillList();
    }

    private void fillList() {
        ListView listView = (ListView) findViewById(R.id.msgView);

        //Collection<BroadcastMessage> bmsg = detail.getBroadcastMessages();

        se.getBroadcasts(detail.getTripId());
        List<BroadcastMessage> bmsg = se.getBroadcastList();

      //  SimpleDateFormat df = new SimpleDateFormat("dd MMMM yyyy - HH:mm:ss", Locale.ENGLISH);
        if(bmsg != null) {
            for(BroadcastMessage b : bmsg) {
                msgs.add(b.getDate() + ": \n" + b.getMsg());
            }
            ArrayAdapter<String> arrayAdapt = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, android.R.id.text1, msgs);
            listView.setAdapter(arrayAdapt);
        }
        listView.setSelection(listView.getCount() - 1);

    }


}

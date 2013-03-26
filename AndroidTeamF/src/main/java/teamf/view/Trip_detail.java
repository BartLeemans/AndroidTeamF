package teamf.view;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;
import com.project.TeamFAndroid.R;
import teamf.model.Trip;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Jeroen
 * Date: 1/03/13
 * Time: 14:35
 * To change this template use File | Settings | File Templates.
 */
public class Trip_detail extends TabActivity {

    private TabHost tabHost;
    private Trip detail;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.trip_detail);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.custom_title);

        detail = (Trip)getIntent().getSerializableExtra("Trip");
        this.setTitle(detail.getTripName());


        init_tabs();

    }

    private void init_tabs() {
        tabHost = getTabHost();

        //General tab
        Intent generalIntent = new Intent(this,Detail_General.class);
        generalIntent.putExtra("Trip",detail);
        TabHost.TabSpec generalspec = tabHost.newTabSpec("General");
        generalspec.setIndicator("General",getResources().getDrawable(R.drawable.options_icon));
        generalspec.setContent(generalIntent);

        tabHost.addTab(generalspec);

        //Map tab
        Intent mapIntent = new Intent(this,Map.class);
        mapIntent.putExtra("Trip",detail);
        TabHost.TabSpec mapspec = tabHost.newTabSpec("Map");
        mapspec.setIndicator("Map",getResources().getDrawable(R.drawable.options_icon));
        mapspec.setContent(mapIntent);

       tabHost.addTab(mapspec);

        //Chat tab
        Intent chatIntent = new Intent(this,Chat.class);
        chatIntent.putExtra("Trip",detail);
        TabHost.TabSpec chatspec = tabHost.newTabSpec("Chat");
        chatspec.setIndicator("Chat",getResources().getDrawable(R.drawable.options_icon));
        chatspec.setContent(chatIntent);

        tabHost.addTab(chatspec);

        //broadcast tab
        Intent broadcastIntent = new Intent(this,Messages.class);
        broadcastIntent.putExtra("Trip",detail);
        TabHost.TabSpec broadcastspec = tabHost.newTabSpec("Messages");
        broadcastspec.setIndicator("Messages",getResources().getDrawable(R.drawable.options_icon));
        broadcastspec.setContent(broadcastIntent);

        tabHost.addTab(broadcastspec);

        tabHost.setCurrentTab(0);



    }

}

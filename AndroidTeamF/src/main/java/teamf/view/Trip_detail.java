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

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trip_detail);

        TextView tripName = (TextView)findViewById(R.id.tripname);
        TextView equipmentText = (TextView)findViewById(R.id.equipment);
        TextView tripLoc = (TextView)findViewById(R.id.tripLoc);
        Trip detail = (Trip)getIntent().getSerializableExtra("Trip");
        tripName.setText(detail.getTripName());
        tripLoc.setText(detail.getStartLocation());
        RelativeLayout BG = (RelativeLayout)findViewById(R.id.DetailBG);
        BG.setBackgroundColor(Color.parseColor(detail.getBgcolor()));
        init_tabs();

    }

    private void init_tabs() {
        tabHost = getTabHost();

        //options tab
        Intent optionsIntent = new Intent(this,Options.class);
        TabHost.TabSpec optionsspec = tabHost.newTabSpec("Options");
        optionsspec.setIndicator("Options",getResources().getDrawable(R.drawable.options_icon));
        optionsspec.setContent(optionsIntent);

        tabHost.addTab(optionsspec);

        //options tab
        Intent optionsIntent2 = new Intent(this,Options.class);
        TabHost.TabSpec optionsspec2 = tabHost.newTabSpec("Options");
        optionsspec.setIndicator("Options",getResources().getDrawable(R.drawable.options_icon));
        optionsspec.setContent(optionsIntent);

        tabHost.addTab(optionsspec);

        tabHost.setCurrentTab(0);



    }

}

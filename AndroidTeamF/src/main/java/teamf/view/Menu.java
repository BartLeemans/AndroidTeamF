package teamf.view;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.Window;
import android.widget.TabHost;
import android.widget.TextView;
import com.project.TeamFAndroid.R;
import teamf.controller.ServerCaller;

/**
 * Created with IntelliJ IDEA.
 * User: Jeroen
 * Date: 14/02/13
 * Time: 15:14
 * To change this template use File | Settings | File Templates.
 */
public class Menu extends TabActivity implements OnGestureListener {
    private TabHost tabHost;
    private GestureDetector gDetector;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        ServerCaller sc = ServerCaller.getInstance();
        TextView username = (TextView) findViewById(R.id.username);
        username.setText(sc.getCurrentUser().getUsername());
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.menu);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.custom_title);


        init_tabs();
        gDetector = new GestureDetector(this);
    }

   private void init_tabs() {
        tabHost = getTabHost();

       //Trips tab
       Intent tripIntent = new Intent(this,ViewTrips.class);
       TabHost.TabSpec tripspec = tabHost.newTabSpec("Trips");
       tripspec.setIndicator("Trips",getResources().getDrawable(R.drawable.acc_icon));
       tripspec.setContent(tripIntent);

       tabHost.addTab(tripspec);

        //account tab
        Intent accountIntent = new Intent(this,Account.class);
        TabHost.TabSpec accountspec = tabHost.newTabSpec("account");
        accountspec.setIndicator("account",getResources().getDrawable(R.drawable.acc_icon));
        accountspec.setContent(accountIntent);

        tabHost.addTab(accountspec);

        //options tab
        Intent optionsIntent = new Intent(this,Options.class);
        TabHost.TabSpec optionsspec = tabHost.newTabSpec("Options");
        optionsspec.setIndicator("Options",getResources().getDrawable(R.drawable.options_icon));
        optionsspec.setContent(optionsIntent);

        tabHost.addTab(optionsspec);



       tabHost.setCurrentTab(0);



    }

    @Override
    public boolean onTouchEvent(MotionEvent me) {
        return gDetector.onTouchEvent(me);
    }

    public boolean onDown(MotionEvent e) {
        return true;
      //To change body of implemented methods use File | Settings | File Templates.
    }

    public void onShowPress(MotionEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean onSingleTapUp(MotionEvent e) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void onLongPress(MotionEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean onFling(MotionEvent start, MotionEvent stop, float velocityX, float velocityY) {
       if(start.getRawX()<stop.getRawX()){
           tabHost.setCurrentTab(tabHost.getCurrentTab()-1);
       }
        else if(start.getRawX()>stop.getRawX()){
            tabHost.setCurrentTab(tabHost.getCurrentTab()+1);
        }


        return true;  //To change body of implemented methods use File | Settings | File Templates.
    }


}


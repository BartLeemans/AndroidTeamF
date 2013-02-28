package teamf.view;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import com.project.TeamFAndroid.R;

/**
 * Created with IntelliJ IDEA.
 * User: Jeroen
 * Date: 14/02/13
 * Time: 15:14
 * To change this template use File | Settings | File Templates.
 */
public class Menu extends TabActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        TabHost tabHost = getTabHost();

        //account tab
        TabHost.TabSpec accountspec = tabHost.newTabSpec("Account");
        accountspec.setIndicator("Account",getResources().getDrawable(R.drawable.tab_icon));
        Intent accountIntent = new Intent(this,Account.class);
        accountspec.setContent(accountIntent);

        //options tab
        TabHost.TabSpec optionsspec = tabHost.newTabSpec("Options");
        accountspec.setIndicator("Options",getResources().getDrawable(R.drawable.tab_icon));
        Intent optionsIntent = new Intent(this,Options.class);
        accountspec.setContent(optionsIntent);

        tabHost.addTab(accountspec);
        tabHost.addTab(optionsspec);
       tabHost.setCurrentTab(0);

    }
}

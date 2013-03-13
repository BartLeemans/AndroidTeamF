
package teamf.view;

import android.app.Activity;
import android.os.Bundle;
import com.google.android.maps.MapActivity;
import com.project.TeamFAndroid.R;


/**
 * Created with IntelliJ IDEA.
 * User: BART.LEEMANS
 * Date: 12/03/13
 * Time: 21:02
 * To change this template use File | Settings | File Templates.
 */


public class Map extends MapActivity
{

/** Called when the activity is first created. */

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.map);
}

    @Override
    protected boolean isRouteDisplayed() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }


}


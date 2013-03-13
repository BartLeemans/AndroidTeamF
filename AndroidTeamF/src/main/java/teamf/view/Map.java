package teamf.view;

/**
 * Created with IntelliJ IDEA.
 * User: BART.LEEMANS
 * Date: 12/03/13
 * Time: 21:02
 * To change this template use File | Settings | File Templates.
 */

import android.os.Bundle;
import com.google.android.maps.MapActivity;
import com.project.TeamFAndroid.R;

public class Map extends MapActivity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    @Override
    protected boolean isRouteDisplayed()
    {
        return false;
    }
}

package teamf.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;
import com.project.TeamFAndroid.R;
import teamf.model.Trip;

/**
 * Created with IntelliJ IDEA.
 * User: Jeroen
 * Date: 1/03/13
 * Time: 14:35
 * To change this template use File | Settings | File Templates.
 */
public class Trip_detail extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trip_detail);

        TextView tripName = (TextView)findViewById(R.id.tripname);
        Trip detail = (Trip)getIntent().getSerializableExtra("TripName");
        tripName.setText(detail.getTripName());
    }
}

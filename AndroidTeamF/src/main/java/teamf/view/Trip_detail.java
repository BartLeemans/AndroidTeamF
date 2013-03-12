package teamf.view;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;
import android.widget.LinearLayout;
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
public class Trip_detail extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trip_detail);

        TextView tripName = (TextView)findViewById(R.id.tripname);
        TextView equipmentText = (TextView)findViewById(R.id.equipment);
        Trip detail = (Trip)getIntent().getSerializableExtra("Trip");
        tripName.setText(detail.getTripName());
        LinearLayout BG = (LinearLayout)findViewById(R.id.DetailBG);
        BG.setBackgroundColor(Color.parseColor(detail.getBgcolor()));
        List<String> equipment = new ArrayList<String>(detail.getEquipment());
        StringBuilder eq = new StringBuilder();
        for(String e:equipment){
            eq.append(e+"\n");
        }
        equipmentText.setText(eq);

    }
}

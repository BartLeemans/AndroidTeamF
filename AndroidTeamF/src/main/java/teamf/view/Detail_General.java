package teamf.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.project.TeamFAndroid.R;
import teamf.controller.ServerCaller;
import teamf.model.Trip;
import java.text.DateFormat;

/**
 * Created with IntelliJ IDEA.
 * User: Jeroen
 * Date: 14/03/13
 * Time: 13:32
 * To change this template use File | Settings | File Templates.
 */
public class Detail_General extends Activity {

    private Trip detail;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_general);

        detail = (Trip)getIntent().getSerializableExtra("Trip");

        setDates();
        setLocation();
        setEquipment();
        setButtonListener();

    }

    private void setButtonListener() {
        Button start = (Button)findViewById(R.id.startTrip);
        start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent startIntent = new Intent(Detail_General.this,Stopplaatsen.class);
                startIntent.putExtra("trip",detail.getTripId());
                startActivity(startIntent);
            }
        });
    }

    private void setLocation() {
        TextView loc = (TextView)findViewById(R.id.locationText);
        loc.setText(detail.getStartLocation());
    }

    private void setDates() {
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        TextView start = (TextView)findViewById(R.id.startDateText);
        TextView end = (TextView)findViewById(R.id.endDateText);
        start.setText(df.format(detail.getStartDate()));
        end.setText(df.format(detail.getEndDate()));
    }

    private void setEquipment() {
        TextView equipment = (TextView)findViewById(R.id.equipmentText);
        StringBuilder sb = new StringBuilder();
        for(String s:detail.getEquipment()){
            sb.append(s+"\n");
        }
        equipment.setText(sb.toString());
    }
}

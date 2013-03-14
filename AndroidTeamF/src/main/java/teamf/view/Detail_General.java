package teamf.view;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import com.project.TeamFAndroid.R;
import teamf.model.Trip;

import java.io.StringWriter;
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

        setEquipment();
        setDates();
    }

    private void setDates() {
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        TextView start = (TextView)findViewById(R.id.startDateText);
        TextView end = (TextView)findViewById(R.id.endDateText);
        start.setText(df.format(detail.getStartDate()));
        end.setText(df.format(detail.getEndDate()));
    }

    private void setEquipment() {
        TextView equipment = (TextView)findViewById(R.id.equipment);
        StringBuilder sb = new StringBuilder();
        for(String s:detail.getEquipment()){
            sb.append(s+"\n");
        }
        equipment.setText(sb.toString());
    }
}

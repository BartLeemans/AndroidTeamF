package teamf.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.project.TeamFAndroid.R;
import teamf.controller.ServerCaller;
import teamf.model.StopPlaats;
import teamf.model.Trip;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Jeroen
 * Date: 14/03/13
 * Time: 13:32
 * To change this template use File | Settings | File Templates.
 */
public class Detail_General extends Activity {

    private Trip detail;
    private ViewFlipper vf;
    private List<StopPlaats> plaatsen;
    private ListView listView;
    private List<String> plaatsnamen = new ArrayList<String>();
    private Integer stops;
    private ServerCaller sc = ServerCaller.getInstance();

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_general);

        detail = (Trip)getIntent().getSerializableExtra("Trip");
        vf = (ViewFlipper)findViewById(R.id.ViewFlipper);
        Button back = (Button)findViewById(R.id.det_gen_back);
        setDates();
        setLocation();
        setEquipment();
        setButtonListener();

        sc.getStopsTrip(detail.getTripId());
        plaatsen = new ArrayList<StopPlaats>(sc.getStops());

        if(plaatsen.size()!=0){
            for (StopPlaats sp : plaatsen){
                if(sp.isVrijgegeven()){
                    plaatsnamen.add(sp.getAdres());
                }
            }
        }

        stops=0;
        listView = (ListView) findViewById(R.id.stopPlaatsenList);
          //brol
        setStopsList();

        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                vf.showPrevious();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                Boolean end = false;
                if(position == plaatsen.size()-1){
                    end = true;
                }
                Intent stopDetail = new Intent(Detail_General.this, Stopplaats_detail.class);
                stopDetail.putExtra("Stop",plaatsen.get(position));
                stopDetail.putExtra("Einde",end);
                startActivityForResult(stopDetail, 1);

            }
        });

    }

    private void setButtonListener() {
        Button start = (Button)findViewById(R.id.startTrip);
        start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                vf.showNext();
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

    private void setStopsList() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, android.R.id.text1,plaatsnamen);
        listView.setAdapter(adapter);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {

            if(resultCode == RESULT_OK){
                Boolean result = data.getBooleanExtra("result", false);
                if(plaatsnamen.size()<= plaatsen.size()&& result){
                    stops++;
                    if(!plaatsnamen.contains(plaatsen.get(stops).getAdres())){
                        plaatsnamen.add(plaatsen.get(stops).getAdres());
                        setStopsList();
                    }

                }
            }
        }
    }
}

package teamf.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.project.TeamFAndroid.R;
import teamf.controller.ServerCaller;
import teamf.model.StopPlaats;
import teamf.model.Trip;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Jeroen
 * Date: 14/03/13
 * Time: 16:43
 * To change this template use File | Settings | File Templates.
 */
public class Stopplaatsen extends Activity {

    private Integer tripid;
    private String[] plaatsNamen;
    private ServerCaller sc = ServerCaller.getInstance();
    private List<StopPlaats> plaatsen;
    private ListView listView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stopplaatsen);

        tripid = getIntent().getIntExtra("tripid",0);
        sc.getStopsTrip(tripid);
        plaatsen = new ArrayList<StopPlaats>(sc.getStops());

        plaatsNamen = new String[1];
        if(plaatsen.size()!=0){
            plaatsNamen[0]=plaatsen.get(0).getAdres();
        }
        listView = (ListView) findViewById(R.id.stopPlaatsenList);

        setStopsList();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {

                Intent stopDetail = new Intent(Stopplaatsen.this, Stopplaats_detail.class);
                stopDetail.putExtra("Stop",plaatsen.get(position));
                startActivity(stopDetail);

            }
        });

    }


    private void setStopsList() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, android.R.id.text1, plaatsNamen);
        listView.setAdapter(adapter);
    }

}
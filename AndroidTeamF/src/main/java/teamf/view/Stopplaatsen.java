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
    private ServerCaller sc = ServerCaller.getInstance();
    private List<StopPlaats> plaatsen;
    private ListView listView;
    private List<String> plaatsnamen = new ArrayList<String>();
    private Integer stops;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stopplaatsen);

        tripid = getIntent().getIntExtra("tripid",0);
        sc.getStopsTrip(tripid);
        plaatsen = new ArrayList<StopPlaats>(sc.getStops());

        if(plaatsen.size()!=0){
            plaatsnamen.add(plaatsen.get(0).getAdres());
        }

        stops=0;
        listView = (ListView) findViewById(R.id.stopPlaatsenList);

        setStopsList();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                Boolean end = false;
                if(position == plaatsen.size()){
                    end = true;
                }
                Intent stopDetail = new Intent(Stopplaatsen.this, Stopplaats_detail.class);
                stopDetail.putExtra("Stop",plaatsen.get(position));
                stopDetail.putExtra("Einde",end);
                startActivityForResult(stopDetail, 1);

            }
        });

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
                    plaatsnamen.add(plaatsen.get(stops).getAdres());
                    setStopsList();
                }

            }
            if (resultCode == RESULT_CANCELED) {
                //Write your code on no result return
            }
        }
    }

}
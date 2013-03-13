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
import teamf.model.Trip;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Jeroen
 * Date: 12/03/13
 * Time: 14:15
 * To change this template use File | Settings | File Templates.
 */
public class Trips extends Activity {
    List<String> tripNames = new ArrayList<String>();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trips);

        ListView listView = (ListView) findViewById(R.id.ParticipatingList);

        ServerCaller sc = ServerCaller.getInstance();
        sc.getTripsUser(sc.getCurrentUser());

        final List<Trip> trips = new ArrayList<Trip>(sc.getTrips());

        for(Trip t:trips){
            tripNames.add(t.getTripName());
        }
        String[] stringValues = new String[tripNames.size()];
        for(int i = 0;i<tripNames.size();i++){
            stringValues[i]=tripNames.get(i);
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, android.R.id.text1, stringValues);


        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                try{
                Intent tripDetail = new Intent(Trips.this,Trip_detail.class);
                tripDetail.putExtra("Trip",trips.get(position));
                startActivity(tripDetail);
                }catch(Exception e){
                    String message = e.getMessage();
                }
            }
        });
    }
}

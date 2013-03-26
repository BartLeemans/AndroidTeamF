package teamf.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.*;
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
    ListView listView;
    ServerCaller sc;
    EditText edt;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trips);

        listView = (ListView) findViewById(R.id.ParticipatingList);
        Button b = (Button) findViewById(R.id.btnSearch);
        Button bClear = (Button) findViewById(R.id.btnClear);
        edt = (EditText) findViewById(R.id.edtSearch);


        sc = ServerCaller.getInstance();
        sc.getTripsUser(sc.getCurrentUser());

        search("");

        bClear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                edt.setText("");
                search("");
            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                search(edt.getText().toString());
            }
        });
    }

    public void search(String s) {
        tripNames.clear();
        List<Trip> trips = new ArrayList<Trip>(sc.getTrips());
        List<Trip> searchTrips = new ArrayList<Trip>();

        for(Trip t:trips){
            if(t.getTripName().contains(s)) {
                searchTrips.add(t);
                tripNames.add(t.getTripName());
            }
        }
        if(tripNames.size() == 0) {
            tripNames.add("No results!");
        }

        final List<Trip> tempTrips = new ArrayList<Trip>(searchTrips);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, android.R.id.text1, tripNames);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                try{
                    Intent tripDetail = new Intent(Trips.this,Trip_detail.class);
                    tripDetail.putExtra("Trip",tempTrips.get(position));
                    startActivity(tripDetail);

                }catch(Exception e){
                    String message = e.getMessage();
                }
            }
        });

    }
}

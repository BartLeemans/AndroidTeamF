package teamf.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.view.Window;
import android.widget.*;
import com.project.TeamFAndroid.R;
import teamf.controller.ServerCaller;
import teamf.model.Trip;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Jeroen
 * Date: 1/03/13
 * Time: 9:56
 * To change this template use File | Settings | File Templates.
 */
public class Main extends Activity {

    Trip[] testValues;

    List<String> tripNames = new ArrayList<String>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        ServerCaller sc = ServerCaller.getInstance();
       /* TextView myTextView = (TextView) findViewById(R.id.username);
        myTextView.setText(sc.getCurrentUser().getUsername());  */

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.main);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.custom_title);

        ListView listView = (ListView) findViewById(R.id.openTripList);


        sc.listTrips();
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
                Intent tripDetail = new Intent(Main.this,Trip_detail.class);
                tripDetail.putExtra("Trip", (Parcelable) trips.get(position));
                startActivity(tripDetail);
            }
        });

    }
}

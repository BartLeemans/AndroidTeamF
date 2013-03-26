package teamf.view;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.project.TeamFAndroid.R;
import teamf.controller.ServerCaller;
import teamf.model.Trip;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Jerre
 * Date: 26/03/13
 * Time: 14:21
 * To change this template use File | Settings | File Templates.
 */
public class Search extends Activity {
    ServerCaller sc;
    List<String> tripNames = new ArrayList<String>();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        sc = ServerCaller.getInstance();
        fillSearchlist();
    }

    public void fillSearchlist() {

        sc.getTripsUser(sc.getCurrentUser());
        ListView listView = (ListView) findViewById(R.id.listViewSearch);
        List<Trip> trips = new ArrayList<Trip>(sc.getTrips());

        if(trips != null) {
            for(Trip t : trips) {
                tripNames.add(t.getTripName());
            }
            ArrayAdapter<String> arrayAdapt = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, android.R.id.text1, tripNames);
            listView.setAdapter(arrayAdapt);
        }
        listView.setSelection(listView.getCount() - 1);


    }
}

package teamf.view;

import android.app.Activity;
import android.os.Bundle;
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

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stopplaatsen);

        tripid = getIntent().getIntExtra("tripid",0);

        ListView listView = (ListView) findViewById(R.id.stopPlaatsenList);
        try{
        sc.getStopsTrip(tripid);
        List<StopPlaats> plaatsen = new ArrayList<StopPlaats>(sc.getStops());

        plaatsNamen = new String[plaatsen.size()];
        for(int i = 0;i<plaatsen.size();i++){
            plaatsNamen[i]=plaatsen.get(i).getAdres();
        }
        }catch(Exception e){
            String message = e.getMessage();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, android.R.id.text1, plaatsNamen);

        listView.setAdapter(adapter);
    }
}

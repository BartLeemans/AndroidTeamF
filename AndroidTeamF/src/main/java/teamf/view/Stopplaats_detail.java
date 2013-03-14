package teamf.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
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
 * Time: 20:26
 * To change this template use File | Settings | File Templates.
 */
public class Stopplaats_detail extends Activity {

    private ServerCaller se = ServerCaller.getInstance();
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stopplaats_detail);

        StopPlaats detail = (StopPlaats)getIntent().getSerializableExtra("Stop");
        TextView stop = (TextView)findViewById(R.id.stoptest);
        stop.setText(detail.getAdres());

        try{
        if(detail.getVraag()!=null){
            TextView vraag = (TextView)findViewById(R.id.vraag);
            ListView antwoorden = (ListView)findViewById(R.id.antwoordList);

            vraag.setVisibility(View.VISIBLE);
            antwoorden.setVisibility(View.VISIBLE);

            vraag.setText(detail.getVraag());

            //se.getVraagStop(detail.getStopPlaatsID());
            List<String> antw = new ArrayList<String>(detail.getAntwoorden());
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, android.R.id.text1, antw);
            antwoorden.setAdapter(adapter);
        }
        }catch(Exception e){
            String message = e.getMessage();
        }

    }

}

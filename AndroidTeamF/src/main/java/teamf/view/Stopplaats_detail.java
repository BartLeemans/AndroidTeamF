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
    private StopPlaats detail;
    private Button next;
    private List<String> antw;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stopplaats_detail);

        detail = (StopPlaats)getIntent().getSerializableExtra("Stop");
        TextView stop = (TextView)findViewById(R.id.stoptest);
        next = (Button)findViewById(R.id.nextStop);
        stop.setText(detail.getAdres());

        setButtonListener();


        if(detail.getVraag()!=null){
            setVraag();
        }else{
             next.setVisibility(View.VISIBLE);
            if(getIntent().getBooleanExtra("Einde",false)){
                next.setText("Terug");
            }
        }

    }

    private void setButtonListener() {
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result",true);
                setResult(RESULT_OK,returnIntent);
                finish();
            }
        });
    }

    private void setVraag() {
        if(detail.getVraag()!=null){
            TextView vraag = (TextView)findViewById(R.id.vraag);
            ListView antwoorden = (ListView)findViewById(R.id.antwoordList);

            vraag.setVisibility(View.VISIBLE);
            antwoorden.setVisibility(View.VISIBLE);

            vraag.setText(detail.getVraag());

            antw = new ArrayList<String>(detail.getAntwoorden());
            antw.add(detail.getCorrectAntwoord());
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, android.R.id.text1, antw);
            antwoorden.setAdapter(adapter);

            antwoorden.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String antwoord = antw.get(i);
                    if(antwoord.equals(detail.getCorrectAntwoord())){
                        next.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
    }

}

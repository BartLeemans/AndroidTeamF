package teamf.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.View;
import android.view.Window;
import android.widget.*;
import com.project.TeamFAndroid.R;
import teamf.model.Trip;

/**
 * Created with IntelliJ IDEA.
 * User: Jeroen
 * Date: 1/03/13
 * Time: 9:56
 * To change this template use File | Settings | File Templates.
 */
public class Main extends Activity {

    Trip[] testValues;
    String[] stringValues = new String[10];

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.main);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.custom_title);


        Button loginButton = (Button)findViewById(R.id.loginButton);
        loginButton.setVisibility(View.VISIBLE);
        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent login = new Intent(Main.this,Login.class);
                startActivity(login);
            }
        });

        ListView listView = (ListView) findViewById(R.id.openTripList);
        testValues = new Trip[10];
        for(int i = 0;i<10;i++){
            Trip t = new Trip();
            t.setTripName("TestTrip_"+i);
            testValues[i]=t;
        }

        for(int j =0;j<10;j++){
            stringValues[j] = testValues[j].getTripName();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, android.R.id.text1, stringValues);


        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                Toast.makeText(getApplicationContext(),"Click ListItem Number " + position, Toast.LENGTH_LONG).show();
                Intent tripDetail = new Intent(Main.this,Trip_detail.class);
                tripDetail.putExtra("TripName",testValues[position]);
                startActivity(tripDetail);
            }
        });

    }
}

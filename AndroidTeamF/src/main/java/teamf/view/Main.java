package teamf.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.View;
import android.view.Window;
import android.widget.*;
import com.project.TeamFAndroid.R;

/**
 * Created with IntelliJ IDEA.
 * User: Jeroen
 * Date: 1/03/13
 * Time: 9:56
 * To change this template use File | Settings | File Templates.
 */
public class Main extends Activity {

    private String[] values;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.main);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.custom_title);

        Button loginButton = (Button)findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent login = new Intent(Main.this,Login.class);
                startActivity(login);
            }
        });

        ListView listView = (ListView) findViewById(R.id.openTripList);
        String[] values = new String[] { "VoorbeeldTrip1", "VoorbeeldTrip2", "VoorbeeldTrip3",
                "VoorbeeldTrip4", "VoorbeeldTrip5", "VoorbeeldTrip6", "VoorbeeldTrip7", "VoorbeeldTrip8",
                "VoorbeeldTrip9", "VoorbeeldTrip10" };



        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, android.R.id.text1, values);


        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                Toast.makeText(getApplicationContext(),"Click ListItem Number " + position, Toast.LENGTH_LONG).show();
                Intent tripDetail = new Intent(Main.this,Trip_detail.class);
                tripDetail.putExtra("TripName",parent.getItemIdAtPosition(position));
                startActivity(tripDetail);
            }
        });

    }
}

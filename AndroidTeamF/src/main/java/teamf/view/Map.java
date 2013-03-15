package teamf.view;

import android.app.Activity;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import com.project.TeamFAndroid.R;


import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import teamf.controller.ServerCaller;
import teamf.model.StopPlaats;
import teamf.model.Trip;

import java.util.ArrayList;
import java.util.List;

public class Map extends Activity {
    private ServerCaller sc = ServerCaller.getInstance();
    private Trip detail;
    private List<StopPlaats> plaatsen;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        String message = "";
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.map);

            FragmentManager fragmentManager = getFragmentManager();
            MapFragment mapFragment = (MapFragment) fragmentManager.findFragmentById(R.id.map);
            GoogleMap googleMap = mapFragment.getMap();

            detail = (Trip)getIntent().getSerializableExtra("Trip");
            plaatsen = new ArrayList<StopPlaats>(sc.getStops());

            final Geocoder geocoder = new Geocoder(getBaseContext());
            List<Address> GeocodeResponse = geocoder.getFromLocationName(plaatsen.get(0).getAdres(), 10);
            LatLng position = new LatLng(GeocodeResponse.get(0).getLatitude(), GeocodeResponse.get(0).getLongitude());
            googleMap.addMarker(new MarkerOptions()
                    .position(position)
                    .title("Paris")
                    .snippet("Population: kaka")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

            googleMap.getUiSettings().setCompassEnabled(true);
            googleMap.getUiSettings().setZoomControlsEnabled(true);
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(position, 10));


        } catch (Exception e) {
            message = e.getMessage();
        }


    }
}

package teamf.view;

import android.app.Activity;
import android.os.Bundle;
import com.google.android.maps.MapActivity;
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

public class Map extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        String message = "";
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.map);

            FragmentManager fragmentManager = getFragmentManager();
            MapFragment mapFragment = (MapFragment) fragmentManager.findFragmentById(R.id.map);
            GoogleMap googleMap = mapFragment.getMap();
            LatLng sfLatLng = new LatLng(37.7750, -122.4183);
            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            googleMap.addMarker(new MarkerOptions()
                    .position(sfLatLng)
                    .title("San Francisco")
                    .snippet("Population: 776733")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
            googleMap.getUiSettings().setCompassEnabled(true);
            googleMap.getUiSettings().setZoomControlsEnabled(true);
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sfLatLng, 10));

        } catch (Exception e) {
            message = e.getMessage();
        }


    }
}

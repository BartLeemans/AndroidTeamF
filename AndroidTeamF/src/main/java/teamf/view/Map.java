
package teamf.view;

import android.app.Activity;
import android.location.*;
import android.os.Bundle;
import com.google.android.gms.maps.model.*;
import com.project.TeamFAndroid.R;


import android.app.FragmentManager;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import teamf.controller.ServerCaller;
import teamf.model.StopPlaats;
import teamf.model.Trip;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Map extends Activity implements LocationListener {
    private ServerCaller sc = ServerCaller.getInstance();
    private Trip trip;
    private ArrayList<MarkerOptions> otherPositions = new ArrayList<MarkerOptions>();
    private List<StopPlaats> plaatsen;
    private GoogleMap googleMap;
    private String provider;
    private LocationManager lm;
    private MarkerOptions currentMarker= new MarkerOptions();
    private int refresh;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        String message = "";
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.map);

            lm = (LocationManager) getSystemService(LOCATION_SERVICE);
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 4000, 0, this);
            provider = lm.getBestProvider(new Criteria(), true);


            FragmentManager fragmentManager = getFragmentManager();
            MapFragment mapFragment = (MapFragment) fragmentManager.findFragmentById(R.id.map);
            googleMap = mapFragment.getMap();
            googleMap.setMyLocationEnabled(true);

            trip = (Trip)getIntent().getSerializableExtra("Trip");
            sc.getStopsTrip(trip.getTripId());
            plaatsen = new ArrayList<StopPlaats>(sc.getStops());

            addStopplaatsenToMap();
            addCurrentMarkerToMap();
            addOtherMarkers();
            refresh=0;



        } catch (Exception e) {
            message = e.getMessage();
        }
    }

    public void onLocationChanged(Location location) {
        if(refresh==200){
            googleMap.clear();
            refresh=0;
        }
        addStopplaatsenToMap();
        addCurrentMarkerToMap();
        addOtherMarkers();
    }


    public void onStatusChanged(String s, int i, Bundle bundle) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void onProviderEnabled(String s) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void onProviderDisabled(String s) {
        //To change body of implemented methods use File | Settings | File Templates.
    }


    private void addOtherMarkers() {
        if(refresh<200){
            int i=0;
            for(MarkerOptions temp: otherPositions ){
                temp.visible(false);
                otherPositions.add(i,new MarkerOptions());
            }
        }
        List<String> strings = sc.getLocOthers(sc.getCurrentUser().getUserID(), trip.getTripId());

        int i =0;
        for(String s: strings){
            String[] split = s.split(";");
            double lat = Double.valueOf(split[0]);
            double lng = Double.valueOf(split[1]);
            LatLng latLng = new LatLng(lat,lng);
            String userName = String.valueOf(split[2]);
            MarkerOptions markerOther = new MarkerOptions();
            markerOther.position(latLng);
            markerOther.title("naam");
            markerOther.snippet(userName);
            markerOther.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
            otherPositions.add(markerOther);
            googleMap.addMarker(markerOther);
            i++;
        }


    }

    private void addStopplaatsenToMap() {
        final Geocoder geocoder = new Geocoder(getBaseContext());
        PolylineOptions rectOptions = new PolylineOptions();
        for(StopPlaats p :plaatsen){
            List<Address> GeocodeResponse = null;
            try {
                GeocodeResponse = geocoder.getFromLocationName(p.getAdres(), 10);
            } catch (IOException e) {
                e.printStackTrace();
            }
            LatLng position = new LatLng(GeocodeResponse.get(0).getLatitude(), GeocodeResponse.get(0).getLongitude());
            googleMap.addMarker(new MarkerOptions()
                    .position(position)
                    .title(p.getAdres())
                    .snippet(p.getInformatie())
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
            rectOptions.add(position);
            googleMap.getUiSettings().setCompassEnabled(true);
            googleMap.getUiSettings().setZoomControlsEnabled(true);
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(position, 11));
        }
        googleMap.addPolyline(rectOptions);
    }

    private void addCurrentMarkerToMap() {
        if(refresh<200){
            currentMarker.visible(false);
            currentMarker = new MarkerOptions();
        }
        currentMarker.title("Current Position");
        currentMarker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        if(lm.getLastKnownLocation(provider)==null){
            LatLng temp = new LatLng(0,0);
            currentMarker.position(temp);
        }  else {
            currentMarker.position(new LatLng(lm.getLastKnownLocation(provider).getLatitude(), lm.getLastKnownLocation(provider).getLongitude()));
        }
        googleMap.addMarker(currentMarker);
    }
}


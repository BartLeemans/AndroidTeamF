
package teamf.view;

import android.app.Activity;
import android.location.*;
import android.os.Bundle;
import com.google.android.gms.maps.model.PolylineOptions;
import com.project.TeamFAndroid.R;


import android.app.FragmentManager;
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

public class Map extends Activity implements LocationListener {
    private ServerCaller sc = ServerCaller.getInstance();
    private Trip detail;
    private ArrayList<MarkerOptions> otherPositions = new ArrayList<MarkerOptions>();
    private List<StopPlaats> plaatsen;
    private GoogleMap googleMap;
    private String provider;
    private LocationManager lm;
    private MarkerOptions currentMarker= new MarkerOptions();

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

            detail = (Trip)getIntent().getSerializableExtra("Trip");
            sc.getStopsTrip(detail.getTripId());
            plaatsen = new ArrayList<StopPlaats>(sc.getStops());

            final Geocoder geocoder = new Geocoder(getBaseContext());
            PolylineOptions rectOptions = new PolylineOptions();
            for(StopPlaats p :plaatsen){
                List<Address> GeocodeResponse = geocoder.getFromLocationName(p.getAdres(), 10);
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
            currentMarker.title("Current Position");
            currentMarker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
            currentMarker.position(new LatLng(lm.getLastKnownLocation(provider).getLatitude(), lm.getLastKnownLocation(provider).getLongitude()));
            googleMap.addMarker(currentMarker);

            List<LatLng> locs = sc.getLocOthers(sc.getCurrentUser().getUserID(),detail.getTripId());
            for(LatLng l : locs){
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(l);
                otherPositions.add(markerOptions);
            }


        } catch (Exception e) {
            message = e.getMessage();
        }
    }

    public void onLocationChanged(Location location) {
        LatLng current = new LatLng(lm.getLastKnownLocation(provider).getLatitude(), lm.getLastKnownLocation(provider).getLongitude());
        currentMarker.position(current);
        sc.sendCurLoc(current.latitude, current.longitude, sc.getCurrentUser().getUserID(), detail.getTripId());
        List<LatLng> locs = sc.getLocOthers(sc.getCurrentUser().getUserID(),detail.getTripId());
        int i =0;
        for(LatLng l : locs){
            MarkerOptions markerOptions = otherPositions.get(i);
            markerOptions.position(l);
            i++;
        }

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
}


package to.msn.wings.mapmylocation;

import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Looper;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private FusedLocationProviderClient fusedClient;
    private SettingsClient setClient;
    private LocationSettingsRequest locSetReq;
    private LocationCallback locCallback;
    private LocationRequest locReq;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        fusedClient = LocationServices.getFusedLocationProviderClient(this);
        setClient = LocationServices.getSettingsClient(this);

        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[] {android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        locCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                Location loc = locationResult.getLastLocation();
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                        new LatLng(loc.getLatitude(), loc.getLongitude()), 16f));
            }
        };

        locReq = new LocationRequest();
        locReq.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY).
                setInterval(5000).
                setFastestInterval(1000);

        LocationSettingsRequest.Builder builder =
        new LocationSettingsRequest.Builder();
        locSetReq = builder.addLocationRequest(locReq).build();

        startWatchLocation();
    }

    private void startWatchLocation() {
        setClient.checkLocationSettings(locSetReq)
            .addOnSuccessListener(this,
                new OnSuccessListener<LocationSettingsResponse>() {
                    @Override
                    public void onSuccess(
                        LocationSettingsResponse locationSettingsResponse) {
                        if (ActivityCompat.checkSelfPermission(MapsActivity.this,
                            android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                            PackageManager.PERMISSION_GRANTED ) {
                                return;
                        }
                        fusedClient.requestLocationUpdates(
                            locReq, locCallback, Looper.myLooper());
                    }
                })
            .addOnFailureListener(this, new OnFailureListener() {
                @Override
                public void onFailure(Exception e) {
                    Log.d("MapMyLocation",  e.getMessage());
                }
            });
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    @Override
    protected void onPause() {
        super.onPause();
        fusedClient.removeLocationUpdates(locCallback);
    }

    @Override
    protected void onResume() {
        super.onResume();
        startWatchLocation();
    }
}

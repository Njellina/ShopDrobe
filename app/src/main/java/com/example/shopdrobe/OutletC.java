package com.example.shopdrobe;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import org.osmdroid.config.Configuration;
import org.osmdroid.events.MapEventsReceiver;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.MapEventsOverlay;
import org.osmdroid.views.overlay.Marker;

import java.util.ArrayList;


public class OutletC extends AppCompatActivity implements MapEventsReceiver {
    private final int REQUEST_PERMISSION_REQUEST_CODE = 1;
    private MapView map = null;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);

        Context ctx = getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));
        map = (MapView) findViewById(R.id.map);
        MapEventsOverlay mapEventsOverlay = new MapEventsOverlay(this, this);
        map.getOverlays().add(0, mapEventsOverlay);
        map.setTileSource(TileSourceFactory.MAPNIK);

        String[] permissionStrings = {Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};
        requestPermissionsIfNecessary(permissionStrings);

        GeoPoint OutletC = new GeoPoint(-6.912804868628957, 107.59174141113208);
        map.getController().setCenter(OutletC);

        Marker outletC = new Marker(map);
        outletC.setPosition(OutletC);
        outletC.setTitle("Outlet C");
        outletC.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        map.getOverlays().add(outletC);
        map.invalidate();

    }

    private void requestPermissionsIfNecessary(String[] permissions){
        ArrayList<String> permissionToRequest = new ArrayList<>();
        for(String permission:permissions){
            if(ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED){
                permissionToRequest.add(permission);
            }
        }
        if(permissionToRequest.size() > 0){
            ActivityCompat.requestPermissions(this, permissionToRequest.toArray(new String[permissionToRequest.size()]),
                    REQUEST_PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public boolean singleTapConfirmedHelper(GeoPoint p) {
        return false;
    }

    @Override
    public boolean longPressHelper(GeoPoint p) {
        return false;
    }
}

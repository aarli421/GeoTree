package com.example.geotree.Map;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.geotree.Leaderboard.LeaderboardActivity;
import com.example.geotree.MainActivity;
import com.example.geotree.R;
import com.example.geotree.User.PlantTreeActivity;
import com.example.geotree.User.RequestTreeActivity;
import com.example.geotree.User.User;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Button mRequest, mPlant, mLogOut, mLeader;
    private TextView mDesc;
    private static LatLng clickPos;
    public static User user;
    private Marker selected;
    private boolean selectedAnything = false, selectedMarker = false;

    private int bitmapWidth = 100, bitmapHeight = 100;
    private Bitmap station, tree, sapling;

    private static ArrayList<Station> stations = new ArrayList<>();
    public static ArrayList<String> requestedTreesIds = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        mRequest = findViewById(R.id.request);
        mPlant = findViewById(R.id.plant);
        mLogOut = findViewById(R.id.logout);
        mLeader = findViewById(R.id.leader);

        mDesc = findViewById(R.id.desc);

        mDesc.setText("Selected nothing"
                + "\n Your Trees Planted: " + user.getPlanted().size()
                + "\n Your Trees Requested: " + user.getRequested().size()
                + "\n Your Account " + user.getBalance() );

        BitmapDrawable bitmap = (BitmapDrawable) getResources().getDrawable(R.drawable.station);
        Bitmap b = bitmap.getBitmap();
        station = Bitmap.createScaledBitmap(b, bitmapWidth, bitmapHeight, false);

        bitmap = (BitmapDrawable) getResources().getDrawable(R.drawable.tree);
        b = bitmap.getBitmap();
        tree = Bitmap.createScaledBitmap(b, bitmapWidth, bitmapHeight, false);

        bitmap = (BitmapDrawable) getResources().getDrawable(R.drawable.sapling);
        b = bitmap.getBitmap();
        sapling = Bitmap.createScaledBitmap(b, bitmapWidth/2, bitmapHeight/2, false);

//        stations.add(new Station(2, new LatLng(23, -109)));
//        stations.add(new Station(4, new LatLng(41, -119)));

        mRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedAnything) {
                    requestedTreesIds.clear();

                    Intent intent = new Intent(getApplicationContext(), RequestTreeActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                    return;
                }
            }
        });

        mPlant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedMarker) {
                    System.out.println("selected");

                    requestedTreesIds.clear();

                    Intent intent = new Intent(getApplicationContext(), PlantTreeActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                    return;
                }
            }
        });

        mLeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestedTreesIds.clear();

                Intent intent = new Intent(getApplicationContext(), LeaderboardActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                return;
            }
        });

        mLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestedTreesIds.clear();

                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                return;
            }
        });

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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

        for (Tree t : user.getRequested()) {
            LatLng treePos = t.getPos();
            Marker m = mMap.addMarker(new MarkerOptions().position(treePos).title("Your Requested Tree").icon(BitmapDescriptorFactory.fromBitmap(sapling)));
            requestedTreesIds.add(m.getId());
        }

        for (Tree t : User.getToPlant()) {
            if (!user.getRequested().contains(t)) {
                LatLng treePos = t.getPos();
                Marker m = mMap.addMarker(new MarkerOptions().position(treePos).title("Other People's Requested Trees").icon(BitmapDescriptorFactory.fromBitmap(sapling)));
                requestedTreesIds.add(m.getId());
            }
        }

        for (Tree t : user.getPlanted()) {
            LatLng treePos = t.getPos();
            Marker m = mMap.addMarker(new MarkerOptions().position(treePos).title("Planted Trees").icon(BitmapDescriptorFactory.fromBitmap(tree)));
        }

        for (Tree t : User.getTotalPlanted()) {
            if (!user.getPlanted().contains(t)) {
                LatLng treePos = t.getPos();
                Marker m = mMap.addMarker(new MarkerOptions().position(treePos).title("Planted Trees").icon(BitmapDescriptorFactory.fromBitmap(tree)));
            }
        }

        for (Station s : stations) {
            LatLng stationPos = s.getPos();
            mMap.addMarker(new MarkerOptions().position(stationPos).title("Station").icon(BitmapDescriptorFactory.fromBitmap(station)));
        }

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                if (selected != null) {
                    selected.remove();
                }

                clickPos = latLng;
                selectedAnything = true;

                selected = mMap.addMarker(new MarkerOptions().position(latLng).title("Clicked here!"));

                mDesc.setText("Selected a location"// + latLng.latitude + "Long - " + latLng.longitude
                        + "\n Your Trees Planted: " + user.getPlanted().size()
                        + "\n Your Trees Requested: " + user.getRequested().size()
                        + "\n Your Account " + user.getBalance() );
                //mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            }
        });

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Tree tr = null;
                for (Tree t : User.getToPlant()) {
                    if (t.getPos().equals(MapActivity.getClickPos())) {
                        tr = t;
                    }
                }

                if (requestedTreesIds.contains(marker.getId())) {
                    if (selected != null) {
                        selectedAnything = false;

                        if (marker.equals(selected)) {
                            selectedMarker = false;

                            mDesc.setText("Selected nothing"
                                    + "\n Your Trees Planted: " + user.getPlanted().size()
                                    + "\n Your Trees Requested: " + user.getRequested().size()
                                    + "\n Your Account " + user.getBalance() );
                        } else {
                            selectedMarker = true;
                        }

                        selected.remove();
                    }

                    clickPos = marker.getPosition();

                    if (tr != null) {
                        mDesc.setText("Selected a tree requested by " + tr.getRequester().getName()
                                + "\n Your Trees Planted: " + user.getPlanted().size()
                                + "\n Your Trees Requested: " + user.getRequested().size()
                                + "\n Your Account " + user.getBalance() );
                    }

                    return true;
                } else {
                    if (tr != null) {
                        mDesc.setText("Selected a tree planted by " + tr.getPlanter().getName()
                                + "\n Your Trees Planted: " + user.getPlanted().size()
                                + "\n Your Trees Requested: " + user.getRequested().size()
                                + "\n Your Account " + user.getBalance() );
                    }
                    return false;
                }
            }
        });

//        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    public static LatLng getClickPos() {
        return clickPos;
    }
}

package com.example.geotree.Map;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.geotree.Leaderboard.LeaderboardActivity;
import com.example.geotree.MainActivity;
import com.example.geotree.R;
import com.example.geotree.Shop.ShopActivity;
import com.example.geotree.User.PlantTreeActivity;
import com.example.geotree.User.RequestTreeActivity;
import com.example.geotree.User.User;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Button mRequest, mPlant, mLogOut, mLeader, mShop;
    private TextView mDesc;
    private static LatLng clickPos;
    public static User user;
    private Marker selected;
    private boolean selectedAnything = false, selectedMarker = false;

    private int bitmapWidth = 100, bitmapHeight = 100;
    private Bitmap station, tree, sapling;

    private static ArrayList<Station> stations = new ArrayList<>();
    public static ArrayList<String> requestedTreesIds = new ArrayList<>();

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        mRequest = findViewById(R.id.request);
        mPlant = findViewById(R.id.plant);
        mLogOut = findViewById(R.id.logout);
        mLeader = findViewById(R.id.leader);
        mShop = findViewById(R.id.shop_button);

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

        stations.add(new Station(23, new LatLng(23, -109)));
        stations.add(new Station(42, new LatLng(41, -119)));
        stations.add(new Station(63, new LatLng(52.5, 13.4)));
        stations.add(new Station(60, new LatLng(55.8, 37.6)));
        stations.add(new Station(53, new LatLng(48.9, 2.4)));
        stations.add(new Station(50, new LatLng(37.8, 122.4)));
        stations.add(new Station(20, new LatLng(41.9, 12.5)));
        stations.add(new Station(37, new LatLng(34.1, 118.2)));
        stations.add(new Station(100, new LatLng(47.5, 19.0)));
        stations.add(new Station(3, new LatLng(49.3, 123.1)));
        stations.add(new Station(102, new LatLng(37.5, 122.0)));
        stations.add(new Station(120, new LatLng(37.98, 23.73 )));
        stations.add(new Station(214, new LatLng(0.3, 32.6)));
        stations.add(new Station(43, new LatLng(46.1, 18.2)));
        stations.add(new Station(500, new LatLng(32.89, 13.19)));
        stations.add(new Station(69, new LatLng(26.8, 30.8)));
        stations.add(new Station(72, new LatLng(48.9, 2.4)));
        stations.add(new Station(72, new LatLng(48.9, 2.4)));
        stations.add(new Station(16, new LatLng(28.6139, 77.2090)));
        stations.add(new Station(6, new LatLng(25.2, 55.3)));
        stations.add(new Station(64, new LatLng(-45.0312, 168.6626)));
        stations.add(new Station(82, new LatLng(34.1, -118.2)));
        stations.add(new Station(58, new LatLng(58.3, -134.41)));
        stations.add(new Station(9, new LatLng(49.3, -123.1)));
        stations.add(new Station(100, new LatLng(-79.687184,40.553454)));
        stations.add(new Station(100, new LatLng(-10.211121,-66.955774)));
        stations.add(new Station(54, new LatLng(-39.422615,-69.312091)));
        stations.add(new Station(101, new LatLng(-32.546813,-62.387409)));
        stations.add(new Station(59, new LatLng(35.600146,-582.024485)));
        stations.add(new Station(61, new LatLng(47.052160,-434.263379)));
        stations.add(new Station(73, new LatLng(-34.307144,-573.452746)));
        stations.add(new Station(92, new LatLng(9.830322,-710.740231)));

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

        mShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent intent;
                    intent = new Intent(getApplicationContext(), ShopActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                    return;
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

//        Places.initialize(getApplicationContext(), "AIzaSyCghiObJW7kYwAGkgIpTmtAsD07kGqHqv4");
//        PlacesClient placesClient = Places.createClient(this);

        if (!Places.isInitialized()) {
            Places.initialize(getApplicationContext(), "AIzaSyCghiObJW7kYwAGkgIpTmtAsD07kGqHqv4", Locale.US);
        }

        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);

        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME));
        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                Log.i(TAG, "Place: " + place.getName() + ", " + place.getId());
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i(TAG, "An error occurred: " + status);
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
            mMap.addMarker(new MarkerOptions().position(stationPos).title("Current Stock: " + s.getStock()).icon(BitmapDescriptorFactory.fromBitmap(station)));
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

                    if (selected != null) {
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
                        if (tr.getPlanter() != null) {
                            mDesc.setText("Selected a tree planted by " + tr.getPlanter().getName()
                                    + "\n Your Trees Planted: " + user.getPlanted().size()
                                    + "\n Your Trees Requested: " + user.getRequested().size()
                                    + "\n Your Account " + user.getBalance());
                        }
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
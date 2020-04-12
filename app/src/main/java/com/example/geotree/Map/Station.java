package com.example.geotree.Map;

import com.google.android.gms.maps.model.LatLng;

public class Station {
    private int stock;
    private LatLng pos;

    public Station(int stock, LatLng pos) {
        this.stock = stock;
        this.pos = pos;
    }

    public void removeTree(int trees) {
        stock -= trees;
    }

    public void addTree(int trees) {
        stock += trees;
    }

    public LatLng getPos() {
        return pos;
    }
}

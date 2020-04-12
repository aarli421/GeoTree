package com.example.geotree.Map;

import com.example.geotree.User.User;
import com.google.android.gms.maps.model.LatLng;

public class Tree {
    private boolean isPlanted = false;
    private User planter;
    private User requester;
    private LatLng pos;

    public Tree(User requester, LatLng pos) {
        this.requester = requester;
        this.pos = pos;
    }

    public Tree(User planter, User requester, LatLng pos) {
        this.planter = planter;
        this.requester = requester;
        this.pos = pos;
    }

    public User getPlanter() {
        return planter;
    }

    public User getRequester() {
        return requester;
    }

    public LatLng getPos() {
        return pos;
    }

    public void setPlanter(User a){
        planter = a;
    }

    public void setIsPlantedTrue(){
        isPlanted = true;
    }
}

package com.example.geotree.Map;

import com.example.geotree.User.User;

public class Tree {
    private boolean isPlanted;
    private User planter, requester;
    private Position loc;

    public Tree(User planter, User requester, Position loc) {

    }

    public User getPlanter() {
        return planter;
    }

    public User getRequester() {
        return requester;
    }

    public Position getLoc() {
        return loc;
    }
}

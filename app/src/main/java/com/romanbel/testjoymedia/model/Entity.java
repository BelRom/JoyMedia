package com.romanbel.testjoymedia.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by roman on 21.01.18.
 */

public class Entity {
    @SerializedName("shop")
    @Expose
    private List<Shop> shop = null;

    public List<Shop> getShop() {
        return shop;
    }

    public void setShop(List<Shop> shop) {
        this.shop = shop;
    }
}

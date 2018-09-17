package com.kh618.entmaa.RestaurantClasses;

public class RestaurantListItem {
    private int imageId;
    private String title;
    private String offer =null;

    public RestaurantListItem(int imageId, String title, String offer) {
        this.imageId = imageId;
        this.title = title;
        this.offer = offer;
    }

    public RestaurantListItem(int imageId, String title) {
        this.imageId = imageId;
        this.title = title;
    }

    public String getOffer() {
        return offer;
    }

    public int getImageId() {
        return imageId;
    }

    public String getTitle() {
        return title;
    }
}

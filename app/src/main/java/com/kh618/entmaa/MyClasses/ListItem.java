package com.kh618.entmaa.MyClasses;

public class ListItem  {
    private int imageId;
    private String title;

    public ListItem(int imageId, String title) {
        this.imageId = imageId;
        this.title = title;
    }

    public int getImageId() {
        return imageId;
    }

    public String getTitle() {
        return title;
    }
}

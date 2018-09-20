package com.kh618.entmaa.MyClasses;

import com.kh618.entmaa.R;

public class NotificationsItem {
    private int imageId;
    private String postedName;
    private String notificationContent;

    public NotificationsItem(String postedName, String notificationContent) {
        this.postedName = postedName;
        this.notificationContent = notificationContent;
        this.imageId = R.mipmap.notification;
    }

    public NotificationsItem(int imageId, String postedName, String notificationContent) {

        this.imageId = imageId;
        this.postedName = postedName;
        this.notificationContent = notificationContent;
    }

    public int getImageId() {
        return imageId;
    }

    public String getPostedName() {
        return postedName;
    }

    public String getNotificationContent() {
        return notificationContent;
    }
}

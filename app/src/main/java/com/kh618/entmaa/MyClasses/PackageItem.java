package com.kh618.entmaa.MyClasses;

public class PackageItem {
    private int backgroundId;
    private int imageId;
    private String packageName;
    private String packagrPrice;

    public PackageItem(int backgroundId, int imageId, String packageName, String packagrPrice) {
        this.backgroundId = backgroundId;
        this.imageId = imageId;
        this.packageName = packageName;
        this.packagrPrice = packagrPrice;
    }

    public void setBackgroundId(int backgroundId) {
        this.backgroundId = backgroundId;
    }

    public int getBackgroundId() {

        return backgroundId;
    }

    public int getImageId() {
        return imageId;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getPackagrPrice() {
        return packagrPrice;
    }
}

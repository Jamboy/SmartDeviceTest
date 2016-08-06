package com.example.jambo.smartdevicetest;

/**
 * Created by Jambo on 2016/8/6.
 */
public class ItemObject {
    private int imageId;
    private String deviceName;


    public ItemObject(){

    }

    public ItemObject(String deviceName){
        this.deviceName = deviceName;
    }


    public ItemObject(int imageId){
        this.imageId = imageId;
    }


    public ItemObject(int imageId, String deviceName){
        this.imageId = imageId;
        this.deviceName = deviceName;
    }


    public int getImageId(){
        return imageId;
    }


    public String getDeviceName() {
        return deviceName;
    }
}

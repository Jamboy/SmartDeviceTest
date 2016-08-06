package com.example.jambo.smartdevicetest;

import android.app.Application;
import java.net.Socket;
import java.util.HashMap;

/**
 * Created by Jambo on 2016/8/6.
 */
public class DefinApplication extends Application {
    private HashMap<String,Socket> hashMap;


    @Override public void onCreate() {
        super.onCreate();
        hashMap = new HashMap<>();
    }

    public void saveSocket(String key, Socket socket){
        hashMap.put(key,socket);
    }

    public Socket getSocket(String key){
        Socket socket = null;
        socket = hashMap.get(key);
        return socket;
    }
}

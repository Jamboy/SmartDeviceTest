package com.example.jambo.smartdevicetest;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * Created by Jambo on 2016/8/6.
 */
public class SmartLogin extends Activity {
    private EditText txt_ip;
    private EditText txt_port;
    private String str_ip;
    private String str_port;
    private int error;
    private Commanfun commanfun = null;
    private DefinApplication definApplication = null;
    private Socket clientsck;
    private int chooseId;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smart_login);
        //Intent intent = getIntent();
        chooseId = getIntent().getIntExtra("chooseName",0);
        initView();

        definApplication = (DefinApplication)getApplication();
    }


    private void initView() {
        txt_ip = (EditText) findViewById(R.id.tv_ip);
        txt_port = (EditText) findViewById(R.id.tv_port);
        commanfun = new Commanfun();
    }


    private void getInputValue() {
        str_ip = txt_ip.getText().toString().trim();
        str_port = txt_port.getText().toString().trim();
    }


    public void doWorkAfterError(int _errno) {
        if (_errno == 1) {
            txt_ip.setFocusable(true);
            txt_ip.requestFocus();
            Log.d("er", "1");
            return;
        }
        if (_errno == 2) {
            txt_port.setFocusable(true);
            txt_port.requestFocus();
            Log.d("er", "2");
        }
        if (_errno == 3) {
            txt_ip.setText("");
            txt_ip.setFocusable(true);
            Log.d("er", "3");
            txt_ip.requestFocus();
        }
        if (_errno == 4) {
            txt_port.setText("");
            txt_port.setFocusable(true);
            txt_port.requestFocus();
            Log.d("er", "4");
            return;
        }
        if (_errno == 5) {
            txt_port.setText("");
            txt_ip.setText("");
            txt_ip.setFocusable(true);
            txt_port.requestFocus();
        }
    }


    public void showMessageAfterError(Context context, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("错误提示");
        builder.setMessage(message);
        builder.setPositiveButton("ok", new AlertDialog.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int which) {
                doWorkAfterError(error);
            }
        });
        builder.create().show();
    }


    public void onClick(View view) {
        getInputValue();
        switch (view.getId()) {
            case R.id.btn_connect:
                if (commanfun.isEmpty(str_ip)) {
                    error = 1;
                    doWorkAfterError(error);
                    showMessageAfterError(SmartLogin.this,"IP地址为空");
                    return;
                }
                if (commanfun.isIPFormart(str_ip) && commanfun.isPortFormat(str_port)){
                    error = 5;
                    doWorkAfterError(error);
                    showMessageAfterError(this,"IP格式错误，端口值错误");
                    return;
                }
                if (commanfun.isEmpty(str_port)) {
                    error = 2;
                    doWorkAfterError(error);
                    showMessageAfterError(this,"端口值为空");
                    return;
                }
                if (commanfun.isIPFormart(str_ip)) {
                    error = 3;
                    doWorkAfterError(error);
                    showMessageAfterError(this,"IP地址格式错误");
                    return;
                }
                if (commanfun.isPortFormat(str_port)) {
                    error = 4;
                    doWorkAfterError(error);
                    showMessageAfterError(this,"端口值错误");
                    return;
                }
                Log.d("onClick", str_ip);
                Log.d("onClick", "fff");

                ConnectNetTast netWork = new ConnectNetTast();
                netWork.connectToHost(str_ip,str_port);
                break;

            case R.id.btn_cancel:
                error = 5;
                doWorkAfterError(error);

                break;

        }
    }


    class ConnectNetTast extends AsyncTask<String, Void, Object> {
        @Override protected Object doInBackground(String... params) {
            String ip = params[0];
            String port = params[1];

            boolean connectResult = connectToHost(ip,port);
            //Boolean connectResult = new Boolean(connectToHost(ip,port));
            if (connectResult){
                return new Integer(1);
            }
            return null;
        }


        @Override protected void onPostExecute(Object o) {
            if (o == null){
                error = 5;
                showMessageAfterError(SmartLogin.this,"网络连接失败");
                return;
            }
            Intent intent = null;
            Log.d("onClick", chooseId+"");
            switch (chooseId){
                case 0:
                    intent = new Intent(SmartLogin.this, SmartLightActivity.class);
                    startActivity(intent);
                    break;
                case 1:
                    intent = new Intent(SmartLogin.this, SmartCarActivity.class);
                    startActivity(intent);
                    break;
                case 2:
                    intent = new Intent(SmartLogin.this, SmartWindowControl.class);
                    startActivity(intent);
                    break;
                case 3:
                    intent = new Intent(SmartLogin.this, SmartPanControl.class);
                    startActivity(intent);
                    break;

            }

        }


        public boolean connectToHost(String ip, String port) {
            boolean isConnected = false;

            SocketAddress address = new InetSocketAddress(ip,Integer.parseInt(port));
            clientsck = new Socket();
            try {
                clientsck.connect(address,5000);
                definApplication.saveSocket("clientSck",clientsck);
                isConnected =true;

            }catch (Exception e){
                e.printStackTrace();
            }
            return isConnected;
        }
    }
}



package com.example.jambo.smartdevicetest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<ItemObject> list = null;
    private String[] deviceName = {"智    能    灯","智    能    车","智  能 窗 帘","智能电压锅"};
    private int[] images = {R.drawable.bulb,R.drawable.car,R.drawable.blind,R.drawable.pob};
    private ListView listView;
    private DefineDataAdapter defineDataAdapter = null;
    private Class[] className = {SmartLogin.class};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }


    public void initView(){
        listView = (ListView) findViewById(R.id.list_view);
        if (list == null){
            list = new ArrayList<>();
            for (int i =0; i <deviceName.length; i++){
                ItemObject itemObject = new ItemObject(images[i],deviceName[i]);

                list.add(itemObject);
            }

        }
        defineDataAdapter = new DefineDataAdapter(MainActivity.this,R.layout.activity_dataitem,list);
        listView.setAdapter(defineDataAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this,className[0]);
                intent.putExtra("chooseName",position);
                Log.d("MainActivity",position + "");
                startActivity(intent);
            }
        });
    }


}

package com.example.jambo.smartdevicetest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by Jambo on 2016/8/6.
 */

public class DefineDataAdapter extends BaseAdapter {
    private ArrayList<ItemObject> mList;
    private LayoutInflater layoutInflater = null;
    private int layoutId;
    private Context context = null;




    public DefineDataAdapter(Context context, int layoutId, ArrayList<ItemObject> mList){
        this.context =  context;
        this.layoutId = layoutId;
        layoutInflater = LayoutInflater.from(context);
        this.mList = mList;
    }



    @Override public int getCount() {
        return mList.size();
    }


    @Override public Object getItem(int position) {
        return mList.get(position);
    }


    @Override public long getItemId(int position) {
        return position;
    }


    @Override public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (viewHolder == null){
            viewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(layoutId,null);
            viewHolder.iv_viewHold = (ImageView) convertView.findViewById(R.id.iv_item_image);
            viewHolder.tv_viewHold = (TextView) convertView.findViewById(R.id.iv_item_title);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (mList !=null){
            ItemObject itemObject = mList.get(position);
            viewHolder.tv_viewHold.setText(itemObject.getDeviceName());
            viewHolder.iv_viewHold.setImageResource(itemObject.getImageId());
        }

        return convertView;
    }

    class ViewHolder{
        private ImageView iv_viewHold;
        private TextView tv_viewHold;
    }
}

package com.caknow.android.customer.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.caknow.android.customer.app.model.Vehicle;
import com.caknow.android.customer.app.ui.VehicleView;
import com.caknow.app.R;

import java.util.List;

/**
 * Created by junu on 1/2/2017.
 */

public class GarageAdapter extends BaseAdapter {

    private List<Vehicle> vehicleList;
    private Context mContext;
    private LayoutInflater inflater;

     public GarageAdapter(Context context, List<Vehicle> vehicles){
        this.vehicleList = vehicles;
         this.mContext = context;
         this.inflater = (LayoutInflater) this.mContext
                 .getSystemService(Context.LAYOUT_INFLATER_SERVICE);     }

    @Override
    public int getCount() {
        return vehicleList.size();
    }

    @Override
    public Object getItem(int position) {
        return vehicleList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final VehicleView viewHolder;

        //Timber.d("position =" + position);
        if (convertView == null ) {
            if(this.inflater != null) {
                convertView = this.inflater.inflate(R.layout.garage_list_item, parent, false);
                viewHolder = new VehicleView(convertView, vehicleList.get(position), position);
                convertView.setTag(viewHolder);
            }
        } else {
            viewHolder = (VehicleView) convertView.getTag();
        }

        return convertView;
    }
}

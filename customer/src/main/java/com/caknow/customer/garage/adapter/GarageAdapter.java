package com.caknow.customer.garage.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.caknow.customer.util.net.garage.Vehicle;

import com.caknow.app.R;
import com.caknow.customer.garage.VehicleView;

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
    public Vehicle getItem(int position) {
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
                convertView = this.inflater.inflate(R.layout.list_item_garage, parent, false);
                viewHolder = new VehicleView(convertView, vehicleList.get(position));
                convertView.setTag(viewHolder);
        } else {
            viewHolder = (VehicleView) convertView.getTag();
        }
        if(viewHolder.getVehicle().getQuoteCount() > 0){
            viewHolder.quoteBubble.setVisibility(View.VISIBLE);
            viewHolder.quoteBubble.setText(String.valueOf(viewHolder.getVehicle().getQuoteCount()).concat(" Quotes"));
        }
        return convertView;
    }
}

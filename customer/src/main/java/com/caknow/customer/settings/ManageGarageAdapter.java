package com.caknow.customer.settings;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.caknow.app.R;
import com.caknow.customer.garage.Vehicle;

import java.util.List;

/**
 * Created by junu on 1/2/2017.
 */

public class ManageGarageAdapter extends BaseAdapter {

    private List<Vehicle> vehicleList;
    private Context mContext;
    private LayoutInflater inflater;

    public ManageGarageAdapter(Context context, List<Vehicle> vehicles){
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
        final ManageVehicleView viewHolder;

        //Timber.d("position =" + position);
        if (convertView == null ) {
            if(this.inflater != null) {
                convertView = this.inflater.inflate(R.layout.list_item_manage_car, parent, false);
                viewHolder = new ManageVehicleView(convertView, vehicleList.get(position));
                convertView.setTag(viewHolder);
            }
        } else {
            viewHolder = (ManageVehicleView) convertView.getTag();
        }

        return convertView;
    }
}

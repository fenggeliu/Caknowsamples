package com.caknow.customer.service.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.caknow.app.R;
import com.caknow.customer.service.ServiceItemView;
import com.caknow.customer.util.net.service.Services;

import java.util.ArrayList;

/**
 * Created by junu on 1/2/2017.
 */

public class ServiceTypeAdapter extends BaseAdapter {

    private ArrayList<Services> serviceItemList;
    private Context mContext;
    private LayoutInflater inflater;

    public ServiceTypeAdapter(Context context, ArrayList<Services> serviceItems) {
        this.serviceItemList = serviceItems;
        this.mContext = context;
        this.inflater = (LayoutInflater) this.mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return serviceItemList.size();
    }

    @Override
    public Services getItem(int position) {
        return serviceItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ServiceItemView viewHolder;

        //Timber.d("position =" + position);
        if (convertView == null) {
            convertView = this.inflater.inflate(R.layout.list_item_service_type, parent, false);
            viewHolder = new ServiceItemView(convertView, serviceItemList.get(position));
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ServiceItemView) convertView.getTag();
        }
        Services item = serviceItemList.get(position);
        viewHolder.setTitle(item.getName());
        viewHolder.setImage(item.getIcon());
        viewHolder.setItem(item);

        return convertView;
    }
}

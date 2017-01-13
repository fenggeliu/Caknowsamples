package com.caknow.customer.service.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.caknow.app.R;
import com.caknow.customer.service.model.Maintenance;
import com.caknow.customer.service.model.Repair;
import com.caknow.customer.service.model.VehicleServiceInterface;

import java.util.ArrayList;
import java.util.List;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

/**
 * Created by junu on 1/11/2017.
 */

public class VehicleServiceAdapter extends BaseAdapter implements StickyListHeadersAdapter {

    List<Repair> repairsList;
    List<Maintenance> maintenanceList;
    List<VehicleServiceInterface> combinedList;


    private LayoutInflater inflater;

    public VehicleServiceAdapter(Context context, List<Repair> repairList, List<Maintenance> maintenanceList) {
        inflater = LayoutInflater.from(context);
        combinedList = new ArrayList<>();
        this.repairsList = repairList;
        this.maintenanceList = maintenanceList;
        combinedList.addAll(repairList);
        combinedList.addAll(maintenanceList);
    }

    @Override
    public int getCount() {
        if (combinedList == null) {
            return 0;
        }
        return combinedList.size();
    }

    @Override
    public VehicleServiceInterface getItem(int position) {
        return combinedList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ServiceRequestViewHolder holder;

        if (convertView == null) {
            holder = new ServiceRequestViewHolder();
            convertView = inflater.inflate(R.layout.list_item_vehicle_service_request, parent, false);
            holder.text = (TextView) convertView.findViewById(R.id.srli_super_title);
            convertView.setTag(holder);
        } else {
            holder = (ServiceRequestViewHolder) convertView.getTag();
        }

        holder.text.setText(combinedList.get(position).getDisplayTitle());

        return convertView;
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        HeaderViewHolder holder;
        if (convertView == null) {
            holder = new HeaderViewHolder();
            convertView = inflater.inflate(R.layout.list_header_vehicle_service, parent, false);
            holder.text = (TextView) convertView.findViewById(R.id.service_header_textview);
            convertView.setTag(holder);
        } else {
            holder = (HeaderViewHolder) convertView.getTag();
        }
        String headerText;
        if (position < repairsList.size() - 1) {
            headerText = "Repairs";
        } else {
            headerText = "Maintenance";

        }
        //set header text as first char in name
        holder.text.setText(headerText);
        return convertView;
    }

    @Override
    public long getHeaderId(int position) {
        if (position < repairsList.size() - 1) {
            return 'A';
        } else {
            return 'B';
        }

    }

    class HeaderViewHolder {
        TextView text;
    }

    class ServiceRequestViewHolder {
        TextView text;
    }

}
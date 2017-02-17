package com.caknow.customer.service.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.caknow.app.R;
import com.caknow.customer.util.TimeUtils;
import com.caknow.customer.widget.BaseFragment;
import com.caknow.customer.CAKNOWApplication;
import com.caknow.customer.garage.fragment.VehicleServiceFragment;
import com.caknow.customer.service.model.Maintenance;
import com.caknow.customer.service.model.Repair;
import com.caknow.customer.service.model.VehicleServiceInterface;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

/**
 * Created by junu on 1/11/2017.
 */

public class VehicleServiceAdapter extends BaseAdapter implements StickyListHeadersAdapter {

    List<Repair> repairsList;
    List<Maintenance> maintenanceList;
    List<VehicleServiceInterface> combinedList;
    BaseFragment fragment;
    private VehicleServiceFragment.OnListFragmentInteractionListener mListener;


    private LayoutInflater inflater;

    public VehicleServiceAdapter(Context context, BaseFragment fragment, List<Repair> repairList, List<Maintenance> maintenanceList, VehicleServiceFragment.OnListFragmentInteractionListener listener) {
        inflater = LayoutInflater.from(context);
        combinedList = new ArrayList<>();
        this.repairsList = repairList;
        this.maintenanceList = maintenanceList;
        this.fragment = fragment;
        combinedList.addAll(maintenanceList);
        combinedList.addAll(repairList);
        this.mListener = listener;
    }

    @Override
    public int getCount() {
        if(combinedList == null){
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
    public View getView(int currentPosition, View convertView, ViewGroup parent) {
        ServiceRequestViewHolder holder;
        int position = currentPosition;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item_vehicle_service_request_new, parent, false);
            if(position > maintenanceList.size()){
                position = position - 1;
            }
            holder = new ServiceRequestViewHolder(convertView, combinedList.get(position));
            convertView.setTag(holder);
        } else {
            holder = (ServiceRequestViewHolder) convertView.getTag();
        }

        holder.serviceItemTitle.setText(combinedList.get(position).getDisplayTitle());
        holder.serviceItemSubTitle.setText(combinedList.get(position).getServiceField());
        Glide.with(CAKNOWApplication.get()).load(holder.mItem.getIconUrl()).into(holder.serviceItemIcon);

        switch(holder.mItem.getStatus()){
            case 1:
                if (holder.mItem.getQuoteCount() == 0) {
                    holder.serviceItemQuote.setVisibility(View.GONE);
                } else {
                    holder.serviceItemQuote.setVisibility(View.VISIBLE);
                    holder.serviceItemQuote.setText(
                            String.valueOf(holder.mItem.getQuoteCount()).concat(" Quotes"));
                }
                break;
            case 2:
                holder.serviceItemQuote.setVisibility(View.VISIBLE);
                holder.serviceItemQuote.setText("Accepted");
                break;
            case 3:
                holder.serviceItemQuote.setVisibility(View.VISIBLE);
                holder.serviceItemQuote.setBackgroundResource(R.drawable.quote_gray_2x);
                holder.serviceItemQuote.setText("In Service");
                break;
            case 8:
                holder.serviceItemQuote.setVisibility(View.VISIBLE);
                holder.serviceItemQuote.setText("Complete");
                break;
            default:
                break;
        }

        Date df = new java.util.Date(holder.mItem.getCreateTime());

        String text = TimeUtils.SHORT_DATE_FORMAT.format(df);
        holder.serviceItemTime.setText(text);
        holder.serviceItemCardView.setOnClickListener(v -> mListener.onListFragmentInteraction(holder.mItem));
        return convertView;
    }

    /**
     * Currently using type to key off of whether it is a maintenance or repair item. Probably not the best approach
     *
     * TODO
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
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
        String headerText="";

        if (getHeaderId(position)==1) {
            headerText = "Repairs";
        }
        else{
            headerText = "Maintenance";
        }
        //set header text as first char in name
        holder.text.setText(headerText);
        return convertView;
    }

    @Override
    public long getHeaderId(int position) {
        if(repairsList.size() > 0 && position < repairsList.size()){
            return 1;
        }
        else{
            return 2;
        }


    }

    class HeaderViewHolder {
        TextView text;
    }

    class ServiceRequestViewHolder {
        View parentView;
        CardView serviceItemCardView;
        ImageView serviceItemIcon;
        TextView serviceItemTime;
        TextView serviceItemTitle;
        TextView serviceItemSubTitle;
        TextView serviceItemQuote;
        ImageView serviceItemNextButton;
        VehicleServiceInterface mItem;

        ServiceRequestViewHolder(View v, VehicleServiceInterface mItem){
            this.parentView = v;
            this.mItem = mItem;
            serviceItemCardView = (CardView) v.findViewById(R.id.card_view);
            serviceItemIcon = (ImageView) v.findViewById(R.id.service_item_icon);
            serviceItemTime = (TextView) v.findViewById(R.id.service_item_time_display);
            serviceItemTitle = (TextView) v.findViewById(R.id.service_item_title);
            serviceItemSubTitle = (TextView) v.findViewById(R.id.service_item_sub_title);
            serviceItemQuote = (TextView) v.findViewById(R.id.service_item_quote_display);
            serviceItemNextButton = (ImageView) v.findViewById(R.id.service_item_next_button);
        }
    }

}
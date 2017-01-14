package com.caknow.customer.history;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.telephony.PhoneNumberUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.caknow.app.R;
import com.caknow.customer.util.net.history.History;

import java.util.ArrayList;
import java.util.TreeSet;

/**
 * Created by junu on 1/15/2017.
 */

public class HistoryDetailsAdapter extends BaseAdapter {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_SERVICE_INFO = 1;
    private static final int TYPE_SERVICE_FEE = 2;
    private static final int TYPE_REVIEWS = 3;
    private static final int TYPE_VEHICLE_INFO = 4;
    private static final int TYPE_CALL = 5;
    private static final int TYPE_SEPARATOR = 6;
    private static final int TYPE_SERVICE_ITEM = 7;

    private ArrayList<String> mData = new ArrayList();
    private LayoutInflater mInflater;
    private History history;
    private TreeSet mSeparatorsSet = new TreeSet();
    private HistoryDetailsFragment fragment;

    public HistoryDetailsAdapter(HistoryDetailsFragment fragment, History history) {
        this.fragment = fragment;
        this.history = history;
        mInflater = (LayoutInflater) fragment.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void addItem(final String item) {
        mData.add(item);
        notifyDataSetChanged();
    }

    public void addSeparatorItem(final String item) {
        mData.add(item);
        // save separator position
        mSeparatorsSet.add(mData.size() - 1);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        switch(position){
            case TYPE_SERVICE_INFO:
                return TYPE_SERVICE_INFO;
            case TYPE_SERVICE_FEE:
                return TYPE_SERVICE_FEE;
             case TYPE_REVIEWS:
                return TYPE_REVIEWS;
             case TYPE_VEHICLE_INFO:
                return TYPE_VEHICLE_INFO;
             case TYPE_CALL:
                return TYPE_CALL;
             case TYPE_SEPARATOR:
                return TYPE_SEPARATOR;
             case TYPE_SERVICE_ITEM:
                return TYPE_SERVICE_ITEM;
            default:
                return TYPE_SERVICE_ITEM;

        }
    }

    @Override
    public int getViewTypeCount() {
        return 6;
    }

    @Override
    public int getCount() {
        return 7 + mData.size();
    }

    @Override
    public String getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        int type = getItemViewType(position);
        System.out.println("getView " + position + " " + convertView + " type = " + type);
        if (convertView == null) {
            holder = new ViewHolder();
            switch (type) {
                case TYPE_SERVICE_INFO:
                    convertView = mInflater.inflate(R.layout.list_item_quote_details, null);
                    holder.textView = (TextView)convertView.findViewById(R.id.text);
                    break;
                case TYPE_SERVICE_FEE:
                    String serviceFee = history.getServiceFee();
                    if(serviceFee.isEmpty()){
                        serviceFee = "$0.00";
                    }
                    convertView = mInflater.inflate(R.layout.list_item_quote_service_fee, null);
                    ((TextView)convertView.findViewById(R.id.service_fee_textview)).setText(serviceFee);
                    break;
                case TYPE_REVIEWS:
                    convertView = mInflater.inflate(R.layout.list_item_quote_reviews, null);
                    history.getAverageRating();
                    holder.textView = (TextView)convertView.findViewById(R.id.text);
                    break;
                case TYPE_VEHICLE_INFO:
                    convertView = mInflater.inflate(R.layout.list_item_quote_vehicle_information, null);
                    holder.textView = (TextView)convertView.findViewById(R.id.text);
                    break;
                case TYPE_CALL:
                    convertView = mInflater.inflate(R.layout.list_item_quote_call, null);
                    ((TextView)convertView.findViewById(R.id.list_item_phone)).setText(history.getShopPhone());
                    convertView.setOnClickListener(v -> {
                        try{
                            if(fragment.checkCallPermission()){
                                String phoneNumber = PhoneNumberUtils.formatNumber(history.getShopPhone(), "+1");
                                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
                                fragment.getActivity().startActivity(intent);
                            }
                        }catch(Exception e){

                        }
                    });
                    break;
                case TYPE_SEPARATOR:
                    convertView = mInflater.inflate(R.layout.list_header_vehicle_service, null);
                    holder.textView = (TextView)convertView.findViewById(R.id.service_header_textview);
                    break;
                case TYPE_SERVICE_ITEM:
                default:
                    convertView = mInflater.inflate(R.layout.list_item_vehicle_service_request, null);
                    holder.textView = (TextView)convertView.findViewById(R.id.service_item_title);
                    break;

            }
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }
        //holder.textView.setText(mData.get(position));
        return convertView;
    }

    public static class ViewHolder {
        public TextView textView;
    }

}


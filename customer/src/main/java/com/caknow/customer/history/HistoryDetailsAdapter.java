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
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.caknow.app.R;
import com.caknow.customer.CAKNOWApplication;
import com.caknow.customer.util.TimeUtils;
import com.caknow.customer.util.net.history.History;
import com.caknow.customer.util.net.history.ServiceList;

import java.util.ArrayList;
import java.util.TreeSet;

/**
 * Created by junu on 1/15/2017.
 */

public class HistoryDetailsAdapter extends BaseAdapter {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_SERVICE_FEE = 1;
    private static final int TYPE_REVIEWS = 2;
    private static final int TYPE_SHOP_INFO_SEPARATOR = 3;
    private static final int TYPE_SHOP_LOCATION = 4;
    private static final int TYPE_CALL = 5;
    private static final int TYPE_VEHICLE_INFO_SEPARATOR = 6;
    private static final int TYPE_VEHICLE_MAKE = 7;
    private static final int TYPE_VEHICLE_MODEL = 8;
    private static final int TYPE_VEHICLE_YEAR= 9;
    private static final int TYPE_VEHICLE_TRIM= 10;
    private static final int TYPE_VEHICLE_MILEAGE= 11;
    private static final int TYPE_REPAIR_SEPARATOR= 12;
    private static final int TYPE_SERVICE_ITEM= 13;
    private static final int TYPE_ORDER_NUMBER_SEPARATOR= 14;
    private static final int TYPE_ORDER_NUMBER= 15;

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
            case TYPE_HEADER:
                return TYPE_HEADER;
            case TYPE_SERVICE_FEE:
                return TYPE_SERVICE_FEE;
             case TYPE_REVIEWS:
                return TYPE_REVIEWS;
             case TYPE_SHOP_INFO_SEPARATOR:
                return TYPE_SHOP_INFO_SEPARATOR;
            case TYPE_SHOP_LOCATION:
                return TYPE_SHOP_LOCATION;
            case TYPE_CALL:
                return TYPE_CALL;
            case TYPE_VEHICLE_INFO_SEPARATOR:
                return TYPE_VEHICLE_INFO_SEPARATOR;
            case TYPE_VEHICLE_MAKE:
                return TYPE_VEHICLE_MAKE;
            case TYPE_VEHICLE_MODEL:
                return TYPE_VEHICLE_MODEL;
            case TYPE_VEHICLE_YEAR:
                return TYPE_VEHICLE_YEAR;
            case TYPE_VEHICLE_TRIM:
                return TYPE_VEHICLE_TRIM;
            case TYPE_VEHICLE_MILEAGE:
                return TYPE_VEHICLE_MILEAGE;
            case TYPE_REPAIR_SEPARATOR:
                return TYPE_REPAIR_SEPARATOR;
            case TYPE_SERVICE_ITEM:
                return TYPE_SERVICE_ITEM;
            case TYPE_ORDER_NUMBER_SEPARATOR:
                return TYPE_ORDER_NUMBER_SEPARATOR;
            case TYPE_ORDER_NUMBER:
                default:
                return TYPE_ORDER_NUMBER;

        }
    }

    @Override
    public int getViewTypeCount() {
        return 16;
    }

    @Override
    public int getCount() {
        return 16;
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
                case TYPE_HEADER:
                    convertView = mInflater.inflate(R.layout.transparent_header_view, null);
                    ((TextView)convertView.findViewById(R.id.service_shop_name)).setText(history.getShopName());
                    convertView.findViewById(R.id.space).setVisibility(View.GONE);
                    convertView.findViewById(R.id.service_shop_name).setVisibility(View.VISIBLE);
                    Glide.with(CAKNOWApplication.get()).load(history.getShopImage()).into((ImageView)convertView.findViewById(R.id.header_image_view));
                    (convertView.findViewById(R.id.header_image_view)).setVisibility(View.VISIBLE);

                    break;
//                case TYPE_SERVICE_INFO:
//                    convertView = mInflater.inflate(R.layout.list_item_quote_details, null);
//                    holder.textView = (TextView)convertView.findViewById(R.id.text);
//                    break;
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
                case TYPE_SHOP_INFO_SEPARATOR:
                    convertView = mInflater.inflate(R.layout.list_header_vehicle_service, null);

                    holder.textView = (TextView) convertView.findViewById(R.id.service_header_textview);
                    holder.textView.setText("Shop Info");
                    break;
                case TYPE_SHOP_LOCATION:
                    convertView = mInflater.inflate(R.layout.list_item_quote_location, null);
                    if (history == null) {
                        convertView.setVisibility(View.GONE);
                        break;
                    }
                    holder.textView = (TextView) convertView.findViewById(R.id.list_item_address_textview);
                    String formattedAddress = history.getShopAddress();
                    try {
                        String[] addressParts = history.getShopAddress().split(",", 2);
                        formattedAddress = addressParts[0].concat("\n").concat(addressParts[1].trim());
                    }catch(Exception e){}
                    holder.textView.setText(formattedAddress);
                    break;
//                case TYPE_VEHICLE_INFO:
//                    convertView = mInflater.inflate(R.layout.list_item_quote_vehicle_information, null);
//                    holder.textView = (TextView)convertView.findViewById(R.id.text);
//                    break;
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
                case TYPE_VEHICLE_INFO_SEPARATOR:
                    convertView = mInflater.inflate(R.layout.list_header_vehicle_service, null);
                    holder.textView = (TextView)convertView.findViewById(R.id.service_header_textview);
                    holder.textView.setText("Vehicle Info");
                    break;
                case TYPE_VEHICLE_MAKE:
                    convertView = mInflater.inflate(R.layout.list_item_vehicle_information, null);
                    ((TextView)convertView.findViewById(R.id.vehicle_info_label)).setText("Make");
                    holder.textView = (TextView)convertView.findViewById(R.id.vehicle_info_text);
                    holder.textView.setText(history.getVehicle().getMake());
                    break;
                case TYPE_VEHICLE_MODEL:
                    convertView = mInflater.inflate(R.layout.list_item_vehicle_information, null);
                    ((TextView)convertView.findViewById(R.id.vehicle_info_label)).setText("Model");
                    holder.textView = (TextView)convertView.findViewById(R.id.vehicle_info_text);
                    holder.textView.setText(history.getVehicle().getModel());
                    break;
                case TYPE_VEHICLE_YEAR:
                    convertView = mInflater.inflate(R.layout.list_item_vehicle_information, null);
                    ((TextView)convertView.findViewById(R.id.vehicle_info_label)).setText("Year");
                    holder.textView = (TextView)convertView.findViewById(R.id.vehicle_info_text);
                    holder.textView.setText(history.getVehicle().getYear());
                    break;
                case TYPE_VEHICLE_TRIM:
                    convertView = mInflater.inflate(R.layout.list_item_vehicle_information, null);
                    ((TextView)convertView.findViewById(R.id.vehicle_info_label)).setText("Trim");
                    holder.textView = (TextView)convertView.findViewById(R.id.vehicle_info_text);
                    holder.textView.setText(history.getVehicle().getTrim());
                    break;
                case TYPE_VEHICLE_MILEAGE:
                    convertView = mInflater.inflate(R.layout.list_item_vehicle_information, null);
                    ((TextView)convertView.findViewById(R.id.vehicle_info_label)).setText("Mileage");
                    holder.textView = (TextView)convertView.findViewById(R.id.vehicle_info_text);
                    holder.textView.setText(history.getVehicle().getMileage());
                    break;
                case TYPE_REPAIR_SEPARATOR:
                    convertView = mInflater.inflate(R.layout.list_header_vehicle_service, null);

                    holder.textView = (TextView) convertView.findViewById(R.id.service_header_textview);
                    holder.textView.setText("Service Items");
                    break;
                case TYPE_SERVICE_ITEM:
                    convertView = mInflater.inflate(R.layout.list_item_vehicle_service_request_new, null);
                    holder.textView = (TextView) convertView.findViewById(R.id.service_item_title);
                    ServiceList serviceList = history.getServiceList().get(0);
                    holder.textView.setText(serviceList.getField());
                    holder.textView.invalidate();
                    ((TextView) convertView.findViewById(R.id.service_item_sub_title)).setText(history.getServiceCategory());
                    convertView.findViewById(R.id.service_item_sub_title).invalidate();
                    ((TextView) convertView.findViewById(R.id.service_item_time_display)).setText(TimeUtils.getShortTime(history.getUpdateTime() * 1000));
                    convertView.findViewById(R.id.service_item_time_display).invalidate();
                    Glide.with(fragment).load(serviceList.getIcon()).fitCenter().into(((ImageView) convertView.findViewById(R.id.service_item_icon)));
                    convertView.findViewById(R.id.service_item_next_button).setVisibility(View.GONE);
                    break;
                case TYPE_ORDER_NUMBER_SEPARATOR:
                    convertView = mInflater.inflate(R.layout.list_header_vehicle_service, null);

                    holder.textView = (TextView) convertView.findViewById(R.id.service_header_textview);
                    holder.textView.setText("Order Number");
                    break;
                case TYPE_ORDER_NUMBER:
                default:
                    convertView = mInflater.inflate(R.layout.list_item_vehicle_information, null);
                    ((TextView)convertView.findViewById(R.id.vehicle_info_text)).setVisibility(View.GONE);
                    holder.textView = (TextView)convertView.findViewById(R.id.vehicle_info_label);
                    holder.textView.setText(history.getOrderNo());
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


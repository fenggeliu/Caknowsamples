package com.caknow.customer.job;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.caknow.app.R;
import com.caknow.customer.service.model.VehicleServiceInterface;
import com.caknow.customer.util.net.service.quotes.QuoteList;
import com.caknow.customer.util.net.service.quotes.QuotePayload;

import java.util.ArrayList;
import java.util.TreeSet;

/**
 * This adapter is used to populate list items for NEW service requests
 *
 * Status < 1?
 *
 * Created by junu on 1/15/2017.
 */

public class EmptyJobDetailListAdapter extends BaseAdapter {

    private static final int TYPE_SHOP_HEADER= 0;
    private static final int TYPE_SERVICE_ITEM = 1;
    private static final int TYPE_TIME_FRAME = 3;
    private static final int TYPE_LIST_SEPARATOR = 2;

    private ArrayList<String> mData = new ArrayList();
    private LayoutInflater mInflater;

    private TreeSet mSeparatorsSet = new TreeSet();
    private Context context;
    VehicleServiceInterface item;
    QuotePayload payload;
    private QuoteList currentQuote;


    public EmptyJobDetailListAdapter(Context context, VehicleServiceInterface item, QuotePayload payload) {
        this.context = context;
        this.item = item;
        this.payload = payload;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getItemViewType(int position) {
        switch(position){
            case TYPE_SHOP_HEADER:
                return TYPE_SHOP_HEADER;
             case TYPE_SERVICE_ITEM:
                return TYPE_SERVICE_ITEM;
            case TYPE_LIST_SEPARATOR:
                return TYPE_LIST_SEPARATOR;
            case TYPE_TIME_FRAME:
            default:
                return TYPE_TIME_FRAME;

        }
    }

    @Override
    public int getViewTypeCount() {
        return 4;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public String getItem(int position) {
        //TODO return a service item?
        return "";
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    // Not recycled, when this list gets longer will need optimizations
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        int type = getItemViewType(position);
        System.out.println("getView " + position + " " + convertView + " type = " + type);
        if (convertView == null) {
            holder = new ViewHolder();
            switch (type) {
                case TYPE_SHOP_HEADER:
                    convertView = mInflater.inflate(R.layout.list_header_service_detail, null);
                    convertView.setBackground(context.getResources().getDrawable(R.drawable.service_detail_step1));
                    convertView.findViewById(R.id.service_step_1_textview).setVisibility(View.VISIBLE);
                    convertView.invalidate();
                    break;

                case TYPE_SERVICE_ITEM:
                default:
                    convertView = mInflater.inflate(R.layout.list_item_vehicle_service_request_new, null);
                    holder.textView = (TextView)convertView.findViewById(R.id.service_item_title);
                    // Update service item text
                    ((TextView)convertView.findViewById(R.id.service_item_title)).setText(String.valueOf(item.getServiceCatagory()));
                    ((TextView)convertView.findViewById(R.id.service_item_sub_title)).setText(String.valueOf(item.getServiceField()));
                    Glide.with(context).load(item.getIconUrl()).into(((ImageView)convertView.findViewById(R.id.service_item_icon)));
                    convertView.findViewById(R.id.service_item_sub_title).invalidate();
                    convertView.findViewById(R.id.service_item_title).invalidate();

                    break;
                case TYPE_LIST_SEPARATOR:
                    convertView = mInflater.inflate(R.layout.list_header_vehicle_service, null);
//                    if (affiliate == null) {
//                        convertView.setVisibility(View.GONE);
//                        break;
//                    }
                    holder.textView = (TextView) convertView.findViewById(R.id.service_header_textview);
                    holder.textView.setText("Time Frame");

                    break;
                case TYPE_TIME_FRAME:
                    convertView = mInflater.inflate(R.layout.list_item_time_frame, null);
                    ((TextView)convertView.findViewById(R.id.time_frame)).setText(String.valueOf(payload.getTimeframe()));
                    convertView.findViewById(R.id.time_frame).invalidate();
            }
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }
        return convertView;
    }

    public static class ViewHolder {
        public TextView textView;
    }

}


package com.caknow.customer.quote;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.telephony.PhoneNumberUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.caknow.app.R;
import com.caknow.customer.util.TimeUtils;
import com.caknow.customer.util.net.history.History;
import com.caknow.customer.util.net.service.quotes.QuoteList;
import com.caknow.customer.util.net.service.quotes.ServiceList;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * Created by junu on 1/15/2017.
 */

public class QuoteDetailListAdapter extends BaseAdapter {

    private static final int TYPE_SERVICE_INFO = 0;
    private static final int TYPE_SERVICE_FEE = 1;
    private static final int TYPE_REVIEWS = 2;
//    private static final int TYPE_VEHICLE_INFO = 3;
    private static final int TYPE_SEPARATOR = 3;
    private static final int TYPE_SERVICE_ITEM = 4;

    private ArrayList<String> mData = new ArrayList();
    private LayoutInflater mInflater;

    private TreeSet mSeparatorsSet = new TreeSet();
    private Context context;
    private List<QuoteList> quotes;
    private List<ServiceList> services;
    private QuoteList currentQuote;
    public QuoteDetailListAdapter(Context context, List<QuoteList> quoteList, List<ServiceList> serviceList) {
        this.context = context;
        this.quotes = quoteList;
        this.services = serviceList;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        currentQuote = quotes.get(0);
    }

    public QuoteList selectItem(int i){
        if(i < quotes.size()) {
            currentQuote = this.quotes.get(i);
            notifyDataSetInvalidated();
        }
        return currentQuote;
    }
    public void addItem(final String item) {
        mData.add(item);

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
//             case TYPE_VEHICLE_INFO:
//                return TYPE_VEHICLE_INFO;
             case TYPE_SEPARATOR:
                return TYPE_SEPARATOR;
             case TYPE_SERVICE_ITEM:
            default:
                return TYPE_SERVICE_ITEM;

        }
    }

    @Override
    public int getViewTypeCount() {
        return 5;
    }

    @Override
    public int getCount() {
        return 4 + quotes.size();
    }

    @Override
    public String getItem(int position) {
        return quotes.get(position).toString();
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
                    Long time = currentQuote.getQuoteTime();
                    ((TextView)convertView.findViewById(R.id.quote_detail_date_textview)).setText(TimeUtils.getShortTime(time));
                    ((TextView)convertView.findViewById(R.id.quote_detail_date_textview)).invalidate();

                    ((LinearLayout)convertView.findViewById(R.id.quote_detail_verified_layout)).setVisibility(currentQuote.getVerified() ? View.VISIBLE : View.INVISIBLE);
                    ((LinearLayout)convertView.findViewById(R.id.quote_detail_verified_layout)).invalidate();
                    String distanceString = currentQuote.getDistance();
                    if(distanceString == null){
                        distanceString = "";
                    }
                    ((TextView)convertView.findViewById(R.id.quote_detail_distance_textview)).setText(distanceString);
                    ((TextView)convertView.findViewById(R.id.quote_detail_distance_textview)).invalidate();

                    break;
                case TYPE_SERVICE_FEE:
                    String serviceFee = "";
                    if(currentQuote.getPriceDetails() != null && !currentQuote.getPriceDetails().isEmpty()){
                        serviceFee = currentQuote.getPriceDetails().get(currentQuote.getPriceDetails().size()-1).getPrice();
                    }
                    if(serviceFee.isEmpty()){
                        serviceFee = "$0.00";
                    }
                    convertView = mInflater.inflate(R.layout.list_item_quote_service_fee, null);
                    ((TextView)convertView.findViewById(R.id.service_fee_textview)).setText(serviceFee);
                    ((TextView)convertView.findViewById(R.id.service_fee_textview)).invalidate();
                    break;
                case TYPE_REVIEWS:
                    convertView = mInflater.inflate(R.layout.list_item_quote_reviews, null);
                    ArrayList<ImageView> starViews = new ArrayList<>(6);
                    starViews.add((ImageView) convertView.findViewById(R.id.star1));
                    starViews.add((ImageView) convertView.findViewById(R.id.star2));
                    starViews.add((ImageView) convertView.findViewById(R.id.star3));
                    starViews.add((ImageView) convertView.findViewById(R.id.star4));
                    starViews.add((ImageView) convertView.findViewById(R.id.star5));

                    long rating = quotes.get(0).getAverageRating();
                    for(int i = 0; i < (int) rating ; i++){
                        starViews.get(i).setImageResource(R.drawable.star_red_2x);
                        starViews.get(i).invalidate();
                    }
                    ((TextView)convertView.findViewById(R.id.reviews_label)).setText(String.valueOf(currentQuote.getReviewCount()).concat(" Reviews"));
                    ((TextView)convertView.findViewById(R.id.reviews_label)).invalidate();

                    break;
//                case TYPE_VEHICLE_INFO:
//                    convertView = mInflater.inflate(R.layout.list_item_quote_vehicle_information, null);
//                    holder.textView = (TextView)convertView.findViewById(R.id.text);
//                    break;
                case TYPE_SEPARATOR:
                    convertView = mInflater.inflate(R.layout.list_header_vehicle_service, null);
                    holder.textView = (TextView)convertView.findViewById(R.id.service_header_textview);
                    break;
                case TYPE_SERVICE_ITEM:
                default:
                    convertView = mInflater.inflate(R.layout.list_item_vehicle_service_request_new, null);
                    holder.textView = (TextView)convertView.findViewById(R.id.service_item_title);
                    int serviceListPosition = position - 4;
                    ServiceList currentService = services.get(serviceListPosition);
                    holder.textView.setText(currentService.getCategory());
                    ((TextView)convertView.findViewById(R.id.service_item_sub_title)).setText(String.valueOf(currentService.getField()));
                    ((TextView)convertView.findViewById(R.id.service_item_sub_title)).invalidate();
                    Glide.with(context).load(currentService.getIcon()).into(((ImageView)convertView.findViewById(R.id.service_item_icon)));
                    convertView.findViewById(R.id.service_item_next_button).setVisibility(View.GONE);
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


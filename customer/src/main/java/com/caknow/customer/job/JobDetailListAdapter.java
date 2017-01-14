package com.caknow.customer.job;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.caknow.app.R;
import com.caknow.customer.service.model.VehicleServiceInterface;
import com.caknow.customer.util.TimeUtils;
import com.caknow.customer.util.net.service.quotes.Affiliate;
import com.caknow.customer.util.net.service.quotes.QuoteList;

import java.util.ArrayList;
import java.util.TreeSet;

/**
 * Created by junu on 1/15/2017.
 */

public class JobDetailListAdapter extends BaseAdapter {

    private static final int TYPE_SHOP_HEADER = 0;
    private static final int TYPE_SERVICE_INFO = 1;
    private static final int TYPE_SERVICE_FEE = 2;
    private static final int TYPE_REVIEWS = 3;
    private static final int TYPE_INFO_SEPARATOR = 4;
    private static final int TYPE_SHOP_LOCATION = 5;
    private static final int TYPE_CALL = 6;
    private static final int TYPE_LIST_SEPARATOR = 7;
    private static final int TYPE_SERVICE_ITEM = 8;

    private ArrayList<String> mData = new ArrayList();
    private LayoutInflater mInflater;

    private TreeSet mSeparatorsSet = new TreeSet();
    private Context context;
    VehicleServiceInterface item;
    Affiliate affiliate;
    private QuoteList currentQuote;

    public JobDetailListAdapter(Context context, VehicleServiceInterface item, Affiliate affiliate) {
        this.context = context;
        this.item = item;
        this.currentQuote = null;
        this.affiliate = affiliate;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public JobDetailListAdapter(Context context, VehicleServiceInterface item, QuoteList quote) {
        this.context = context;
        this.item = item;
        this.currentQuote = quote;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    //    public QuoteList selectItem(int i){
//
//        return currentQuote;
//    }
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
        switch (position) {
            case TYPE_SHOP_HEADER:
                return TYPE_SHOP_HEADER;
            case TYPE_SERVICE_INFO:
                return TYPE_SERVICE_INFO;
            case TYPE_SERVICE_FEE:
                return TYPE_SERVICE_FEE;
            case TYPE_REVIEWS:
                return TYPE_REVIEWS;
            case TYPE_INFO_SEPARATOR:
                return TYPE_INFO_SEPARATOR;
            case TYPE_SHOP_LOCATION:
                return TYPE_SHOP_LOCATION;
            case TYPE_CALL:
                return TYPE_CALL;
            case TYPE_LIST_SEPARATOR:
                return TYPE_LIST_SEPARATOR;
            case TYPE_SERVICE_ITEM:
            default:
                return TYPE_SERVICE_ITEM;

        }
    }

    @Override
    public int getViewTypeCount() {
        return 9;
    }

    @Override
    public int getCount() {
        return 9;
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
                    ((TextView)convertView.findViewById(R.id.service_shop_name)).setText(affiliate.getShopName());
                    convertView.findViewById(R.id.service_shop_name).setVisibility(View.VISIBLE);
                    switch (item.getStatus()) {
                        case 2:
                            convertView.setBackground(context.getResources().getDrawable(R.drawable.service_detail_step2));
                            convertView.findViewById(R.id.service_step_2_textview).setVisibility(View.VISIBLE);
                            break;
                        case 3:
                            convertView.setBackground(context.getResources().getDrawable(R.drawable.service_detail_step3));
                            convertView.findViewById(R.id.service_step_3_textview).setVisibility(View.VISIBLE);
                            break;
                        case 8:
                        default:
                            convertView.setBackground(context.getResources().getDrawable(R.drawable.service_detail_step4));
                            convertView.findViewById(R.id.service_step_4_textview).setVisibility(View.VISIBLE);
                            break;
                    }
                    convertView.invalidate();
                    break;
                case TYPE_SERVICE_INFO:
                    convertView = mInflater.inflate(R.layout.list_item_quote_details, null);

                    holder.textView = (TextView) convertView.findViewById(R.id.text);
                    ((TextView) convertView.findViewById(R.id.quote_detail_date_textview)).setText(TimeUtils.getShortTime(item.getDate()));
                    convertView.findViewById(R.id.quote_detail_date_textview).invalidate();
                    if (affiliate != null) {
                        convertView.findViewById(R.id.quote_detail_verified_layout).setVisibility(affiliate.getVerified() ? View.VISIBLE : View.INVISIBLE);
                        convertView.findViewById(R.id.quote_detail_verified_layout).setVisibility(View.VISIBLE);
                        convertView.findViewById(R.id.quote_detail_verified_layout).invalidate();
                        String distanceString = affiliate.getDistance();
                        if (distanceString == null) {
                            distanceString = "";
                        }
                        ((TextView) convertView.findViewById(R.id.quote_detail_distance_textview)).setText(distanceString);
                        convertView.findViewById(R.id.quote_detail_distance_textview).invalidate();
                    }

                    break;
                case TYPE_SERVICE_FEE:
                    convertView = mInflater.inflate(R.layout.list_item_quote_service_fee, null);

                    if (affiliate == null) {
                        convertView.setVisibility(View.GONE);
                        break;
                    }
                    String serviceFee = affiliate.getServiceFee().isEmpty() ? "$0.00" : affiliate.getServiceFee();
                    ((TextView) convertView.findViewById(R.id.service_fee_textview)).setText(serviceFee);
                    convertView.findViewById(R.id.service_fee_textview).invalidate();
                    break;
                case TYPE_REVIEWS:
                    convertView = mInflater.inflate(R.layout.list_item_quote_reviews, null);
                    if (affiliate == null) {
                        convertView.setVisibility(View.GONE);
                        break;
                    }
                    ArrayList<ImageView> starViews = new ArrayList<>(6);
                    starViews.add((ImageView) convertView.findViewById(R.id.star1));
                    starViews.add((ImageView) convertView.findViewById(R.id.star2));
                    starViews.add((ImageView) convertView.findViewById(R.id.star3));
                    starViews.add((ImageView) convertView.findViewById(R.id.star4));
                    starViews.add((ImageView) convertView.findViewById(R.id.star5));

                    long rating = affiliate.getAverageRating();
                    for (int i = 0; i < (int) rating; i++) {
                        starViews.get(i).setImageResource(R.drawable.star_red_2x);
                        starViews.get(i).invalidate();
                    }
                    ((TextView) convertView.findViewById(R.id.reviews_label)).setText(String.valueOf(affiliate.getReviewCount()).concat(" Reviews"));
                    convertView.findViewById(R.id.reviews_label).invalidate();

                    break;
                case TYPE_INFO_SEPARATOR:
                    convertView = mInflater.inflate(R.layout.list_header_vehicle_service, null);

                    if (affiliate == null) {
                        convertView.setVisibility(View.GONE);
                        break;
                    }

                    holder.textView = (TextView) convertView.findViewById(R.id.service_header_textview);
                    holder.textView.setText("Shop Info");
                    break;
                case TYPE_SHOP_LOCATION:
                    convertView = mInflater.inflate(R.layout.list_item_quote_location, null);
                    if (affiliate == null) {
                        convertView.setVisibility(View.GONE);
                        break;
                    }
                    holder.textView = (TextView) convertView.findViewById(R.id.list_item_address_textview);
                    String formattedAddress = affiliate.getAddress();
                    try {
                        String[] addressParts = affiliate.getAddress().split(",", 1);
                        formattedAddress = addressParts[0].concat("\n").concat(addressParts[1].trim());
                    }catch(Exception e){}
                    holder.textView.setText(formattedAddress);
                    break;
                case TYPE_CALL:
                    convertView = mInflater.inflate(R.layout.list_item_quote_call, null);
                    if (affiliate == null) {
                        convertView.setVisibility(View.GONE);
                        break;
                    }
                    holder.textView = (TextView) convertView.findViewById(R.id.list_item_phone);
                    holder.textView.setText(affiliate.getTelephoneNumber());
                    break;
                case TYPE_LIST_SEPARATOR:
                    convertView = mInflater.inflate(R.layout.list_header_vehicle_service, null);
                    if (affiliate == null) {
                        convertView.setVisibility(View.GONE);
                        break;
                    }
                    holder.textView = (TextView) convertView.findViewById(R.id.service_header_textview);
                    holder.textView.setText("Service Items");

                    break;
                case TYPE_SERVICE_ITEM:
                default:
                    convertView = mInflater.inflate(R.layout.list_item_vehicle_service_request_new, null);
                    holder.textView = (TextView) convertView.findViewById(R.id.service_item_title);
                    holder.textView.setText(item.getDisplayTitle());
                    holder.textView.invalidate();
                    ((TextView) convertView.findViewById(R.id.service_item_sub_title)).setText(item.getServiceCatagory());
                    convertView.findViewById(R.id.service_item_sub_title).invalidate();
                    ((TextView) convertView.findViewById(R.id.service_item_time_display)).setText(TimeUtils.getShortTime(item.getDate()));
                    convertView.findViewById(R.id.service_item_time_display).invalidate();
                    Glide.with(context).load(item.getIconUrl()).fitCenter().into(((ImageView) convertView.findViewById(R.id.service_item_icon)));
                    break;

            }
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }

    public static class ViewHolder {
        public TextView textView;
    }

}


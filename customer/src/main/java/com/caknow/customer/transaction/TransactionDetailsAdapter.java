package com.caknow.customer.transaction;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.caknow.app.R;
import com.caknow.customer.service.model.VehicleServiceInterface;
import com.caknow.customer.util.TimeUtils;
import com.caknow.customer.util.net.quote.Quote;
import com.caknow.customer.util.net.service.quotes.PriceDetail;
import com.caknow.customer.util.net.service.quotes.QuoteList;

import java.util.ArrayList;
import java.util.List;

/**
 * THis is the top list in the transaction details screen that shows itemized PriceDetail items
 * Created by junu on 1/15/2017.
 */

public class TransactionDetailsAdapter extends BaseAdapter {

    private static final int TYPE_PRICE_DETAIL = 0;
    private static final int TYPE_PROMOTION_CODE = 0;
    private static final int TYPE_LIST_SEPARATOR = 1;
    private static final int TYPE_SERVICE_ITEM = 2;

    private boolean paymentMode = false;
    private List<PriceDetail> mData = new ArrayList();
    private LayoutInflater mInflater;
    private Quote quote;
    private Context context;
    private QuoteList quoteList;
    VehicleServiceInterface item;

    public TransactionDetailsAdapter(Context context, QuoteList quoteList, List<Quote> quoteItems) {
        this.context = context;
        this.quoteList = quoteList;
        if(quoteList != null){
            this.mData = quoteList.getPriceDetails();
            paymentMode = true;
        }else{
            this.mData = quoteItems.get(0).getItemizedAmounts();
        }
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public TransactionDetailsAdapter(Context context, Quote quote, VehicleServiceInterface item) {
        this.context = context;
        this.quote = quote;
        this.item = item;
        this.mData = quote.getItemizedAmounts();
        paymentMode = true;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }



    @Override
    public int getItemViewType(int position) {
       return position;
    }

    @Override
    public int getViewTypeCount() {
        return mData.size() + 3;
    }

    @Override
    public int getCount() {
        return mData.size() + 3;
    }

    @Override
    public PriceDetail getItem(int position) {
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
                if(position < mData.size()) {
                    convertView = mInflater.inflate(R.layout.list_item_price_detail, null);
                    convertView.setTag(holder);
                    holder.labelTextView = (TextView) convertView.findViewById(R.id.list_item_pricedetail_label);
                    holder.priceTextView = (TextView) convertView.findViewById(R.id.list_item_pricedetail_value);
                    holder.labelTextView.setText(mData.get(position).getPriceItem());
                    holder.priceTextView.setText(mData.get(position).getPrice());

                }else {
                    switch (type - mData.size()) {

                        case TYPE_PROMOTION_CODE:
                            convertView = mInflater.inflate(R.layout.list_item_promotion_code, null);
                            if (quote == null) {
                                convertView.setVisibility(View.GONE);
                                break;
                            }
                            break;
                        case TYPE_LIST_SEPARATOR:
                            convertView = mInflater.inflate(R.layout.list_header_vehicle_service, null);
                            if (quote == null) {
                                convertView.setVisibility(View.GONE);
                                break;
                            }
                            holder.textView = (TextView) convertView.findViewById(R.id.service_header_textview);
                            holder.textView.setText("Service Items");
                            convertView.findViewById(R.id.service_header_textview).invalidate();
                            break;
                        case TYPE_SERVICE_ITEM:
                        default:
                            convertView = mInflater.inflate(R.layout.list_item_vehicle_service_request_new, null);
                            if (quote == null) {
                                convertView.setVisibility(View.GONE);
                                break;
                            }
                            holder.textView = (TextView) convertView.findViewById(R.id.service_item_title);
                            holder.textView.setText(item.getDisplayTitle());
                            holder.textView.invalidate();
                            ((TextView) convertView.findViewById(R.id.service_item_sub_title)).setText(item.getServiceCatagory());
                            convertView.findViewById(R.id.service_item_sub_title).invalidate();
                            ((TextView) convertView.findViewById(R.id.service_item_time_display)).setText(TimeUtils.getShortTime(item.getDate()));
                            convertView.findViewById(R.id.service_item_time_display).invalidate();
                            Glide.with(context).load(item.getIconUrl()).fitCenter().into(((ImageView) convertView.findViewById(R.id.service_item_icon)));
                            break;
//
                    }
                }

        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        return convertView;
    }

    public static class ViewHolder {
        public TextView labelTextView;
        public TextView priceTextView;
        public TextView textView;
    }

}


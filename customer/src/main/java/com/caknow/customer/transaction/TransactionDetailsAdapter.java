package com.caknow.customer.transaction;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.caknow.app.R;
import com.caknow.customer.util.net.quote.Quote;
import com.caknow.customer.util.net.service.quotes.PriceDetail;
import com.caknow.customer.util.net.service.quotes.QuoteList;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * THis is the top list in the transaction details screen that shows itemized PriceDetail items
 * Created by junu on 1/15/2017.
 */

public class TransactionDetailsAdapter extends BaseAdapter {

    private boolean paymentMode = false;
    private List<PriceDetail> mData = new ArrayList();
    private LayoutInflater mInflater;
    private QuoteList quote;
    private Context context;

    public TransactionDetailsAdapter(Context context, QuoteList quote, List<Quote> quoteItems) {
        this.context = context;
        this.quote = quote;
        if(quote != null){
            this.mData = quote.getPriceDetails();
            paymentMode = true;
        }else{
            this.mData = quoteItems.get(0).getItemizedAmounts();
        }
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }



    @Override
    public int getItemViewType(int position) {
       return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public int getCount() {
        return mData.size();
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
        PriceDetailViewHolder holder = null;
        int type = getItemViewType(position);
        System.out.println("getView " + position + " " + convertView + " type = " + type);
        if (convertView == null) {
            holder = new PriceDetailViewHolder();
            convertView = mInflater.inflate(R.layout.list_item_price_detail, null);
            convertView.setTag(holder);
        } else {
            holder = (PriceDetailViewHolder)convertView.getTag();
        }
        holder.labelTextView = (TextView) convertView.findViewById(R.id.list_item_pricedetail_label);
        holder.priceTextView = (TextView) convertView.findViewById(R.id.list_item_pricedetail_value);
        holder.labelTextView.setText(mData.get(position).getPriceItem());
        holder.priceTextView.setText(mData.get(position).getPrice());
        return convertView;
    }

    public static class PriceDetailViewHolder {
        public TextView labelTextView;
        public TextView priceTextView;
    }

}


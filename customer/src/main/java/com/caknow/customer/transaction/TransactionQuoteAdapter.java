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
 * This is the adapter that shows list of quotes from the same serviceId
 * Created by junu on 1/15/2017.
 */

public class TransactionQuoteAdapter extends BaseAdapter {

    private List<Quote> mData = new ArrayList();
    private LayoutInflater mInflater;
    private TreeSet mSeparatorsSet = new TreeSet();

    public TransactionQuoteAdapter(Context context, List<Quote> quoteItems) {
        this.mData = quoteItems;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getItemViewType(int position) {
       return 0;
    }

    /**
     * There is only 1 view type for this adapter. Quote display
     * @return
     */
    @Override
    public int getViewTypeCount() {
        return 1;
    }

    /**
     * Get the total count of quotes
     * @return
     */
    @Override
    public int getCount() {
        return mData.size();
    }

    /**
     * Call this method to retrieve the quote item in the adapter. Price item details can be used to update
     * TransactionDetailsAdapter
     * @param position
     * @return
     */
    @Override
    public Quote getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PriceDetailViewHolder holder;
        if (convertView == null) {
            holder = new PriceDetailViewHolder();
            convertView = mInflater.inflate(R.layout.list_item_price_detail, null);
            convertView.setTag(holder);
        } else {
            holder = (PriceDetailViewHolder)convertView.getTag();
        }
        // Set the quote name and price details here
        Quote currentItem = mData.get(position);
        holder.labelTextView = (TextView) convertView.findViewById(R.id.list_item_pricedetail_label);
        holder.priceTextView = (TextView) convertView.findViewById(R.id.list_item_pricedetail_value);
        holder.labelTextView.setText(currentItem.getId());
        // Display the last price item's value which is assumed to be the total
        holder.priceTextView.setText(currentItem.getItemizedAmounts().get(currentItem.getItemizedAmounts().size() -1 ).getPrice());
        return convertView;
    }

    public static class PriceDetailViewHolder {
        public TextView labelTextView;
        public TextView priceTextView;
    }

}


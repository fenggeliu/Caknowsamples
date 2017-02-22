package com.caknow.customer.transaction;

import android.content.Context;

import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.caknow.app.R;
import com.caknow.customer.service.model.VehicleServiceInterface;
import com.caknow.customer.util.TimeUtils;
import com.caknow.customer.util.net.quote.GetQuotesByServiceId;
import com.caknow.customer.util.net.quote.GetQuotesByServiceIdPayload;
import com.caknow.customer.util.net.quote.Quote;
import com.caknow.customer.util.net.service.quotes.PriceDetail;
import com.caknow.customer.util.net.service.quotes.QuoteList;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;

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
    private GetQuotesByServiceIdPayload payload;
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

    public TransactionDetailsAdapter(Context context, Quote quote, VehicleServiceInterface item, GetQuotesByServiceIdPayload payload) {
        this.context = context;
        this.quote = quote;
        this.item = item;
        this.mData = quote.getItemizedAmounts();
        this.payload = payload;
        paymentMode = true;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }



    @Override
    public int getItemViewType(int position) {
       return position;
    }

    @Override
    public int getViewTypeCount() {
        if(quote == null) return mData.size();
        if(payload == null) {
            return mData.size() + 3;
        }else{ return mData.size() + 2 + payload.getQuotes().size();}
    }

    @Override
    public int getCount() {
        if(quote == null) return mData.size();
        if(payload == null) {
            return mData.size() + 3;
        }else{ return mData.size() + 2 + payload.getQuotes().size();}
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

                }else if(position - mData.size() <= 1){
                    switch (type - mData.size()) {
                        case TYPE_PROMOTION_CODE:
                            convertView = mInflater.inflate(R.layout.list_item_promotion_code, null);
                            if (quote == null) {
                                convertView.setVisibility(View.GONE);
                                convertView.invalidate();
                                break;
                            }
                            if (payload != null) {
                                convertView = mInflater.inflate(R.layout.list_item_quote_history, null);
                                holder.labelTextView = (TextView) convertView.findViewById(R.id.quote_info);
                                holder.textView = (TextView) convertView.findViewById(R.id.quote_date);
                                holder.labelTextView.setText("Note: " + payload.getRemark());
                                holder.textView.setText(TimeUtils.getTime(payload.getCt() * 1000));
                                convertView.invalidate();
                                break;
                            }
                            break;
                        case TYPE_LIST_SEPARATOR:
                        default:
                            convertView = mInflater.inflate(R.layout.list_header_vehicle_service, null);
                            if (quote == null) {
                                convertView.setVisibility(View.GONE);
                                break;
                            }
                            if (payload != null) {
                                holder.textView = (TextView) convertView.findViewById(R.id.service_header_textview);
                                holder.textView.setText("Quote History");
                                convertView.findViewById(R.id.service_header_textview).invalidate();
                                break;
                            }
                            holder.textView = (TextView) convertView.findViewById(R.id.service_header_textview);
                            holder.textView.setText("Service Items");
                            convertView.findViewById(R.id.service_header_textview).invalidate();
                            break;
                    }
                } else {
                    if (payload != null) {
                        convertView = mInflater.inflate(R.layout.list_item_quote_history, null);
                        holder.labelTextView = (TextView) convertView.findViewById(R.id.quote_info);
                        holder.textView = (TextView) convertView.findViewById(R.id.quote_date);
                        Quote oldQuote = payload.getQuotes().get(position - mData.size() - 2);
                        holder.labelTextView.setText("Quote " + oldQuote.getStatus());
                        holder.textView.setText(TimeUtils.getTime(oldQuote.getAcceptTime() * 1000));
                        holder.listView = (ListView) convertView.findViewById(R.id.quote_history_listview);
                        holder.cardView = (CardView) convertView.findViewById(R.id.quote_card_view);
                        TransactionDetailsAdapter quoteAdapter =
                                new TransactionDetailsAdapter(parent.getContext(), null, payload.getQuotes());
                        holder.listView.setAdapter(quoteAdapter);
                        final ListView mlistView = holder.listView;
                        holder.cardView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mlistView.setVisibility((mlistView.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
                            }
                        });
                        convertView.invalidate();
                    } else {
                        convertView = mInflater.inflate(R.layout.list_item_vehicle_service_request_new, null);
                        holder.textView = (TextView) convertView.findViewById(R.id.service_item_title);
                        holder.textView.setText(item.getServiceCatagory());
                        ((TextView) convertView.findViewById(R.id.service_item_sub_title)).setText(item.getServiceField());
                        convertView.findViewById(R.id.service_item_sub_title).invalidate();
                        ((TextView) convertView.findViewById(R.id.service_item_time_display)).setText(TimeUtils.getShortTime(item.getDate() * 1000));
                        convertView.findViewById(R.id.service_item_next_button).setVisibility(View.GONE);
                        convertView.findViewById(R.id.service_item_time_display).invalidate();
                        Glide.with(context).load(item.getIconUrl()).fitCenter().into(((ImageView) convertView.findViewById(R.id.service_item_icon)));

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
        public ListView listView;
        public CardView cardView;
    }

}


package com.caknow.customer.transaction;

import android.app.Activity;
import android.content.Context;

import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.caknow.app.R;
import com.caknow.customer.service.model.VehicleServiceInterface;
import com.caknow.customer.util.TimeUtils;
import com.caknow.customer.util.constant.Constants;
import com.caknow.customer.util.net.quote.GetQuotesByServiceId;
import com.caknow.customer.util.net.quote.GetQuotesByServiceIdPayload;
import com.caknow.customer.util.net.quote.Quote;
import com.caknow.customer.util.net.service.quotes.PriceDetail;
import com.caknow.customer.util.net.service.quotes.QuoteList;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;

import static com.facebook.FacebookSdk.getApplicationContext;

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
    private ArrayList<String> validPromotionCodes;
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

    public TransactionDetailsAdapter(Context context, Quote quote, VehicleServiceInterface item, GetQuotesByServiceIdPayload payload, ArrayList<String> validPromotionCodes) {
        this.context = context;
        this.quote = quote;
        this.item = item;
        this.mData = quote.getItemizedAmounts();
        this.payload = payload;
        this.validPromotionCodes = validPromotionCodes;
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
        if(payload != null) return mData.size() + 2;
        return mData.size() + 3;
    }

    @Override
    public int getCount() {
        if(quote == null) return mData.size();
        if(payload != null) return mData.size() + 2;
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
                    convertView.invalidate();
                }else{
                    switch (type - mData.size()) {
                        case TYPE_PROMOTION_CODE:
                            if (payload != null) {
                                convertView = mInflater.inflate(R.layout.list_item_exp_parent, null);
                                holder.labelTextView = (TextView) convertView.findViewById(R.id.quote_info);
                                holder.textView = (TextView) convertView.findViewById(R.id.quote_date);
                                holder.labelTextView.setText("Note: " + payload.getRemark());
                                holder.textView.setText(TimeUtils.getTime(payload.getCt() * 1000));
                                convertView.invalidate();
                                break;
                            }else{
                                convertView = mInflater.inflate(R.layout.list_item_promotion_code, null);
                                if (validPromotionCodes != null) {
                                    holder.listView = (ListView) convertView.findViewById(R.id.valid_promotion_codes_list);
                                    holder.listView.setVisibility(View.VISIBLE);
                                    PromotionCodesAdapter codesAdapter = new PromotionCodesAdapter(context, validPromotionCodes);
                                    holder.listView.setAdapter(codesAdapter);
                                    float factor = holder.listView.getContext().getResources().getDisplayMetrics().density;
                                    convertView.setLayoutParams(new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, Math.round((validPromotionCodes.size()*30 +120)*factor)));
//                                    holder.listView.setOnItemClickListener();
                                }
                            }
                            convertView.invalidate();
                            break;
                        case TYPE_LIST_SEPARATOR:
                        default:
                            convertView = mInflater.inflate(R.layout.list_header_vehicle_service, null);
//                            if (quote == null) {
//                                convertView.setVisibility(View.GONE);
//                                break;
//                            }
                            if (payload != null) {
                                holder.textView = (TextView) convertView.findViewById(R.id.service_header_textview);
                                holder.textView.setText("Quote History");
                                convertView.findViewById(R.id.service_header_textview).invalidate();
                                convertView.invalidate();
                                break;
                            }
                            holder.textView = (TextView) convertView.findViewById(R.id.service_header_textview);
                            holder.textView.setText("Service Items");
                            convertView.findViewById(R.id.service_header_textview).invalidate();
                            convertView.invalidate();
                            break;
                        case TYPE_SERVICE_ITEM:
                            convertView = mInflater.inflate(R.layout.list_item_vehicle_service_request_new, null);
                            holder.textView = (TextView) convertView.findViewById(R.id.service_item_title);
                            holder.textView.setText(item.getServiceCatagory());
                            ((TextView) convertView.findViewById(R.id.service_item_sub_title)).setText(item.getServiceField());
                            convertView.findViewById(R.id.service_item_sub_title).invalidate();
                            ((TextView) convertView.findViewById(R.id.service_item_time_display)).setText(TimeUtils.getShortTime(item.getDate() * 1000));
                            convertView.findViewById(R.id.service_item_next_button).setVisibility(View.GONE);
                            convertView.findViewById(R.id.service_item_time_display).invalidate();
                            Glide.with(context).load(item.getIconUrl()).fitCenter().into(((ImageView) convertView.findViewById(R.id.service_item_icon)));
                            break;
                    }
/*                    if (payload != null) {
                        convertView = mInflater.inflate(R.layout.list_item_quote_history, null);
                        convertView.setFocusable(false);
                        holder.quoteHistoryView = (ExpandableListView) convertView.findViewById(R.id.quote_exp_listview);
                        QuoteHistoryAdapter quoteHistoryAdapter = new QuoteHistoryAdapter(context, payload);
                        holder.quoteHistoryView.setAdapter(quoteHistoryAdapter);
                        convertView.invalidate();
//                        convertView = mInflater.inflate(R.layout.list_item_quote_history, null);
//                        holder.labelTextView = (TextView) convertView.findViewById(R.id.quote_info);
//                        holder.textView = (TextView) convertView.findViewById(R.id.quote_date);
//                        Quote oldQuote = payload.getQuotes().get(position - mData.size() - 2);
//                        holder.labelTextView.setText("Quote " + oldQuote.getStatus());
//                        holder.textView.setText(TimeUtils.getTime(oldQuote.getAcceptTime() * 1000));
//                        holder.listView = (ListView) convertView.findViewById(R.id.quote_history_listview);
//                        holder.cardView = (CardView) convertView.findViewById(R.id.quote_card_view);
//                        TransactionDetailsAdapter quoteAdapter =
//                                new TransactionDetailsAdapter(((Activity)context), oldQuote, null, null);
//                        holder.listView.setAdapter(quoteAdapter);
//                        final ListView mlistView = holder.listView;
//                        holder.cardView.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                mlistView.setVisibility((mlistView.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
//                            }
//                        });
//                        convertView.invalidate();
                    } else {  */
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
    }

}


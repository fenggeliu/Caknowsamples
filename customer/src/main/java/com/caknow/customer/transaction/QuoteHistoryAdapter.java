package com.caknow.customer.transaction;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.caknow.app.R;
import com.caknow.customer.util.TimeUtils;
import com.caknow.customer.util.net.quote.GetQuotesByServiceId;
import com.caknow.customer.util.net.quote.GetQuotesByServiceIdPayload;
import com.caknow.customer.util.net.service.quotes.PriceDetail;

/**
 * Created by fengge on 2/22/2017.
 */

public class QuoteHistoryAdapter extends BaseExpandableListAdapter{

    private GetQuotesByServiceIdPayload payload;
    private LayoutInflater mInflater;

    public QuoteHistoryAdapter(Context context, GetQuotesByServiceIdPayload payload){
        this.payload = payload;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getGroupCount() {
        return payload.getQuotes().size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return payload.getQuotes().get(groupPosition).getItemizedAmounts().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return payload.getQuotes().get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return payload.getQuotes().get(groupPosition).getItemizedAmounts().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String label = payload.getQuotes().get(groupPosition).getStatus();
        String time = TimeUtils.getTime(payload.getQuotes().get(groupPosition).getAcceptTime() * 1000);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item_exp_parent, null);

        }
        TextView labelText = (TextView) convertView.findViewById(R.id.quote_info);
        TextView timeText = (TextView) convertView.findViewById(R.id.quote_date);
        labelText.setText(String.format("Quote %s", label));
        timeText.setText(String.format(time));
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        PriceDetail priceDetail = payload.getQuotes().get(groupPosition).getItemizedAmounts().get(childPosition);
        String item = priceDetail.getPriceItem();
        String price = priceDetail.getPrice();
        if (convertView == null){
            convertView = mInflater.inflate(R.layout.list_item_price_detail, null);
        }
        TextView itemText = (TextView) convertView.findViewById(R.id.list_item_pricedetail_label);
        TextView priceText = (TextView) convertView.findViewById(R.id.list_item_pricedetail_value);
        itemText.setText(item);
        priceText.setText(price);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}

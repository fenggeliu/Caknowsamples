package com.caknow.customer.transaction;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.caknow.app.R;
import com.caknow.customer.util.net.transaction.PromotionCodesPayload;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static java.lang.String.format;

/**
 * Created by fliu on 2/27/17.
 */

public class PromotionCodesAdapter extends BaseAdapter {
    private List<String> validPromotionCodes = new ArrayList<>();
    private PromotionCodesPayload payload;
    private LayoutInflater mInflater;
    public PromotionCodesAdapter (Context context, PromotionCodesPayload payload){
        this.payload = payload;
        this.validPromotionCodes = payload.getAcceptedPromoCodes();
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return validPromotionCodes.size();
    }

    @Override
    public Object getItem(int position) {
        return validPromotionCodes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = new ViewHolder();
        if (convertView == null){
            convertView = mInflater.inflate(R.layout.list_item_valid_code, null);
        }
        holder.textView = (TextView) convertView.findViewById(R.id.promotion_codes);
        holder.textView.setText(validPromotionCodes.get(position));
//        holder.refundAmount = (TextView) convertView.findViewById(R.id.refund_amount);
//        holder.refundAmount.setText(format(Locale.ENGLISH, "$%.2f", - payload.getRefundAmount().doubleValue() / 100));
        convertView.invalidate();
        return convertView;
    }

    public static class ViewHolder {
        public TextView textView;
//        public TextView refundAmount;
    }
}

package com.caknow.customer.transaction;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.caknow.app.R;

import java.util.ArrayList;

/**
 * Created by fliu on 2/27/17.
 */

public class PromotionCodesAdapter extends BaseAdapter {
    private ArrayList<String> validPromotionCodes;
    private LayoutInflater mInflater;
    public PromotionCodesAdapter (Context context, ArrayList<String> validPromotionCodes){
        this.validPromotionCodes = validPromotionCodes;
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
        convertView.invalidate();
        return convertView;
    }

    public static class ViewHolder {
        public TextView textView;
    }
}

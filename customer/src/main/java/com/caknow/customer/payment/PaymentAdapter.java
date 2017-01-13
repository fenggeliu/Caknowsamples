
package com.caknow.customer.payment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.caknow.app.R;

import java.util.List;

/**
 * Created by junu on 1/2/2017.
 */

public class PaymentAdapter extends BaseAdapter {

    private List<Payment> paymentList;
    private Context mContext;
    private LayoutInflater inflater;

     public PaymentAdapter(Context context, List<Payment> payments){
        this.paymentList = payments;
         this.mContext = context;
         this.inflater = (LayoutInflater) this.mContext
                 .getSystemService(Context.LAYOUT_INFLATER_SERVICE);     }

    @Override
    public int getCount() {
        return paymentList.size();
    }

    @Override
    public Object getItem(int position) {
        return paymentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final PaymentView viewHolder;

        //Timber.d("position =" + position);
        if (convertView == null ) {
            if(this.inflater != null) {
                convertView = this.inflater.inflate(R.layout.list_item_cc, parent, false);
                viewHolder = new PaymentView(convertView, paymentList.get(position), position);
                //TODO: Edit Mode
                convertView.findViewById(R.id.no_edit_card).setVisibility(View.GONE);
                convertView.setTag(viewHolder);
            }
        } else {
            viewHolder = (PaymentView) convertView.getTag();
        }

        return convertView;
    }
}

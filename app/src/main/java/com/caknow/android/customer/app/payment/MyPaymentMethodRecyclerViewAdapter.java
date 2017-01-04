package com.caknow.android.customer.app.payment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.caknow.android.customer.app.payment.PaymentMethodFragment.OnListFragmentInteractionListener;
import com.caknow.app.R;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Payment} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyPaymentMethodRecyclerViewAdapter extends RecyclerView.Adapter<MyPaymentMethodRecyclerViewAdapter.ViewHolder> {

    private final List<Payment> paymentList;
    private final OnListFragmentInteractionListener mListener;

    public MyPaymentMethodRecyclerViewAdapter(List<Payment> items, OnListFragmentInteractionListener listener) {
        paymentList = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_paymentmethod, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = paymentList.get(position);
        holder.cardHolder.setText(paymentList.get(position).getName());
        holder.cardNumber.setText(paymentList.get(position).getName());
        holder.expiry.setText(paymentList.get(position).getName());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return paymentList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView cardHolder;
        public final TextView cardNumber;
        public final TextView expiry;
        public final ImageView category;
        public Payment mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            cardHolder = (TextView) view.findViewById(R.id.ccli_card_holder);
            cardNumber = (TextView) view.findViewById(R.id.ccli_card_number);
            category = (ImageView) view.findViewById(R.id.ccli_credit_card_category);
            expiry = (TextView) view.findViewById(R.id.ccli_expire_day);
        }

        @Override
        public String toString() {
            return super.toString() + " '"  + "'";
        }
    }
}

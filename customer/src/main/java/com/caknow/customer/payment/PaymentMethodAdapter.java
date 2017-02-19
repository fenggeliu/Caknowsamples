package com.caknow.customer.payment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.caknow.app.R;
import com.caknow.customer.payment.PaymentMethodFragment.OnListFragmentInteractionListener;
import com.caknow.customer.util.net.payment.model.PaymentMethodItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PaymentMethodItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 */
public class PaymentMethodAdapter extends RecyclerView.Adapter<PaymentMethodAdapter.PaymentItemViewHolder> {

    private final List<PaymentMethodItem> paymentList;
    private final OnListFragmentInteractionListener mListener;
    private boolean editMode = false;
    private PaymentActivity activity;

    public PaymentMethodAdapter(List<PaymentMethodItem> items, OnListFragmentInteractionListener listener, PaymentActivity activity) {
        paymentList = items;
        this.activity = activity;
        mListener = listener;
    }

    @Override
    public PaymentItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_paymentmethod, parent, false);
        return new PaymentItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final PaymentItemViewHolder holder, int position) {
        PaymentMethodItem paymentMethodItem = paymentList.get(position);
        holder.mItem = paymentMethodItem;
        String cardHolderName = "";
        if(paymentMethodItem.getName() != null){
            cardHolderName = paymentMethodItem.getName().toString();
        }
        holder.cardHolder.setText(cardHolderName);
        holder.deleteButton.setOnClickListener(v -> {
            try{
                activity.deleteCard("stripe" ,holder.mItem.getId());
            }catch(Exception e){

            }
            notifyDataSetChanged();
        });

        // Update the views before being loaded here using the holder object
        holder.cardNumber.setText("XXXX XXXX XXXX ".concat(paymentMethodItem.getLast4()));
        holder.expiry.setText(paymentList.get(position).getExpMonth().toString().concat("/").concat(paymentList.get(position).getExpYear().toString()));

        holder.mView.setOnClickListener(v -> {
            if (null != mListener) {
                // Notify the active callbacks interface (the activity, if the
                // fragment is attached to one) that an item has been selected.
                mListener.onListFragmentInteraction(holder.mItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return paymentList.size();
    }

    /**
     * View Holder class for PaymentMethodItem
     */
    public class PaymentItemViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView cardHolder;
        public final TextView cardNumber;
        public final TextView expiry;
        public final ImageView category;
        public final ImageView deleteButton;
        public PaymentMethodItem mItem;

        public PaymentItemViewHolder(View view) {
            super(view);
            mView = view;
            deleteButton = (ImageView) view.findViewById(R.id.ccli_del_icon_btn);
            deleteButton.setVisibility(editMode ? View.VISIBLE : View.GONE);
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

    public void toggleEditMode(){
        editMode = !editMode;
    }
}

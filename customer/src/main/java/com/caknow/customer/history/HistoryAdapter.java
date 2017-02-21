package com.caknow.customer.history;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.caknow.app.R;
import com.caknow.customer.CAKNOWApplication;
import com.caknow.customer.util.TimeUtils;
import com.caknow.customer.util.net.history.History;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.Long.parseLong;

/**
 * Created by junu on 12/31/16.
 */

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    private List<History> cardItems;
    private HistoryFragment.OnListFragmentInteractionListener mListener;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        CardView cardView;
        private History historyItem;
        public TextView statusTextView;
        public TextView shopNameTextView;
        public ImageView shopImageView;
        public TextView serviceTypeTextView;
        public TextView serviceDetailTextView;
        public TextView serviceActionTextView;
        public ImageView carImageView;
        public TextView timeTextView;
        public TextView historyPrice;
        private LinearLayout dropDownLayout;
        private LinearLayout userInfo;
        public TextView reviewLabel;
        private ArrayList<ImageView> starViews = new ArrayList<>(6);
        public LinearLayout serviceDetail;
        public ViewHolder(View v) {
            super(v);
            cardView = (CardView) v;
            shopNameTextView = (TextView) v.findViewById(R.id.history_item_title);
            serviceTypeTextView = (TextView) v.findViewById(R.id.history_item_info);
            shopImageView = (ImageView) v.findViewById(R.id.history_shop_photo);
            statusTextView = (TextView) v.findViewById(R.id.history_status);
            timeTextView = (TextView) v.findViewById(R.id.service_item_time);
            historyPrice = (TextView) v.findViewById(R.id.history_price);
            dropDownLayout = (LinearLayout) v.findViewById(R.id.review_detail_line);
            userInfo = (LinearLayout) v.findViewById(R.id.user_repair_info);
            //ArrayList<ImageView> starViews = new ArrayList<>(6);
            starViews.add((ImageView) v.findViewById(R.id.star1));
            starViews.add((ImageView) v.findViewById(R.id.star2));
            starViews.add((ImageView) v.findViewById(R.id.star3));
            starViews.add((ImageView) v.findViewById(R.id.star4));
            starViews.add((ImageView) v.findViewById(R.id.star5));
            reviewLabel = (TextView) v.findViewById(R.id.stars_level);
            serviceDetail = (LinearLayout) v.findViewById(R.id.vehicle_display);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public HistoryAdapter(List<History> dataSet, HistoryFragment.OnListFragmentInteractionListener listener) {
        cardItems = dataSet;
        mListener = listener;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                        int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_history, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        History item = cardItems.get(position);
        holder.historyItem = item;
        holder.shopNameTextView.setText(item.getShopName());
        holder.serviceTypeTextView.setText(item.getServiceCategory());
        Glide.with(CAKNOWApplication.get()).load(item.getShopImage()).into(holder.shopImageView);
        holder.statusTextView.setText(String.valueOf(item.getStatus()));

        holder.userInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.dropDownLayout.getVisibility() == View.GONE) {
                    holder.dropDownLayout.setVisibility(View.VISIBLE);
                }else{
                    holder.dropDownLayout.setVisibility(View.GONE);
                }
            }
        });

        String averageRating = item.getAverageRating();

        long rating = (averageRating == "") ? 0 : parseLong(averageRating);
        for (int i = 0; i < (int) rating; i++) {
            holder.starViews.get(i).setImageResource(R.drawable.star_red_2x);
            holder.starViews.get(i).invalidate();
        }
        holder.reviewLabel.setText(String.valueOf(rating).concat(" Stars"));
        Date df = new java.util.Date(item.getUpdateTime() * 1000);
        String text = TimeUtils.SHORT_DATE_FORMAT.format(df);
        holder.timeTextView.setText(text);
        holder.historyPrice.setText(item.getServiceFee());
        holder.serviceDetail.setOnClickListener(v -> {
            if (null != mListener) {
                // Notify the active callbacks interface (the activity, if the
                // fragment is attached to one) that an item has been selected.
                mListener.onListFragmentInteraction(holder.historyItem);
            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return cardItems.size();
    }

}

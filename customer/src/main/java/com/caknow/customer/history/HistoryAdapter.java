package com.caknow.customer.history;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.caknow.app.R;
import com.caknow.customer.home.HomeCardItem;

import java.util.List;

/**
 * Created by junu on 12/31/16.
 */

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    private List<HomeCardItem> cardItems;
    private HomeFragment.OnListFragmentInteractionListener mListener;

    // Provide a suitable constructor (depends on the kind of dataset)
    public HistoryAdapter(List<HomeCardItem> dataSet, HomeFragment.OnListFragmentInteractionListener listener) {
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
        HomeCardItem item = cardItems.get(position);
        holder.homeCardItem = item;
        holder.statusTextView.setText(item.getStatus());
        holder.statusTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.homeCardItem);
                }
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return cardItems.size();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView statusTextView;
        public TextView shopNameTextView;
        public ImageView shopImageView;
        public TextView serviceTypeTextView;
        public TextView serviceDetailTextView;
        public TextView serviceActionTextView;
        public ImageView carImageView;
        // each data item is just a string in this case
        private HomeCardItem homeCardItem;

        public ViewHolder(View v) {
            super(v);
            statusTextView = (TextView) v.findViewById(R.id.message_item_title);

        }
    }

}

package com.caknow.android.customer.app.home;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.caknow.app.R;

import java.util.List;

/**
 * Created by junu on 12/31/16.
 */

public class HomeCardAdapter extends RecyclerView.Adapter<HomeCardAdapter.ViewHolder> {
    private List<HomeCardItem> cardItems;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView statusTextView;
        public TextView carNameTextView;
        public TextView serviceTypeTextView;
        public TextView serviceDetailTextView;
        public TextView serviceActionTextView;
        public ImageView carImageView;
        public ViewHolder(View v) {
            super(v);
            statusTextView = (TextView) v.findViewById(R.id.title);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public HomeCardAdapter(List<HomeCardItem> dataSet) {
        cardItems = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public HomeCardAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.message_list_item, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        HomeCardItem item = cardItems.get(position);
        holder.statusTextView.setText(item.getStatus());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return cardItems.size();
    }

}

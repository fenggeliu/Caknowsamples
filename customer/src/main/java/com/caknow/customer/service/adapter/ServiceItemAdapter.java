package com.caknow.customer.service.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.caknow.app.BuildConfig;
import com.caknow.app.R;
import com.caknow.customer.service.fragment.NewServiceListFragment;
import com.caknow.customer.util.net.service.ServiceItem;

import java.util.List;

import butterknife.ButterKnife;

/**
 * {@link RecyclerView.Adapter} that can display a {@link com.caknow.customer.service.model.ServiceItem} and makes a call to the
 * specified {@link NewServiceListFragment.OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class ServiceItemAdapter extends RecyclerView.Adapter<ServiceItemAdapter.ViewHolder> {

    private final List<ServiceItem> mValues;
    private final NewServiceListFragment.OnListFragmentInteractionListener mListener;

    public ServiceItemAdapter(List<ServiceItem> items, NewServiceListFragment.OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_serviceitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).getCatagoryId());
        holder.mContentView.setText(mValues.get(position).getName());
        if(BuildConfig.DEBUG){
//            holder.mIdView.setVisibility(View.VISIBLE);
        }
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
        return mValues.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        final TextView mIdView;
        final TextView mContentView;
        ServiceItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = ButterKnife.findById(view, R.id.id);
            mContentView = ButterKnife.findById(view, R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}

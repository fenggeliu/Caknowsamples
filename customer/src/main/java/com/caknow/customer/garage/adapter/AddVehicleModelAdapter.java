package com.caknow.customer.garage.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.caknow.app.R;
import com.caknow.customer.garage.Model;
import com.caknow.customer.garage.fragment.AddVehicleMakeFragment;
import com.caknow.customer.garage.fragment.AddVehicleModelFragment;
import com.caknow.customer.garage.fragment.MMYListItem;
import com.caknow.customer.home.HomeCardItem;
import com.caknow.customer.home.HomeFragment;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link HomeCardItem} and makes a call to the
 * specified {@link HomeFragment.OnListFragmentInteractionListener}.
 *
 */
public class AddVehicleModelAdapter extends RecyclerView.Adapter<AddVehicleModelAdapter.ViewHolder> {

    private final List<Model> mValues;
    private final AddVehicleModelFragment.OnListFragmentInteractionListener mListener;

    public AddVehicleModelAdapter(List<Model> items, AddVehicleModelFragment.OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_addvehicle, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).getNiceName());
        holder.mContentView.setText(mValues.get(position).getName());

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
        if(mValues == null){
            return 0;
        }
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public Model mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}

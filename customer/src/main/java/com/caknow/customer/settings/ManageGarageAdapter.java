package com.caknow.customer.settings;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.caknow.customer.util.net.garage.Vehicle;

import com.caknow.app.R;
import com.caknow.customer.CAKNOWApplication;
import com.caknow.customer.util.net.garage.GarageAPI;
import com.caknow.customer.util.net.garage.GarageResponse;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by junu on 1/2/2017.
 */

public class ManageGarageAdapter extends BaseAdapter {

    private List<Vehicle> vehicleList;
    private Context mContext;
    private LayoutInflater inflater;
    @Inject
    Retrofit retrofit;

    public ManageGarageAdapter(Context context, List<Vehicle> vehicles){
        this.vehicleList = vehicles;
        this.mContext = context;
        this.inflater = (LayoutInflater) this.mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        CAKNOWApplication.get().getNetComponent().inject(this);
    }

    @Override
    public int getCount() {
        return vehicleList.size();
    }

    @Override
    public Vehicle getItem(int position) {
        return vehicleList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ManageVehicleView viewHolder;

        //Timber.d("position =" + position);
        if (convertView == null ) {
                convertView = this.inflater.inflate(R.layout.list_item_manage_car, parent, false);
                viewHolder = new ManageVehicleView(convertView, vehicleList.get(position));
                convertView.setTag(viewHolder);

        } else {
            viewHolder = (ManageVehicleView) convertView.getTag();
        }
        viewHolder.deleteButton.setOnClickListener(view -> delete(vehicleList.get(position).getId()));
        return convertView;
    }

    private void delete(String vehicleId){
        Call<GarageResponse> call = retrofit.create(GarageAPI.class).deleteVehicle(vehicleId);

        call.enqueue(new Callback<GarageResponse>() {
            @Override
            public void onResponse(Call<GarageResponse> call, Response<GarageResponse> response) {
                vehicleList = response.body().getPayload().getVehicles();
                notifyDataSetInvalidated();
            }

            @Override
            public void onFailure(Call<GarageResponse> call, Throwable t) {

            }
        });
    }
}

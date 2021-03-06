package com.caknow.customer.settings;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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

import static java.lang.String.format;

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
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(mContext);

        //Timber.d("position =" + position);
//        if (convertView == null ) {
                convertView = this.inflater.inflate(R.layout.list_item_manage_car, parent, false);
                viewHolder = new ManageVehicleView(convertView, vehicleList.get(position));
                convertView.invalidate();
//                convertView.setTag(viewHolder);
//        } else {
//            viewHolder = (ManageVehicleView) convertView.getTag();
//        }
        viewHolder.deleteButton.setOnClickListener(view -> {
            dialogBuilder.setMessage(format("Are you sure to delete %s?", vehicleList.get(position).getModel()));
            dialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    delete(vehicleList.get(position).getId());
                    vehicleList.remove(vehicleList.get(position)); //Actually change your list of items here
                    notifyDataSetChanged();
                }
            });
            dialogBuilder.setNegativeButton("Cancel", (dialogInterface, i) -> dialogInterface.dismiss());
            dialogBuilder.setCancelable(true);
            dialogBuilder.show();
        });
        return convertView;
    }

    private void delete(String vehicleId){
        Call<GarageResponse> call = retrofit.create(GarageAPI.class).deleteVehicle(vehicleId);

        call.enqueue(new Callback<GarageResponse>() {
            @Override
            public void onResponse(Call<GarageResponse> call, Response<GarageResponse> response) {
//                vehicleList = response.body().getPayload().getVehicles();

            }

            @Override
            public void onFailure(Call<GarageResponse> call, Throwable t) {

            }
        });
    }
}

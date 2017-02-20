package com.caknow.customer.settings;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.caknow.customer.CAKNOWApplication;
import com.caknow.customer.util.net.garage.Vehicle;

import com.caknow.app.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by junu on 1/10/2017.
 */

public class ManageVehicleView {
    private Context context;
    private Vehicle vehicle;
    @BindView(R.id.manage_vehicle_display_name_textview)
    TextView displayName;
    @BindView(R.id.manage_vehicle_delete_layout)
    LinearLayout deleteLayout;
    @BindView(R.id.manage_vehicle_delete_button)
    TextView deleteButton;
    @BindView(R.id.list_car_logo)
    ImageView carLogo;

    public ManageVehicleView(final View view, @NonNull final Vehicle vehicle) {
        this.vehicle = vehicle;
        ButterKnife.bind(this, view);
        context = view.getContext();

        StringBuilder name = new StringBuilder();
        name.append(vehicle.getYear()).append(vehicle.getMake()).append(vehicle.getModel());
        displayName.setText(name);
        displayName.invalidate();
        Glide.with(CAKNOWApplication.get()).load(vehicle.getLogo()).into(carLogo);
    }

    public Vehicle getVehicle(){
        return this.vehicle;
    }
}

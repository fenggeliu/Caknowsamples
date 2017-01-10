package com.caknow.customer.settings;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.caknow.app.R;
import com.caknow.customer.garage.Vehicle;

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

    public ManageVehicleView(final View view, @NonNull final Vehicle vehicle) {
        this.vehicle = vehicle;
        ButterKnife.bind(this, view);
        context = view.getContext();

        StringBuilder name = new StringBuilder();
        name.append(vehicle.getYear()).append(vehicle.getMake()).append(vehicle.getModel());
        displayName.setText(vehicle.getDisplayName());
        displayName.invalidate();

    }

    public Vehicle getVehicle(){
        return this.vehicle;
    }
}

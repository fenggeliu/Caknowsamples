package com.caknow.android.customer.app.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.caknow.android.customer.app.garage.Vehicle;
import com.caknow.app.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by junu on 1/2/2017.
 */

public class VehicleView {

    private Context context;
    private Vehicle vehicle;
    private int quoteCount;
    @BindView(R.id.vehicle_display_name)
    TextView displayName;

    @BindView(R.id.gli_car_logo_display)
    TextView logoView;

    @BindView(R.id.gli_quote_display)
    TextView quoteBubble;

    public VehicleView(final View view, @NonNull final Vehicle vehicle, int quoteCount) {
        this.vehicle = vehicle;
        ButterKnife.bind(this, view);
        context = view.getContext();
        displayName.setText(vehicle.getName());
        if(quoteCount == 0){
            quoteBubble.setVisibility(View.GONE);
        }
        else {
            String quoteString = " Quote";
            if(quoteCount > 1){
                quoteString = quoteString.concat("s");
            }
            quoteBubble.setText(String.valueOf(quoteCount).concat(quoteString));
        }
    }
}

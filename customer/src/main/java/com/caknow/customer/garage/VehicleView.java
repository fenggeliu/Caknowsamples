package com.caknow.customer.garage;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.caknow.app.R;
import com.caknow.customer.CAKNOWApplication;
import com.caknow.customer.util.constant.Constants;
import com.caknow.customer.util.net.garage.Vehicle;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by junu on 1/2/2017.
 */

public class VehicleView {

    private Context context;
    private Vehicle vehicle;
    private long quoteCount;
    @BindView(R.id.vehicle_display_name)
    TextView displayName;

    @BindView(R.id.gli_car_logo_display)
    ImageView logoView;

    @BindView(R.id.gli_quote_display)
    public TextView quoteBubble;

    public VehicleView(final View view, @NonNull final Vehicle vehicle) {
        this.vehicle = vehicle;
        ButterKnife.bind(this, view);
        context = view.getContext();
        quoteCount = vehicle.getQuoteCount();
        displayName.setText(vehicle.getMake().concat(" ").concat(vehicle.getModel()));
        String logoUrl = Constants.LOGOURL.concat(vehicle.getMake());
        Glide.with(CAKNOWApplication.get()).load(vehicle.getLogo()).into(logoView);
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

    public Vehicle getVehicle(){
        return this.vehicle;
    }
}

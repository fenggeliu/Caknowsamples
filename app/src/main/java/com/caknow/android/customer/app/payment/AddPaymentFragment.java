package com.caknow.android.customer.app.payment;

import android.os.Bundle;
import android.support.v4.BuildConfig;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.caknow.app.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by junu on 1/1/17.
 */

public class AddPaymentFragment extends Fragment {

    public static final String FRAGMENT_TAG = BuildConfig.APPLICATION_ID + AddPaymentFragment.class.getName();

    private String displayName;

    @BindView(R.id.acsl_vehicle_name)
    TextView vehicleName;

    @OnClick(R.id.ccl_have_card_add_btn)
    void addCard() {
        PaymentActivity activity = (PaymentActivity) getActivity();
        if (activity != null) {
            activity.finish();
        }
    }


    @OnClick(R.id.ccl_back_btn)
    void close() {
        PaymentActivity activity = (PaymentActivity) getActivity();
        if (activity != null && !activity.isFinishing()) {
            activity.finish();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        displayName = bundle.getString("displayName", "SOMETHING WENT WRONG");
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_payment, container, false);
        ButterKnife.bind(this, v);
        vehicleName.setText(displayName);
        return v;
    }

    //TODO DELETE

    private List<Payment> createDummyData() {
        List<Payment> payments = new ArrayList<>();
        payments.add(new Payment(Payment.Type.AMEX, 1234, "12/27", "John Doe"));

        return payments;
    }

}

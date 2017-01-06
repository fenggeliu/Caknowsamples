package com.caknow.customer.payment;

import android.os.Bundle;
import android.support.v4.BuildConfig;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.caknow.app.R;
import com.caknow.customer.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by junu on 1/1/17.
 */

public class AddPaymentFragment extends BaseFragment {

    public static final String FRAGMENT_TAG = BuildConfig.APPLICATION_ID + AddPaymentFragment.class.getName();


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
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add_payment, container, false);
        unbinder = ButterKnife.bind(this, v);
        return v;
    }

    //TODO DELETE

    private List<Payment> createDummyData() {
        List<Payment> payments = new ArrayList<>();
        payments.add(new Payment(Payment.Type.AMEX, 1234, "12/27", "John Doe"));

        return payments;
    }

}

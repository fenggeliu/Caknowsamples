package com.caknow.android.customer.app.payment;

import android.os.Bundle;
import android.support.v4.BuildConfig;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.caknow.android.customer.app.garage.VehicleType;
import com.caknow.app.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by junu on 1/1/17.
 */

public class PaymentFragment extends Fragment {

    public static final String FRAGMENT_TAG = BuildConfig.APPLICATION_ID + PaymentFragment.class.getName();

    private String displayName;

    @BindView(R.id.credit_card_content_list_view)
    RecyclerView cardsRecyclerView;

    @OnClick(R.id.ccl_have_card_add_btn)
    void addCard() {
        PaymentActivity activity = (PaymentActivity) getActivity();
        if (activity != null) {
            activity.replaceFragment(R.id.flContent, new AddPaymentFragment(), AddPaymentFragment.FRAGMENT_TAG, "add");
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
//        PaymentAdapter paymentAdapter = new PaymentAdapter(getContext(), createDummyData());
//        cardsRecyclerView.setAdapter(paymentAdapter);
        return v;
    }

    //TODO DELETE

    //TODO DELETE

    private List<Payment> createDummyData() {
        List<Payment> payments = new ArrayList<>();
        payments.add(new Payment(Payment.Type.AMEX, 1234, "12/27", "John Doe"));
        payments.add(new Payment(Payment.Type.AMEX, 1234, "12/27", "John Doe"));
        payments.add(new Payment(Payment.Type.AMEX, 1234, "12/27", "John Doe"));
        payments.add(new Payment(Payment.Type.AMEX, 1234, "12/27", "John Doe"));

        return payments;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(VehicleType item);
    }
}

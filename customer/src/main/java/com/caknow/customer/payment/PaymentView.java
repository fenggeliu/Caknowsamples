package com.caknow.customer.payment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.caknow.app.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by junu on 1/2/2017.
 */

public class PaymentView {

    @BindView(R.id.ccli_card_number)
    TextView cardNumber;
    @BindView(R.id.ccli_card_holder)
    TextView cardHolder;
    @BindView(R.id.ccli_del_expire_day)
    TextView expiration;
    @BindView(R.id.ccli_credit_card_category)
    TextView category;
    private Context context;
    private Payment payment;
    private int quoteCount;


    public PaymentView(final View view, @NonNull final Payment payment, int quoteCount) {
        this.payment = payment;
        ButterKnife.bind(this, view);
        context = view.getContext();
        cardHolder.setText(payment.getName());
        expiration.setText(payment.getExp());
        cardNumber.setText("XXXX XXXX XXXX ".concat(String.valueOf(payment.getLastFour())));


    }
}

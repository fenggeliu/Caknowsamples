package com.caknow.customer.transaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.BuildConfig;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.caknow.app.R;
import com.caknow.customer.history.HistoryActivity;
import com.caknow.customer.payment.PaymentActivity;
import com.caknow.customer.util.constant.Constants;
import com.caknow.customer.util.net.history.HistoryResponse;
import com.caknow.customer.util.net.quote.GetQuotesByServiceId;
import com.caknow.customer.util.net.quote.Quote;
import com.caknow.customer.util.net.service.quotes.QuoteList;
import com.caknow.customer.util.net.service.quotes.ServiceList;
import com.caknow.customer.widget.BaseFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by junu on 1/1/17.
 */

public class TransactionDetailsFragment extends BaseFragment implements Callback<HistoryResponse> {
    public static final String FRAGMENT_TAG = BuildConfig.APPLICATION_ID + TransactionDetailsFragment.class.getName();

    ArrayList<Parcelable> arguments;
    boolean paymentMode = false;
    @BindView(R.id.transaction_detail_listview)
    ListView detailListView;

    @BindView(R.id.transaction_submit_button)
    Button submitButton;

    @OnClick(R.id.transaction_submit_button)
    void selectCard(){
        final Intent intent = new Intent(getActivity(), PaymentActivity.class);
        intent.putExtra(Constants.PAYMENT_TYPE_PARCEL_KEY, "payment");
        getActivity().startActivityForResult(intent, TransactionActivity.PAYMENT_SELECTED_REQUEST_CODE);
    }
    TransactionDetailsAdapter adapter;
    TransactionQuoteAdapter quotesAdapter;
    String serviceRequestId;
    String quoteId;
    Quote quote;
    ServiceList service;
    ArrayList<Quote> quoteList;
    GetQuotesByServiceId response;
    ListView quotes;
    QuoteList mapQuote;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        quoteList = new ArrayList<>();
        View v = inflater.inflate(R.layout.fragment_transaction, container, false);
        unbinder = ButterKnife.bind(this, v);
        paymentMode = getArguments().getBoolean("paymentMode");
        if(paymentMode) {
            quoteList = null;
            try {
                quote = getArguments().getParcelable(Constants.TOP_QUOTE_ITEM_ID_PARCEL_KEY);
                mapQuote = getArguments().getParcelable(Constants.SELECTED_QUOTE_ITEM_ID_PARCEL_KEY);
            } catch (Exception e) {
            }
        }
        else{
            submitButton.setVisibility(View.GONE);
            response = getArguments().getParcelable(Constants.QUOTE_ITEM_ID_PARCEL_KEY);
            quoteList.add(response.getGetQuotesByServiceIdPayload().getTopQuote());
            quoteList.addAll(response.getGetQuotesByServiceIdPayload().getQuotes());
            quotesAdapter = new TransactionQuoteAdapter(getContext(), quoteList);
            quotes = (ListView) v.findViewById(R.id.transaction_quotes_listview);
            quotes.setVisibility(View.VISIBLE);
            quotes.setAdapter(quotesAdapter);
        }
        adapter = new TransactionDetailsAdapter(getContext(), quote, mapQuote,quoteList);
        detailListView.setAdapter(adapter);
        return v;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        try {
            getActivity().setTitle("Service Fee");
            ((HistoryActivity) getActivity()).showProgress();
        } catch(Exception e){
            //
        }

    }

    @Override
    public void onResponse(Call<HistoryResponse> call, Response<HistoryResponse> response) {

    }

    @Override
    public void onFailure(Call<HistoryResponse> call, Throwable t) {

    }
}

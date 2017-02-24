package com.caknow.customer.transaction;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.BuildConfig;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;

import com.caknow.app.R;
import com.caknow.customer.history.HistoryActivity;
import com.caknow.customer.job.JobActivity;
import com.caknow.customer.payment.PaymentActivity;
import com.caknow.customer.service.model.VehicleServiceInterface;
import com.caknow.customer.util.constant.Constants;
import com.caknow.customer.util.net.history.HistoryResponse;
import com.caknow.customer.util.net.payment.PaymentAPI;
import com.caknow.customer.util.net.quote.GetQuotesByServiceId;
import com.caknow.customer.util.net.quote.Quote;
import com.caknow.customer.util.net.service.quotes.QuoteList;
import com.caknow.customer.util.net.service.quotes.ServiceList;
import com.caknow.customer.widget.BaseFragment;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_FIRST_USER;
import static com.facebook.FacebookSdk.getApplicationContext;

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

    @BindView(R.id.quote_exp_listview)
    ExpandableListView quoteHistoryListView;

    @OnClick(R.id.transaction_submit_button)
    void selectCard(){
        if(mapQuote != null) {
            final Intent intent = new Intent(getActivity(), PaymentActivity.class);
            intent.putExtra(Constants.PAYMENT_TYPE_PARCEL_KEY, "payment");
            getActivity().startActivityForResult(intent, TransactionActivity.PAYMENT_SELECTED_REQUEST_CODE);
        }else if (response != null) {
            final Intent intent = new Intent(getActivity(), PaymentActivity.class);
            Bundle extras = new Bundle();
            extras.putString(Constants.PAYMENT_TYPE_PARCEL_KEY, "payment");
            extras.putParcelable(Constants.SELECTED_QUOTE_ITEM_ID_PARCEL_KEY, response.getGetQuotesByServiceIdPayload());
            intent.putExtras(extras);
            getActivity().startActivityForResult(intent, TransactionActivity.PAYMENT_SELECTED_REQUEST_CODE);
        }else{
            ((TransactionActivity) getActivity()).payToShop();
        }
    }
    QuoteHistoryAdapter quoteHistoryAdapter;
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
    VehicleServiceInterface item;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        quoteList = new ArrayList<>();
        View v = inflater.inflate(R.layout.fragment_transaction, container, false);
        unbinder = ButterKnife.bind(this, v);
        paymentMode = getArguments().getBoolean("paymentMode");
        item = getArguments().getParcelable(Constants.JOB_FRAGMENT_SERVICE_ITEM_PARCEL_KEY);
        if(paymentMode) {
            quoteList = null;
            try {
                item = getArguments().getParcelable(Constants.JOB_FRAGMENT_SERVICE_ITEM_PARCEL_KEY);
                quote = getArguments().getParcelable(Constants.TOP_QUOTE_ITEM_ID_PARCEL_KEY);
                mapQuote = getArguments().getParcelable(Constants.SELECTED_QUOTE_ITEM_ID_PARCEL_KEY);
            } catch (Exception e) {
            }
        }else{
            response = getArguments().getParcelable(Constants.QUOTE_ITEM_ID_PARCEL_KEY);
            try {
                if(response.getGetQuotesByServiceIdPayload().getTopQuote().getStatus().equals("unconfirmed")) {
                    ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("New Quote");
                    setHasOptionsMenu(true);
                    submitButton.setText("Accept New Quote");
                    quoteHistoryListView.setVisibility(View.VISIBLE);
                    quoteHistoryAdapter = new QuoteHistoryAdapter(getContext(), response.getGetQuotesByServiceIdPayload());
                    quoteHistoryListView.setAdapter(quoteHistoryAdapter);
                    quote = response.getGetQuotesByServiceIdPayload().getTopQuote();
                    quoteList.addAll(response.getGetQuotesByServiceIdPayload().getQuotes());
                }else{
                    submitButton.setVisibility(View.GONE);
                    quoteList.add(response.getGetQuotesByServiceIdPayload().getTopQuote());
                    quoteList.addAll(response.getGetQuotesByServiceIdPayload().getQuotes());
                }
            }catch (Exception e) {

            }
//            quotesAdapter = new TransactionQuoteAdapter(getContext(), quoteList);
//            quotes = (ListView) v.findViewById(R.id.transaction_quotes_listview);
//            quotes.setVisibility(View.VISIBLE);
//            quotes.setAdapter(quotesAdapter);
        }
        if(response != null && response.getGetQuotesByServiceIdPayload().getTopQuote().getStatus().equals("unconfirmed")){
            adapter = new TransactionDetailsAdapter(getContext(), quote, item, response.getGetQuotesByServiceIdPayload());
        }else if(quote == null){
            adapter = new TransactionDetailsAdapter(getContext(), mapQuote, quoteList);
        }else{
            adapter = new TransactionDetailsAdapter(getContext(), quote, item, null);
        }
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        inflater.inflate(R.menu.reject, menu);
        return;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        // Handle item selection
        int id = menuItem.getItemId();
        if (id == R.id.reject_top_quote){
            //rejection API endpoint
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("serviceRequestId", item.getServiceRequestId());
            jsonObject.addProperty("quoteId", response.getGetQuotesByServiceIdPayload().getTopQuote().getId());
            RequestBody rejectionRequest = RequestBody.create(MediaType.parse("application/json"),
                    jsonObject.toString());
            PaymentAPI paymentAPI = retrofit.create(PaymentAPI.class);
            paymentAPI.rejectTopQuote(rejectionRequest).enqueue(new Callback<RequestBody>() {
                @Override
                public void onResponse(Call<RequestBody> call, Response<RequestBody> response) {
                    Toast.makeText(getActivity(), "Quote Rejected", Toast.LENGTH_SHORT).show();
                    getActivity().finish();
//                    startActivity(getActivity().getIntent());
                }

                @Override
                public void onFailure(Call<RequestBody> call, Throwable t) {
                    getActivity().finish();
                }
            });
            return true;
        }

        return super.onOptionsItemSelected(menuItem);
    }
}

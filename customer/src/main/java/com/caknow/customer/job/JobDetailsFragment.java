package com.caknow.customer.job;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.BuildConfig;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.caknow.app.R;
import com.caknow.customer.service.model.VehicleServiceInterface;
import com.caknow.customer.transaction.TransactionActivity;
import com.caknow.customer.transaction.TransactionDetailsFragment;
import com.caknow.customer.util.constant.Constants;
import com.caknow.customer.util.net.BaseResponse;
import com.caknow.customer.util.net.quote.GetQuotesByServiceId;
import com.caknow.customer.util.net.service.GetQuotesResponse;
import com.caknow.customer.util.net.service.ServiceAPI;
import com.caknow.customer.util.net.service.quotes.QuoteList;
import com.caknow.customer.util.net.service.GetQuotesResponse;
import com.caknow.customer.widget.BaseFragment;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by junu on 1/1/17.
 */

public class JobDetailsFragment extends BaseFragment{
    public static final String FRAGMENT_TAG = BuildConfig.APPLICATION_ID + JobDetailsFragment.class.getName();


    @BindView(R.id.transaction_detail_listview)
    ListView detailListView;
    @BindView(R.id.job_detail_response_button)
    Button responseButton;
//    @BindView(R.id.job_detail_inservice_button)
//    Button inserviceButton;
//    @BindView(R.id.job_detail_confirmation_button)
//    Button confirmationButton;
    VehicleServiceInterface serviceItem;
    GetQuotesResponse responseBody;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_job_details, container, false);
        unbinder = ButterKnife.bind(this, v);
        serviceItem = getArguments().getParcelable(Constants.JOB_FRAGMENT_SERVICE_ITEM_PARCEL_KEY);
        responseBody = getArguments().getParcelable("responseBody");

        // Use the EmptyJobDetailListAdapter to show minimal items in the serivce detail list.
        if(serviceItem.getStatus() < 2){
            EmptyJobDetailListAdapter minimalDataAdapter = new EmptyJobDetailListAdapter(getContext(), serviceItem);
            detailListView.setAdapter(minimalDataAdapter);
        }
        else{
            JobDetailListAdapter adapter = new JobDetailListAdapter(getContext(), serviceItem, responseBody.getPayload().getAffiliate());
            detailListView.setAdapter(adapter);
            try {
                switch (serviceItem.getStatus()) {
                    case 2:
                        responseButton.setVisibility(View.VISIBLE);
                        responseButton.setText("Make Appointment");
                        responseButton.setBackgroundColor(Color.parseColor("#017aff"));
                        //TODO send request to shop too

                        responseButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
//                                showProgress();
                                // prepare call in Retrofit 2.0
                                JsonObject appointment = new JsonObject();
                                appointment.addProperty("serviceRequestId", serviceItem.getServiceRequestId());
                                appointment.addProperty("status","3");
                                RequestBody appointmentRequest = RequestBody.create(MediaType.parse("application/json"), appointment.toString());
                                ServiceAPI serviceAPI = retrofit.create(ServiceAPI.class);
                                serviceAPI.makeAppointment(appointmentRequest).enqueue(new Callback<BaseResponse>() {
                                    @Override
                                    public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                                        ((JobActivity)getActivity()).hideProgress();
                                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + responseBody.getPayload().getAffiliate().getTelephoneNumber()));
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                    }

                                    @Override
                                    public void onFailure(Call<BaseResponse> call, Throwable t) {

                                    }
                                });
                            }
                        });



                        break;
                    case 3:
                        responseButton.setVisibility(View.VISIBLE);
                        responseButton.setText("In Service");
                        responseButton.setBackgroundColor(Color.parseColor("#696969"));
                        break;
                    case 8:
                        responseButton.setVisibility(View.VISIBLE);
                        responseButton.setText("Confirm Completion");
                        responseButton.setBackgroundColor(Color.parseColor("#3CB371"));
                        responseButton.setOnClickListener(new View.OnClickListener() {
                            //TODO make sure it goes to the right transaction page.
                        @Override
                        public void onClick(View view) {

                            final Intent intent = new Intent(getActivity(), TransactionActivity.class);
                            final Bundle extras = new Bundle();

                            //extras.putParcelable(Constants.SELECTED_QUOTE_ITEM_ID_PARCEL_KEY, response.body());
                            extras.putString(Constants.SERVICE_REQUEST_ID_PARCEL_KEY, serviceItem.getServiceRequestId());
                            extras.putBoolean("paymentMode", false);
                            //extras.putString(Constants.PAYMENT_TYPE_PARCEL_KEY, "payment");
                            intent.putExtras(extras);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            //intent.putExtra(Constants.PAYMENT_TYPE_PARCEL_KEY, "payment");
                            startActivity(intent);
                        }});
                            break;
                    default:
                        break;
                }
            }catch (Exception e) {
            }
            detailListView.setOnItemClickListener((adapterView, view, i, l) -> {
               switch(i){
                   case 2:
                       retrofit.create(ServiceAPI.class).getQuotesForId(serviceItem.getServiceRequestId()).enqueue(new Callback<GetQuotesByServiceId>() {
                           @Override
                           public void onResponse(Call<GetQuotesByServiceId> call, Response<GetQuotesByServiceId> response) {
                               TransactionDetailsFragment fragment = new TransactionDetailsFragment();
                               final Bundle args = new Bundle();
                               //TODO need to put the proper quote to display item.
                               args.putParcelable(Constants.QUOTE_ITEM_ID_PARCEL_KEY, response.body());
                               args.putBoolean("paymentMode", false);
                               fragment.setArguments(args);
                               ((JobActivity)getActivity()).replaceFragment(R.id.job_fragment, fragment, TransactionDetailsFragment.FRAGMENT_TAG, "transaction");
                           }

                           @Override
                           public void onFailure(Call<GetQuotesByServiceId> call, Throwable t) {

                           }
                       });

                       break;
                   case 6:
                       if(((JobActivity)getActivity()).checkCallPermission()) {
                           Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + responseBody.getPayload().getAffiliate().getTelephoneNumber()));
                           intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                           startActivity(intent);
                       }
                       break;
                }
            });
        }

        return v;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        try {
            getActivity().setTitle("Service");
        } catch(Exception e){
            //
        }

    }

}

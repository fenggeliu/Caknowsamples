package com.caknow.customer.history;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.BuildConfig;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.caknow.app.R;
import com.caknow.customer.job.EmptyJobDetailListAdapter;
import com.caknow.customer.job.JobActivity;
import com.caknow.customer.job.JobDetailListAdapter;
import com.caknow.customer.service.model.VehicleServiceInterface;
import com.caknow.customer.transaction.TransactionDetailsFragment;
import com.caknow.customer.util.constant.Constants;
import com.caknow.customer.util.net.history.History;
import com.caknow.customer.util.net.quote.GetQuotesByServiceId;
import com.caknow.customer.util.net.service.GetQuotesResponse;
import com.caknow.customer.util.net.service.ServiceAPI;
import com.caknow.customer.widget.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by junu on 1/1/17.
 */

public class HistoryDetailsFragment extends BaseFragment{
    public static final String FRAGMENT_TAG = BuildConfig.APPLICATION_ID + HistoryDetailsFragment.class.getName();

    @BindView(R.id.transaction_detail_listview)
    ListView detailListView;
    History serviceItem;
    GetQuotesResponse responseBody;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_job_details, container, false);
        unbinder = ButterKnife.bind(this, v);
        serviceItem = getArguments().getParcelable(Constants.HISTORY_ITEM_PARCEL_KEY);
        responseBody = getArguments().getParcelable("responseBody");

        // Use the EmptyJobDetailListAdapter to show minimal items in the serivce detail list.

        HistoryDetailsAdapter adapter = new HistoryDetailsAdapter(this, serviceItem);
        detailListView.setAdapter(adapter);
        detailListView.setOnItemClickListener((adapterView, view, i, l) -> {
           switch(i){
               case 2:
                   ((HistoryActivity)getActivity()).showProgress();
                   retrofit.create(ServiceAPI.class).getQuotesForId(serviceItem.getServiceRequestId()).enqueue(new Callback<GetQuotesByServiceId>() {
                       @Override
                       public void onResponse(Call<GetQuotesByServiceId> call, Response<GetQuotesByServiceId> response) {
                           ((HistoryActivity)getActivity()).hideProgress();
                           TransactionDetailsFragment fragment = new TransactionDetailsFragment();
                           final Bundle args = new Bundle();
                           //TODO need to put the proper quote to display item.
                           args.putParcelable(Constants.QUOTE_ITEM_ID_PARCEL_KEY, response.body());
                           args.putBoolean("paymentMode", false);
                           fragment.setArguments(args);
                           ((HistoryActivity)getActivity()).replaceFragment(R.id.job_fragment, fragment, TransactionDetailsFragment.FRAGMENT_TAG, "transaction");
                       }

                       @Override
                       public void onFailure(Call<GetQuotesByServiceId> call, Throwable t) {
                           ((HistoryActivity)getActivity()).hideProgress();
                       }
                   });

                   break;
               case 6:
                   Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + responseBody.getPayload().getAffiliate().getTelephoneNumber()));
                   intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                   startActivity(intent);
                   break;
            }
        });


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

    public boolean checkCallPermission(){
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.CALL_PHONE)) {
            } else {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.CALL_PHONE},
                        1);
            }
        }
        return true;
    }

}

package com.caknow.customer.history;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.BuildConfig;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.caknow.app.R;

import com.caknow.customer.transaction.TransactionActivity;
import com.caknow.customer.util.constant.Constants;
import com.caknow.customer.util.net.history.History;
import com.caknow.customer.util.net.service.GetQuotesResponse;
import com.caknow.customer.widget.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;


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
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
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
               case 1:
                   Intent intent = new Intent(getActivity(), TransactionActivity.class);
                   final Bundle extras = new Bundle();
                   extras.putString(Constants.SERVICE_REQUEST_ID_PARCEL_KEY, serviceItem.getServiceRequestId());
                   extras.putBoolean("paymentMode", false);
                   intent.putExtras(extras);
                   intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                   startActivity(intent);
                   break;
               case 4:
                   Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + serviceItem.getShopAddress());
                   Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                   mapIntent.setPackage("com.google.android.apps.maps");
                   startActivity(mapIntent);
                   break;
               case 5:
                   Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + serviceItem.getShopPhone()));
                   callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                   startActivity(callIntent);
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

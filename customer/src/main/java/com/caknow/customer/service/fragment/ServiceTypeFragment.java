package com.caknow.customer.service.fragment;

import android.os.Bundle;
import android.support.v4.BuildConfig;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.caknow.app.R;
import com.caknow.customer.BaseFragment;
import com.caknow.customer.CAKNOWApplication;
import com.caknow.customer.service.NewServiceRequestActivity;
import com.caknow.customer.service.ServiceItemView;
import com.caknow.customer.service.adapter.ServiceTypeAdapter;
import com.caknow.customer.service.model.ServiceItem;
import com.caknow.customer.util.constant.Constants;
import com.caknow.customer.util.net.service.ServiceAPI;
import com.caknow.customer.util.net.service.ServiceList;
import com.caknow.customer.util.net.service.ServiceTypeResponse;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by junu on 1/1/17.
 */

public class ServiceTypeFragment extends BaseFragment implements Callback<ServiceTypeResponse> {

    public static final String FRAGMENT_TAG = BuildConfig.APPLICATION_ID + ServiceTypeFragment.class.getName();

    @BindView(R.id.rcll_grid_view)
    GridView repairLayout;

    @Inject
    Retrofit retrofit;
    ServiceAPI serviceAPI;
    private int typeId;

    private ServiceTypeAdapter adapter;
    private ArrayList<ServiceList> itemsToDisplay;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        CAKNOWApplication.get().getNetComponent().inject(this);
        Bundle args = getArguments();
        if(args!=null){
           itemsToDisplay = args.getParcelableArrayList(Constants.ITEM_LIST_PARCEL_KEY);
            typeId = args.getInt(Constants.SERVICE_TYPE_KEY, 1);
        }
        if(itemsToDisplay == null){
            itemsToDisplay = new ArrayList();
        }
        adapter = new ServiceTypeAdapter(this.getContext(), itemsToDisplay);

        serviceAPI = retrofit.create(ServiceAPI.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_service_type, container, false);
        unbinder = ButterKnife.bind(this, v);

        repairLayout.setAdapter(adapter);
        repairLayout.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ServiceList item = itemsToDisplay.get(i);
                loadDataOnClick(ServiceTypeFragment.this.typeId, item.getCatagoryId());
            }
        });
        return v;
    }


    private void loadDataOnClick(final int typeId, final String parentId){

        Call<ServiceTypeResponse> call = serviceAPI.getServiceTypeList(typeId, parentId);
        //asynchronous call
        call.enqueue(this);

    }

    /**
     * Load the first list of service types with a null parentId
     * @param parcelableArrayList
     */
    private void openNextFragment(ArrayList parcelableArrayList){
        if(getActivity() != null) {
            NewServiceRequestActivity homeActivity = (NewServiceRequestActivity) getActivity();
            ServiceTypeFragment fragment = new ServiceTypeFragment();
            Bundle args = new Bundle();
            args.putParcelableArrayList(Constants.ITEM_LIST_PARCEL_KEY, parcelableArrayList);
            fragment.setArguments(new Bundle());
            homeActivity.replaceFragment(R.id.flContent,
                    new ServiceTypeFragment(),
                    ServiceTypeFragment.FRAGMENT_TAG, "serviceTypeFragment");
        }
    }


    @Override
    public void onResponse(Call<ServiceTypeResponse> call, Response<ServiceTypeResponse> response) {
        ArrayList<ServiceList> parcelableArrayList = new ArrayList<ServiceList>();
        parcelableArrayList.addAll(response.body().getPayload().getList());
        openNextFragment(parcelableArrayList);
    }

    @Override
    public void onFailure(Call<ServiceTypeResponse> call, Throwable t) {

    }

}

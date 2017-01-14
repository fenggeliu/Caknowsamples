package com.caknow.customer.service.fragment;

import android.os.Bundle;
import android.support.v4.BuildConfig;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.caknow.app.R;
import com.caknow.customer.widget.BaseFragment;
import com.caknow.customer.service.NewServiceRequestActivity;
import com.caknow.customer.service.adapter.ServiceTypeAdapter;
import com.caknow.customer.util.constant.Constants;
import com.caknow.customer.util.net.service.ServiceAPI;
import com.caknow.customer.util.net.service.ServiceTypeResponse;
import com.caknow.customer.util.net.service.ServiceItem;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by junu on 1/1/17.
 */

public class NewServiceTypeFragment extends BaseFragment implements Callback<ServiceTypeResponse> {

    public static final String FRAGMENT_TAG = BuildConfig.APPLICATION_ID + NewServiceTypeFragment.class.getName();

    @BindView(R.id.rcll_grid_view)
    GridView repairLayout;

    ServiceAPI serviceAPI;
    private int typeId;
    private String serviceId;
    private ServiceTypeAdapter adapter;
    private ArrayList<ServiceItem> itemsToDisplay;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        ((NewServiceRequestActivity) getActivity()).updateTitle("Choose Service Type", R.drawable.ic_action_back);
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
        setHasOptionsMenu(false);
    }

    @Override
    public void onResume(){
        super.onResume();
        String typeString = "";
        switch(typeId){
            case 1:
                typeString = "Repair";
                break;
            case 2:
                typeString = "Maintenance";
                break;
            case 3:
                typeString = "Emergency";
                break;
        }
        ((NewServiceRequestActivity)getActivity()).updateTitle(typeString, R.drawable.ic_action_back);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_service_type, container, false);
        unbinder = ButterKnife.bind(this, v);

        repairLayout.setAdapter(adapter);
        repairLayout.setOnItemClickListener((adapterView, view, i, l) -> {
            ServiceItem item = itemsToDisplay.get(i);
            serviceId = item.getCatagoryId();
            loadDataOnClick(NewServiceTypeFragment.this.typeId, serviceId);
        });
        return v;
    }


    private void loadDataOnClick(final int typeId, final String parentId){
        ((NewServiceRequestActivity) getActivity()).showProgress();
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
            NewServiceListFragment fragment = new NewServiceListFragment();
            Bundle args = new Bundle();
            args.putParcelableArrayList(Constants.ITEM_LIST_PARCEL_KEY, parcelableArrayList);
            fragment.setArguments(args);
            homeActivity.replaceFragment(R.id.flContent,
                    fragment,
                    NewServiceListFragment.FRAGMENT_TAG, "serviceList");
        }
    }


    @Override
    public void onResponse(Call<ServiceTypeResponse> call, Response<ServiceTypeResponse> response) {
        try{
            ((NewServiceRequestActivity) getActivity()).hideProgress();
        } catch(Exception e){
            // Thread safe
        }
        ArrayList<ServiceItem> parcelableArrayList = new ArrayList<ServiceItem>();
        parcelableArrayList.addAll(response.body().getServicesPayload().getList());
        // If there are no more sub items to display...
        if(parcelableArrayList.size() > 0) {

            ((NewServiceRequestActivity) getActivity()).setServiceDescription(serviceId);

            openNextFragment(parcelableArrayList);

        }
        else{

            NewServiceDetailsFragment fragment = new NewServiceDetailsFragment();
            Bundle args = new Bundle();

            fragment.setArguments(args);
            try {
                ((NewServiceRequestActivity) getActivity()).setServiceId(serviceId);
                ((NewServiceRequestActivity)getActivity()).replaceFragment(R.id.flContent, fragment, NewServiceDetailsFragment.FRAGMENT_TAG, "details");
            } catch (Exception e){
                //
            }
        }
    }

    @Override
    public void onFailure(Call<ServiceTypeResponse> call, Throwable t) {
        try{
            ((NewServiceRequestActivity) getActivity()).hideProgress();
        } catch(Exception e){
            // Thread safe
        }
    }

}

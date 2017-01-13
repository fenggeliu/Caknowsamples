package com.caknow.customer.service.fragment;

import android.os.Bundle;
import android.support.v4.BuildConfig;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.caknow.app.R;
import com.caknow.customer.BaseFragment;
import com.caknow.customer.CAKNOWApplication;
import com.caknow.customer.service.NewServiceRequestActivity;
import com.caknow.customer.util.constant.Constants;
import com.caknow.customer.util.net.service.ServiceAPI;
import com.caknow.customer.util.net.service.Services;
import com.caknow.customer.util.net.service.ServiceTypeResponse;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by junu on 1/1/17.
 */

public class NewServiceFragment extends BaseFragment implements Callback<ServiceTypeResponse> {

    public static final String FRAGMENT_TAG = BuildConfig.APPLICATION_ID + NewServiceFragment.class.getName();

    //FIXME: group listener
    @BindView(R.id.nsr_repair_layout)
    LinearLayout repairLayout;
    @BindView(R.id.nsr_maintenance_layout)
    LinearLayout maintenanceLayout;
    @BindView(R.id.nsr_emergency_layout)
    LinearLayout emergencyLayout;

    @Inject
    Retrofit retrofit;

    ServiceAPI serviceAPI;

    private int typeId = 0;
    @OnClick(R.id.nsr_repair_layout)
    void startRepair(){
        loadDataOnClick(1);
    }

    @OnClick(R.id.nsr_maintenance_layout)
    void startMaintenance(){
        loadDataOnClick(2);
    }

    @OnClick(R.id.nsr_emergency_layout)
    void startEmergency(){
        loadDataOnClick(3);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    @Override
    public void onResume(){
        super.onResume();
        ((NewServiceRequestActivity)getActivity()).updateTitle("Select Type", R.drawable.ic_action_back);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_new_service, container, false);
        unbinder = ButterKnife.bind(this, v);
        CAKNOWApplication.get().getNetComponent().inject(this);

        serviceAPI = retrofit.create(ServiceAPI.class);


        return v;
    }

    private void loadDataOnClick(final int typeId){
        try{
            ((NewServiceRequestActivity) getActivity()).showProgress();
            ((NewServiceRequestActivity) getActivity()).setServiceType(typeId);
        } catch(Exception e){
            // Thread safe
        }
        this.typeId = typeId;
        Call<ServiceTypeResponse> call = serviceAPI.getServiceTypeList(typeId, null);
        //asynchronous call
        call.enqueue(this);

    }

    /**
     * Load the first list of service types with a null parentId
     * @param parcelableArrayList
     */
    private void openNextFragment(final int typeId, final ArrayList parcelableArrayList){
        if(getActivity() != null) {
            NewServiceRequestActivity homeActivity = (NewServiceRequestActivity) getActivity();
            homeActivity.setServiceType(typeId);
            ServiceTypeFragment fragment = new ServiceTypeFragment();
            Bundle args = new Bundle();
            args.putParcelableArrayList(Constants.ITEM_LIST_PARCEL_KEY, parcelableArrayList);
            fragment.setArguments(args);
            homeActivity.replaceFragment(R.id.flContent,
                    fragment,
                    ServiceTypeFragment.FRAGMENT_TAG, "serviceTypeFragment");
        }
    }


    @Override
    public void onResponse(Call<ServiceTypeResponse> call, Response<ServiceTypeResponse> response) {
        ArrayList<Services> parcelableArrayList = new ArrayList<Services>();
        parcelableArrayList.addAll(response.body().getPayload().getList());
        openNextFragment(this.typeId, parcelableArrayList);
        try{
            ((NewServiceRequestActivity) getActivity()).hideProgress();
        } catch(Exception e){
            // Thread safe
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

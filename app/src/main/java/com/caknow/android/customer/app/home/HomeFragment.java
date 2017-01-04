package com.caknow.android.customer.app.home;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.BuildConfig;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.caknow.android.customer.app.garage.AddVehicleFragment;
import com.caknow.android.customer.app.garage.VehicleType;
import com.caknow.app.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by junu on 1/1/17.
 */

public class HomeFragment extends Fragment {
    public static final String FRAGMENT_TAG = BuildConfig.APPLICATION_ID + HomeFragment.class.getName();

    @BindView(R.id.home_recyclerview)
    RecyclerView homeCardRV;
    @BindView(R.id.home_empty_garage_view)
    FrameLayout emptyGarageLayout;

    HomeCardAdapter homeCardAdapter;
    OnListFragmentInteractionListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, v);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        homeCardRV.setLayoutManager(llm);
        this.homeCardAdapter = new HomeCardAdapter(createTestData(), mListener);
        if(homeCardAdapter.getItemCount() == 0){
            emptyGarageLayout.setVisibility(View.VISIBLE);
        }
        homeCardRV.setAdapter(homeCardAdapter);

        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        getActivity().setTitle("CAKNOW");

        if (context instanceof HomeFragment.OnListFragmentInteractionListener) {
            mListener = (HomeFragment.OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }



    //TODO REMOVE!
    private List<HomeCardItem> createTestData(){
        List<HomeCardItem> cardItemList = new ArrayList<>();
        cardItemList.add(new HomeCardItem("status", "vehicleId", "vehicleType", "date", "detailInfo", "action" ));
        cardItemList.add(new HomeCardItem("status1", "vehicleId", "vehicleType", "date", "detailInfo", "action" ));
        cardItemList.add(new HomeCardItem("status2", "vehicleId", "vehicleType", "date", "detailInfo", "action" ));

        return cardItemList;
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
        void onListFragmentInteraction(HomeCardItem item);
    }
}

package com.caknow.android.customer.app.fragment;

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

import com.caknow.android.customer.app.home.HomeCardAdapter;
import com.caknow.android.customer.app.home.HomeCardItem;
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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, v);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        homeCardRV.setLayoutManager(llm);
        this.homeCardAdapter = new HomeCardAdapter(createTestData());
        if(homeCardAdapter.getItemCount() == 0){
            emptyGarageLayout.setVisibility(View.VISIBLE);
        }
        homeCardRV.setAdapter(homeCardAdapter);
        return v;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        getActivity().setTitle("CAKNOW");
    }

    //TODO REMOVE!
    private List<HomeCardItem> createTestData(){
        List<HomeCardItem> cardItemList = new ArrayList<>();
        cardItemList.add(new HomeCardItem("status", "vehicleId", "vehicleType", "date", "detailInfo", "action" ));
        cardItemList.add(new HomeCardItem("status1", "vehicleId", "vehicleType", "date", "detailInfo", "action" ));
        cardItemList.add(new HomeCardItem("status2", "vehicleId", "vehicleType", "date", "detailInfo", "action" ));

        return cardItemList;
    }
}

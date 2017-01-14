package com.caknow.customer.history;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.caknow.app.R;
import com.caknow.customer.job.JobActivity;
import com.caknow.customer.util.constant.Constants;
import com.caknow.customer.widget.BaseActivity;
import com.caknow.customer.CAKNOWApplication;
import com.caknow.customer.util.net.history.History;
import com.caknow.customer.util.net.history.HistoryAPI;
import com.caknow.customer.util.net.history.HistoryResponse;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by junu on 1/2/2017.
 */

// TODO Need to load next screen after selecting history item
public class HistoryActivity extends BaseActivity implements HistoryFragment.OnListFragmentInteractionListener, Callback<HistoryResponse> {

    @Inject
    Retrofit retrofit;

    private ArrayList<History> historyList;
    @Override
    protected void initContentView() {

        setContentView(R.layout.activity_history);
        CAKNOWApplication.get().getNetComponent().inject(this);
        ButterKnife.bind(this);

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        loadHistoryData();
    }

    @Override
    protected void configView() {

    }

    @Override
    protected void setTitle() {
        try {
            ((TextView)getSupportActionBar().getCustomView().findViewById(R.id.mytext)).setText("History");
            ((ImageView)getSupportActionBar().getCustomView().findViewById(R.id.custom_ab_home_button)).setImageResource(R.drawable.ic_action_close);
        } catch (NullPointerException e){
            //
        }
    }

    @Override
    public void onListFragmentInteraction(History item) {
        HistoryDetailsFragment detailFragment = new HistoryDetailsFragment();
        final Bundle args = new Bundle();
        args.putParcelable(Constants.HISTORY_ITEM_PARCEL_KEY, item);
        detailFragment.setArguments(args);
        replaceFragment(R.id.historyContent, detailFragment, HistoryDetailsFragment.FRAGMENT_TAG, "historyDetail");
    }
    private void loadHistoryData(){
        Call<HistoryResponse> call = retrofit.create(HistoryAPI.class).getHistory();
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<HistoryResponse> call, Response<HistoryResponse> response) {
        try {
            hideProgress();
            HistoryFragment fragment = new HistoryFragment();
            final Bundle args = new Bundle();

            ArrayList<History> data = new ArrayList<>();
            data.addAll(response.body().getPayload().getList());
            historyList = data;
            args.putParcelableArrayList("historyList", historyList);
            fragment.setArguments(args);
            addFragment(R.id.historyContent, fragment, HistoryFragment.FRAGMENT_TAG);

        } catch(Exception e){
            //
        }
    }

    @Override
    public void onFailure(Call<HistoryResponse> call, Throwable t) {

    }
}

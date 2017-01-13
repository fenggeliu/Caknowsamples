package com.caknow.customer.history;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.BuildConfig;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.caknow.app.R;
import com.caknow.customer.BaseFragment;
import com.caknow.customer.CAKNOWApplication;
import com.caknow.customer.util.net.history.HistoryAPI;
import com.caknow.customer.util.net.history.HistoryResponse;

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

public class HistoryFragment extends BaseFragment implements Callback<HistoryResponse> {
    public static final String FRAGMENT_TAG = BuildConfig.APPLICATION_ID + HistoryFragment.class.getName();

    @BindView(R.id.no_history_display)
    LinearLayout emptyHistoryView;

    @Inject
    Retrofit retrofit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_history, container, false);
        unbinder = ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            getActivity().setTitle("History");
            ((HistoryActivity) getActivity()).showProgress();
        } catch (Exception e) {
            //
        }
        CAKNOWApplication.get().getNetComponent().inject(this);

        Call<HistoryResponse> call = retrofit.create(HistoryAPI.class).getHistory();
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<HistoryResponse> call, Response<HistoryResponse> response) {
        try {
            ((HistoryActivity) getActivity()).hideProgress();

        } catch (Exception e) {
            //
        }
        if (response.body().getPayload().getList().isEmpty()) {
            emptyHistoryView.setVisibility(View.VISIBLE);
            emptyHistoryView.invalidate();
        } else {

        }

    }

    @Override
    public void onFailure(Call<HistoryResponse> call, Throwable t) {
        try {
            ((HistoryActivity) getActivity()).hideProgress();
            emptyHistoryView.setVisibility(View.VISIBLE);
            emptyHistoryView.invalidate();
        } catch (Exception e) {
            //
        }
    }
}

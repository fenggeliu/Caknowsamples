package com.caknow.customer.history;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.BuildConfig;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.caknow.app.R;
import com.caknow.customer.home.HomeFragment;
import com.caknow.customer.widget.BaseFragment;
import com.caknow.customer.util.net.history.History;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by junu on 1/1/17.
 */

public class HistoryFragment extends BaseFragment{
    public static final String FRAGMENT_TAG = BuildConfig.APPLICATION_ID + HistoryFragment.class.getName();

    @BindView(R.id.no_history_display)
    LinearLayout emptyHistoryView;

    @BindView(R.id.history_recyclerview)
    RecyclerView recyclerView;
    private HistoryAdapter adapter;
    private OnListFragmentInteractionListener mListener;
    private ArrayList<History> historyList;
    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        Bundle bundle = getArguments();
        historyList = bundle.getParcelableArrayList("historyList");
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_history, container, false);
        unbinder = ButterKnife.bind(this, v);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(llm);
        if(historyList == null || historyList.size() == 0){
            emptyHistoryView.setVisibility(View.VISIBLE);
        }
        else{
            emptyHistoryView.setVisibility(View.GONE);
            adapter = new HistoryAdapter(historyList, mListener);
            recyclerView.setAdapter(adapter);
        }
        return v;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        getActivity().setTitle("History");

        if (context instanceof HistoryFragment.OnListFragmentInteractionListener) {
            mListener = (HistoryFragment.OnListFragmentInteractionListener) context;
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




    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and niceName
        void onListFragmentInteraction(History item);
    }
}

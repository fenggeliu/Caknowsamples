package com.caknow.customer.payment;

import android.content.Context;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.BuildConfig;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.caknow.app.R;
import com.caknow.customer.widget.BaseFragment;
import com.caknow.customer.util.constant.Constants;
import com.caknow.customer.util.net.payment.model.PaymentMethodItem;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A fragment that contains the list of all available payment methods
 *
 * The data for this fragment should be retrieved in the {@link PaymentActivity}
 *
 * All OnCLick interactions in the list are handled by the {@link PaymentActivity}
 *
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class PaymentMethodFragment extends BaseFragment {
    public static final String FRAGMENT_TAG = BuildConfig.APPLICATION_ID + PaymentMethodFragment.class.getName();

    @BindView(R.id.empty_card_layout)
    ConstraintLayout emptyCardLayout;

    @BindView(R.id.credit_card_recyclerview)
    RecyclerView recyclerView;

    private OnListFragmentInteractionListener mListener;
    // List of payment method items
    private ArrayList<PaymentMethodItem> data;
    private PaymentMethodAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    // Add New Card button On Click event
    @OnClick(R.id.add_card_button)
    void addCard(){
        AddPaymentFragment fragment = new AddPaymentFragment();
        ((PaymentActivity)getActivity()).replaceFragment(R.id.paymentContent, fragment, AddPaymentFragment.FRAGMENT_TAG, "addPayment");
    }
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PaymentMethodFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static PaymentMethodFragment newInstance(int columnCount) {
        PaymentMethodFragment fragment = new PaymentMethodFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null) {
            data = getArguments().getParcelableArrayList(Constants.PAYMENT_METHOD_LIST_PARCEL_KEY);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View mainView =  inflater.inflate(R.layout.fragment_creditcard, container, false);
        unbinder = ButterKnife.bind(this, mainView);
        View view =  ButterKnife.findById(mainView, R.id.credit_card_recyclerview);
        checkEmptyCards();
        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            layoutManager = new LinearLayoutManager(context);
            recyclerView.setLayoutManager(layoutManager);
            adapter = new PaymentMethodAdapter(data, mListener, ((PaymentActivity)getActivity()));
            recyclerView.setAdapter(adapter);
        }
        return mainView;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
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
        void onListFragmentInteraction(PaymentMethodItem item);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.credit_card, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // Enter edit mode
        // Since we are using the same recycler view, after changing the items in the adapter, these are the steps required
        // to update the data
        if (id == R.id.action_edit_cards) {
            adapter.toggleEditMode();
            recyclerView.setAdapter(null);
            recyclerView.setLayoutManager(null);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(layoutManager);
            //adapter.notifyDataSetChanged();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Toggles visibility of the empty card layout based on data returned
    private void checkEmptyCards(){
        if (data == null || data.isEmpty()){
            emptyCardLayout.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else{
            emptyCardLayout.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }


}

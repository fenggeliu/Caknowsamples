package com.caknow.customer.garage;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.BuildConfig;
import com.caknow.customer.BaseFragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.caknow.app.R;
import com.caknow.customer.garage.adapter.AddVehicleAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class AddVehicleFragment extends BaseFragment {

    public static final String FRAGMENT_TAG = BuildConfig.APPLICATION_ID + AddVehicleFragment.class.getName();

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    private ListTypes listType;
    private enum ListTypes{
        MAKE, MODEL, YEAR, DONE
    }
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public AddVehicleFragment() {
    }



    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static AddVehicleFragment newInstance(int columnCount) {
        AddVehicleFragment fragment = new AddVehicleFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            final Bundle bundle = getArguments();
            mColumnCount = bundle.getInt(ARG_COLUMN_COUNT);
            int type = bundle.getInt("type");
            switch(type){
                case 0:
                    listType = ListTypes.MAKE;
                    break;

                case 1:
                    listType = ListTypes.MODEL;
                    break;

                case 2:
                    listType = ListTypes.YEAR;
                    break;

                case 3:
                    listType = ListTypes.DONE;
                    break;
                default:
                    break;
            }
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_addvehicle_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new AddVehicleAdapter(createDummyData(), mListener));
        }
        return view;
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

    private List<VehicleType> createDummyData(){
        List<VehicleType> strings = new ArrayList<>();

        if(listType == ListTypes.MAKE) {
            strings.add(new VehicleType("0000", "AM General", ListTypes.MAKE.ordinal()));
            strings.add(new VehicleType("0000", "Acura", ListTypes.MAKE.ordinal()));
            strings.add(new VehicleType("0000", "Alfa Romeo", ListTypes.MAKE.ordinal()));
            strings.add(new VehicleType("0000", "Aston Martin", ListTypes.MAKE.ordinal()));
            strings.add(new VehicleType("0000", "Audi", ListTypes.MAKE.ordinal()));
            strings.add(new VehicleType("0000", "Acura", ListTypes.MAKE.ordinal()));
            strings.add(new VehicleType("0000", "BMW", ListTypes.MAKE.ordinal()));
            strings.add(new VehicleType("0000", "Bentley", ListTypes.MAKE.ordinal()));
            strings.add(new VehicleType("0000", "Bugatti", ListTypes.MAKE.ordinal()));
            strings.add(new VehicleType("0000", "Chevrolet", ListTypes.MAKE.ordinal()));
            strings.add(new VehicleType("0000", "Dodge", ListTypes.MAKE.ordinal()));
            strings.add(new VehicleType("0000", "Ford", ListTypes.MAKE.ordinal()));
            strings.add(new VehicleType("0000", "GMC", ListTypes.MAKE.ordinal()));
            strings.add(new VehicleType("0000", "General Motors", ListTypes.MAKE.ordinal()));
            strings.add(new VehicleType("0000", "Honda", ListTypes.MAKE.ordinal()));
            strings.add(new VehicleType("0000", "Infiniti", ListTypes.MAKE.ordinal()));
            strings.add(new VehicleType("0000", "Jaguar", ListTypes.MAKE.ordinal()));
        } else if (listType == ListTypes.MODEL) {
            for(int i = 0; i < 20; i++) {
                strings.add(new VehicleType(String.valueOf(i), "Model ".concat(String.valueOf(i)), ListTypes.MODEL.ordinal()));
            }
        }
        else if (listType == ListTypes.YEAR) {
            for(int i = 0; i < 20; i++) {
             strings.add(new VehicleType(String.valueOf(i), String.valueOf(2017-i), ListTypes.YEAR.ordinal()));
            }
        }
        return strings;
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
        void onListFragmentInteraction(VehicleType item);
    }
}

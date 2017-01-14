package com.caknow.customer.widget;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.caknow.customer.CAKNOWApplication;

import javax.inject.Inject;

import butterknife.Unbinder;
import retrofit2.Retrofit;

/**
 * Created by jkang on 1/4/17.
 */
public class BaseFragment extends Fragment {
    protected Unbinder unbinder;
    @Inject
    protected
    Retrofit retrofit;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        CAKNOWApplication.get().getNetComponent().inject(this);
    }
    @Override
    public void onDetach(){
        super.onDetach();
        if(unbinder != null){
            unbinder.unbind();
        }
    }
    // ===========================================================
    // Fields
    // ===========================================================
}

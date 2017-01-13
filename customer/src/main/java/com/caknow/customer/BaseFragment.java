package com.caknow.customer;

import android.support.v4.app.Fragment;

import butterknife.Unbinder;

/**
 * Created by jkang on 1/4/17.
 */
public class BaseFragment extends Fragment {
    protected Unbinder unbinder;

    // ===========================================================
    // Constants
    // ===========================================================
    @Override
    public void onDetach() {
        super.onDetach();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
    // ===========================================================
    // Fields
    // ===========================================================
}

package com.caknow.android.customer.app.util;

import android.app.Activity;
import android.content.Context;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Created by wesson_wxy on 2016/12/21.
 */

public class SoftKeyboardUtils {

    /**
     * hidden the soft keyboard
     * @param context
     */
    public static void hideKeyboard(Context context) {
        if(context == null || !(context instanceof Activity)){
            return;
        }

        InputMethodManager inputmm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        // call hideSoftInputFromWindow method to hidden soft keyboard
        // force to hidden the soft keyboard
        inputmm.hideSoftInputFromWindow(((Activity)context).getWindow().getDecorView().getWindowToken(),0);
    }

    /**
     * hidden the soft keyboard
     * @param editText
     * @param context
     */
    public static void hideKeyboard(EditText editText,Context context) {
        hideKeyboard(context);
    }

    /**
     * display the soft keyboard
     * @param context
     */
    public static void showKeyboard(Context context) {
        InputMethodManager inputmm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputmm.toggleSoftInput(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE,InputMethodManager.SHOW_FORCED);
    }

    /**
     * display the soft keyboard
     * @param editText
     * @param context
     */
    public static void showKeyboard(EditText editText,Context context) {
        InputMethodManager inputmm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputmm.showSoftInput(editText,InputMethodManager.RESULT_SHOWN);
        inputmm.toggleSoftInput(InputMethodManager.SHOW_FORCED,InputMethodManager.HIDE_IMPLICIT_ONLY);
    }
}

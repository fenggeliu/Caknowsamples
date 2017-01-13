package com.caknow.customer.util;

/**
 * Created by wesson_wxy on 2016/12/21.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Toast manager
 */
public class ToastUtils {
    private static boolean isShow = true;

    private ToastUtils(){
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * default toast
     * @param context
     * @param message
     * @param time Toast.LENGTH_LONG and Toast.LENGTH_SHORT
     */
    public static void defaultToast(Context context, CharSequence message,int time) {
        if(isShow) {
            Toast.makeText(context,message,time).show();

        }
    }

    /**
     * short toast with custom location
     * @param context
     * @param message
     * @param time Toast.LENGTH_LONG and Toast.LENGTH_SHORT
     * @param gravity Gravity.TOP, Gravity.CENTER,etc
     * @param xOffset positive means right offset, negative means left offset
     * @param yOffset positive means top offset , negative means down offset
     */
    public static void customLocationToast(Context context,CharSequence message, int time,int gravity,int xOffset,int yOffset) {
        if(isShow) {
            Toast toast = Toast.makeText(context,message,time);
            toast.setGravity(gravity,xOffset,yOffset);
            toast.show();
        }
    }

    /**
     * toast with custom image
     * @param context
     * @param message
     * @param time Toast.LENGTH_LONG and Toast.LENGTH_SHORT
     * @param imageId drawable image id
     * @param orientation orientation id
     * @param locId location id
     */
    public static void customImageToast(Context context, CharSequence message, int time, int imageId, int orientation,int locId) {
        Toast toast = Toast.makeText(context, message, time);
        //create image view object
        ImageView imageView= new ImageView(context);
        // setting the image
        imageView.setImageResource(imageId);
        // get the toast layout
        LinearLayout toastView = (LinearLayout) toast.getView();
        // setting the image orientation
        toastView.setOrientation(orientation);
        // add the image view into toast layout by locId
        toastView.addView(imageView, locId);
        toast.show();
    }

    /**
     * completely custom the toast
     * @param customLayoutId layout id
     * @param viewId view group id
     * @param imageViewId imageView
     * @param imageId image id
     * @param titleTextViewId title textView
     * @param titleContent title message
     * @param messageTextViewId message textView
     * @param message message
     * @param context
     * @param gravity Gravity.TOP, Gravity.CENTER,etc
     * @param xOffset positive means right offset, negative means left offset
     * @param yOffset  positive means top offset , negative means down offset
     * @param time Toast.LENGTH_LONG and Toast.LENGTH_SHORT
     */
    public static void completeCustomToast(int customLayoutId,
                                                ViewGroup viewId,
                                                ImageView imageViewId,
                                                int imageId,
                                                TextView titleTextViewId,
                                                CharSequence titleContent,
                                                TextView messageTextViewId,
                                                CharSequence message,
                                                Context context,
                                                int gravity,
                                                int xOffset,
                                                int yOffset,
                                                int time) {
        Toast toast= new Toast(context);
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View layout = inflater.inflate(customLayoutId,viewId);

        ImageView image = imageViewId;
        // setting image in layout
        image.setImageResource(imageId);

        TextView title = titleTextViewId;
        // setting title
        title.setText(titleContent);

        TextView text = messageTextViewId;
        // setting content
        text.setText(message);


        toast.setGravity(gravity , xOffset, yOffset);
        toast.setDuration(time);
        toast.setView(layout);
        toast.show();
    }

}

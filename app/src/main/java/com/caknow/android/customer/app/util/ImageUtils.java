package com.caknow.android.customer.app.util;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.caknow.app.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

/**
 * Created by wesson_wxy on 2016/12/19.
 */

public class ImageUtils {
    protected ImageLoader imageLoader = ImageLoader.getInstance();

    public ImageUtils() {

    }

    /*
    String imageUri = "http://site.com/image.png";                       // from Web
    String imageUri = "file:///mnt/sdcard/image.png";                    // from SD card
    String imageUri = "content://media/external/audio/albumart/13";      // from content provider
    String imageUri = "assets://image.png";                              // from assets
    String imageUri = "drawable://" + R.drawable.image;                  // from drawable (only images, non-9patch)
    */

    /**
     * get display image options
     * @param roundPixels
     * @return options
     */
    public DisplayImageOptions getDisplayImageOptions(int roundPixels) {
        DisplayImageOptions options;
        options = new DisplayImageOptions.Builder()
                //.showStubImage(R.drawable.ic_launcher)
                .showImageForEmptyUri(R.drawable.ic_launcher)
                .showImageOnFail(R.drawable.ic_launcher)
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .resetViewBeforeLoading(true)
                .displayer(new RoundedBitmapDisplayer(roundPixels))
                .build();

        return options;
    }

    /**
     * get the image by cache in memory
     * @param roundPixels
     * @return options
     */
    public DisplayImageOptions getDisplayImageOptionsWithCacheInMemory(int roundPixels) {
        DisplayImageOptions options;
        options = new DisplayImageOptions
                .Builder()
                .showImageForEmptyUri(R.drawable.ic_launcher)
                .showImageOnFail(R.drawable.ic_launcher)
                .cacheInMemory(true)
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .resetViewBeforeLoading(true)
                .displayer(new RoundedBitmapDisplayer(roundPixels))
                .build();

        return options;
    }

    /**
     * get the image by cache on disc
     * @param roundPixels
     * @return options
     */
    public DisplayImageOptions getDisplayImageOptionsWithCacheOnDisc(int roundPixels) {
        DisplayImageOptions options;
        options = new DisplayImageOptions
                .Builder()
                .showImageForEmptyUri(R.drawable.ic_launcher)
                .showImageOnFail(R.drawable.ic_launcher)
                .cacheOnDisc(true)
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .resetViewBeforeLoading(true)
                .displayer(new RoundedBitmapDisplayer(roundPixels))
                .build();

        return options;
    }

    /**
     * display image
     * @param imageUrl
     * @param imageView
     */
    public void displayImage(String imageUrl,ImageView imageView) {
        if(imageUrl != null && !imageUrl.equals("") && imageView != null ) {
            imageLoader.displayImage(imageUrl, imageView);
        }
    }

    /**
     * display image
     * @param imageUrl
     * @param imageView
     * @param options
     */
    public void displayImage(String imageUrl,ImageView imageView,DisplayImageOptions options) {
        if(imageView != null) {
            imageLoader.displayImage(imageUrl, imageView, options);
        }
    }

    /**
     * display image
     * @param imageUrl
     * @param imageView
     * @param options
     * @param listener
     */
    public void displayImage(String imageUrl, ImageView imageView, DisplayImageOptions options, ImageLoadingListener listener) {
        if(imageUrl != null && imageUrl.equals("") && imageView != null) {
            imageLoader.displayImage(imageUrl, imageView, options, new ImageLoadingListener(){
                @Override
                public void onLoadingStarted(String s,View v) {

                }
                @Override
                public void onLoadingFailed(String s,View v,FailReason failReason) {

                }
                @Override
                public void onLoadingComplete(String s,View v,Bitmap loadedImage) {

                }
                @Override
                public void onLoadingCancelled(String s,View v) {

                }
            });
        }
    }

    /**
     * load image from SD card
     *
     * @param uri
     * @param imageView
     */
    public void displayFromSDCard(String uri,ImageView imageView) {
        // String imageUri = "file:///mnt/sdcard/image.png";    // from SD card
        ImageLoader.getInstance().displayImage("file://" + uri,imageView);
    }

    /**
     * load image from asset
     * @param imageName the image name with extension
     * @param imageView
     */
    public void displayFromAssets(String imageName,ImageView imageView) {
        // String imageUri = "asset://image/png"    // from assets
        ImageLoader.getInstance().displayImage("asset://" + imageName,imageView);
    }

    /**
     * load image from drawable
     * @param imageId
     * @param imageView
     */
    public void displayFromDrawable(int imageId,ImageView imageView) {
        // String imageUri = "drawable://" + R.drawable.image;    // from drawables
        // (only images, non-9patch)
        ImageLoader.getInstance().displayImage("drawable://" + imageId,imageView);
    }

    /**
     * load image from content provider
     * @param imageUri
     * @param imageView
     */
    public void displayFromContent(String imageUri,ImageView imageView) {
        // String imageUri = "content://media/external/audio/albumart/13"    // from content provider
        ImageLoader.getInstance().displayImage("content://" + imageUri,imageView);
    }

}

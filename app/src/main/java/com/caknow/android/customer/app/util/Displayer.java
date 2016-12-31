package com.caknow.android.customer.app.util;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;

import com.nostra13.universalimageloader.core.assist.LoadedFrom;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;

/**
 * Created by wesson_wxy on 2016/12/19.
 */

public class Displayer extends RoundedBitmapDisplayer {
    public Displayer(int cornerRadiusPixels) {
        super(cornerRadiusPixels);
    }

    // display bitmap
    @Override
    public void display(Bitmap bitmap, ImageAware imageAware, LoadedFrom loadedFrom) {
        imageAware.setImageDrawable(new CircleDrawable(bitmap,margin));
    }

    public static class CircleDrawable extends Drawable {
        private final int margin;
        private final RectF mRect = new RectF();
        private final BitmapShader bitmapShader;
        private final Paint paint;
        private RectF mBitmapRect;

        public CircleDrawable(Bitmap bitmap,int margin) {
            this.margin = 0;
            // create bitmap shader
            bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP,Shader.TileMode.CLAMP);
            mBitmapRect = new RectF(margin,margin,bitmap.getWidth() - margin,bitmap.getHeight() - margin);
            // settings paint
            paint = new Paint();
            paint.setAntiAlias(true);
            paint.setShader(bitmapShader);
        }

        // draw the image
        @Override
        protected void onBoundsChange(Rect bounds) {
            super.onBoundsChange(bounds);
            mRect.set(margin,margin,bounds.width() - margin,bounds.height() - margin);

            //adjust the bitmap
            Matrix shaderMatrix = new Matrix();
            shaderMatrix.setRectToRect(mBitmapRect,mRect,Matrix.ScaleToFit.FILL);
            // setting shader matrix
            bitmapShader.setLocalMatrix(shaderMatrix);
        }

        // draw the bolder
        @Override
        public void draw(Canvas canvas) {
            canvas.drawRoundRect(mRect,mRect.width() / 2,mRect.height() / 2,paint);
        }

        /**
         * return Alpha of the image
         * return value is PixelFormat
         */
        @Override
        public int getOpacity(){
            //  return unknown
             return PixelFormat.UNKNOWN;
            //  return translucent
            // return PixelFormat.TRANSLUCENT;
            //  return transparent
            // return PixelFormat.TRANSPARENT;
            //  return opaque
            // return PixelFormat.OPAQUE;
        }

        /**
         * set the alpha
         * @param alpha
         */
        @Override
        public void setAlpha(int alpha) {
            paint.setAlpha(alpha);
        }

        /**
         * set color filter
         * @param cf
         */
        @Override
        public void setColorFilter(ColorFilter cf) {
            paint.setColorFilter(cf);
        }
    }
}

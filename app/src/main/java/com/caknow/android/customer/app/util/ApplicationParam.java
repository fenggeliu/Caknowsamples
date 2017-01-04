package com.caknow.android.customer.app.util;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.nostra13.universalimageloader.cache.disc.impl.LimitedAgeDiskCache;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

/**
 * Created by wesson_wxy on 2016/12/19.
 */

public class ApplicationParam extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();

        Context context = getApplicationContext();
        File cacheDir = StorageUtils.getOwnCacheDirectory(context,"imageloader/Cache");

        ImageLoaderConfiguration config = new ImageLoaderConfiguration
                .Builder(context)
                .memoryCacheExtraOptions(480,800)
                .discCacheExtraOptions(480,800,null)
                .threadPoolSize(3)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024))
                .memoryCacheSize(2 * 1024 *1024)
                .discCacheSize(50 * 1024 * 1024)
                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                .discCacheFileCount(100)
                .discCache(new UnlimitedDiskCache(cacheDir))
                .discCache(new LimitedAgeDiskCache(cacheDir,7 * 24 * 60 * 60))
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
                .imageDecoder(new BaseImageDecoder(true))
                .imageDownloader(new BaseImageDownloader(context,5 * 1000, 30 * 1000))
                .writeDebugLogs()
                .build();

        ImageLoader.getInstance().init(config);
    }
}

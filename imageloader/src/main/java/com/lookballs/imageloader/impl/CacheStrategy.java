package com.lookballs.imageloader.impl;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 0对应DiskCacheStrategy.ALL,1对应DiskCacheStrategy.NONE,2对应DiskCacheStrategy.SOURCE,3对应DiskCacheStrategy.RESULT
 * {@link com.bumptech.glide.load.engine.DiskCacheStrategy}
 */
public interface CacheStrategy {
    int ALL = 0;

    int NONE = 1;

    int RESOURCE = 2;

    int DATA = 3;

    int AUTOMATIC = 4;

    @IntDef({ALL, NONE, RESOURCE, DATA, AUTOMATIC})
    @Retention(RetentionPolicy.SOURCE)
    @interface Strategy {
    }
}
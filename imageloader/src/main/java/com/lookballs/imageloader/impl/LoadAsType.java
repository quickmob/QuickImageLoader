package com.lookballs.imageloader.impl;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 0对应RequestManager.asDrawable,1对应RequestManager.asBitmap,2对应RequestManager.asFile,3对应RequestManager.asGif
 * {@link com.bumptech.glide.RequestManager}
 */
public interface LoadAsType {
    int asDrawable = 0;

    int asBitmap = 1;

    int asFile = 2;

    int asGif = 3;

    @IntDef({asDrawable, asBitmap, asFile, asGif})
    @Retention(RetentionPolicy.SOURCE)
    @interface AsType {
    }
}

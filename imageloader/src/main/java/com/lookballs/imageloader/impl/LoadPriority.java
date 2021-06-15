package com.lookballs.imageloader.impl;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 0对应Priority.LOW,1对应Priority.NORMAL,2对应Priority.HIGH,3对应Priority.IMMEDIATE
 * {@link com.bumptech.glide.Priority}
 */
public interface LoadPriority {
    int LOW = 0;

    int NORMAL = 1;

    int HIGH = 2;

    int IMMEDIATE = 3;

    @IntDef({LOW, NORMAL, HIGH, IMMEDIATE})
    @Retention(RetentionPolicy.SOURCE)
    @interface Priority {
    }
}

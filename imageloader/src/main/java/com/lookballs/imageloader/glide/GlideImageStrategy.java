package com.lookballs.imageloader.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Looper;
import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.TransitionOptions;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestOptions;
import com.lookballs.imageloader.ImageConfig;
import com.lookballs.imageloader.ImageOptions;
import com.lookballs.imageloader.glide.util.GlideUtil;
import com.lookballs.imageloader.glide.util.blur.BlurTransformation;
import com.lookballs.imageloader.impl.CacheStrategy;
import com.lookballs.imageloader.impl.ImageStrategy;
import com.lookballs.imageloader.impl.LoadAsType;
import com.lookballs.imageloader.impl.LoadPriority;

import java.io.File;

/**
 * 此类只是简单的实现了 Glide 加载的策略,方便快速使用,但大部分情况会需要应对复杂的场景
 * 这时可自行实现 {@link ImageStrategy} 和 {@link ImageOptions} 替换现有策略
 */
public class GlideImageStrategy implements ImageStrategy<ImageConfig> {

    @Override
    public void loadImage(@NonNull Object mContext, @NonNull ImageConfig config) {
        if (mContext == null) return;

        /************************************这是一条分割线开始************************************/
        RequestOptions requestOptions = new RequestOptions();
        switch (config.getCacheStrategy()) {//缓存策略
            case CacheStrategy.ALL:
                requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
                break;
            case CacheStrategy.NONE:
                requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
                break;
            case CacheStrategy.RESOURCE:
                requestOptions.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
                break;
            case CacheStrategy.DATA:
                requestOptions.diskCacheStrategy(DiskCacheStrategy.DATA);
                break;
            case CacheStrategy.AUTOMATIC:
                requestOptions.diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
                break;
            default:
                requestOptions.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
                break;
        }
        switch (config.getLoadPriority()) {//加载优先顺序
            case LoadPriority.LOW:
                requestOptions.priority(Priority.LOW);
                break;
            case LoadPriority.NORMAL:
                requestOptions.priority(Priority.NORMAL);
                break;
            case LoadPriority.HIGH:
                requestOptions.priority(Priority.HIGH);
                break;
            case LoadPriority.IMMEDIATE:
                requestOptions.priority(Priority.IMMEDIATE);
                break;
            default:
                requestOptions.priority(Priority.NORMAL);
                break;
        }
        if (config.isCenterCrop()) {//设置裁剪方式
            requestOptions.centerCrop();
        }
        if (config.getImageRadius() > 0) {//设置圆角
            requestOptions.transform(new RoundedCorners(config.getImageRadius()));
        }
        if (config.getBlurValue() > 0) {//设置高斯模糊
            requestOptions.transform(new BlurTransformation(config.getBlurValue()));
        }
        if (config.getPlaceholder() != 0) {//设置占位符
            requestOptions.placeholder(config.getPlaceholder());
        }
        if (config.getErrorPlaceholder() != 0) {//设置错误的图片
            requestOptions.error(config.getErrorPlaceholder());
        }
        if (config.getFallback() != 0) {//设置请求 url 为空图片
            requestOptions.fallback(config.getFallback());
        }
        if (config.getWidth() >= 0 && config.getHeight() >= 0) {//设置宽高
            requestOptions.override(config.getWidth(), config.getHeight());
        }
        /************************************这是一条分割线结束************************************/

        /************************************这是一条分割线开始************************************/
        if (!TextUtils.isEmpty(config.getThumbnailUrl())) {
            //设置先加载网络缩略图后加载网络原图片
            RequestBuilder requestBuilder = GlideUtil.getRequestManager(mContext).load(config.getUrl()).thumbnail(GlideUtil.getRequestManager(mContext).load(config.getThumbnailUrl()));
            requestBuilderConfig(config, requestBuilder, requestOptions);
        } else {
            //加载网络原图片
            switch (config.getLoadAsType()) {
                case LoadAsType.asDrawable:
                default:
                    RequestBuilder<Drawable> requestBuilder = GlideUtil.getRequestManager(mContext).asDrawable().load(config.getUrl());
                    requestBuilderConfig(config, requestBuilder, requestOptions);
                    break;
                case LoadAsType.asBitmap:
                    RequestBuilder<Bitmap> requestBuilder1 = GlideUtil.getRequestManager(mContext).asBitmap().load(config.getUrl());
                    requestBuilderConfig(config, requestBuilder1, requestOptions);
                    break;
                case LoadAsType.asFile:
                    RequestBuilder<File> requestBuilder2 = GlideUtil.getRequestManager(mContext).asFile().load(config.getUrl());
                    requestBuilderConfig(config, requestBuilder2, requestOptions);
                    break;
                case LoadAsType.asGif:
                    RequestBuilder<GifDrawable> requestBuilder3 = GlideUtil.getRequestManager(mContext).asGif().load(config.getUrl());
                    requestBuilderConfig(config, requestBuilder3, requestOptions);
                    break;
            }
        }
        /************************************这是一条分割线结束************************************/
    }

    private <A> void requestBuilderConfig(ImageConfig config, RequestBuilder<A> requestBuilder, RequestOptions requestOptions) {
        if (config.isCrossFade()) {//设置显示动画效果
            requestBuilder.transition((TransitionOptions<?, ? super A>) DrawableTransitionOptions.withCrossFade());
        }
        if (config.getThumbnailValues() > 0) {//设置缩略值
            requestBuilder.thumbnail(config.getThumbnailValues());
        }
        //添加配置并加载
        requestBuilder.apply(requestOptions).into(config.getImageView());
    }

    @Override
    public void clear(@NonNull Context mContext, @NonNull ImageConfig config) {
        if (mContext == null) return;

        if (config.isClearDiskCache()) {//清除本地缓存
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Glide.get(mContext).clearDiskCache();
                }
            }).start();
        }

        if (config.isClearMemory()) {//清除内存缓存
            if (Looper.myLooper() == Looper.getMainLooper()) {
                Glide.get(mContext).clearMemory();
            }
        }
    }
}

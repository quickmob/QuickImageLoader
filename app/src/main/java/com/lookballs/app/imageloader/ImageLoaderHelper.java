package com.lookballs.app.imageloader;

import android.widget.ImageView;

import com.lookballs.imageloader.ImageConfig;
import com.lookballs.imageloader.impl.CacheStrategy;
import com.lookballs.imageloader.impl.LoadPriority;

/**
 * 配置Glide加载参数的工具类
 */
public class ImageLoaderHelper {

    private static ImageConfig.Builder imageConfigBuilder(Object url, ImageView imageView) {
        return ImageConfig.createBuilder()
                .url(url)
                .imageView(imageView)
                .placeholder(R.mipmap.img_loading_error)
                .errorPlaceholder(R.mipmap.img_loading_error)
                .thumbnailValues(0.5f)
                .loadPriority(LoadPriority.HIGH);
    }

    private static ImageConfig.Builder imageConfigBuilder(Object url, ImageView imageView, int width, int height) {
        return imageConfigBuilder(url, imageView)
                .override(width, height);
    }

    public static ImageConfig getImageConfig1(Object url, ImageView imageView, int width, int height) {
        return imageConfigBuilder(url, imageView, width, height)
                .cacheStrategy(CacheStrategy.RESOURCE)
                .create();
    }

    public static ImageConfig getImageConfig2(Object url, ImageView imageView) {
        return imageConfigBuilder(url, imageView)
                .cacheStrategy(CacheStrategy.RESOURCE)
                .create();
    }

    public static ImageConfig getImageConfig3(Object url, ImageView imageView, int width, int height) {
        return imageConfigBuilder(url, imageView, width, height)
                .cacheStrategy(CacheStrategy.NONE)
                .create();
    }

    public static ImageConfig getImageConfig4(Object url, ImageView imageView) {
        return imageConfigBuilder(url, imageView)
                .cacheStrategy(CacheStrategy.NONE)
                .create();
    }

    public static ImageConfig getThumbImageConfig(String url, String thumbUrl, ImageView imageView) {
        return ImageConfig.createBuilder()
                .url(url)
                .imageView(imageView)
                .errorPlaceholder(R.mipmap.img_loading_error)
                .thumbnailValues(0.5f)
                .thumbnailUrl(thumbUrl)
                .cacheStrategy(CacheStrategy.NONE)
                .loadPriority(LoadPriority.IMMEDIATE)
                .create();
    }

    public static ImageConfig getImageClearConfig() {
        return ImageConfig.createBuilder().isClearDiskCache(true).isClearMemory(true).create();
    }
}

package com.lookballs.imageloader;

import android.widget.ImageView;

import com.lookballs.imageloader.impl.CacheStrategy;
import com.lookballs.imageloader.impl.LoadAsType;
import com.lookballs.imageloader.impl.LoadPriority;

/**
 * 这里存放图片请求的配置信息,可以一直扩展字段,如果外部调用时想让图片加载框架
 * 做一些操作,比如清除缓存或者切换缓存策略,则可以定义一个 int 类型的变量,内部根据 switch(int) 做不同的操作
 * 其他操作同理
 */
public class ImageConfig extends ImageOptions {
    @CacheStrategy.Strategy
    private int cacheStrategy = CacheStrategy.RESOURCE;//0对应DiskCacheStrategy.ALL,1对应DiskCacheStrategy.NONE,2对应DiskCacheStrategy.SOURCE,3对应DiskCacheStrategy.RESULT
    @LoadPriority.Priority
    private int loadPriority = LoadPriority.NORMAL;//0对应Priority.LOW,1对应Priority.NORMAL,2对应Priority.HIGH,3对应Priority.IMMEDIATE
    @LoadAsType.AsType
    private int loadAsType = LoadAsType.asDrawable;//0对应RequestManager.asDrawable,1对应RequestManager.asBitmap,2对应RequestManager.asFile,3对应RequestManager.asGif

    private int fallback;//请求 url 为null时,则使用此图片作为占位图
    private int imageRadius;//图片每个圆角的大小
    private int blurValue;//高斯模糊值, 值越大模糊效果越大
    private boolean isCrossFade;//是否使用淡入淡出过渡动画
    private boolean isCenterCrop;//是否将图片剪切为CenterCrop
    private boolean isClearMemory;//清理内存缓存
    private boolean isClearDiskCache;//清理本地缓存
    private float thumbnailValues;//缩略值0-1f
    private String thumbnailUrl;//缩略图地址
    private int width = -1;//图片宽
    private int height = -1;//图片高

    private ImageConfig(Builder builder) {
        this.url = builder.url;
        this.imageView = builder.imageView;
        this.placeholder = builder.placeholder;
        this.errorPlaceholder = builder.errorPlaceholder;
        /******************************这是一条分割线**********************************/
        this.cacheStrategy = builder.cacheStrategy;
        this.loadPriority = builder.loadPriority;
        this.loadAsType = builder.loadAsType;
        this.fallback = builder.fallback;
        this.imageRadius = builder.imageRadius;
        this.blurValue = builder.blurValue;
        this.isCrossFade = builder.isCrossFade;
        this.isCenterCrop = builder.isCenterCrop;
        this.isClearMemory = builder.isClearMemory;
        this.isClearDiskCache = builder.isClearDiskCache;
        this.thumbnailValues = builder.thumbnailValues;
        this.thumbnailUrl = builder.thumbnailUrl;
        this.width = builder.width;
        this.height = builder.height;
    }

    @CacheStrategy.Strategy
    public int getCacheStrategy() {
        return cacheStrategy;
    }

    @LoadPriority.Priority
    public int getLoadPriority() {
        return loadPriority;
    }

    @LoadAsType.AsType
    public int getLoadAsType() {
        return loadAsType;
    }

    public boolean isClearMemory() {
        return isClearMemory;
    }

    public boolean isClearDiskCache() {
        return isClearDiskCache;
    }

    public int getFallback() {
        return fallback;
    }

    public int getBlurValue() {
        return blurValue;
    }

    public int getImageRadius() {
        return imageRadius;
    }

    public boolean isCrossFade() {
        return isCrossFade;
    }

    public boolean isCenterCrop() {
        return isCenterCrop;
    }

    public float getThumbnailValues() {
        return thumbnailValues;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public static Builder createBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private Object url;//图片地址
        private ImageView imageView;//图片ImageView
        private int placeholder;//加载中的占位图
        private int errorPlaceholder;//加载失败的占位图
        /******************************这是一条分割线**********************************/
        @CacheStrategy.Strategy
        private int cacheStrategy = CacheStrategy.RESOURCE;//0对应DiskCacheStrategy.ALL,1对应DiskCacheStrategy.NONE,2对应DiskCacheStrategy.SOURCE,3对应DiskCacheStrategy.RESULT
        @LoadPriority.Priority
        private int loadPriority = LoadPriority.NORMAL;//0对应Priority.LOW,1对应Priority.NORMAL,2对应Priority.HIGH,3对应Priority.IMMEDIATE
        @LoadAsType.AsType
        private int loadAsType = LoadAsType.asDrawable;//0对应RequestManager.asDrawable,1对应RequestManager.asBitmap,2对应RequestManager.asFile,3对应RequestManager.asGif

        private int fallback;//请求 url 为null时,则使用此图片作为占位图
        private int imageRadius;//图片每个圆角的大小
        private int blurValue;//高斯模糊值, 值越大模糊效果越大
        private boolean isCrossFade;//是否使用淡入淡出过渡动画
        private boolean isCenterCrop;//是否将图片剪切为CenterCrop
        private boolean isClearMemory;//清理内存缓存
        private boolean isClearDiskCache;//清理本地缓存
        private float thumbnailValues;//缩略值0-1f
        private String thumbnailUrl;//缩略图地址
        private int width = -1;//图片宽
        private int height = -1;//图片高

        private Builder() {

        }

        public Builder url(Object url) {
            this.url = url;
            return this;
        }

        public Builder placeholder(int placeholder) {
            this.placeholder = placeholder;
            return this;
        }

        public Builder errorPlaceholder(int errorPlaceholder) {
            this.errorPlaceholder = errorPlaceholder;
            return this;
        }

        public Builder fallback(int fallback) {
            this.fallback = fallback;
            return this;
        }

        public Builder imageView(ImageView imageView) {
            this.imageView = imageView;
            return this;
        }

        public Builder cacheStrategy(@CacheStrategy.Strategy int cacheStrategy) {
            this.cacheStrategy = cacheStrategy;
            return this;
        }

        public Builder loadPriority(@LoadPriority.Priority int loadPriority) {
            this.loadPriority = loadPriority;
            return this;
        }

        public Builder loadAsType(@LoadAsType.AsType int loadAsType) {
            this.loadAsType = loadAsType;
            return this;
        }

        public Builder imageRadius(int imageRadius) {
            this.imageRadius = imageRadius;
            return this;
        }

        public Builder blurValue(int blurValue) {
            this.blurValue = blurValue;
            return this;
        }

        public Builder isCrossFade(boolean isCrossFade) {
            this.isCrossFade = isCrossFade;
            return this;
        }

        public Builder isCenterCrop(boolean isCenterCrop) {
            this.isCenterCrop = isCenterCrop;
            return this;
        }

        public Builder isClearMemory(boolean isClearMemory) {
            this.isClearMemory = isClearMemory;
            return this;
        }

        public Builder isClearDiskCache(boolean isClearDiskCache) {
            this.isClearDiskCache = isClearDiskCache;
            return this;
        }

        public Builder override(int width, int height) {
            this.width = width;
            this.height = height;
            return this;
        }

        public Builder thumbnailUrl(String thumbnailUrl) {
            this.thumbnailUrl = thumbnailUrl;
            return this;
        }

        public Builder thumbnailValues(float thumbnailValues) {
            this.thumbnailValues = thumbnailValues;
            return this;
        }

        public ImageConfig create() {
            return new ImageConfig(this);
        }
    }
}

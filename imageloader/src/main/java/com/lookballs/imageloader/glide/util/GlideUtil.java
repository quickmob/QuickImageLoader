package com.lookballs.imageloader.glide.util;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.resource.gif.GifDrawable;

import java.io.File;
import java.math.BigDecimal;

/**
 * 一些Glide加载的其他工具类
 */
public class GlideUtil {

    private GlideUtil() {

    }

    public static RequestManager getRequestManager(@NonNull Object mContext) {
        if (mContext instanceof Fragment) {
            return Glide.with((Fragment) mContext);
        } else if (mContext instanceof android.app.Fragment) {
            return Glide.with((android.app.Fragment) mContext);
        } else {
            return Glide.with((Context) mContext);
        }
    }

    public static boolean checkContext(Object mContext) {
        if (mContext == null) {
            return false;
        } else if (!(mContext instanceof Application)) {
            if (mContext instanceof Activity) {
                Activity activity = (Activity) mContext;
                return !activity.isDestroyed();
            } else if (mContext instanceof Fragment) {
                Fragment fragment = (Fragment) mContext;
                return fragment.getActivity() != null && !fragment.getActivity().isDestroyed();
            } else if (mContext instanceof android.app.Fragment) {
                android.app.Fragment fragment = (android.app.Fragment) mContext;
                return fragment.getActivity() != null && !fragment.getActivity().isDestroyed();
            } else if (mContext instanceof ContextWrapper
                    && ((ContextWrapper) mContext).getBaseContext().getApplicationContext() != null) {
                return checkContext(((ContextWrapper) mContext).getBaseContext());
            }
        }
        return true;
    }

    /**
     * 根据url获取到一个Bitmap
     *
     * @param mContext
     */
    public static Bitmap getBitmap(@NonNull Object mContext, @NonNull Object url) {
        Bitmap bitmap = null;
        try {
            bitmap = getRequestManager(mContext)
                    .asBitmap()
                    .load(url)
                    .submit()
                    .get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    /**
     * 根据url获取到一个File
     *
     * @param mContext
     */
    public static File getFile(@NonNull Object mContext, @NonNull Object url) {
        File file = null;
        try {
            file = getRequestManager(mContext)
                    .asFile()
                    .load(url)
                    .submit()
                    .get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    /**
     * 根据url获取到一个Drawable
     *
     * @param mContext
     */
    public static Drawable getDrawable(@NonNull Object mContext, @NonNull Object url) {
        Drawable drawable = null;
        try {
            drawable = getRequestManager(mContext)
                    .asDrawable()
                    .load(url)
                    .submit()
                    .get();
        } catch (Exception e) {
            return null;
        }
        return drawable;
    }

    /**
     * 根据url获取到一个GifDrawable
     *
     * @param mContext
     */
    public static GifDrawable getGifDrawable(@NonNull Object mContext, @NonNull Object url) {
        GifDrawable gifDrawable = null;
        try {
            gifDrawable = getRequestManager(mContext)
                    .asGif()
                    .load(url)
                    .submit()
                    .get();
        } catch (Exception e) {
            return null;
        }
        return gifDrawable;
    }

    /**
     * 预加载图片
     *
     * @param mContext
     */
    public static void preloadImage(@NonNull Object mContext, int width, int height) {
        if (width >= 0 && height >= 0) {
            getRequestManager(mContext).asDrawable().preload(width, height);
        } else {
            getRequestManager(mContext).asDrawable().preload();
        }
    }

    /**
     * 下载图片
     *
     * @param mContext
     */
    public static void downloadImage(@NonNull Object mContext) {
        getRequestManager(mContext).downloadOnly();
    }

    /**
     * 当列表滑动的时候，调用pauseRequests()取消请求(一般用于列表高速滑动中)
     *
     * @param mContext
     */
    public static void pauseRequests(@NonNull Object mContext) {
        getRequestManager(mContext).pauseRequests();
    }

    /**
     * 当列表滑动停止时，调用resumeRequests()恢复请求(一般用于列表高速滑动中)
     *
     * @param mContext
     */
    public static void resumeRequests(@NonNull Object mContext) {
        getRequestManager(mContext).resumeRequests();
    }

    /**
     * 清除指定ImageView在执行的任务
     *
     * @param mContext
     */
    public static void clearImage(@NonNull Object mContext, @NonNull ImageView imageView) {
        getRequestManager(mContext).clear(imageView);
    }

    /**
     * onLowMemory
     *
     * @param mContext
     */
    public static void onLowMemory(@NonNull Context mContext) {
        Glide.get(mContext).onLowMemory();
    }

    /**
     * onTrimMemory
     *
     * @param mContext
     */
    public static void onTrimMemory(@NonNull Context mContext, int level) {
        Glide.get(mContext).onTrimMemory(level);
    }

    /**
     * 获取Glide造成的缓存大小：单位级别KB、MB、GB、TB
     *
     * @param mContext
     * @return
     */
    public static String getGlideCacheSize(@NonNull Context mContext) {
        String cacheSize = "0.00KB";
        try {
            cacheSize = getCacheSize(Glide.getPhotoCacheDir(mContext.getApplicationContext()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cacheSize;
    }

    /**
     * 获取指定文件夹内造成的缓存大小：单位级别KB、MB、GB、TB
     *
     * @param file
     * @return
     */
    public static String getCacheSize(@NonNull File file) {
        String cacheSize = "0.00KB";
        try {
            cacheSize = getFormatSize(getFolderSize(file));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cacheSize;
    }

    /**
     * 获取指定文件夹内造成的缓存大小：单位级别B
     *
     * @param file
     * @return
     */
    public static long getFolderSize(@NonNull File file) {
        long size = 0;
        try {
            File[] fileList = file.listFiles();
            for (File aFileList : fileList) {
                if (aFileList.isDirectory()) {
                    size = size + getFolderSize(aFileList);
                } else {
                    size = size + aFileList.length();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }

    /**
     * 格式化内存大小单位：单位级别KB、MB、GB、TB
     *
     * @param size
     * @return
     */
    public static String getFormatSize(double size) {
        double kiloByte = size / 1024;
        if (kiloByte < 1) {
            return size + "KB";
        }

        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "KB";
        }

        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "MB";
        }

        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);

        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "TB";
    }

}

package com.lookballs.app.imageloader;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.SizeUtils;
import com.lookballs.imageloader.ImageLoader;
import com.lookballs.imageloader.glide.util.GlideUtil;

public class MainActivity extends AppCompatActivity {

    private final String url1 = "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fi3.bbswater.fd.zol-img.com.cn%2Ft_s800x5000%2Fg1%2FM05%2F0B%2F02%2FCg-4jVSdUtuILqJbAAOXIexweXQAAPZ2QH72ikAA5c5773.jpg&refer=http%3A%2F%2Fi3.bbswater.fd.zol-img.com.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1626348450&t=a9c5cd254c0288693f7475ae7c698d5f";
    private final String url2 = "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fwww.bbra.cn%2F%28S%28ekzw3ieoykngwkbn0b022v55%29%29%2FUploadfiles%2Fimgs%2F20110215%2Fguaishou%2F009.jpg&refer=http%3A%2F%2Fwww.bbra.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1626348410&t=0c5a4b139de7c08e386c26f69cfffc5c";

    private ImageView imageView1, imageView2;
    private TextView cacheSize;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView1 = findViewById(R.id.iv_image1);
        imageView2 = findViewById(R.id.iv_image2);
        cacheSize = findViewById(R.id.tv_size);
    }

    public void btn1(View view) {
        ImageLoader.loadImage(MainActivity.this, ImageLoaderHelper.getImageConfig1(url2, imageView2, SizeUtils.dp2px(300), SizeUtils.dp2px(200)));
        ImageLoader.loadImage(MainActivity.this, ImageLoaderHelper.getImageConfig2(url1, imageView1));
    }

    public void btn2(View view) {
        cacheSize.setText("图片缓存大小：" + GlideUtil.getGlideCacheSize(MainActivity.this));
    }

    public void btn3(View view) {
        ImageLoader.clear(MainActivity.this, ImageLoaderHelper.getImageClearConfig());
        cacheSize.setText("图片缓存大小：" + GlideUtil.getGlideCacheSize(MainActivity.this));
    }
}

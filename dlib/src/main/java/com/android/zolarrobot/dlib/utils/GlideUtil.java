package com.android.zolarrobot.dlib.utils;

import android.widget.ImageView;

import com.bumptech.glide.Glide;

import static com.android.zolarrobot.dlib.base.BaseApplication.mContext;

/**
 * Created by DuanYuntian on 2019/7/31.
 */

public class GlideUtil {
    /**
     * 一般加载图片
     * @param imageView
     * @param url
     */
    public static void imageUrl(ImageView imageView ,String url){
        Glide.with(mContext)
                .load(url)
                .into(imageView);
    }
    /**
     * 缩略图
     * @param imageView
     * @param url
     */
    public static void imageThumbnail(ImageView imageView ,String url){
        Glide.with( mContext )
                .load( url )
                .thumbnail( 0.2f )//参数是 float 类型，作为其倍数大小
                .into( imageView );
    }
}

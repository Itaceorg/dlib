package com.android.zolarrobot.dlib.utils;

import com.android.zolarrobot.dlib.callback.StringCallBack;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.xutils.x;

import java.io.IOException;

import static com.android.zolarrobot.dlib.base.BaseApplication.mContext;
import static com.android.zolarrobot.dlib.constant.BaseConstant.DATA;
import static com.android.zolarrobot.dlib.constant.BaseConstant.TTS;

/**
 * Created by DuanYuntian on 2019/7/30.
 */

public class CommonlyUsedUtil {
    public static void TTS(String ttsStr){
        BroadCastReceiverUtil.sendBroadcast(TTS, DATA, ttsStr);
    }

    /** 解析html */
    public static void parseHTML(String url, String id ,String tag,StringCallBack stringCallBack) {
        x.task().run(() -> {
            String contentText = "";
            //从一个URL加载一个Document对象。
            Document doc = null;
            try {
                doc = Jsoup.connect(url).get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Elements contents = doc.getElementById(id).getElementsByTag(tag);
            for (Element sElement : contents) {

                contentText = contentText + sElement.text();
            }
            stringCallBack.doCallBack(contentText);
        });

    }
}

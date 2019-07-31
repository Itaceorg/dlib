package com.android.zolarrobot.dlib.base;
import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.android.zolarrobot.dlib.constant.BaseConstant;
import com.android.zolarrobot.dlib.utils.ActivityUtils;
import com.android.zolarrobot.dlib.utils.BroadCastReceiverUtil;

import org.xutils.x;

import static com.android.zolarrobot.dlib.constant.BaseConstant.*;

/**
 *
 * @author DuanYuntian
 * @date 2019/7/30
 */

public class BaseActivity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
//        x.view().inject(this);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //隐藏虚拟按键
        ActivityUtils.hideBottomUIMenu(this);
        getSupportActionBar().hide();
        //无title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //全屏
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,
                WindowManager.LayoutParams. FLAG_FULLSCREEN);
    }

    protected  void initAll(){
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //隐藏虚拟按键
        ActivityUtils.hideBottomUIMenu(this);
        if (getSupportActionBar()!=null){
        getSupportActionBar().hide();
        }
        //全屏
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,
                WindowManager.LayoutParams. FLAG_FULLSCREEN);

        x.view().inject(this);
    }
    @Override
    protected void onResume() {
        super.onResume();
        BroadCastReceiverUtil.sendBroadcast(LifeCycle,Extra_LifeCycle,ONRESUME);
    }
    @Override
    protected void onPause() {
        super.onPause();
        BroadCastReceiverUtil.sendBroadcast(LifeCycle,Extra_LifeCycle,ONPAUSE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //发送广播关闭
        BroadCastReceiverUtil.sendBroadcast(BaseConstant.TTS_STOP);
    }
}

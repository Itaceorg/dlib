package com.android.zolarrobot.dlib.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import java.io.Serializable;

import static com.android.zolarrobot.dlib.base.BaseApplication.mContext;

/**
 * 注册广播，发送广播，注销广播
 */
public class BroadCastReceiverUtil {

    public interface OnReceiveBroadcast {
        public void onReceive( Intent intent);
    }

    private static class CustomBroadcastReceiver extends BroadcastReceiver {
        private OnReceiveBroadcast onReceiveBroadcast;

        public CustomBroadcastReceiver(OnReceiveBroadcast onReceiveBroadcast) {
            this.onReceiveBroadcast = onReceiveBroadcast;
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            if (onReceiveBroadcast != null) {
                onReceiveBroadcast.onReceive(intent);
            }
        }
    }

    public static BroadcastReceiver registerBroadcastReceiver(String[] filters, OnReceiveBroadcast onReceiveBroadcast) {
        BroadcastReceiver broadcastReceiver = new CustomBroadcastReceiver(
                onReceiveBroadcast);
        IntentFilter filter = new IntentFilter();
        for (String filterStr : filters) {
            filter.addAction(filterStr);
        }

        mContext.registerReceiver(broadcastReceiver, filter);
        return broadcastReceiver;
    }

    public static void sendBroadcast( String filter) {
        if (mContext == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setAction(filter);
        mContext.sendBroadcast(intent);
    }

    public static void sendBroadcast( String filter, Bundle bundle) {
        if (mContext == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setAction(filter);
        intent.putExtras(bundle);
        mContext.sendBroadcast(intent);
    }

    public static void sendBroadcast( String filter,
                                     String bundleName, Bundle bundle) {
        if (mContext == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setAction(filter);
        intent.putExtra(bundleName, bundle);
        mContext.sendBroadcast(intent);
    }

    public static void sendBroadcast( String filter, String name, Serializable serializable) {
        Intent intent = new Intent();
        intent.putExtra(name, serializable);
        intent.setAction(filter);
        mContext.sendBroadcast(intent);
    }

    public static void sendBroadcast( String filter, String name, String value) {
        Intent intent = new Intent();
        intent.putExtra(name, value);
        intent.setAction(filter);
        mContext.sendBroadcast(intent);
    }

    public static void sendBroadcast( String filter, String name, long value) {
        Intent intent = new Intent();
        intent.putExtra(name, value);
        intent.setAction(filter);
        mContext.sendBroadcast(intent);
    }

    public static void sendBroadcast( String filter, String name, int value) {
        Intent intent = new Intent();
        intent.putExtra(name, value);
        intent.setAction(filter);
        mContext.sendBroadcast(intent);
    }

    public static void sendBroadcast( String filter, String name, String value, String name1, String value1) {
        Intent intent = new Intent();
        intent.putExtra(name, value);
        intent.putExtra(name1, value1);
        intent.setAction(filter);
        mContext.sendBroadcast(intent);
    }

    public static void unRegisterBroadcastReceiver(
                                                   BroadcastReceiver broadcastReceiver) {
        if (mContext != null && broadcastReceiver != null) {
            mContext.unregisterReceiver(broadcastReceiver);
        }
    }

}
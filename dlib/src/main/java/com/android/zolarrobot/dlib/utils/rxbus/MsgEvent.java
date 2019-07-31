package com.android.zolarrobot.dlib.utils.rxbus;

public class MsgEvent {
    private Object msg;

    public MsgEvent(Object msg) {
        this.msg = msg;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }
}
package com.android.zolarrobot.dlib.utils.rxbus;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public class RxBus {
    private volatile static RxBus mDefaultInstance;
    private final Subject<Object> mBus;

    private RxBus() {
        mBus = PublishSubject.create().toSerialized();
    }

    public static RxBus getInstance() {
        if (mDefaultInstance == null) {
            synchronized (RxBus.class) {
                if (mDefaultInstance == null) {
                    mDefaultInstance = new RxBus();
                }
            }
        }
        return mDefaultInstance;
    }

    /**
     * 发送事件
     */
    public void post(Object event) {
        mBus.onNext(event);
    }

    /**
     * 根据传递的 eventType 类型返回特定类型(eventType)的 被观察者
     */
    public <T> Observable<T> toObservable(final Class<T> eventType) {
        return mBus.ofType(eventType);
    }

    /**
     * 判断是否有订阅者
     */
    public boolean hasObservers() {
        return mBus.hasObservers();
    }

    /**
     * 重置
     */
    public void reset() {
        mDefaultInstance = null;
    }

    //使用方法
//    注册
//    RxBus.getInstance().toObservable(MsgEvent.class).subscribe(new Consumer<MsgEvent>() {
//        @Override
//        public void accept(MsgEvent msgEvent) throws Exception {
//            Timber.e(msgEvent.getMsg());
//        }
//    });
//    反注册
//    RxBus.getInstance().destroy();
//    发射广播
//    RxBus.getInstance().post(new MsgEvent("Java"))
}
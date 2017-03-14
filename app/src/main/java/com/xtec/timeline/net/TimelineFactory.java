package com.xtec.timeline.net;

/**
 * Created by 武昌丶鱼 on 2016/11/16.
 * Description:
 */
public class TimelineFactory {
    private static final String TAG = "TimelineFactory";

    private static TimelineApi mTimelineSingleton = null;

    public static TimelineApi getTimelineSingleton(){
        synchronized (TimelineFactory.class){
            if(mTimelineSingleton==null){
                mTimelineSingleton = new TimelineRetrofit().getTimelineApi();
            }
            return mTimelineSingleton;
        }
    }
}

package com.xing.sample.advancedimmersivemode.common.logger;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/10/17.
 */
public class LogView extends TextView implements LogNode {

    public LogView(Context context) {
        super(context);
    }

    public LogView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LogView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void println(int priority, String tag, String msg, Throwable tr) {
        String priorityStr=null;
        switch (priority){
            case android.util.Log.VERBOSE:
                priorityStr = "VERBOSE";
                break;
            case android.util.Log.DEBUG:
                priorityStr = "DEBUG";
                break;
            case android.util.Log.INFO:
                priorityStr = "INFO";
                break;
            case android.util.Log.WARN:
                priorityStr = "WARN";
                break;
            case android.util.Log.ERROR:
                priorityStr = "ERROR";
                break;
            case android.util.Log.ASSERT:
                priorityStr = "ASSERT";
                break;
            default:
                break;
        }

        String exceptionStr=null;
        if(tr!=null){
            exceptionStr=android.util.Log.getStackTraceString(tr);
        }

        StringBuilder outputBuilder=new StringBuilder();
    }
}

package com.xing.sample.advancedimmersivemode.common.logger;

import android.util.Log;

/**
 * Created by Administrator on 2016/10/17.
 */
public class LogWrapper implements LogNode {
    private LogNode mNext;

    public LogNode getLogNode() {
        return mNext;
    }

    public void setLogNode(LogNode mLogNode) {
        this.mNext = mLogNode;
    }

    @Override
    public void println(int priority, String tag, String msg, Throwable tr) {
        String useMsg = msg;
        if (useMsg == null) {
            useMsg = "";
        }

        if (tr != null) {
            msg += "\n" + Log.getStackTraceString(tr);
        }
        Log.println(priority, tag, useMsg);

        if (mNext != null) {
            mNext.println(priority, tag, msg, tr);
        }
    }
}

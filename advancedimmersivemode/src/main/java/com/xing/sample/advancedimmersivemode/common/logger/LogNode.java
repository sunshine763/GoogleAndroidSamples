package com.xing.sample.advancedimmersivemode.common.logger;

/**
 * Created by Administrator on 2016/10/17.
 */
public interface LogNode {
    void println(int priority,String tag,String msg,Throwable tr);
}

package com.verificer.utils;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@SuppressWarnings("restriction")
public class Signal implements sun.misc.SignalHandler{
    private static final Log logger = LogFactory.getLog(Signal.class);
    @Override
    public void handle(sun.misc.Signal signal) {
        String name = signal.getName();
        int num = signal.getNumber();

        logger.info("接收到系统信号:"+name+":"+num);
        //// TODO: 2020/12/3
    }

    public static void handle(){
        Signal sh = new Signal();
        //// TODO
        for (;;) {
            try {
                Thread.sleep(300000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

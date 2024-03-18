package com.verificer.designpatterns.listener;

import com.verificer.utils.ThreadUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 基于读写锁实现的，线程安全的Listener模式的实现
 * @param <T>
 */
public abstract class ConcurrentNotifier<T,E> {
    private final ReentrantReadWriteLock L = new ReentrantReadWriteLock();
    private final Lock WLock = L.writeLock();
    private final Lock RLock = L.readLock();

    private List<T> listeners = new LinkedList<>();

    protected abstract void trigger(T listener,E event);

    public void addListener(T listener){
        WLock.lock();
        try {
            listeners.add(listener);
        } finally {
            WLock.unlock();
        }
    }

    public void triggerAll(E event) {
        RLock.lock();
        try {
            for(T listener : listeners ){
                trigger(listener,event);
            }
        } finally {
            RLock.unlock();
        }
    }

    static class Listener{
        volatile int count = 0;

        public synchronized void addOne(){
            count++;
        }

        public synchronized int getCount(){
           return count;
        }
    }



    public static void testUnit() throws Exception{
        ConcurrentNotifier<Listener,Object> notifier = new ConcurrentNotifier<Listener,Object>() {
            @Override
            public void trigger(Listener listener,Object o) {
                listener.addOne();
            }
        };


        long st = System.currentTimeMillis();
        long ms = 10* 1000;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        notifier.triggerAll(null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if(System.currentTimeMillis() - st > ms)
                        break;;
                }

            }
        }).start();

        List<Listener> listeners = new LinkedList<>();
        for(int i = 0 ; i < 100; i++){
            listeners.add(new Listener());
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(Listener listener : listeners){
                    notifier.addListener(listener);
                    ThreadUtils.sleep(ms /listeners.size()/5);
                }
            }
        }).start();

        ThreadUtils.sleep(ms);

        for(int i=0; i<listeners.size(); i++){
            Listener l = listeners.get(i);
            System.out.println("第"+(i+1)+"个listener的count="+l.getCount());
            if(l.getCount() == 0){
                System.out.println("测试不通过");
                System.exit(-1);
            }
        }
        System.out.println("测试通过");
    }

    private static void testRW(){
        ConcurrentNotifier<Listener,Object> notifier = new ConcurrentNotifier<Listener,Object>() {
            @Override
            public void trigger(Listener listener,Object o) {
                ThreadUtils.sleep(4000);
                listener.addOne();
            }
        };

        long st = System.currentTimeMillis();
        notifier.addListener(new Listener());
        System.out.println("第一次addListener的时间为"+(System.currentTimeMillis()-st)+"毫秒");

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    notifier.triggerAll(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        ThreadUtils.sleep(1000);
        st = System.currentTimeMillis();
        notifier.addListener(new Listener());
        System.out.println("第二次addListener的时间为"+(System.currentTimeMillis()-st)+"毫秒");
    }

    public static void main(String args[])throws Exception{
//        testRW();
//        testUnit();
    }
}

package com.jc.usermanage.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author LinXing
 * @version 1.0
 * @desc TODO
 * @date 2020/6/15 16:45
 */
public class Test1 {
    private AtomicInteger atomicI = new AtomicInteger(0);
    private int i = 0;
    private final static int COUNT = 10000;

    public static void main(String[] args) {
        final Test1 cas = new Test1();
        List<Thread> ts = new ArrayList<Thread>(600);
        long start = System.currentTimeMillis();
        for (int j = 0; j < 100; j++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < COUNT; i++) {
                        cas.count();
                        cas.safeCount();
                    }
                }
            });
            ts.add(t);
        }
        for (Thread t : ts) {
            t.start();
        }
        // 等待所有线程执行完成
        for (Thread t : ts) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(cas.i);
        System.out.println(cas.atomicI.get());
        System.out.println(System.currentTimeMillis() - start);
    }

    private void count() {
        i++;
//        i = i + 1;
    }

    private void safeCount() {
        atomicI.incrementAndGet();
//        for (;;) {
//            int i = atomicI.get();
//            boolean suc = atomicI.compareAndSet(i, ++i);
//            if (suc) {
//                break;
//            }
//        }
    }

}

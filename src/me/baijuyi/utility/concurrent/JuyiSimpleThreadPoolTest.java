package me.baijuyi.utility.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JuyiSimpleThreadPoolTest {
    public static void main(String[] args) {
        Runnable r = () -> System.out.println(Thread.currentThread().getId() + " is Working!");
        JuyiSimpleThreadPool pool = new JuyiSimpleThreadPool(99);
        for (int i = 0; i < 10000; i++) {
            pool.execute(r);
        }
        pool.shutdown();
    }
}

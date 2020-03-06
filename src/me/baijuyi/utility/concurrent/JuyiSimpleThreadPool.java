package me.baijuyi.utility.concurrent;

import java.util.Arrays;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class JuyiSimpleThreadPool {
    private int size;
    private boolean isRunning;
    private Queue<Runnable> tasks;
    private Thread[] pool;

    public JuyiSimpleThreadPool(int size) {
        this.size = size;
        isRunning = true;
        tasks = new LinkedBlockingQueue<>();
        pool = new Thread[size];
        Arrays.stream(pool).forEach(w -> {
            w = new Worker();
            w.start();
        });
    }

    public void execute(Runnable runnable) {
        synchronized (tasks) {
            tasks.offer(runnable);
            tasks.notifyAll();
        }
    }

    public void shutdown() {
        isRunning = false;
        tryShutdown();
    }

    private void tryShutdown() {
        while (Thread.activeCount() > 2) {
            synchronized (tasks) {

                tasks.notifyAll();
            }
        }

    }

    class Worker extends Thread {
        Runnable r;

        @Override
        public void run() {

            while (isRunning || !tasks.isEmpty()) {
                synchronized (tasks) {
                    if (tasks.isEmpty()) {
                        try {
                            tasks.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        continue;
                    }
                    r = tasks.poll();
                    r.run();
                }

            }
        }
    }

}

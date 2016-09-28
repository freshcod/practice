package com.coral.practice.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by qiuhai on 2016/9/22.
 */
public class MyWorkThread implements Runnable{

    private BlockingQueue<Runnable> blockingQueue = new LinkedBlockingDeque<>();

    private static boolean isRunning = true;

    public MyWorkThread(BlockingQueue<Runnable> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    /**取出线程任务,并执行**/
    @Override
    public void run() {
        while (isRunning){
            try {
                Runnable runnable = blockingQueue.take();
                if(runnable != null) {
                    Thread thread = new Thread(runnable);
                    thread.start();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**停止线程**/
    public void stopWorker(){
        isRunning = false;
    }
}

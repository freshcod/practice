package com.coral.practice.thread;

import java.util.concurrent.BlockingQueue;

/**
 * Created by qiuhai on 2016/9/20.
 */
public class Consumer implements Runnable{

    private BlockingQueue blockingQueue;

    public Consumer(BlockingQueue blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        String message = "null";
        while (true){
            try {
                message = blockingQueue.take().toString();
                System.out.println(Thread.currentThread().getName()+"消费:"+message);
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }

    }
}

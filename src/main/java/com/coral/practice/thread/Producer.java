package com.coral.practice.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by qiuhai on 2016/9/20.
 */
public class Producer implements Runnable{

    private BlockingQueue blockingQueue;

    public Producer(BlockingQueue blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        System.out.println("生产者生产消息");
        for(int i = 0;i<10000;i++){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            blockingQueue.add("产品"+i);
            System.out.println("生产"+(i+1)+"个");
            i++;
        }
    }


    public static void main(String[] args){
        BlockingQueue<String> queue = new LinkedBlockingQueue<>();
        Thread producer = new Thread(new Producer(queue));
        producer.setName("生产者");
        Thread consumer1 = new Thread(new Consumer(queue));
        consumer1.setName("消费者1");
        Thread consumer2 = new Thread(new Consumer(queue));
        consumer2.setName("消费者2");
        producer.start();
        consumer1.start();
        consumer2.start();

    }
}

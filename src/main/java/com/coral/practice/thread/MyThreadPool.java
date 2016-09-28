package com.coral.practice.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by qiuhai on 2016/9/22.
 */
public class MyThreadPool {

    /**线程池中默认线程个数**/
    private static int workerNum = 5;

    /**工作线程**/
    private MyWorkThread[] myWorkThreads;

    /**任务队列**/
    private BlockingQueue<Runnable> blockingQueue = new LinkedBlockingDeque<>();

    private MyThreadPool(int workerNum){
        MyThreadPool.workerNum = workerNum;
        myWorkThreads = new MyWorkThread[workerNum];
        for(int i = 0;i < workerNum; i++){
            myWorkThreads[i] = new MyWorkThread(blockingQueue);

        }
    }




}

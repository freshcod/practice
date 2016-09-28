package com.coral.practice.thread;

/**
 * Created by qiuhai on 2016/7/13.
 */
public class MyThread extends Thread {

    ThreadArgs threadArgs;
    public MyThread(ThreadArgs threadArgs){
        this.threadArgs = threadArgs;
    }

    public void run(){
        for(int i=0;i<1000;i++){
            System.out.println(Thread.currentThread().getName()+":"+i+this.threadArgs.getArgs());
        }
        this.threadArgs.setResult("change");
    }

    public static void main(String[] args) throws InterruptedException {

        ThreadArgs threadArgs = new ThreadArgs();
        threadArgs.setArgs("zs");
        threadArgs.setResult("null");
        Thread t1 = new MyThread(threadArgs);
        t1.start();
        t1.join();
        for(int i=0;i<1000;i++){
            System.out.println(Thread.currentThread().getName()+":"+i);
        }
//        Thread t2 = new Thread(new Runnable(){
//            public void run(){
//                for(int i=0;i<1000;i++){
//                    System.out.println(Thread.currentThread().getName()+":"+i);
//                }
//            }
//        });
//        t2.start();
//        t2.join();

        System.out.println(threadArgs.getResult());
    }


}

package com.coral.practice.thread;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by qiuhai on 2016/7/13.
 */
public class MyCallable implements Callable<String> {

    private String result = "before";

    public MyCallable(String result){
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }



    public String call() throws Exception {
        result = "after "+this.result;
        for(int i=0;i<100;i++){
            System.out.println(this.result+i);
        }
        return result;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(7);
        List<Future<String>> futures = new ArrayList<Future<String>>();
        List<String> names = Arrays.asList(new String[]{"zs","ls","ww","zl","zq","ss","lk"});
        for(String name:names){
            futures.add(service.submit(new MyCallable(name)));
        }
//        Future<String> future = service.submit(new MyCallable("middle"));

        for(Future<String> future:futures){

            System.out.println(future.get());
        }
        System.out.println("++++++++++++++");
        service.shutdown();
    }
}

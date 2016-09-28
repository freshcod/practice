package com.coral.practice.framework.exception;

/**
 * Created by qiuhai on 2016/9/27.
 */
public class TaskServiceException extends Exception {

    public TaskServiceException(String message){
        super(message);
    }

    public TaskServiceException(String message, Throwable throwable){
        super(message,throwable);
    }

    public TaskServiceException(Throwable throwable){
        super(throwable);
    }
}

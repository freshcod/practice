package com.coral.practice.thread;

/**
 * Created by qiuhai on 2016/7/13.
 */
public class ThreadArgs {
    private String args ;
    private String result;

    public String getArgs() {
        return args;
    }

    public void setArgs(String args) {
        this.args = args;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "ThreadArgs{" +
                "args='" + args + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}

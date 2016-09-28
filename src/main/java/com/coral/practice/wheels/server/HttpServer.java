package com.coral.practice.wheels.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by qiuhai on 2016/9/14.
 */
public class HttpServer {


    public static void main(String [] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5678);
        ExecutorService service = Executors.newCachedThreadPool();
        while (true){
            Socket socket = serverSocket.accept();
            service.execute(new ServerThread(socket));
        }



    }

}

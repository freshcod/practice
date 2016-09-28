package com.coral.practice.wheels.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by qiuhai on 2016/9/14.
 */
public class ServerThread implements Runnable {

    private Socket socket;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try{
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            while(true){
                String str = in.readLine();
                System.out.println(str);
                out.println(str);
                out.flush();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}

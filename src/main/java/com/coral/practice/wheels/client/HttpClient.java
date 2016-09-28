package com.coral.practice.wheels.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;


/**
 * Created by qiuhai on 2016/9/14.
 */
public class HttpClient {

    public static void main(String[] args) throws Exception {
        Socket socket = new Socket(InetAddress.getLocalHost(),5678);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream());
        while (true){
            BufferedReader wt = new BufferedReader(new InputStreamReader(System.in));
            String str = wt.readLine();
            out.println(str);
            out.flush();
            System.out.println(in.readLine());
        }

    }
}

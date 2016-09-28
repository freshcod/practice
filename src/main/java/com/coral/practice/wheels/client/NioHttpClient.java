package com.coral.practice.wheels.client;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
 * Created by qiuhai on 2016/9/17.
 */
public class NioHttpClient {

    public static void main(String[] args){
        try {
            Selector selector = Selector.open();
            SocketAddress socketAddress = new InetSocketAddress("127.0.0.1",10000);
            SocketChannel socketChannel = SocketChannel.open(socketAddress);
            socketChannel.configureBlocking(false);

            socketChannel.register(selector, SelectionKey.OP_READ);

            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                if("quit".equals(line)) break;
                Charset charset = Charset.forName("UTF-8");
                ByteBuffer byteBuffer = charset.encode(line);
                while (byteBuffer.hasRemaining()){
                    socketChannel.write(byteBuffer);
                }


            }
            socketChannel.socket().shutdownOutput();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

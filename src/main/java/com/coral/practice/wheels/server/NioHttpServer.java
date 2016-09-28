package com.coral.practice.wheels.server;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * Created by qiuhai on 2016/9/17.
 */
public class NioHttpServer {

    public static void main(String [] args){
        Selector selector = null;
        ServerSocketChannel serverSocketChannel = null;
        try{
            selector = Selector.open();

            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);

            serverSocketChannel.socket().setReuseAddress(true);
            serverSocketChannel.socket().bind(new InetSocketAddress(10000));

            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            while (selector.select()>0){
                Iterator<SelectionKey> it = selector.selectedKeys().iterator();

                while (it.hasNext()){
                    SelectionKey readyKey = it.next();
                    it.remove();
                    ServerSocketChannel channel = (ServerSocketChannel)readyKey.channel();

                    SocketChannel socketChannel = channel.accept();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    int size = 0;
                    byte[] bytes;
                    //写模式 将接收到的数据写入ByteBuffer
                    while ((size = socketChannel.read(byteBuffer))>0){
                        //切换写模式为读模式
                        byteBuffer.flip();
                        //将数据读到bytes中
                        bytes = new byte[size];
                        byteBuffer.get(bytes);
                        //清空ByteBuffer
                        byteBuffer.clear();
                        String result = new String(bytes);
                        System.out.println(result);
                    }
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

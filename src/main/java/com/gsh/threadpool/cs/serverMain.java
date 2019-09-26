package com.gsh.threadpool.cs;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class serverMain {
    static int listening_port = 12345;
    static int wait_timeout = 3000;
    static int buffer_size = 2048;
    ServerSocketChannel channel;
    Selector selector;
    static int PRINT_INTERVAL = 100000;
    int recv_count = 0;



    public void createListenSocket() throws IOException {
        channel = ServerSocketChannel.open();
        channel.socket().bind(new InetSocketAddress("localhost", listening_port));
        channel.configureBlocking(false);
        selector = Selector.open();
        channel.register(selector, SelectionKey.OP_ACCEPT);
    }

    public void dataTrans() throws IOException {
        BlockingQueue queue = new LinkedBlockingQueue(1000);
        while (true) {
            selector.select();
            Iterator<SelectionKey> iter = selector.selectedKeys().iterator();


            while (iter.hasNext()) {
                SelectionKey key = iter.next();
                if ((key.readyOps() & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT) {
                    SocketChannel clientChannel = ((ServerSocketChannel) key.channel()).accept();
                    if (clientChannel != null) {
                        clientChannel.configureBlocking(false);
                        System.out.println("new conn coming");
                        clientChannel.register(selector, SelectionKey.OP_READ);
                    }
                    continue;
                }

                if ((key.readyOps() & SelectionKey.OP_READ) == SelectionKey.OP_READ) {
                    SocketChannel clientChannel = (SocketChannel) key.channel();

                    header h = new header();
                    byte[] body = protocol.readProtocal(clientChannel, h);
                    if (body != null) {
                        protocol.parseRequest(clientChannel, h, body);
                    } else {
                        System.out.println("connection lost by client");
                        clientChannel.close();
                    }
                }
                iter.remove();
            }
        }
    }

    public static void main(String args[]) {
        serverMain sm = new serverMain();
        try {
            sm.createListenSocket();

          //  for (int i = 0; i < 5; i++) {
                Thread server = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            sm.dataTrans();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                server.start();
          //  }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

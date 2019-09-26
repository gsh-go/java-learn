package com.gsh.threadpool.cs.answer;

import com.gsh.threadpool.cs.header;
import com.gsh.threadpool.cs.protocol;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @Author: gsh
 * @Date: Created in 2018/11/9 17:09
 * @Description:
 */
public class ThreadCallBack implements IThreadCallBack {
    @Override
    public void doTask(ThreadTask task) throws IOException {
        SelectionKey key = task.getKey();
        if ((key.readyOps() & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT) {
            SocketChannel clientChannel = ((ServerSocketChannel) key.channel()).accept();
            if (clientChannel != null) {
                clientChannel.configureBlocking(false);
                System.out.println("new conn coming");
                clientChannel.register(task.getSelector(), SelectionKey.OP_READ);
            }
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
    }
}

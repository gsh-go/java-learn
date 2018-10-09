package com.gsh.designpatterns.reactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;


/**
 * @Author: gsh
 * @Date: Created in 2018/9/28 15:28
 * @Description: 单线程Reactor
 */
public class TCPReactor implements Runnable {

    private final ServerSocketChannel ssc;
    private final Selector selector;

    public TCPReactor(int port) throws IOException {
        selector = Selector.open();
        ssc = ServerSocketChannel.open();
        InetSocketAddress addr = new InetSocketAddress(port);
        /*绑定监听端口*/
        ssc.socket().bind(addr);
        /*设置channel为非阻塞*/
        ssc.configureBlocking(false);
        /*在Selector上注册感兴趣的事件*/
        SelectionKey sk = ssc.register(selector, SelectionKey.OP_ACCEPT);
        sk.attach(new Acceptor(selector, ssc));
    }

    public void run() {
        while (!Thread.interrupted()) {
            System.out.println("Waiting for new event on port: " + ssc.socket().getLocalPort() + "...");
            try {
                if (selector.select() == 0){
                    continue;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> it = selectedKeys.iterator();
            while (it.hasNext()) {
                dispatch((SelectionKey) (it.next()));
                it.remove();
            }
        }
    }


    private void dispatch(SelectionKey key) {
        Runnable r = (Runnable) (key.attachment());
        if (r != null){
            r.run();
        }

    }

}

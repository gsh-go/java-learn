package com.gsh.threadpool.cs;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.*;

import java.util.Calendar;
import java.util.TimeZone;


public class clientMain {
    static int server_port = 12345;
    static int PRINT_INTERVAL = 100000;
    static int REQ_LEN = 1024 * 128;
    private SocketChannel channel;
    private int recv_count = 0;
    private int send_count = 0;

    public boolean connectServer() throws IOException {
        InetSocketAddress servAddr = new InetSocketAddress("localhost", server_port);
        this.channel = SocketChannel.open(servAddr);
        if (channel == null) return false;
        return true;
    }

    public boolean sendRequest() throws IOException {
        myRequest1 mr = new myRequest1();
        mr.age = 24;
        mr.sex = 1;
        mr.name = "zhangsan";

        byte[] bts = protocol.serializeRequest1(mr);
        boolean b = protocol.writeTCPData(channel, bts);
        if (b == false) {
            System.out.println("write data failed\n");
            return false;
        }

        send_count++;
        if (send_count % PRINT_INTERVAL == 0)
            System.out.println("sent request " + send_count);
        return true;
    }

    public boolean waitResponse() throws IOException {
        header h = new header();
        byte[] body = protocol.readProtocal(channel, h);
        if (body != null) {
            protocol.parseResponse(channel, h, body);
        } else {
            System.out.println("connection lost by server");
            return false;
        }

        recv_count++;
        if (recv_count % PRINT_INTERVAL == 0)
            System.out.println("recved response " + recv_count);
        return true;
    }

    public Calendar getTime() {
        TimeZone zone = TimeZone.getTimeZone("Asia/Beijing");
        return Calendar.getInstance(zone);
    }

    public long calcInterval(Calendar c1, Calendar c2) {
        long diff = c2.getTimeInMillis() - c1.getTimeInMillis();
        return diff;
    }

    public static void main(String args[]) {
        clientMain cm = new clientMain();
        try {
            if (false == cm.connectServer()) {
                System.out.println("connect server failed");
                return;
            }


            long callCount = 0;
            Calendar startTime = cm.getTime();
            while (true) {
                callCount++;
                if (false == cm.sendRequest()) break;
                if (false == cm.waitResponse()) break;
                if (callCount % PRINT_INTERVAL == 0) {
                    Calendar endTime = cm.getTime();
                    long inteval = cm.calcInterval(startTime, endTime);
                    System.out.println("has wasted " + inteval + " miniseconds");
                    startTime = endTime;
                }
            }
        } catch (Exception e) {
            System.out.println("client exception!");
            e.printStackTrace();
        }
    }
}

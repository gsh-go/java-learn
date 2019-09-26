package com.gsh.threadpool.cs.answer;

import com.gsh.threadpool.cs.header;
import com.gsh.threadpool.cs.myRequest1;
import com.gsh.threadpool.cs.protocol;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.Calendar;
import java.util.TimeZone;


public class AsynClient {
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

    public Calendar getTime() {
        TimeZone zone = TimeZone.getTimeZone("Asia/Beijing");
        return Calendar.getInstance(zone);
    }

    public long calcInterval(Calendar c1, Calendar c2) {
        long diff = c2.getTimeInMillis() - c1.getTimeInMillis();
        return diff;
    }


    public void sendRequest() throws IOException {
        myRequest1 mr = new myRequest1();
        mr.age = 24;
        mr.sex = 1;
        mr.name = "zhangsan";
        Calendar startTime = getTime();
        while (true) {
            byte[] bts = protocol.serializeRequest1(mr);
            boolean b = protocol.writeTCPData(channel, bts);
            send_count++;

            if (send_count % PRINT_INTERVAL == 0) {
                System.out.println("sent request " + send_count);
                Calendar endTime = getTime();
                long inteval = calcInterval(startTime, endTime);
                System.out.println("sent has wasted " + inteval + " miniseconds");
                startTime = endTime;
            }

        }
    }

    public void waitResponse() throws IOException {
        header h = new header();
        Calendar startTime = getTime();
        while (true) {
            byte[] body = protocol.readProtocal(channel, h);
            if (body != null) {
                protocol.parseResponse(channel, h, body);
            } else {
                System.out.println("connection lost by server");
            }
            recv_count++;

            if (recv_count % PRINT_INTERVAL == 0) {
                System.out.println("recved response " + recv_count);
                Calendar endTime = getTime();
                long inteval = calcInterval(startTime, endTime);
                System.out.println("recved has wasted " + inteval + " miniseconds");
                startTime = endTime;
            }

        }

    }


    public static void main(String args[]) {
        AsynClient cm = new AsynClient();
        try {
            if (false == cm.connectServer()) {
                System.out.println("connect server failed");
                return;
            }

            Thread send = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        cm.sendRequest();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            Thread wait = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        cm.waitResponse();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            send.start();
            wait.start();

        } catch (Exception e) {
            System.out.println("client exception!");
            e.printStackTrace();
        }
    }
}

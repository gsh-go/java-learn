package com.gsh.threadpool.cs;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;


public class protocol {
    private static int REQ1_TYPE = 1;
    private static int RES1_TYPE = 2;
    private static int INT_LEN = 4;

    private static int byteArrayToInt(byte[] b) {
        return b[3] & 0xFF | (b[2] & 0xFF) << 8 | (b[1] & 0xFF) << 16 | (b[0] & 0xFF) << 24;
    }

    private static byte[] intToByteArray(int a) {
        return new byte[]{(byte) ((a >> 24) & 0xFF), (byte) ((a >> 16) & 0xFF), (byte) ((a >> 8) & 0xFF), (byte) ((a) & 0xFF)};
    }

    public static int getHeaderSize() {
        return INT_LEN + INT_LEN;
    }

    public static byte[] serializeHeader(header h) {
        byte[] bl = intToByteArray(h.length);
        byte[] bt = intToByteArray(h.type);
        byte[] res = new byte[getHeaderSize()];
        System.arraycopy(bl, 0, res, 0, INT_LEN);
        System.arraycopy(bt, 0, res, INT_LEN, INT_LEN);
        return res;
    }

    public static boolean readHeader(SocketChannel channel, header h) {
        byte[] bl = new byte[INT_LEN];
        byte[] bt = new byte[INT_LEN];
        if (readTCPData(channel, bl) == false) return false;
        if (readTCPData(channel, bt) == false) return false;

        h.length = byteArrayToInt(bl);
        h.type = byteArrayToInt(bt);
        return true;
    }

    //所有协议的读取函数
    public static byte[] readProtocal(SocketChannel channel, header h) {
        //读协议头
        if (false == readHeader(channel, h)) return null;

        //读协议体
        byte[] body = new byte[h.length];
        if (readTCPData(channel, body) == false) return null;
        return body;
    }

    //协议请求处理函数分派的总入口
    public static boolean parseRequest(SocketChannel channel, header h, byte[] bts) {
        boolean res = false;
        if (h.type == REQ1_TYPE) res = parseRequest1(channel, bts);
        //else if(...

        return res;
    }

    //协议应答处理函数分派的总入口
    public static boolean parseResponse(SocketChannel channel, header h, byte[] bts) {
        boolean res = false;
        if (h.type == RES1_TYPE) res = parseResponse1(channel, bts);
        //else if(...

        return res;
    }

    public static byte[] serializeRequest1(myRequest1 data) {
        if (data.name == null) return null;

        //消息体
        byte[] ba = intToByteArray(data.age);
        byte[] bs = intToByteArray(data.sex);
        byte[] bn = data.name.getBytes();

        //消息头
        header h = new header();
        h.length = ba.length + bs.length + bn.length;
        h.type = REQ1_TYPE;
        byte[] bh = serializeHeader(h);

        byte[] res = new byte[bh.length + ba.length + bs.length + bn.length];
        System.arraycopy(bh, 0, res, 0, bh.length);
        System.arraycopy(ba, 0, res, bh.length, ba.length);
        System.arraycopy(bs, 0, res, bh.length + ba.length, bs.length);
        System.arraycopy(bn, 0, res, bh.length + ba.length + bs.length, bn.length);
        return res;
    }

    public static myRequest1 unSerializeRequest1(byte[] data) {
        int lessSize = 2 * INT_LEN;
        if (data == null || data.length <= lessSize) return null;

        byte[] ba = new byte[INT_LEN];
        byte[] bs = new byte[INT_LEN];
        byte[] bn = new byte[data.length - 2 * INT_LEN];

        System.arraycopy(data, 0, ba, 0, INT_LEN);
        System.arraycopy(data, INT_LEN, bs, 0, INT_LEN);
        System.arraycopy(data, 2 * INT_LEN, bn, 0, data.length - 2 * INT_LEN);

        myRequest1 mr = new myRequest1();
        mr.age = byteArrayToInt(ba);
        mr.sex = byteArrayToInt(bs);
        mr.name = new String(bn);
        return mr;
    }

    public static boolean parseRequest1(SocketChannel channel, byte[] bts) {
        //解析出请求
        myRequest1 myrequest = unSerializeRequest1(bts);
        if (myrequest == null) return false;

        //发送回答
        myResponse1 mr = new myResponse1();
        mr.detail = "recved";
        byte[] bmr = serializeResponse1(mr);

        if (false == writeTCPData(channel, bmr)) {
            System.out.println("write response failed\n");
            return false;
        }
        return true;
    }

    public static byte[] serializeResponse1(myResponse1 data) {
        if (data.detail == null) return null;

        byte[] bn = data.detail.getBytes();

        //消息头
        header h = new header();
        h.length = bn.length;
        h.type = RES1_TYPE;
        byte[] bh = serializeHeader(h);

        byte[] res = new byte[bh.length + bn.length];
        System.arraycopy(bh, 0, res, 0, bh.length);
        System.arraycopy(bn, 0, res, bh.length, bn.length);

        return res;
    }

    public static myResponse1 unSerializeResponse1(byte[] data) {
        if (data == null) return null;

        myResponse1 mr = new myResponse1();
        mr.detail = new String(data);
        return mr;
    }

    public static boolean parseResponse1(SocketChannel channel, byte[] bts) {
        //解析出应答
        myResponse1 myresponse = unSerializeResponse1(bts);

        //System.out.println(myresponse.detail);
        if (null == myresponse) return false;
        return true;
    }

    public static boolean readTCPData(SocketChannel channel, byte[] bts) {
        try {
            int byteCount = bts.length;
            int remainCount = byteCount;
            int startPos = 0;
            while (true) {
                ByteBuffer buffer = ByteBuffer.allocate((int) remainCount);
                buffer.clear();
                int bytesRead = channel.read(buffer);
                if (bytesRead <= 0) {
                    System.out.println("connection lost by peer");
                    channel.close();
                    return false;
                }

                remainCount = remainCount - bytesRead;
                System.arraycopy(buffer.array(), 0, bts, startPos, bytesRead);
                startPos += bytesRead;
                if (remainCount <= 0) return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("connection lost by peer");
            return false;
        }
    }

    public static boolean writeTCPData(SocketChannel channel, byte[] bts) {
        try {
            int byteCount = bts.length;
            int remainCount = byteCount;
            int startPos = 0;
            while (true) {
                byte[] tempbts = new byte[remainCount];
                System.arraycopy(bts, startPos, tempbts, 0, remainCount);

                ByteBuffer buffer = ByteBuffer.wrap(tempbts);
                int bytesWrite = channel.write(buffer);
                if (bytesWrite <= 0) {
                    System.out.println("connection lost by peer");
                    channel.close();
                    return false;
                }

                remainCount = remainCount - bytesWrite;
                startPos += bytesWrite;
                if (remainCount <= 0) return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("connection lost by peer");
            return false;
        }
    }
}

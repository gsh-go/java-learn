package com.gsh.io.socket;




import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


/**
 * @Author: gsh
 * @Date: Created in 2018/11/9 11:12
 * @Description:
 */
public class AsynClient {
    public static void main(String[] args) {
        try {
            Socket s = new Socket("127.0.0.others",8888);

            //构建IO
            InputStream is = s.getInputStream();
            OutputStream os = s.getOutputStream();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            Scanner sc = new Scanner(System.in);
            Thread readThread = new Thread(){
                public void run(){
                    while(true){
                        String msg = null;
                        try {
                            msg = br.readLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        System.out.println(msg);
                    }
                }
            };

            Thread writeThread = new Thread(){
                public void run(){
                    while(true){
                        try {
                            bw.write(sc.next()+"\n");
                            bw.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
            readThread.start();
            writeThread.start();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

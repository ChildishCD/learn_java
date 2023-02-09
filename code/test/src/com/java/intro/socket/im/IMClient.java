package com.java.intro.socket.im;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class IMClient {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Socket client = new Socket("localhost",8888);
//        Socket client = new Socket("192.168.21.140",8888);
        // 向服务端发送信息
        new Thread(()->{
            while (true){
                try {
                    DataOutputStream dos = new DataOutputStream(client.getOutputStream());
                    dos.writeUTF(scanner.next());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        // 读取服务端发送的信息
        new Thread(()->{
            while (true){
                try {
                    DataInputStream dis = new DataInputStream(client.getInputStream());
                    System.out.println(""+dis.readUTF());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

    }
}

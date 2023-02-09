package com.java.intro.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws Exception {
        // 声明一个客户端对象，根据服务器地址和端口
        Socket client = new Socket("localhost",8888);

        Scanner scanner = new Scanner(System.in);
        DataOutputStream dos = new DataOutputStream(client.getOutputStream());
        // 向服务器发送信息
        dos.writeUTF(scanner.next());
        // 获取服务器的信息
        DataInputStream dis = new DataInputStream(client.getInputStream());
        System.out.println(dis.readUTF());
    }
}

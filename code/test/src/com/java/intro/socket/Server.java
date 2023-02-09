package com.java.intro.socket;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws Exception {
        ChatServer();
    }

    private static void ChatServer() throws Exception {
        // 默认为 stream socket 用 TCP/IP 实现

        // 声明一个服务器对象，需要指定端口
        ServerSocket serverSocket = new ServerSocket(8888);
        // 等待客户端连接
        Socket client = serverSocket.accept();// 这里三次握手,仅有一次
        String clientInfo = client.getRemoteSocketAddress().toString();
        System.out.println(clientInfo);// 输出服务器的信息

        // 读取客户端发送的信息
        DataInputStream dis = new DataInputStream(client.getInputStream());
        String info = dis.readUTF();
        System.out.println("收到客户端的信息为："+info);
        // 对客户端的回答
        DataOutputStream dos = new DataOutputStream(client.getOutputStream());
        dos.writeUTF("服务器已经收到您的信息："+info);
    }
}

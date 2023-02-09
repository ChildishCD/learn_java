package com.java.intro.socket.im;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class IMServer {
    // 如果和php,node.js,.net,对接一般走http协议, 传输json数据
    //  c c++,通常用 tcp/ip协议去对接, 传输数据
    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(8888);
        System.out.println("===== 服务器启动 =====");
        //观察者模式的思想  client是观察者 server是被观察者 当server产生变化的时候，通知观察者
        List<Socket> clientList = new ArrayList<>();// 引用数据类型，存的就是客户端的对象
        while (true) {
            Socket client = server.accept();
            clientList.add(client);
            String name = client.getRemoteSocketAddress().toString();
            System.out.println("连接客户端：" + name);
            new Thread(() -> {
                // 多线程负责读取客户端的信息
                while (true) {
                    try {
                        // 接受并打印客户端的消息
                        DataInputStream dis = new DataInputStream(client.getInputStream());
                        String info = name +" : "+dis.readUTF();
                        System.out.println(info);
                        // 服务端将接受的消息发给所有的客户端
                        for (Socket c : clientList) {
                            DataOutputStream dos = new DataOutputStream(c.getOutputStream());
                            dos.writeUTF(info);
//                            dos.writeUTF(c.getInetAddress().getHostAddress().toString()+" : "+ info);
//                            dos.writeUTF(c.getRemoteSocketAddress().toString()+" : "+ info);
                        }
//                        clientList.forEach(c -> {
//                                DataOutputStream dos = new DataOutputStream(c.getOutputStream());
//                        });
                    } catch (Exception e) {
                        System.out.println(name + " 离开了............");
                        clientList.remove(client);
                        try {
                            client.close();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        // 结束为当前用户建立的线程
                        break;
                    }
                }
            }).start();
        }
    }
}

package com.test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class IMClient {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Socket client = new Socket("localhost", 9999);
        //向服务端发送信息
        new Thread(() -> {
            while (true) {
                try {
                    DataOutputStream dos = new DataOutputStream(client.getOutputStream());
                    System.out.println("1：聊天，2：查询商品");
                    int index = scanner.nextInt();
                    switch (index) {
                        case 1:
                            dos.writeInt(100);
                            dos.writeUTF(scanner.next());
                            break;
                        case 2:
                            System.out.println("请输入查询商品的id");
                            int id = scanner.nextInt();
                            dos.writeInt(101);
                            dos.writeInt(id);
                            break;
                        default:
                            System.out.println("输入有误!");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        //读取并显示服务端返回的信息
        new Thread(() -> {
            try {
                DataInputStream dis = new DataInputStream(client.getInputStream());
                Integer code = dis.readInt();
                switch (code) {
                    case 100:
                        String info = dis.readUTF();
                        System.out.println("server: " + info);
                        Thread.sleep(2000);
                        break;
                    case 101:
                        /**
                         * 服务端提供的文档
                         * 协议号101
                         * 请求参数: int(商品id)
                         * 返回数据: int(商品id)
                         *          String(商品名称)
                         *          int(商品类型id)
                         *          int(库存)
                         *          double(商品价格)
                         *          Byte(商品状态)
                         *          double(折扣)
                         *          String(商品规格)
                         */
                        int pid = dis.readInt();
                        String name = dis.readUTF();
                        int tid = dis.readInt();
                        int inventory = dis.readInt();
                        double price = dis.readDouble();
                        byte status = dis.readByte();
                        double discount = dis.readDouble();
                        String specs = dis.readUTF();
                        //输出商品的信息
                        System.out.println(name + " : " + price);
                        Thread.sleep(2000);
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }).start();
    }
}

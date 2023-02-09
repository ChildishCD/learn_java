package com.test;

import com.javasm.bean.ProductsModel;
import com.javasm.service.ProductsService;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class IMServer {
    private static MessageService messageService = new MessageService();
    private static ProductsService productsService = new ProductsService();

    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(9999);
        System.out.println("======服务器启动=======");
        List<Socket> clientList = new ArrayList<>();
        while (true) {
            Socket client = server.accept();
            clientList.add(client);
            System.out.println("连接客户端：" + client.getRemoteSocketAddress());
            String ip = client.getInetAddress().getHostAddress();
            new Thread(() -> {
                while (true) {
                    try {
                        DataInputStream dis = new DataInputStream(client.getInputStream());

                        //获得第一个数据, 永远是协议号
                        Integer code = dis.readInt();
                        switch (code) {
                            case 100:
                                //聊天程序
                                //获取输入的数据
                                String info = dis.readUTF();
                                //新的线程存储数据
                                new Thread(() -> {
                                    MessageModel msg = new MessageModel();
                                    msg.setMsg(info);
                                    msg.setIp(ip);
                                    msg.setCreateTime(LocalDateTime.now());
                                    messageService.save(msg);
                                    System.out.println(ip + " : " + info);
                                }).start();
                                //转发给所有的客户端
                                for (Socket c : clientList) {
                                    DataOutputStream dos = new DataOutputStream(client.getOutputStream());
                                    dos.writeInt(code);
                                    dos.writeUTF(ip + " : " + info);
                                }
                                break;
                            case 101:
                                //商品查询
                                int pid = dis.readInt();
                                ProductsModel p = productsService.selectById(pid);
                                System.out.println(p);
                                if(p != null){
                                    DataOutputStream dos = new DataOutputStream(client.getOutputStream());
                                    //发送协议号
                                    dos.writeInt(code);
                                    //发送数据信息
                                    dos.writeInt(p.getId());
                                    dos.writeUTF(p.getName());
                                    dos.writeInt(p.getTypeId());
                                    dos.writeInt(p.getInventory());
                                    dos.writeDouble(p.getPrice());
                                    dos.writeByte(p.getState());
                                    dos.writeDouble(p.getDiscount());
                                    dos.writeUTF(p.getSpecs());
                                }
                                break;
                            default:
                                System.out.println("没有此协议!");
                        }

                    } catch (Exception e) {
                        System.out.println(ip + "离开了..............");
                        clientList.remove(client);
                        try {
                            client.close();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    }

                }
            }).start();
        }
    }
}

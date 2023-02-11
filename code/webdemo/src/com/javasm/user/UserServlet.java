package com.javasm.user;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class UserServlet implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("===== 初始化 =====");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    private void test(HttpServletRequest request) {
        //获取多个同名参数的值(多用于checkbox)
        String[] hobbies = request.getParameterValues("hobbies");
        List<String> hobbyList = new ArrayList<>();
        Collections.addAll(hobbyList, hobbies);

        //获取提交的所有参数
        Map<String, String[]> parameterMap = request.getParameterMap();
        parameterMap.forEach((k, v) -> {
            System.out.println("key: " + k + ", value: " + Arrays.toString(v));
        });

        //不使用方法获取数据,使用流的方式获取数据//未完成
//        ServletInputStream inputStream = null;
//        try {
//            //获取请求报文中 请求体数据
//            inputStream = request.getInputStream();
//            //读取请求体数据流 可以自己解析请求参数
//            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
//            System.out.println(br.readLine());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("===== 每次访问都会执行的方法 =====");
        //Request 所有的请求信息, 浏览器 给 服务器 的报文 都在这个对象中
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        //设置request对象编码
        request.setCharacterEncoding("UTF-8");
        //获取username的值
        String uname = request.getParameter("uname");
        System.out.println(uname);//输出日志查看是否乱码
        List<String> poem = Arrays.asList("床前明月光", "疑是地上霜", "举头望明月", "低头思故乡");

        //Response 响应 给浏览器的信息, 服务器给浏览器的信息, 需要我们自己定义里面的值
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setContentType("text/html;charset=UTF-8");
        //从Response对象中获取写对象
        PrintWriter writer = response.getWriter();
        //向浏览器中写html
        writer.println("<h1>静夜思</h1>");
        writer.println("<h2>" + uname + "</h1>");
        poem.forEach(writer::println);
//        writer.flush();//清空缓存
//        writer.close();//关闭流
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("===== 销毁 =====");
    }
}

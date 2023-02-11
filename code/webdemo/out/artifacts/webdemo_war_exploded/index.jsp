<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: JAVASM
  Date: 2023/2/8
  Time: 10:12
  To change this template use File | Settings | File Templates.
--%>
<%--这是jsp文件的注释--%>
<%--jsp开发的WEB应用可以跨平台使用,Linux或window平台都支持--%>
<%--
    JSP全称Java Server Pages，是一种动态网页开发技术。它使用JSP标签在HTML网页中插入Java代码。标签通常以<%开头以%>结束。
    JSP是一种java servlet,主要用来实现java web应用程序中的用户界面部分, 网页开发者们通过结合HTML代码、XHTML代码、XML元素以及嵌入JSP操作和命令来编写JSP。
    JSP通过网页表单获取用户输入数据、访问数据库及其他数据源，然后动态地创建网页。
    JSP标签有多种功能，比如访问数据库、记录用户选择信息、访问JavaBeans组件等，还可以在不同的网页中传递控制信息和共享信息
    与纯 Servlet 相比：JSP可以很方便的编写或者修改HTML网页而不用去面对大量的println语句。
    会把jsp的代码翻译为java servlet的代码，编译为class，再在jvm中运行为html页面（tomcat中的work文件夹）
    现在不怎么用了，没有vue，react等框架快（之间还有模板开发和纯标签开发，都是用HTML代码）
    Servlet 为同步,非异步(Ajax)
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %><%--实际语言为java--%>
<html>
    <head>
        <title>$Title$</title>
    </head>
    <body>
        <%--可以写静态的html语句--%>
        <h1>Hello World!</h1>
        <%
            //控制台输出
            System.out.println("hello world!");
            //文档输出
            out.println("你好");
        %>

        <%--循环输出--%>
        <%
            List<String> users = new ArrayList<>();
            users.add("Jack");
            users.add("Mark");
            users.add("Smith");
        %>
        <table>
            <tr>
                <%--代码可以跨代码块--%>
                <%--但是顺序执行,后面声明的变量无法使用--%>
                <%--不支持lamda--%>
                <%
                    for (String u:users){
                %>
                    <%--加等于号打印变量的值--%>
                    <td><%=u %>:好！</td>
                <%
                    }
                %>
            </tr>
        </table>
<%--        $END$--%>
    </body>
</html>

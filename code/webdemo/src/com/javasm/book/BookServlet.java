package com.javasm.book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/book")
public class BookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("====开始====");
        //设置字符集
        req.setCharacterEncoding("UTF-8");
        ReqUtil reqUtil = new ReqUtil(req);
        resp.setContentType("test/http;charset=UTF-8");

        BookModel book = new BookModel();
        book.setBookId(null);
        book.setBookName(reqUtil.strParam("book_name"));
        book.setAuthorName(reqUtil.strParam("author_name"));
        book.setAttr(reqUtil.intParam("attribution"));
        book.setFtypeId(reqUtil.intParam("ftype_id"));
        book.setStypeId(reqUtil.intParam("stype_id"));
        book.setStatus(reqUtil.intParam("status"));
        book.setVip(reqUtil.intParam("is_vip"));
        book.setDescription(reqUtil.strParam("description"));
        book.setCoverUrl( reqUtil.strParam("cover_url"));
        book.setKeyword(reqUtil.strParam("keyword"));
        book.setWordCount(reqUtil.strParam("word_count"));
        book.setChapterId(null);
        book.setChapterName("");
        book.setIsRecommand(reqUtil.intParam("isRecommand"));

        PrintWriter writer = resp.getWriter();
        writer.println("请稍等.............");

        BookDAO bookDAO = new BookDAO();
        try {
            bookDAO.save(book);
            System.out.println("上传成功!");
            writer.println("上传成功!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("上传失败!!!!!!!!!!!");
            writer.println("上传失败!!!!!!!!!!!");
//            throw new RuntimeException(e);
        }

//        resp.sendRedirect("file:///C:/Users/Administrator/Desktop/learn_web/code/2_1vue-intro/1_2_from-test.html");
        System.out.println("====结束====");
    }
}

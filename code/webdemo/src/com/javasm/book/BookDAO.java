package com.javasm.book;

import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@NoArgsConstructor
public class BookDAO {
    private String url ="jdbc:mysql://127.0.0.1:3306/javasm_market";
    private String username = "root";
    private String password = "root";
    public void save(BookModel book){
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            connection = DriverManager.getConnection(url,username,password);
//            String sql = "INSERT INTO chuangbie_book (book_name,author_name,attribution,ftype_id,stype_id,status,is_vip,description,cover_url,keyword,word_count,last_update_chapter_id,last_update_chapter_name,isRecommand) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            String sql = "INSERT INTO chuangbie_book (book_name) VALUES (?)";
            ps=connection.prepareStatement(sql);
            System.out.println(book.toString());
            ps.setString(1,book.getBookName());
//            ps.setString(2,book.getAuthorName());
//            ps.setInt(3,book.getAttr());
//            ps.setInt(4,book.getFtypeId());
//            ps.setInt(5,book.getStypeId());
//            ps.setInt(6,book.getStatus());
//            ps.setInt(7,book.getVip());
//            ps.setString(8,book.getDescription());
//            ps.setString(9,book.getCoverUrl());
//            ps.setString(10,book.getKeyword());
//            ps.setString(11,book.getWordCount());
//            ps.setInt(12,book.getChapterId());
//            ps.setString(13,book.getChapterName());
//            ps.setInt(14,book.getIsRecommand());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }
}

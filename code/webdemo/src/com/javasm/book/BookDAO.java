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
            connection = DriverManager.getConnection(url,username,password);
            String sql = "INSERT INTO chuangbie_book VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            ps=connection.prepareStatement(sql);
            ps.setInt(1,book.getBookId());
            ps.setString(2,book.getBookName());
            ps.setString(3,book.getAuthorName());
            ps.setInt(4,book.getAttr());
            ps.setInt(5,book.getFtypeId());
            ps.setInt(6,book.getStypeId());
            ps.setInt(7,book.getStatus());
            ps.setInt(8,book.getVip());
            ps.setString(9,book.getDescription());
            ps.setString(10,book.getCoverUrl());
            ps.setString(11,book.getKeyword());
            ps.setString(12,book.getWordCount());
            ps.setInt(13,book.getChapterId());
            ps.setString(14,book.getChapterName());
            ps.setInt(15,book.getIsRecommand());
            ps.execute();
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

package jdbc.book.dao;

import jdbc.book.model.ChuangbieBook;
import jdbc.book.model.ChuangbieBookFtype;
import jdbc.book.model.ChuangbieBookStype;
import utils.JavasmDBUtil;
import utils.ParameterUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    public BookDAO() {
    }

    public List<ChuangbieBook> selectBookByWordLikely(String word) {
        List<ChuangbieBook> books = null;
        String sql = "SELECT distinct b.*FROM chuangbie_book b,chuangbie_book_chapter c,chuangbie_book_ftype ft,chuangbie_book_stype st WHERE b.book_id=c.book_id AND b.ftype_id=ft.type_id AND b.stype_id=st.type_id AND (b.book_name LIKE ? OR c.chapter_name LIKE ? OR ft.type_name LIKE ? OR st.type_name LIKE ? OR b.author_name LIKE ? OR b.keyword LIKE ?) ORDER BY b.book_id";
        PreparedStatement ps = JavasmDBUtil.getPreparedStatement(sql);
        ResultSet rs = null;
        word = "%" + word + "%";
        try {
            for (int i = 1; i <= 6; i++) {
                ps.setString(i, word);
            }
            rs = ps.executeQuery();
            books = new ArrayList<>();
            while (rs.next()) {
                books.add(new ChuangbieBook(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public List<ChuangbieBook> selectBookByFtypeName(String fName, int pageStart, int pageSize) {
        List<ChuangbieBook> books = null;
        String sql = "SELECT b.* FROM chuangbie_book b,chuangbie_book_ftype ft WHERE b.ftype_id = ft.type_id and ft.type_name = ? ORDER BY b.book_id limit ?,?";
        PreparedStatement ps = JavasmDBUtil.getPreparedStatement(sql);
        ResultSet rs = null;
        try {
            ps.setString(1, fName);
            ps.setInt(2, pageStart);
            ps.setInt(3, pageSize);
            rs = ps.executeQuery();
            books = new ArrayList<>();
            while (rs.next()) {
                books.add(new ChuangbieBook(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JavasmDBUtil.close(rs, ps);
        }

        return books;
    }

    public ChuangbieBook selectBookByBookId(long bId) {
        ChuangbieBook book = null;
        String sql = "SELECT b.*,COUNT(c.chapter_id) chapter_num,SUM(b.is_vip*c.is_vip*c.price) price_total FROM chuangbie_book b,chuangbie_book_chapter c WHERE b.book_id=c.book_id AND b.book_id = ? GROUP BY b.book_id";
        PreparedStatement ps = JavasmDBUtil.getPreparedStatement(sql);
        ResultSet rs = null;
        try {
            ps.setLong(1, bId);
            rs = ps.executeQuery();
            rs.next();
            book = new ChuangbieBook(rs);
            book.setChapterNum(rs.getLong("chapter_num"));
            book.setPriceTotal(rs.getLong("price_total"));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JavasmDBUtil.close(rs, ps);
        }
        return book;
    }

    public List<ChuangbieBook> selectBookByPage(int pageStart, int pageSize) {
        List<ChuangbieBook> books = null;
        String sql = "SELECT b.*,COUNT(c.chapter_id) chapter_num FROM chuangbie_book b,chuangbie_book_chapter c WHERE b.book_id=c.book_id GROUP BY b.book_id ORDER BY b.book_id LIMIT ?,?";
        PreparedStatement ps = JavasmDBUtil.getPreparedStatement(sql);
        ResultSet rs = null;
        try {
            ps.setInt(1, pageStart);
            ps.setInt(2, pageSize);
            rs = ps.executeQuery();
            books = new ArrayList<>();
            while (rs.next()) {
                ChuangbieBook book = new ChuangbieBook(rs);
                book.setChapterNum(rs.getLong("chapter_num"));
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JavasmDBUtil.close(rs, ps);
        }
        return books;
    }
}

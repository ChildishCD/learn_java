package jdbc.book.dao;

import jdbc.book.model.ChuangbieBookChapter;
import utils.JavasmDBUtil;
import utils.ParameterUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookChapterDAO {
    public BookChapterDAO() {
    }

    public List<ChuangbieBookChapter> selectChaptersByBookId(long bId, int pageStart, int pageSize) {
        List<ChuangbieBookChapter> chapters = null;
        String sql = "SELECT * FROM chuangbie_book_chapter c WHERE c.book_id = ? ORDER BY c.chapter_id LIMIT ?,?";
        PreparedStatement ps = JavasmDBUtil.getPreparedStatement(sql);
        ResultSet rs = null;
        try {
            ps.setLong(1, bId);
            ps.setInt(2, pageStart);
            ps.setInt(3, pageSize);
            rs = ps.executeQuery();
            chapters = new ArrayList<>();
            while (rs.next()){
                chapters.add(new ChuangbieBookChapter(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JavasmDBUtil.close(rs,ps);
        }
        return chapters;
    }
}

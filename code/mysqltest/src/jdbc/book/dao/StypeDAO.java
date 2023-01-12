package jdbc.book.dao;

import jdbc.book.model.ChuangbieBook;
import jdbc.book.model.ChuangbieBookFtype;
import jdbc.book.model.ChuangbieBookStype;
import utils.JavasmDBUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StypeDAO {
    public StypeDAO() {
    }

    public ChuangbieBook queryStype(ChuangbieBook book){
        List<ChuangbieBook> bookList = new ArrayList<>();
        bookList.add(book);
        return queryStype(bookList).get(0);
    }

    public List<ChuangbieBook> queryStype(List<ChuangbieBook> books) {
        String bId = books.stream().map(ChuangbieBook::getBookId).map(String::valueOf).collect(Collectors.joining(","));
        String sql = "SELECT b.book_id,st.* FROM chuangbie_book b,chuangbie_book_stype st WHERE b.stype_id = st.type_id and b.book_id in (" + bId + ") ORDER BY b.book_id";
        PreparedStatement ps = JavasmDBUtil.getPreparedStatement(sql);
        ResultSet rs = null;
        try {
            rs = ps.executeQuery();
            for (ChuangbieBook book : books){
                if(!rs.next()) break;
                book.setStype(new ChuangbieBookStype(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JavasmDBUtil.close(rs,ps);
        }
        return books;
    }
}

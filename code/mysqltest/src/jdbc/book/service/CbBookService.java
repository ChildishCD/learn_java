package jdbc.book.service;

import jdbc.book.dao.BookChapterDAO;
import jdbc.book.dao.BookDAO;
import jdbc.book.dao.FtypeDAO;
import jdbc.book.dao.StypeDAO;
import jdbc.book.model.ChuangbieBook;
import jdbc.book.model.ChuangbieBookChapter;
import jdbc.book.model.ChuangbieBookFtype;
import utils.PageUtil;
import utils.ParameterUtil;

import java.util.List;

public class CbBookService {
    private BookDAO bookDAO;
    private BookChapterDAO chapterDAO;
    private FtypeDAO ftypeDAO;
    private StypeDAO stypeDAO;

    public CbBookService() {
        bookDAO = new BookDAO();
        chapterDAO = new BookChapterDAO();
        ftypeDAO = new FtypeDAO();
        stypeDAO = new StypeDAO();
    }

    public List<ChuangbieBook> getBookByWordLikely(String word){
        List<ChuangbieBook> books = null;
        if (ParameterUtil.checkLegal(word)){
            books = bookDAO.selectBookByWordLikely(word);
        }
        return books;
    }


    public List<ChuangbieBook> getBookByFtypeName(String fName,int pageNum,int pageSize){
        List<ChuangbieBook> books =null;
        if (ParameterUtil.checkLegal(fName,pageNum,pageSize)){
            books = bookDAO.selectBookByFtypeName(fName,PageUtil.getPageStar(pageNum, pageSize), pageSize);
        }
        return books;
    }

    public List<ChuangbieBookChapter> getChaptersByBookId(long bId, int pageNum, int pageSize) {
        List<ChuangbieBookChapter> chapters = null;
        if (ParameterUtil.checkLegal(bId,pageNum,pageSize)){
            chapters = chapterDAO.selectChaptersByBookId(bId,PageUtil.getPageStar(pageNum, pageSize), pageSize);
        }
        return chapters;
    }

    public ChuangbieBook getBookByBookId(long bId) {
        ChuangbieBook book = null;
        if (ParameterUtil.checkLegal(bId)) {
            book = bookDAO.selectBookByBookId(bId);
            book = ftypeDAO.queryFtype(book);
            book = stypeDAO.queryStype(book);
        }
        return book;
    }

    public List<ChuangbieBook> getExistBookByPage(int pageNum, int pageSize) {
        List<ChuangbieBook> books = null;
        if (ParameterUtil.checkLegal(pageNum, pageSize)) {
            books = bookDAO.selectBookByPage(PageUtil.getPageStar(pageNum, pageSize), pageSize);
            books = ftypeDAO.queryFtype(books);
            books = stypeDAO.queryStype(books);
        }
        return books;
    }
}

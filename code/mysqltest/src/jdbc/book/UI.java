package jdbc.book;

import jdbc.book.model.ChuangbieBook;
import jdbc.book.model.ChuangbieBookChapter;
import jdbc.book.service.CbBookService;
import utils.ParameterUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UI {
    public static void main(String[] args) {
        //1. 分页查询图书集合，根据图书id正序
//        showAllBookByPage();
        //2. 根据图书id查询
//        searchBookById();
        //3. 输入一级分类名称，查询该分类下所有图书，并打印图书信息，分页查看
//        searchBookByFtypeName();
        //4. 根据图书名称或章节名称或一级分类名称或二级分类名称或作者名称或者关键词，模糊查询图书集合，并打印
        searchBookByWordLikely();
    }

    private static void searchBookByWordLikely() {
        Scanner scanner = new Scanner(System.in);
        CbBookService service = new CbBookService();
        System.out.println("Please input word about book:");
        printBooks(service.getBookByWordLikely(scanner.next()));
    }

    private static void searchBookByFtypeName() {
        Scanner scanner = new Scanner(System.in);
        CbBookService service = new CbBookService();
        String go = "Y";
        while (go.equals("Y") || go.equals("y")) {
            //是否展示下一页
            int pageNum = 0, pageSize = 10;
            String next = "Y";
            //输入一级分类名称
            System.out.println("Please input ftype name:");
            String fName = scanner.next();
            List<ChuangbieBook> books = null;
            while (next.equals("Y") || next.equals("y")) {
                pageNum++;
                //获取图书 并 输出图书
                books = service.getBookByFtypeName(fName, pageNum, pageSize);
                if(books != null || !books.isEmpty()){
                    printBooks(books);
                    //是否显示下一页
                    System.out.println("Do you want to show the next page? (Y/N):");
                    next = scanner.next();
                }else {
                    System.out.println("No more book!");
                    next = "N";
                }
            }
            //是否继续查询
            System.out.println("Do you want to input another ftype name? (Y/N):");
            go = scanner.next();
        }
    }

    private static void searchBookById() {
        String go = "Y";
        CbBookService service = new CbBookService();
        while (go.equals("Y") || go.equals("y")) {
            Scanner scanner = new Scanner(System.in);
            //输入书籍的id
            System.out.println("Please input the book id:");
            try {
                long bookId = Long.parseLong(scanner.next());
                //获取图书信息
                List<ChuangbieBook> bookList = Stream.of(service.getBookByBookId(bookId)).collect(Collectors.toList());
                //获取该书籍前10的章节信息
                List<ChuangbieBookChapter> chapters = service.getChaptersByBookId(bookId, 1, 10);
                //输出
                printBooks(bookList);
                for (ChuangbieBookChapter c : chapters) {
                    System.out.println("\t" + c.getChapterName() + "\t| " + c.getPrice() + "(价格)\t| " + c.getWordCount() + "(字数)");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please input correct book id!");
            } finally {
                //询问是否继续查看书籍
                System.out.println("Do you want to get other page of books? (Y/N):");
                go = scanner.next();
            }
        }
    }

    private static void showAllBookByPage() {
        Scanner scanner = new Scanner(System.in);
        CbBookService bookService = new CbBookService();
        //输入每页显示的书籍数目
        System.out.println("Please input page size:");
        int pageSize = Integer.parseInt(scanner.next());
//        int pageSize = 10;
        String go = "Y";
        while (go.equals("Y") || go.equals("y")) {
            //输入页数
            System.out.println("Please input page number:");
            int pageNum = Integer.parseInt(scanner.next());
            //分页查询书籍详情
            List<ChuangbieBook> books = bookService.getExistBookByPage(pageNum, pageSize);
            //显示书籍详情
            printBooks(books);
            //询问是否继续查看书籍
            System.out.println("Do you want to get other page of books? (Y/N):");
            go = scanner.next();
        }
    }

    private static void printBooks(List<ChuangbieBook> books) {
        if(ParameterUtil.checkLegal(books)){
            String temp;
            for (ChuangbieBook book : books) {
                System.out.print(String.join("\t| ", book.getBookName(), book.getAuthorName(), book.getAttributionName()));
                if (book.getFtype() != null) {
                    printExist(book.getFtype().getTypeName());
                }
                if (book.getStype() != null) {
                    printExist(book.getStype().getTypeName());
                }
                if (printExist(book.getChapterNum())) {
                    System.out.print("(章节数量)");
                }
                if (printExist(book.getPriceTotal())) {
                    System.out.println("(总价格)");
                }
                System.out.println();
            }
        }else {
            System.out.println("No match book!");
        }
    }

    private static boolean printExist(Object temp) {
        if (ParameterUtil.checkLegal(temp)) {
            System.out.print("\t| " + temp);
            return true;
        }
        return false;
    }
}

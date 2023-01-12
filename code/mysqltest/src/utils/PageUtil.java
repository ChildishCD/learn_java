package utils;

public class PageUtil {
    private PageUtil() {
    }

    public static int getPageStar(int pageNum, int pageSize) {
        return (pageNum - 1) * pageSize;
    }
}

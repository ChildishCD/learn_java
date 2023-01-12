import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 表达对世界的喜爱
 * 理解java的基础语法
 *
 * @ java
 */
public class HelloWorld {
    public static void main(String[] args) {
//        hello();//输入与输出
//        strings();//字符串
//        array();//数组
    }

    private static void strings() {
        //比较方法
        String a = "set", b = "sad";
        if (!a.equals(b)) {
            System.out.println(a +' '+ b);
        }
        //转换方法
        Integer c = 45;
        System.out.println(c.toString());
        System.out.println(Integer.toString(80));
        //

    }

    private static void hello() {
        //import java.util.Scanner;
        //输入你的心情
        Scanner scan = new Scanner(System.in);//创建Scanner对象
        System.out.println("Input your mood:");
        String mood = scan.nextLine();
        //输出
        //输出字符串
        System.out.println(mood + ",World!");
        //格式化输出数字
        System.out.printf("%.4f\n", 2.2);
    }


    private static void array() {
        //数组的创建和赋值
        //先创建一个新的数组，再赋值
        int[] num = new int[5];
        num[0] = 1;
        //创建时赋值
        double[] point = {1.1, 2.2, 3.3};
        String[] text = {"Java", "is", "the", "best", "!"};
        int[][] ns = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};

        //数组的遍历
        //用索引的方式
        for (int i = 0; i < point.length; i++) {
            System.out.println(point[i]);
        }
        //用for each
        for (double n : point) {
            //具体的操作
        }
    }
}


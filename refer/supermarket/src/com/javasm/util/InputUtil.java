package com.javasm.util;

import java.io.InputStream;
import java.util.Scanner;

/**
 * @author: Lisa
 * @className: InputUtil
 * @description:
 * @date: 2021/6/15 10:04
 * @version: 0.1
 * @since: 1.8
 */
public class InputUtil {

    private InputUtil() {
    }


    public static Scanner input;

    static {
        input = new Scanner(System.in);
    }

    public static int inputInt(String regex, String msg) {

        while (true) {
            String numStr = input.next();
            if (!numStr.matches(regex)) {
                System.out.println(msg);
            } else {
                return Integer.parseInt(numStr);
            }
        }
    }

    public static int inputInt() {
        return input.nextInt();
    }

}

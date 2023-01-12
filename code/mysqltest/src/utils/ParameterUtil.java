package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParameterUtil {
    private ParameterUtil(){

    }
    public static boolean checkNumbers(String numStr){
//        String str = "123,54458";
        String pattern = "^\\d+(,\\d+)*$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(numStr);
        return m.matches();
    }

    public static boolean checkLegal(Object... objects){
        for (Object o :objects){
            if(o == null){
                return false;
            }
            //判断字符合法
            if(o instanceof String){
                if (((String) o).isEmpty()){
                    return false;
                }
            }
        }
        return true;
    }
}

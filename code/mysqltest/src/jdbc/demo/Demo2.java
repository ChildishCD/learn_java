package jdbc.demo;

import java.util.Date;
import java.util.List;

public class Demo2 {
    public static void main(String[] args) {
//        test1();
//        insertFruitList();

    }

    //方法应该写入DAO
    private static void insertFruitList(List<SmFruit> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        //批量添加
        //  1.循环添加，单行sql
        //  2.addBatch() excuteBatch() <这个逻辑简单>
        //  3.拼接字符串, sb.append()

        //添加后返回主键
        //PreparedStatement ps = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
        //ps.executeUpdate();//执行添加
        //ResultSet generatedKeys = ps.getGeneratedKeys();//获取自增id
        //if(generateKeys,next()){
        //generatedKeys.getInt(1);}
    }

    private static void test1() {
        SmFruit fruit = SmFruit.createFruit();
        fruit.setInventory(800);
        fruit.setCreateTime(new Date());
        SmFruitDAO fruitDAO = new SmFruitDAO();
        Integer integer = fruitDAO.insertFruit(fruit);
        if (integer == -1){
            System.out.println("添加失败");
        }else {
            System.out.println("添加成功");
        }
    }
}

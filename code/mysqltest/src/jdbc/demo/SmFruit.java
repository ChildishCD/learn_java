package jdbc.demo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import utils.Roster;

import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;


@AllArgsConstructor
@NoArgsConstructor
//命名规范
//  将一个表变为一个class
//  与表名相同，转换为驼峰式命名
//  Model SmFruitModel
//  Entity
//  POJO

//  VO  （表示与业务展示相关，不完全与数据库相关）
public class SmFruit {
    private Integer id;
    private String name;
    private Double price;
    private Integer inventory;
    private Integer state;
    //用util的date类,传入数据库需要转换为 指定数据库支持 的Data内, 或是传为string
    private java.util.Date createTime;

    public SmFruit(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public static SmFruit createFruit(){
        String name = Roster.getFruitsName();
        ThreadLocalRandom random = ThreadLocalRandom.current();
        Integer price = random.nextInt(1,20);
        return new  SmFruit(name,price.doubleValue());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}

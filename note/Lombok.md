jar包：java编译之后的文件，打包，jar 里面是class文件，是压缩文件，可以引入我们的项目直接使用

jar包里是其他人 写好的代码，我引入这个jar包，就可以直接调用 别人写好的类和方法

引入jar包，单独创建文件夹，放jar文件

重新创建项目，想使用lombok，都需要重新引入jar包，都要重新配置一次

lombok是第三方插件，非必要使用，可选

● 1.下载路径: https://projectlombok.org/download 目前最先版本是1.18.10  
● 2.下载完毕之后，在项目中，新建lib目录，将类库文件存储在文件夹中  
● 3.将类库文件放到编译的路径下。 add  as  library  
● 4.将lombok与IDEA进行集成  
  ○ 1. 在setting里面安装lombok的插件，然后重启。(或者下载离线也可)  
  ○ 2. 开启注解处理器。

> Setting,Build,Compiler
> 
> -Djps.track.ap.dependencies=false

低版本出现问题的解决方案

如果 idea里使用lombok 解决不了， 换idea，一种是不使用 lombok

### 日常注解

● 这些注解只对非static和非瞬态transient修饰的成员变量有效  
  ○ @Setter  
  ○ @Getter  
  ○ @ToString  
  ○ @AllArgsConstructor  
  ○ @NonArgsConstructor  
  ○ @EqualsAndHashCode  
  ○ @Data == @Setter+@Getter+@ToString+@EqualsAndHashCode  
  ○ @NonNull   非空的声明，可用于校验参数，能帮助避免空指针  
  ○ @Cleanup  帮助我们自动调用close()方法，很大的简化了代码(主要与IO有关)  
    @Accessors(chain = true) 链式写法  允许链式 声明  User user = new User().setAge(11).setName("").setHeadPic("");  
     @Accessors(fluent = true) 优雅的写法  流畅的 User user1 = new User().age(11).name("").headPic(""); user1.age()  
  ○ .......

toString 是输出 一个对象的时候 默认执行的 方法 替我们执行的是默认的方法
![[Pasted image 20221229172300.png]]

### 优缺点

-   优点:
    
-   -   能通过注解的形式自动生成构造器、getter/setter、equals、hashcode、toString等方法，提高了一定的开发效率
        
    -   让代码变得简洁，不用过多的去关注相应的方法
        
    -   属性做修改时，也简化了维护为这些属性所生成的getter/setter方法等
        
    -   对新手友好
        
-   缺点:
    
-   -   不支持多种参数构造器的重载
        
    -   虽然省去了手动创建getter/setter方法的麻烦，但大大降低了源代码的可读性和完整性，降低了阅读源代码的舒适度
        
    -   ==会强行让其他人使用lombok==
        
    -   代码可读性低，可调用性低
        
    -   影响程序升级
        
    -   破坏封装，无脑封装不如不封装
        
    -   序列化有死循环风险
        
    -   违反了Java annotation processor 规定，很可能引发一些离奇错误
        

阿里的编码规范中，不建议在setter/getter方法中 加判断的

-   总之虽然非常不建议在属性的getter/setter写一些业务代码，但在多年项目的实战中，有时通过给getter/setter加一点点业务代码，能极大的简化某些业务场景的代码。所谓==取舍==，也许就是这时的舍弃一定的规范，取得极大的方便。
    

```java
import lombok.*;  
​  
//编写代码的时候 使用@Getter 报红色波浪线，表示编译不通过，是lib包没引入成功  
//@Getter//自动生成 getter方法  
//@Setter//自动生成 setter方法  
@AllArgsConstructor//生成 带参数的 构造方法  
@NoArgsConstructor//生成 无参构造  
//@ToString //生成 toString方法，覆盖默认的  
@Data//== @Gtter+@Setter+ToString  
public class Game {  
​  
    @NonNull//非空判断  
    private String name;  
​  
    private double price;  
    private int downloadTimes;  
}
```

```java
package com.javasm.lombok;  
​  
import lombok.Data;  
import lombok.experimental.Accessors;  
​  
//chain = true  链式写法  允许链式 声明  
//@Accessors(chain = true)  
//fluent = true  优雅的写法  流畅的  
@Accessors(fluent = true)  
@Data  
public class User {  
    private String name;  
    private int age;  
    private String headPic;  
}  
​  
//public声明的 类  类名必须和文件名一致  
//不能和同包下的其他类重名  
class Test2{  
    public static void main(String[] args) {  
        //User user = new User().setAge(11).setName("").setHeadPic("");  
        User user1 = new User().age(11).name("").headPic("");  
        System.out.println(user1.age());  
    }  
}
```

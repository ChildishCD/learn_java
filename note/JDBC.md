- 用util的date类,传入数据库需要转换为 指定数据库支持 的Data内, 或是传为string
	```java
   if (fruit.getCreateTime() != null) {  
                //利用char存时间  
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		String format = simpleDateFormat.format(fruit.getCreateTime());  
                preparedStatement.setString(4, format);  
            }  
//            else if (fruit.getCreateTime() != null) {  
                //java.util.Date date = fruit.getCreateTime();                //LocalDateTime localDateTime = LocalDateTime.now();                //String str = "2023-01-05 11:15:30";                // preparedStatement.setTimestamp(3,fruit.getCreateTime());                //向数据库 set时间格式数据的时候，不能直接传 java uitl下的时间类，要new出来对应的sql时间类  
                //preparedStatement.setDate(3,new java.sql.Date(date.getTime()));  
                //preparedStatement.setTime(3,new Time(date.getTime()));//                preparedStatement.setTimestamp(3, new Timestamp(fruit.getCreateTime().getTime()));  
                //preparedStatement.setString(3,str);//            }
```

- 数据库表 转 类 用idea右侧的Database 连接数据库
	- 点击 {1 of n} 选择数据库
	- 右键数据库
		- 新建查询
		- 按照模板生成和表相同的类
			- scripted extensions --> generated POJO groovy
			- 模板 scripted extensions --> go to script directory
			- D:/svn/第一阶段/21-JDBC/jar
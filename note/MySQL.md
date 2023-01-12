```http
C:\Users\Administrator\Documents\Navicat\MySQL\Servers\LocalMySQL\javasm
```

命令行登录用户
mysql -u root -p
数据库客户端
- mysql数据库，user表，更改root用户的host权限为%


SQL为结构化查询语言(struct query language)
- 分为 DDL(defination), DML(manipulation), DQL(query), DCL(control); 
- 通常多指DQL

数据库的编码格式通常用
- utf-8（汉字库）
- utf-8mb4（汉字库的补充，支持表情）

drop 和 delete
- drop 全删除，包括表结构
- delete 只清空数据

图片本质上是流, 所以需要特殊的支持
- 记录地址
- mongodb等支持空间数据的
- 云数据库

表名通常在前面加 前缀下划线区分功能
属性的命名规范是以 "\__" 连接

列名/属性 类型(长度)
- 一般能用小的就用小的
- 数字一般用int
```sql
create table student(
 id int(3), #整型
 stu_name  varchar(20),#可变长字符,有长度限制 string
 stu_age tinyint(2) unsigned,#bool
 stu_gender char(1),
 stu_score float(4,1),
 create_time datetime,#LocalDateTime
 update_time datetime   
);
```

datetime利用日期类工作效率高
bigint(时间毫秒数)只传string

digdecimal定点数 decimal(m,n)

text, longtext 存储大文本

mysql会自动回滚
delete  和 update 后必接where，否则会影响所有的数据

不用外键？
- 必须在数据库表设计完备后加外键
- 加外键后表的设计不好更改

同名区分
库名.表名.属性名

属性自增?

desc 表名 : 显示表的设计

- 设计数据库-->设置"外键"
	- 一对一, 设置一边+unique
	- 一对多, 设置一边
	- 多对多, 中间加一张关联表, 左右两侧都变成了一对多
		- 表名为rel_表1_表2
		- 两个id属性对应两个表
		- 设置联合主键
```sql
		select * from student s,rel_student_teacher r,teacher t 
		where r.teacher_id in (select t.id from t where t.name="Jack") 
		and s.id = r.student_id;
		
```


```sql
INSERT INTO table_name (column1,column2,column3,...)
VALUES (value1,value2,value3,...);

UPDATE table_name
SET column1 = value1, column2 = value2, ...
WHERE condition;

DELETE FROM table_name
WHERE condition;
```


[MySQL Operators (w3schools.com)](https://www.w3schools.com/mysql/mysql_operators.asp)
[SQL EXISTS Operator (w3schools.com)](https://www.w3schools.com/sql/sql_exists.asp)
```sql
SELECT
  DISTINCT 列1,列2...列n / *
FROM
 表1,..表n
[WHERE  条件1 OR / AND 条件2] -- 记录筛选
[GROUP BY 列1] -- 分组查询
[HAVING 条件1 OR / AND 条件2]  -- 分组之后记录筛选
[ORDER BY 列1 ASC/DESC 列2 ASC/DESC] -- 排序查询 默认是升序  ASC  DESC 降序
[LIMIT ?/?,?] -- 限定查询的结果集。分页查询;
```

```sql
-- 将多科的数据放在同一行
SELECT student.*, GROUP_CONCAT(score.c_name), GROUP_CONCAT(score.greade)
FROM student 
LEFT JOIN score ON student.id = score.stu_id
GROUP BY student.id;

-- 使用case
-- 行转列
-- 分级
SELECT student.*,
max(CASE score.c_name
	WHEN '计算机' THEN
		score.greade
	ELSE
		0
END) it,
(CASE 
	WHEN student.birth>1990 THEN
		'old'
	ELSE
		'young'
END) age
FROM student 
LEFT JOIN score ON student.id = score.stu_id
GROUP BY student.id;
```

```sql
-- 按规律错位自连接
SELECT next.*,(next.zz-pre.zz)/next.zz*100 AS increase_rate FROM profit AS next LEFT JOIN profit AS pre ON pre.year = next.year-1;
```


distinct
- 多个相当于联合主键

java调用的数据是select语句查询的结果，不直接作用于数据库表
jdbc <--> sql <--> database

as 可以省略
IFNULL(column,<replaced data>)
ISNULL(column)
count()
avg()

查询结果
视图
- 视图是一张临时生成的表
- 表和列都可赋予别名
- 只要当前运行的sql语句中表和列的名字不冲突即可, 别名可以和数据库表中的列名重复

order by
- 默认是以id进行升序排列
- 正序 esc 升序
- 逆序 desc 降序
- order by c1 esc, c2, c3 desc, c4... 如果相同处理的顺序

union(有其他的手段)
- 多个sql语句的结果 按顺序 连接为一张视图
- 只有一个;
- union 会跳过重复的项
- union all 会保留重复的项
- 大表分多表,性能太差, 太多的select, 会死机!!!
	- 内存, 读写速度, 网速, 磁盘
	- 网络连接超时, 服务器性能

limit 
- limit n; limit start,count
- start不是id, 表的start默认是0
- 实际场景数据会很大
- 谨慎使用select *, 容易造成资源挤兑
- 数据库客户端软件会有保护功能, 但是代码不会
- 使用场景
	- 网站分页 limit (页数-1)*每页项目数 ,每页项目数 
	- 数据表太巨巨巨巨巨巨大


-- NOT IN 后不能有NULL

有些函数仅在select后才能用, 还要加group by????
- 条件函数
- 日期函数
- 文本函数
- 窗口函数

date查询

group_concat()
case


%%where 和 having 的顺序固定，所以运用的时候看数据的生成顺序来选择
函数的作用方式和group by 有关
having 后跟的必须是聚合函数运算的结果！！然后才是此结果的判断条件
having agg()  as ***     =><>         (???)%%

group by 
- 后面是主键 或 非空且唯一的属性
	- select, having 接 aggregate function 和 （主键 或 非空且唯一的属性）所在表的所有属性
- 后面是一般属性
	- select, having 接 aggregate function 和 分组依赖的属性
- 后面是fuction、case结果



检查查询的正确与否
- 数目
- 列
- 关系
- 。。。

连接
where 比 join 的性能高（笛卡尔积）
左右连接会查出null值
左右是join语句出现的顺序规定的
多表可以用 表名.*
[SQL 连接(JOIN) | 菜鸟教程 (runoob.com)](https://www.runoob.com/sql/sql-join.html)

自连接
表中的项之间有关系, 有一列存储的有关系的项的id

数据库范式
1NF 每个属性值只表示一种事物
2NF 每个表中的属性只跟此表每项的属性有关(类的属性,责任单一)
- 当数据量大或其他情况, 每一次查询都要关联时, 经常会牺牲2NF, 将其他的属性放入表中, 以提升查询的效率
3NF(很少满足) 每列都和主键列直接相关，而不是间接相关(但是有"外键")

数据库事务(transaction)
- 开启事务可以回滚
- 不开启会自动提交事务
- MySQL
	- 自动提交事务
	- 一条语句是一个事务
	- 开始标志：Mysql默认情况下 任何一条DML语句(insert、update、delete，select)执行，标志事务的开启
	- 结束标志：
		-   提交：成功的结束，将所有的DML语句操作历史记录和底层硬盘数据来一次同步
		- 提交前, 未结束之前，DML语句是不会更改底层数据, 记录的是操作语句而不是如何撤销, 提交后才会执行语句
    
-   回滚：失败的结束，将所有的DML语句操作历史记录全部清空
- transaction
	- 开启事务 start tansaction;/begin;
	- 提交 commit;
	- 回滚 rollback;

事务的特性 ACID
-   原子性(Atomicity):事务不可分割的最小工作单元，事务内的操作要么全做，要么全不做。事务具有要么全部成功 要么全部失败 这就是原子性。
    
-   一致性(Consistency):在事务执行前数据库的数据处于正确的状态，需事务执行完后数据库的数据依然处于正确的状态，即数据完整性约束没有被破坏，**如A给B转帐，不论转帐是否成功，转帐之后的A和B的帐户总额和转帐之前是相同的** 3000 4000
    
-   隔离性(Isolation):当多个事务处于并发访问同一个数据库资源时，事务之间相互影响，不同的隔离级别决定了各个事务对数据资源访问的不同行为, 修改的数据需要和存在的数据隔离
    
-   持久性(Durability):事务一旦执行成功，它对数据库的改变是不可逆的

存储引擎
事务的隔离级别................................
> 详见数据库原理和数据库面试的要求

备份
- -- 备份数据库表结构，表数据
-- 1. 物理备份  在my.ini  datadir
-- C:\ProgramData\MySQL\MySQL Server 8.0\data
-- 2. 命名行备份  xxx.sql
	-- 2.1 导出
	-- mysqldump -uroot -proot demo > D:\\a.sql
	-- mysqldump -uroot -proot demo sys_user product > D:\\b.sql
	-- 2.2 导入
	-- 在登录服务器成功状态下  source 文件路径  mysql> source D:\\a.sql
-- 3. 使用navicat存为sql语句文件
--  3.1 选择数据库或者表  转储sql
--  3.2 选择数据库 运行sql实现导入
- 通过软件进行数据库与备份数据库之间的数据表传输!!!!! 工具->数据传输
-- 4. navicat里面备份的功能!!!
- 软件中有批处理的功能,可以实现备份同步......并可以在特定时间或是定义trigger自动运行

服务器一般写脚本来备份
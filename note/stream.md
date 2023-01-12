```java
list.stream().map(Object::toString).collect(Collectors.joing(','))
```

```java
//String sql = "delet from sm_user where id in ("+ str +")"
String sql = "delet from sm_user where id in (?)";
sql = sql.replaceAll("\\?","%s");
sql = String.format(sql,idstr);
```
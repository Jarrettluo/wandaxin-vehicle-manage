### 二手车管理平台 

----

依赖的库
```
- springboot v2.4.1
- mybatis v1.6
```

#### TODO list
- 车辆信息注册登记
- 车辆和或购买人信息写入
- 车辆的整备信息写入
- 二手车信息读取

#### 待完成的内容：
https://www.cnblogs.com/cjsblog/p/9152455.html
https://blog.csdn.net/weixin_43461520/article/details/107941983


远程主机：
http://42.192.121.179/

连接方式：raoshihao

导入数据库：
source
导出数据库
mysqldump


```roomsql
create table operation_log(user_code varchar(20), ip varchar(255), type varchar(255), description varchar(255), model varchar(255), operation_time datetime, result varchar(255), params varchar(512));
```


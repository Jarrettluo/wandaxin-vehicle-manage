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

添加销售模块的sql字段
添加浮点字段
```roomsql
private float mortgageRebate; // 贷款返佣
private float insuranceRefund; // 保险退费
```
```roomsql
ALTER TABLE TEST_TABLE ADD B INT;
alter table sale_item add mortgage_rebate float(8, 2);
alter table sale_item add insurance_refund float(8, 2);
```

<<<<<<< HEAD
```shell
lsof -i:8081
```
$ kill -s 9 1827

nohup java -jar XXX.jar >temp.txt &

sudo scp -r wdx /var/www/html/
=======


>>>>>>> 2f602607ca095da331a4dcfe4a3690877288ac5e
```roomsql
![./img_1.png](./img_1.png)
```


```roomsql
CREATE TABLE IF NOT EXISTS `company`(
    `id` int NOT NULL AUTO_INCREMENT,
    `company_name` VARCHAR(100) NOT NULL,
    `abbreviation` VARCHAR(40) NOT NULL,
    `valid_account` INT UNSIGNED DEFAULT 1,
    `expiration_time` datetime NOT NULL,
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间\n',
    `modify_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
   PRIMARY KEY ( `id` )
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
```
公司名称为唯一：
修改：
```roomsql
ALTER TABLE `company` ADD UNIQUE ( `company_name`); 
```

问题： 添加Unique以后数据会丢失吗？

```roomsql
insert into company (company_name, abbreviation, valid_account, expiration_time) values ("成都万达鑫二手车经销有限公司"
, "万达鑫", 4, "2022-10-1 12:00:00");
```

用户表 ![img_3.png](img_3.png)

新增 类型： type: varchar(20)
外键 公司id： company_id: int

```roomsql
ALTER TABLE user ADD type VARCHAR(16) not null default "admin";
```
```roomsql
ALTER TABLE user ADD company_id int not null; // 增加字段
```
用户表的id修改为自增
```roomsql
alter table user modify id int(11) auto_increment;
```
用户名不能重复，修改为独一无二

ALTER TABLE `user` ADD UNIQUE ( `username`);

```roomsql
ALTER TABLE <旧表名> RENAME [TO] <新表名>;
```

```roomsql
ALTER TABLE <表名> CHANGE <旧字段名> <新字段名> <新数据类型>; // 修改字段
```

将company_id的值改为1，代表全是第一家公司的。
```roomsql
UPDATE user SET company_id = 1 WHERE LastName = 0;
```

三张表需要添加company_id
vehicle_information\ sale_item \ operation_log \


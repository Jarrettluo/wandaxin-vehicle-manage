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


```roomsql
ALTER TABLE `user` ADD UNIQUE ( `username`);
```


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



----
操作步骤：
1、先导出生产环境的数据库；
2、生产环境的数据库增加修改字段；
3、增加company；
4、修改user；
5、修改vehicle_information
6、修改sale_item
7、修改operation_log
8、提交到生产环境中去
9、修改代码参数


1、导出数据到本地
```roomsql
mysqldump -uroot -proot --all-databases >/tmp/all.sql
scp ubuntu@129.211.165.193:/home/ubuntu/wdx/test_db_0718.sql ./
```
2、修改sql导入sql
```roomsql
mysql -uroot -pLJR199308 < test_db_0718.sql
```
3、修改company
```roomsql
先导出，再导入
msyq
show create table company;

CREATE TABLE `company` (
    ->   `id` int NOT NULL AUTO_INCREMENT,
    ->   `company_name` varchar(100) NOT NULL,
    ->   `abbreviation` varchar(40) NOT NULL,
    ->   `valid_account` int unsigned DEFAULT '1',
    ->   `expiration_time` datetime NOT NULL,
    ->   `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间\n',
    ->   `modify_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    ->   PRIMARY KEY (`id`),
    ->   UNIQUE KEY `company_name` (`company_name`)
    -> ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb3
    -> ;

```
往company中添加第一条数据
4、修改User表
```roomsql
ALTER TABLE user ADD type VARCHAR(16) not null default "admin";
ALTER TABLE user ADD company_id int not null default 1;
alter table user modify id int(11) auto_increment;
```

5\6\7
```roomsql
ALTER TABLE vehicle_information ADD company_id int not null default 1;
ALTER TABLE sale_item ADD company_id int not null default 1;
ALTER TABLE operation_log ADD company_id int not null default 1;
```
8、导出修改好的库
```roomsql
mysqldump -uroot -pLJR199308 --databases test_db >wan_da_xin_prod_20210718.sql;

```
在服务器上新建数据库后导入 wan_da_xin_prod
```roomsql
// mysql -uroot -p123456 wan_da_xin_prod < wan_da_xin_prod_20210718.sql
```
source ./wan_da_xin_pr....sql //该种方法有问题！

mysqldump -P端口 -u用户名 -p密码 --databases db1 >/tmp/db1.sql


直接修改库名称


----
2022年1月6日更新表
增加车辆的描述信息
vehicle_description
```roomsql
CREATE TABLE IF NOT EXISTS `vehicle_description`(
    `id` int NOT NULL AUTO_INCREMENT,
    `vehicle_id` INT NOT NULL,
    `vin` VARCHAR(18) NOT NULL,
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间\n',
    `modify_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
   PRIMARY KEY ( `id` )
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
```

----
2022年1月10日更新表
```roomsql
CREATE TABLE IF NOT EXISTS `preparatory_item`(
    `id` int NOT NULL AUTO_INCREMENT,
    `company_id` INT NOT NULL,
    `name` VARCHAR(18) NOT NULL,
    `type` VARCHAR(18) NOT NULL default 'user',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间\n',
    `modify_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
   PRIMARY KEY ( `id` )
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
```
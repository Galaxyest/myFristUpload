1.SQL优化：
- 避免使用SELECT*，尽量写全表名
- 避免使用SQL语句小写，尽量全部大写
- 使用union all代替union
- 分页查询limit的时候，如果查询数据量过大，使用between和判断进行筛选
- 使用外连接代替子连接
- 模糊查询的时候前面尽量避免使用"%"
- insert插入的时候选择批量插入而不是循环插入

---
- SQL：
- 添加：
添加多行：INSERT INTO 表名(列名1, 列名2, 列名3) VALUES(值1, 值2, 值3), (值1, 值2, 值3), (值1, 值2, 值3), (值1, 值2, 值3);
```sql
-- 单行添加，添加部分列，没有添加的列必须允许为NULL
INSERT INTO t_jobs(JOB_ID, JOB_TITLE) VALUES ('AAA', 'AAAAAAAA');
-- 单行添加，添加全部列，可以指定所有的列名，也可以不指定列名
INSERT INTO t_jobs(JOB_ID, JOB_TITLE, MIN_SALARY, MAX_SALARY) VALUES ('BBB', 'BBBBBBB', 2000, 20000);
INSERT INTO t_jobs VALUES ('CCC', 'CCCCCCC', 3000, 30000);
```
```sql
-- 添加多行(批量插入)
INSERT INTO t_jobs(JOB_ID, JOB_TITLE, MIN_SALARY, MAX_SALARY) 
VALUES 
('A1', 'A1A1', 2000, 20000), 
('A2', 'A2A1', 2000, 20000), 
('A3', 'A3A1', 2000, 20000),
('A4', 'A4A1', 2000, 20000),
('A5', 'A5A1', 2000, 20000);
```

- 删除：DELETE FROM 表名 WHERE 条件
```sql
TRUNCATE TABLE t_jobs;
```
- 修改：UPDATE 表名 SET 列名1 = 值1, 列名2 = 值2, 列名3 = 值3 WHERE 条件
```sql
-- 根据条件判断修改的行数
-- 修改1行
UPDATE t_jobs SET JOB_TITLE = 'B1B1', MIN_SALARY = 3000, MAX_SALARY = 15000 WHERE JOB_ID = 'BBB';
-- 修改多行
UPDATE t_jobs SET JOB_TITLE = 'B1B1', MIN_SALARY = 3000, MAX_SALARY = 15000 WHERE JOB_TITLE LIKE '%A1';
```
- 查询：SELECT 列名 FROM 表名 WHERE 条件
```sql
SELECT EMPLOYEE_ID, FIRST_NAME, LAST_NAME, SALARY FROM t_employees WHERE SALARY = 11000;
SELECT EMPLOYEE_ID, FIRST_NAME, LAST_NAME, SALARY FROM t_employees WHERE FIRST_NAME = 'Ellen';
```
- 逻辑判断
```sql
-- 逻辑判断使用AND、OR、NOT
SELECT EMPLOYEE_ID, FIRST_NAME, LAST_NAME, SALARY FROM t_employees WHERE SALARY >=12000 AND FIRST_NAME = 'Steven';
-- 薪资不大于等于12000，相当于取反
SELECT EMPLOYEE_ID, FIRST_NAME, LAST_NAME, SALARY FROM t_employees WHERE NOT SALARY >=12000;
```
- 区间判断
```sql
-- 区间判断：使用 BETWEEN 小值 AND 大值。
SELECT EMPLOYEE_ID, FIRST_NAME, LAST_NAME, SALARY FROM t_employees WHERE  SALARY BETWEEN 9000 AND 10000;
-- 相当于
SELECT EMPLOYEE_ID, FIRST_NAME, LAST_NAME, SALARY FROM t_employees WHERE SALARY >= 9000  AND SALARY <= 10000;
```
- Null判断
```sql
-- 要判断Null，需要使用IS NULL或者IS NOT NULL
-- 注意:不能使用 = NULL
SELECT EMPLOYEE_ID, FIRST_NAME, LAST_NAME, SALARY, MANAGER_ID FROM t_employees WHERE MANAGER_ID IS NULL;
SELECT EMPLOYEE_ID, FIRST_NAME, LAST_NAME, SALARY, MANAGER_ID FROM t_employees WHERE MANAGER_ID IS NOT NULL;
```
- 枚举判断
```sql
-- 当需要查询多个=值时，可以使用IN来枚举。
SELECT EMPLOYEE_ID, FIRST_NAME, LAST_NAME, SALARY, MANAGER_ID FROM t_employees 
WHERE EMPLOYEE_ID = '100' OR EMPLOYEE_ID = '102' OR EMPLOYEE_ID = '104';
-- 可以写为
SELECT EMPLOYEE_ID, FIRST_NAME, LAST_NAME, SALARY, MANAGER_ID FROM t_employees 
WHERE EMPLOYEE_ID IN ('100', '102', '104');
```
- 模糊查询
```sql
-- 匹配所有以L开头的
SELECT EMPLOYEE_ID, FIRST_NAME, LAST_NAME, SALARY, MANAGER_ID FROM t_employees 
WHERE FIRST_NAME LIKE 'L%';
-- 匹配以L开头的，长度为4的
SELECT EMPLOYEE_ID, FIRST_NAME, LAST_NAME, SALARY, MANAGER_ID FROM t_employees 
WHERE FIRST_NAME LIKE 'L___';
```
- 分支查询
```sql
--CASE
--  	WHEN 条件1 THEN 结果1
--      WHEN 条件2 THEN 结果2
--  	WHEN 条件3 THEN 结果3
--  	ELSE 结果4
--  END;
SELECT EMPLOYEE_ID, FIRST_NAME, LAST_NAME, SALARY, 
CASE
	WHEN SALARY >= 20000 THEN 'A'
	WHEN SALARY >= 15000 AND SALARY < 20000 THEN 'B'
	WHEN SALARY >= 10000 AND SALARY < 15000 THEN 'C'
	WHEN SALARY >= 6000 AND SALARY < 10000 THEN 'D'
	ELSE 'E'
END AS 级别
 FROM t_employees ORDER BY SALARY DESC;
```
- 时间函数查询
```sql
-- 当前时间
SELECT SYSDATE();
SELECT NOW();
-- 当前日期
SELECT CURDATE();
SELECT CURRENT_DATE();
-- 当前时间
SELECT CURTIME();
SELECT CURRENT_TIME();
-- 获得第几周
SELECT WEEK('2021-12-31');
SELECT WEEK(NOW());
-- 获得年份
SELECT YEAR('2021-12-31');
-- 查询2000年之后出生的员工，用作条件
SELECT * FROM t_employees WHERE YEAR(HIRE_DATE) >= 2000;
SELECT * FROM t_employees WHERE HIRE_DATE >= '2000-02-01';
-- 获得小时，分钟，秒
SELECT HOUR(CURTIME());
SELECT MINUTE(CURTIME());
SELECT SECOND(CURTIME());
-- 获取两个日期之间相隔的天数，前面的日期-后面的日期
SELECT DATEDIFF('2020-12-12','2021-11-11');
-- 获取3天以后的日期
SELECT ADDDATE('2020-12-12', 3);
```
- 聚合查询
```sql
--常用的聚合函数：
-- SUM：求和
-- AVG：求平均数
-- MAX：求最大值
-- MIN：求最小值
-- COUNT：计数
-- 统计数量
SELECT COUNT(EMPLOYEE_ID) FROM t_employees;
SELECT COUNT(*) FROM t_employees;
SELECT COUNT(1) FROM t_employees;
-- 求和
SELECT SUM(SALARY) FROM t_employees;
-- 求平均
SELECT AVG(SALARY) FROM t_employees;
-- 求最大值
SELECT MAX(SALARY) FROM t_employees;
-- 求最小值
SELECT MIN(SALARY) FROM t_employees;
-- 一起查询
SELECT SUM(SALARY), MAX(SALARY), MIN(SALARY),AVG(SALARY),COUNT(1) FROM t_employees;
-- 不正确的写法（不能查询其他字段）
SELECT EMPLOYEE_ID,FIRST_NAME,LAST_NAME,MIN(SALARY) FROM t_employees;
```
- 分组查询(过滤查询，group up可以分组也会过滤掉重复的字段)
```sql
-- 按部门分组，求每个部门的平均薪资
SELECT DEPARTMENT_ID, AVG(SALARY) FROM t_employees GROUP BY DEPARTMENT_ID;
-- 按部门分组，求每个部门的最高薪资
SELECT DEPARTMENT_ID, MAX(SALARY) FROM t_employees GROUP BY DEPARTMENT_ID;
-- 按部门分组，求每个部门的最低薪资
SELECT DEPARTMENT_ID, MIN(SALARY) FROM t_employees GROUP BY DEPARTMENT_ID;
-- 按部门分组，求每个部门的总薪资
SELECT DEPARTMENT_ID, SUM(SALARY) FROM t_employees GROUP BY DEPARTMENT_ID;
-- 按部门分组，求每个部门的人数
SELECT DEPARTMENT_ID, COUNT(1) FROM t_employees GROUP BY DEPARTMENT_ID;
SELECT DEPARTMENT_ID, COUNT(1), AVG(SALARY),MAX(SALARY),MIN(SALARY),SUM(SALARY)  FROM t_employees GROUP BY DEPARTMENT_ID;
```
- 分组过滤查询查询
```sql
-- 使用having关键字，
--注意：一般情况下，having关键字是要以聚合函数作为条件时使用，如果条件没有聚合函数，应该将该条件写在where，而不应该使用having。
-- 查询部门最高薪资大于10000的部门以及其最高薪资
SELECT DEPARTMENT_ID, MAX(SALARY) FROM t_employees GROUP BY DEPARTMENT_ID HAVING MAX(SALARY) > 10000;
```
- 分页查询（限定查询）
```sql
-- 显示前面30条
SELECT * FROM t_employees LIMIT 30;
-- 跳过30条，显示10条，即显示第31-40条
SELECT * FROM t_employees LIMIT 30, 10;
```
---
- 基础查询总结
- SQL的编写顺序：
- SELECT 列名
- FROM 表名
- WHERE 条件
- GROUP BY 分组列
- HAVING 过滤条件
- ORDER BY 排序列 ASC\DESC
- LIMIT skip, size
---
语法：


- 内连接
```sql
--SELECT 列名 FROM 表1 
--INNER JOIN 表2
--ON 连接条件
--INNER JOIN 表3
--ON 连接条件
SELECT e.EMPLOYEE_ID, e.FIRST_NAME, e.LAST_NAME, e.DEPARTMENT_ID, d.DEPARTMENT_NAME 
FROM t_employees e
INNER JOIN 
t_departments d 
ON e.DEPARTMENT_ID = d.DEPARTMENT_ID;
```
---
- 外连接
```sql
SELECT e.EMPLOYEE_ID, e.FIRST_NAME, e.LAST_NAME, e.DEPARTMENT_ID, d.DEPARTMENT_NAME 
FROM t_employees e
LEFT JOIN 
t_departments d 
ON e.DEPARTMENT_ID = d.DEPARTMENT_ID;
```
---






1.redis的存储方式：
key-value方式进行存储
---
2.redis的数据类型：

String,hash(Map),list,set,short set(zset)

---

持久化机制：
1.RDB（redis database）是Redis默认的持久化机制

- RDB持久化文件，速度比较快，而且存储的是一个二进制的文件，传输起来很方便。
- RDB持久化的时机：
  - save 900 1：在900秒内，有1个key改变了，就执行RDB持久化。
  - save 300 10：在300秒内，有10个key改变了，就执行RDB持久化。
  - save 60 10000：在60秒内，有10000个key改变了，就执行RDB持久化。
- RDB无法保证数据的绝对安全。

2.AOF:
AOF（append of file）持久化机制默认是关闭的，Redis官方推荐同时开启RDB和AOF持久化，更安全，避免数据丢失。

- AOF持久化的速度，相对RDB较慢的，存储的是一个文本文件，到了后期文件会比较大，传输困难。
- AOF持久化时机。
  - appendfsync always：每执行一个写操作，立即持久化到AOF文件中，性能比较低。
  - appendfsync everysec：每秒执行一次持久化。
  - appendfsync no：会根据你的操作系统不同，环境的不同，在一定时间内执行一次持久化。
- AOF相对RDB更安全，推荐同时开启AOF和RDB。

应该同时开启AOF和RDB机制
-
---
缓存穿透：查询的数据redis里面没有，数据库中也没有（id是整形的话把id最大值放在redis里，查询之前比较一下，不是整形的话则把所有的id放入redis，查询之前先看redis里有没有）

缓存击穿：redis里的缓存数据突然大量到期，造成大量的访问去访问数据库，造成数据库的宕机。（1.加锁，2.取消生存时间）

缓存雪崩：大量的redis缓存同时到期，造成大量的访问最终去访问数据库，导致数据库宕机。（将缓存中的生存时间去掉，设置为30~60s的一个随机数）


缓存倾斜：热点数据放在了一个redis上，导致当前redis压力过大无法承受，最终宕机。（搭建主从节点）

---
3.String 常用命令
```
#1.  添加值
set key value

#2. 取值
get key

#3. 批量操作
mset key value [key value...]
mget key [key...]

#4. 自增命令（自增1）
incr key 

#5. 自减命令（自减1）
decr key

#6. 自增或自减指定数量
incrby key increment
decrby key increment

#7. 设置值的同时，指定生存时间（每次向Redis中添加数据时，尽量都设置上生存时间）
setex key second value

#8. 设置值，如果当前key不存在的话（如果这个key存在，什么事都不做，如果这个key不存在，和set命令一样）
setnx key value

#9. 在key对应的value后，追加内容
append key value

#10. 查看value字符串的长度
strlen key
```
4.list常用命令：
```
#1. 存储数据（从左侧插入数据，从右侧插入数据）
lpush key value [value ...]
rpush key value [value ...]

#2. 存储数据（如果key不存在，什么事都不做，如果key存在，但是不是list结构，什么都不做）
lpushx key value
rpushx key value

#3. 修改数据（在存储数据时，指定好你的索引位置,覆盖之前索引位置的数据，index超出整个列表的长度，也会失败）
lset key index value

#4. 弹栈方式获取数据（左侧弹出数据，从右侧弹出数据）
lpop key
rpop key

#5. 获取指定索引范围的数据（start从0开始，stop输入-1，代表最后一个，-2代表倒数第二个）
lrange key start stop

#6. 获取指定索引位置的数据
lindex key index

#7. 获取整个列表的长度
llen key

#8. 删除列表中的数据（他是删除当前列表中的count个value值，count > 0从左侧向右侧删除，count < 0从右侧向左侧删除，count == 0删除列表中全部的value）
lrem key count value

#9. 保留列表中的数据（保留你指定索引范围内的数据，超过整个索引范围被移除掉）
ltrim key start stop

#10. 将一个列表中最后的一个数据，插入到另外一个列表的头部位置
rpoplpush list1 list2
```
5.Hash常用命令：
```
#1. 存储数据
hset key field value

#2. 获取数据
hget key field

#3. 批量操作
hmset key field value [field value ...]
hmget key field [field ...]

#4. 自增（指定自增的值）
hincrby key field increment

#5. 设置值（如果key-field不存在，那么就正常添加，如果存在，什么事都不做）
hsetnx key field value

#6. 检查field是否存在
hexists key field 

#7. 删除key对应的field，可以删除多个
hdel key field [field ...]

#8. 获取当前hash结构中的全部field和value
hgetall key

#9. 获取当前hash结构中的全部field
hkeys key

#10. 获取当前hash结构中的全部value
hvals key

#11. 获取当前hash结构中field的数量
hlen key
```
6.set常用命令：
```
#1. 存储数据
sadd key member [member ...]

#2. 获取数据（获取全部数据）
smembers key

#3. 随机获取一个数据（获取的同时，移除数据，count默认为1，代表弹出数据的数量）
spop key [count]

#4. 交集（取多个set集合交集）
sinter set1 set2 ...

#5. 并集（获取全部集合中的数据）
sunion set1 set2 ...

#6. 差集（获取多个集合中不一样的数据）
sdiff set1 set2 ...

# 7. 删除数据
srem key member [member ...]

# 8. 查看当前的set集合中是否包含这个值
sismember key member
```
7.short set常用命令：
```
#1. 添加数据(score必须是数值。member不允许重复的。)
zadd key score member [score member ...]

#2. 修改member的分数（如果member是存在于key中的，正常增加分数，如果memeber不存在，这个命令就相当于zadd）
zincrby key increment member

#3. 查看指定的member的分数
zscore key member

#4. 获取zset中数据的数量
zcard key

#5. 根据score的范围查询member数量
zcount key min max

#6. 删除zset中的成员
zrem key member [member...]

#7. 根据分数从小到大排序，获取指定范围内的数据（withscores如果添加这个参数，那么会返回member对应的分数）
zrange key start stop [withscores]

#8. 根据分数从大到小排序，获取指定范围内的数据（withscores如果添加这个参数，那么会返回member对应的分数）
zrevrange key start stop [withscores]

#9. 根据分数的返回去获取member(withscores代表同时返回score，添加limit，就和MySQL中一样，如果不希望等于min或者max的值被查询出来可以采用 ‘(分数’ 相当于 < 但是不等于的方式，最大值和最小值使用+inf和-inf来标识)
zrangebyscore key min max [withscores] [limit offset count]
#例如：不包含20
zrangebyscore z1 (20 40
#不包含40
zrangebyscore z1 20 (40
#都不包含
zrangebyscore z1 (20 (40
```
8.key常用的命令：
```
#1. 查看Redis中的全部的key（pattern：* ，xxx*，*xxx）
keys pattern

#2. 查看某一个key是否存在（1 - key存在，0 - key不存在）
exists key

#3. 删除key
del key [key ...]

#4. 设置key的生存时间，单位为秒，单位为毫秒,设置还能活多久
expire key second
pexpire key milliseconds

#5. 设置key的生存时间，单位为秒，单位为毫秒,设置能活到什么时间点
expireat key timestamp
pexpireat key milliseconds

#6. 查看key的剩余生存时间,单位为秒，单位为毫秒（-2 - 当前key不存在，-1 - 当前key没有设置生存时间，具体剩余的生存时间）
ttl key
pttl key

#7. 移除key的生存时间（1 - 移除成功，0 - key不存在生存时间，key不存在）
persist key

#8. 选择操作的库
select 0~15

#9. 移动key到另外一个库中
move key db

#10. 登录
auth password
```
9.库常用命令：
```
#1. 清空当前所在的数据库
flushdb

#2. 清空全部数据库
flushall

#3. 查看当前数据库中有多少个key
dbsize

#4. 查看最后一次操作的时间
lastsave

#5. 实时监控Redis服务接收到的命令
monitor
```
10.java连接redis导入的依赖：
```xml
<dependencies>
    <!--    1、 Jedis-->
    <dependency>
        <groupId>redis.clients</groupId>
        <artifactId>jedis</artifactId>
        <version>2.9.1</version>
    </dependency>
    <!--    2、 Junit测试-->
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.12</version>
    </dependency>
    <!--    3、 Lombok-->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>1.18.6</version>
    </dependency>
</dependencies>
```

1.导入依赖：
```xml
<dependencies>
    <dependency>
        <groupId>org.mybatis</groupId>
        <artifactId>mybatis</artifactId>
        <version>3.4.6</version>
    </dependency>
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.12</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>5.1.40</version>
    </dependency>
</dependencies>
```
2.编写核心配置文件：
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">

<configuration>
    <!--默认开发环境-->
    <environments default="development">
        <!--可以配置多个数据库的连接环境，此处表示配置开发环境-->
        <environment id="development">
            <!--直接使用JDBC提供的事务管理-->
            <transactionManager type="JDBC"></transactionManager>
            <!--使用mybatis自带的连接池-->
            <dataSource type="POOLED">
                <!--四要素-->
                <property name="driver" value="com.mysql.jdbc.Driver"/>
                <property name="url"
                          value="jdbc:mysql://localhost:3306/myshop?useUnicode=true&amp;characterEncoding=utf-8"/>
                <property name="username" value="root"/>
                <property name="password" value="root"/>
            </dataSource>
        </environment>
    </environments>

    <!--关联所有的mapper文件-->
    <mappers>
        <mapper resource="ProductMapper.xml"></mapper>
    </mappers>
</configuration>
```
3.编写映射文件：
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!--命名空间，-->
<mapper namespace="a">
    <!--查询标签，编写查询语句-->
    <select id="b" resultType="java.lang.Long">
      SELECT COUNT(1) FROM product
    </select>
</mapper>
```
4.测试链接：
```java
public class ProductDAOTest {
    private SqlSessionFactory factory;
    // 在下面的测试方法调用之前调用
    @Before
    public void before(){
        try {
            // 读取核心配置文件
            InputStream inputStream = Resources.getResourceAsStream("mybatisCfg.xml");
            // 创建连接工厂(会话工厂)、工厂模式
            factory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCount(){
        // 获得连接
        SqlSession session = factory.openSession();
        // 数据库操作
        Long count = session.selectOne("a.b");
        System.out.println(count);
        // 关闭连接
        session.close();
    }
}
```
5.#和$的区别：
  - "#" 相当于占位符？，可以防止sql注入
  - "#"在传入参数时，需要对应对象的属性或者map的key，当只有一个参数时，可以随意
  - "$"相当于字符串拼接，有sql注入的风险
  - "$"在传入参数，只能对应对象的属性或map的key，即使只有一个参数，也需要传入对象或者map，可以用@Param注解自动封装map
  - "$"的使用场景：一般用在需要动态传入表名或列名等关键内容时
---
6.动态SQL：

（1）if
```sql
<!--在动态sql的标签中pname名称，必须是一个对象的属性名称或者是map对应的key-->
<select id="findAll" resultMap="productMap">
    SELECT
    <include refid="productColumns"></include>
    FROM product
    WHERE 1 = 1
    <if test="pname != null">
        AND p_name LIKE #{pname}
    </if>
</select>
```
(2)choose、when、otherwise
```sql
<select id="findActiveBlogLike"
     resultType="Blog">
  SELECT * FROM BLOG WHERE state = ‘ACTIVE’
  <choose>
    <when test="title != null">
      AND title like #{title}
    </when>
    <when test="author != null and author.name != null">
      AND author_name like #{author.name}
    </when>
    <otherwise>
      AND featured = 1
    </otherwise>
  </choose>
</select>
```
(3) where、set
```sql
<select id="findActiveBlogLike"
     resultType="Blog">
  SELECT * FROM BLOG
  <where>
    <if test="state != null">
         state = #{state}
    </if>
    <if test="title != null">
        AND title like #{title}
    </if>
    <if test="author != null and author.name != null">
        AND author_name like #{author.name}
    </if>
  </where>
</select>
```
(4)trim
```sql
<!--trim表示处理拼接后的字符串两边，
    prefix表示在字符串前面加上内容
    suffix表示在字符串后面加上内容
    prefixOverrides表示将字符串前面的内容去掉
    suffixOverrides表示将字符串最后的内容去掉
    -->
<insert id="save">
    INSERT INTO product
    <trim suffixOverrides="," prefix="(" suffix=")VALUES (">
        <if test="type != null and type.tid != null">
            t_id,
        </if>
        <if test="pname != null">
            p_name,
        </if>
        <if test="ptime != null">
            p_time,
        </if>
    </trim>
    <trim suffixOverrides="," suffix=")">
        <if test="type != null and type.tid != null">
            #{type.tid},
        </if>
        <if test="pname != null">
            #{pname},
        </if>
        <if test="ptime != null">
            #{ptime},
        </if>
    </trim>
</insert>
```
(5)foreach
```sql
<!--
 collection：如果只有一个数组或集合参数，此处写参数的类型，集合List写list，也可以写collection，数组写array,如果是一个对象类型，对象中有一个集合或数组的属性，此处写属性名称
open：表示拼接字符串开头
close：表示拼接字符串结尾
item：集合中的元素临时变量名称
index：下标
separator：分割符号
-->
<select id="findAllByIds" resultMap="productMap">
    SELECT
    <include refid="productColumns"></include>
    FROM product
    WHERE
    p_id IN
    <foreach collection="list" open="(" close=")" item="o" separator=",">
        #{o}
    </foreach>
</select>
```

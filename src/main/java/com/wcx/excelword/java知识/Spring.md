常见问题：

AOP和IOC分别什么意思？原理？

AOP即为面向切面的编程，IOC即为控制反转。

AOP是一种概念。

IOC则是通过控制反转CGlib来完成的。使其静态织入程序，降低程序的耦合度。

---
1. Spring解决的问题
  - 方便解耦，简化开发：Spring 就是一个大工厂，可以将所有对象创建和依赖关系维护，交给 Spring 管理
  - AOP 编程的支持：Spring 提供面向切面编程，可以方便的实现对程序进行权限拦截、运行监控等功能
  - 声明式事务的支持：只需要通过配置就可以完成对事务的管理，而无需手动编程
  - 方便程序的测试：Spring 对 Junit4 支持，可以通过注解方便的测试 Spring 程序
  - 方便集成各种优秀框架：Spring 不排斥各种优秀的开源框架，其内部提供了对各种优秀框架（如：Struts、Hibernate、MyBatis、Quartz 等）的直接支持
  - 降低 JavaEE API 的使用难度：Spring对 JavaEE 开发中非常难用的API（JDBC、JavaMail、远程调用等），都提供了封装，使这些 API 应用难度大大降低
---

2.Spring组成图:
![](https://note.youdao.com/yws/public/resource/761be3201af0f3f2d4bc97f763ac08c5/xmlnote/22C1D1F95C774ED8A6D9E8153695E571/4352)
---

3.core核心模块：
- spring-core：依赖注入IoC与DI的最基本实现
- spring-beans：Bean工厂与bean的装配
- spring-context：spring的context上下文即IoC容器
- spring-context-support
- spring-expression：spring表达式语言
---

4.对象的几种创建方式：
- 静态工厂：
```java
/**
 * Person工厂类
 */
public class PersonFactory {
    public static Person  createPerson(){
        System.out.println("静态工厂创建Person");
        return  new Person();
    }
}
```
配置对应的xml文件
```xml
<bean id="person" class="com.qf.day55.util.PersonFactory" factory-method="createPerson"></bean>
```
---
5.非静态工厂
```java
/**
  * Person工厂类
  */
public class PersonFactory {
   /**
     * 非静态创建对象
     * @return Person
     */
 public  Person createPerson1(){
    System.out.println("非静态工厂创建Person");
    return new Person();
 } }  
```
配置文件：spring.xml
```xml
<bean id="personFactory" class="com.qf.day55.util.PersonFactory"></bean>
 <bean id="person" factory-bean="personFactory" factory-method="createPerson1"/>
```

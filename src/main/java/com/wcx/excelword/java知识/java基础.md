1.对象的创建过程
-
步骤：
- 分配内存空间
- 属性的初始值和默认值
- 构造方法调用
- 将对象的地址赋值到变量中
---
2.当构造方法中，变量名与属性相同时，应该使用this关键字调用属性。
-
---
3.面向对象的语言一般应该具备三大特征：封装、继承和多态。
-
封装：
- （1）属性封装：
- 使用private关键字，将属性隐藏在类的内部，让外部不可访问，并添加一些外部可以访问属性的方法，来控制属性的修改和读取。
---
- （2）将不需要暴露的方法隐藏（private），将需要外部使用的方法公开。
- 继承：
- class 子类 extends 父类
- java中继承的特点：
- 单继承（一个类只能继承一个父类），但是可以多级继承，属性和方法逐级叠加。
- 注意：所有的类自动继承Object类。Object作为所有类的顶层父类。
- class A{}
- class A extends Object{}
- 上面两种写法使用上基本没有区别，但是直接继承Object会让该类不能再继承其他类，而间接继承Object还可以继承其他类。
- 构造方法只能创建本类的对象，视作不可继承。
- 使用private修饰的属性和方法，只能本类可见，在子类中无法直接访问，视作不可继承。
- 当没有访问修饰符修饰的属性和方法时，该访问修饰符为默认，只能在同一个包中访问，如果子类不在同一个包中，无法直接访问，视作不可继承。

- public：同一工程下
- protected:非通包下的子类
- default:同包
- private：本类

 - @override：重写，方法名称、参数列表、返回值类型、异常的声明必须与父类相同。访问修饰可以比父类更广。

 - @overload：重载，

- 当使用父类的引用去引用子类的对象时，会呈现多种状态，称为多态。

4.三大修饰符：
- abstract，抽象的。修饰类（抽象类），修饰方法（抽象方法，表示必须重写）
- final（不能和abstract一起使用）：修饰类（表示该类不能被继承），修饰方法（表示该方法不能被重写），修饰属性（该属性必须在创建对象后，要有值），修饰变量（表示该变量一旦赋值，不能改变，称为常量）。
- static:修饰属性（表示该属性为静态属性，也叫类属性）， 修饰方法（表示该方法为静态方法，类方法，即直接使用类名调用的方法，不需要创建对象即可调用），修饰代码块（使用static修饰的代码块叫静态代码块。类加载时执行。而且只执行一次）。
---
5.接口与抽象类的区别：
-
- 相同：可以作为引用类型，不能创建对象，具有Object类方法。
- 不同：
     - 接口中定义的属性，即使不加任何修饰符，也是公有静态常量。
     - 定义的普通方法默认为公有抽象方法。
     - 不能定义构造方法
     - 不能定义静态代码块、动态代码块
---
6.常用类：
-
  - 成员内部类：不能定义方法和属性
  - 静态内部类：使用方式与普通类一样，一般在项目中使用静态内部类来体现封装的特征。
  - 局部内部类：不能定义静态成员和静态方法，不能在类前面添加private、public等关键字，必须在方法中定义后立即使用，否则出了作用域范围会无效
  - 匿名内部类：当需要将一个抽象类或者接口创建对象时，会要求继承该抽象类或者实现接口才可以创建对象。如果需要直接对该抽象类或接口创建对象，并且只使用一次，可以使用匿名内部类的方式。
---
7.集合：
-

- （1）List和Set：
- List的特点：有序、有下标，元素可以重复。
- Set的特点；无序，无下标，元素不可重复。
---
-（2）数组和连表：
- 数组的特点：长度固定，连续空间，类型相同，添加和删除相对较慢（可能涉及到扩容）、遍历速度快（连续空间）
- 链表的特点：无需开辟连续的空间，添加和删除较快，遍历速度较慢
---
- （3）集合工具类，对集合进行操作（排序、打乱）等，相当于Arrays对数组的操作。
- Collection和Collections的区别？
- Collection是List、Set集合的顶层接口
- Collections是一个工具类
---
- （4）HashMap的底层原理：
- Map以键值对的形式（Key，Value）进行存储，Key不可重复，Value可重复
- HashMap的底层由数组加链表的形式组成，它的默认扩容因子为0.75，当它的元素个数等于当前容器长度的0.75时，会扩容为原来的2倍。
第一次扩容时会初始化容器大小为16，再添加元素，
- 当某一个链上的元素大于等于8，且数组长度大于等于64时，便会树化成红黑树，如果链上的元素小于8或数组的元素小于6时，会以扩容代替树化。
- HashMap线程不安全，HashTable线程安全，但是效率低，且当前不推荐使用，HashTable会扩容为原来的2n+1倍。
- 当时现在不推荐使用HashTable，如果想要使用线程安全的HashMap，应该使用currentHashMap。

---
8.异常：
-
Throwable是一切异常或错误的顶层父类。
---
- Error：错误，主要指JVM、硬件等问题，无法通过程序代码解决。
- Exception：程序在运行过程中产生的问题，可以处理。
---
RuntimeException：运行时异常，未检查（非受检）异常，可以处理，可以不处理。
- 没有检查导致出现的异常，一般是程序员的bug，不需要通过异常处理，应该加上检查的语句。例如：除数为0异常（ArithmeticException）、下标越界异常（ArrayIndexOutOfBoundsException）、类型转换异常、空指针异常等。

CheckedException：非运行时（编译）异常，已检查（受检）异常，必须处理。

- 程序做了充分的检查，但是是否出现异常，由用户的使用方式决定，此类异常必须要处理。例如：文件找不到异常，时间格式不正确异常等。
---
- 异常抛出ex: public void modth()throws IOException,SQLException{}
---
- 自定义异常：一般继承自Exception，或Exception的子类，代表特定的问题。
- 语法：class MyException extends Exception{}
```java
public class LoginException extends Exception{
	public LoginException() {}
	
	public LoginException(String message) {
		super(message);
	}
}

public class Demo{
	public static void main(String[] args) throws LoginException {
		login("aaa", "bbb");
	}
	
	public static void login(String username, String password) throws LoginException {
		if(username.equals("zhangsan") && password.equals("666666")) {
			System.out.println("登录成功");
		}else {
			throw new LoginException("10001");
		}
	}
}
```

- 异常捕获ex:try-catch-finally
```java
class demo{
public static void main(String[] args){
      try{
        int i = 0 ;
        }catch (IndexOutOfBoundsException e){
          e.printStackTrace();
        }finally{
          //最终执行代码块（无论是否抛出异常都会执行的区域）
        }
    }
}
```
----
9.多线程：
-
一、多线程的创建方式：
- 继承Thread类，重写run方法
- 实现Runnable接口，实现.start()方法
- 通过Callable接口和future来实现
- 从线程池中获取线程
---
二、线程常用方法：
- sleep方法：指让线程进入休眠状态，直到休眠的时间结束，进入就绪状态。
- yield方法：让渡，让线程放弃当前时间片，进入就绪状态。
- join方法：合并（A线程jion了B线程，A会等待B执行完再执行）
- wait方法：必须和synchronize关键字一起使用，不然会报错。
---
sleep和wait的区别：
- sleep进入限时等待，wait进入不限时等待
- sleep属于Thread类，而wait属于Object类
- sleep主动唤醒，wait被动唤醒
- sleep不会主动释放锁，wait会主动释放锁
---
10.锁
---
1.使用synchronized关键字。
- synchronized同步代码块
- synchronized同步方法
 
2.使用Lock()
与synchronized作用一致。但是比同步方法粒度更细，比同步代码块使用更灵活。功能更强大。

- lock()：加锁开始，相当于同步代码块的{
- unlock()：加锁结束，相当于同步代码块的}
- boolean tryLock()：尝试获取锁



线程阻塞：
- 当线程运行过程中，遇到了加锁的代码，需要去获取锁，在没有获取锁时，进入阻塞状态，需要等待持有锁的线程将加锁的代码执行完毕后，才能继续执行。

死锁：
当一个线程持有锁A，等待锁B，另一个线程持有锁B，等待锁A，两个线程都不会释放锁，此时也无法继续获得另一把锁，产生死锁。
---
死锁的四个必要条件：
- 互斥
- 请求与保持
- 不剥夺
- 循环等待
---
线程终止：
- 使用stop方法，不允许使用。stop是强行终止线程，相当于电脑在运行时断电，会导致一些隐患。
- 自定义标识，在线程运行过程中，定义一个线程运行时需要满足的标识，需要线程停止时，修改该标识，线程就会执行完毕。
- 使用interrupt。系统对线程中定义的标识，通过改变该标识的值，来停止线程。（休眠时不能被interrupt，会抛出异常）
---
synchronized：
- 可见性：执行到同步代码块中时，如果要访问临界资源，会去获取最新的值。
- 互斥性：执行到同步代码块时，会持有锁，其他线程如果也要执行同步代码块时，会等待前一个线程执行完毕同步代码块后释放锁才能去持有锁执行。

volatile修饰属性时，表示该属性具备有可见性。注意：该关键字并不能解决线程安全。

---
同步和异步：

同步是指在一个线程中顺序执行，需要等待第一个执行完成后继续执行第二个。

异步是指多个线程同时执行。

---
重入锁（公平锁）

ReentrantLock：Lock接口的常用实现类。

---
11.设计模式：
-
- 创建型模式：创建对象的不同方式。（5种）单例模式、工厂模式、原型模式等
- 结构型模式：多个对象形成一种新的结构。（7种）桥接模式、适配器模式、装饰模式
- 行为型模式：多个对象之间相互作用。（11种）命令模式、监听者模式、调停者模式、迭代模式

12.IO:
-
流的分类；

- 按JDK的版本分为：BIO（同步阻塞流），AIO（异步阻塞流），NIO（异步非阻塞流）
- 按方向分为：输入流和输出流（input和output）
- 按单位分为：字符流和字节流
- 按功能分为：节点流和过滤流

transient关键字用法：

- 当该关键字修饰属性时，表示该属性不参与序列化和反序列化。









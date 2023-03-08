1.导入依赖：
```xml
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.12</version>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-webmvc</artifactId>
    <version>4.3.18.RELEASE</version>
</dependency>
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.6</version>
    <scope>provided</scope>
</dependency>
<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>javax.servlet-api</artifactId>
    <version>3.1.0</version>
    <scope>provided</scope>
</dependency>
```

2.SpringMVC运行流程：
- 由客户端发送请求到DispatchServlet，DispatchServlet解析一个或多个HandMap，找到对应的Controller
- DispatchServlet将请求交给对应的Controller，Controller调用相应的Service对请求进行处理
- 处理完后返回一个或多个ViewResolver到视图解析器，视图解析器找到对应的视图
- 视图解析器对可视图进行渲染后返回给客户端
![](https://note.youdao.com/yws/public/resource/9751a944607f70dbd86b660d9d7f875f/3AFEF7D540224567839696E0BD09EAB3?ynotemdtimestamp=1597883714724)

3.拦截器与过滤器的区别：
  
  - 拦截器属于springmvc的组件，过滤器是servlet组件
  - 拦截器被dispatcherServlet调用，所以只能拦截被dispatcherServlet处理的请求，如果是jsp或其他servlet的请求，则不能被拦截。过滤器可以拦截所有的请求。
  - 拦截器粒度比较细，可以分三种不同的时机来拦截请求的执行流程，而过滤器只能在请求访问资源之前拦截。



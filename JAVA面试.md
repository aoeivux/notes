# 面试记录



## 排查JVM问题思路

正常运行系统：

1、可以使用**jmap**来查看JVM中各个区域的使用情况

2、可以通过**jstack**来查看线程中的运行情况，比如哪些线程阻塞、是否出现了死锁。

3、可以通过**jstat**命令来查看垃圾回收的情况，特别是**fullgc**，如果发现**fullgc**比较频繁，那么就得进行调优了

4、通过各个命令的结果，或者**jvisualvm**等工具来进行分析

5、首先，初步猜测频繁发送**fullgc**的原因，如果频繁发送**fullgc**但又一直没有出现内存溢出，那么表示**fullgc**实际上是回收了很多对象了，所以这些对象最好能在**yonggc**过程中就直接回收掉，避免这些对象进入到老年代，对应这种情况，就要考虑这些存活时间不长的对象是不是比较大，导致年轻代放不下，直接进入了老年代，尝试加大年轻代的大小，如果改完之后，**fullgc**减少，则证明修改有效。

6、还可以找到占用**CPU**最多的线程，定位到具体的方法，优化这个方法的执行，看是否能避免某些对象的创建，从而节省内存。

对于已经发生了**OOM**的系统：

1、一般生产系统中都会设置当系统发送**OOM**时，生成当时的dump**文件**

2、可以利用**jsisualvm**等工具来分析**dump**文件

3、根据**dump**文件找到异常的实例对象，和异常的线程（占用CPU高），定位到具体的代码

4、然后再进行详细的分析和调试

总之，调优就是一撮而就的，需要分析、推理、实践、总结、再分析，最终定位到具体的问题。



## Spring Security的原理

**Spring Security**的核心原理是**拦截器**（**Filter**）。**Spring Security**会在Web应用程序的**过滤器链**中添加一组**自定义的过滤器**，这些过滤器可以实现**身份验证**和**授权功能**。

当用户请求资源时，Spring Security会拦截请求，并使用配置的身份验证机制来验证用户身份。如果身份验证成功，Spring Security会授权用户访问所请求的资源。



Spring Security的具体工作原理如下：

1. 用户请求Web应用程序的受保护资源。

2. Spring Security拦截请求，并尝试获取用户的身份验证信息。

3. 如果用户没有经过身份验证，Spring Security将向用户显示一个登录页面，并要求用户提供有效的凭据（用户名和密码）。

4. 一旦用户提供了有效的凭据，Spring Security将验证这些凭据，并创建一个**已认证的安全上下文**（**SecurityContext**）对象。

5. 安全上下文对象包含已认证的用户信息，包括用户名、角色和授权信息。

6. 在接下来的请求中，Spring Security将使用已经认证的安全上下文对象来判断用户是否有权访问受保护的资源。

7. 如果用户有权访问资源，Spring Security将允许用户访问资源，否则将返回一个错误信息。



## Spring Security的密码加密

​		在 Spring Security 中对密码进行加密通常使用的是密码编码器（**PasswordEncoder**）。PasswordEncoder 的作用是将**明文密码加密成密文密码**，以便于存储和校验。Spring Security 提供了多种常见的密码编码器，例如 **BCryptPasswordEncoder**、**SCryptPasswordEncoder**、**StandardPasswordEncoder** 等。



1.在 pom.xml 文件中添加 BCryptPasswordEncoder 的依赖：

```xml
<dependency>
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-crypto</artifactId>
    <version>5.6.1</version>
</dependency>
```



2.在 Spring 配置文件中注入 BCryptPasswordEncoder：

```java
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
// ...

}
```

3.在使用密码的地方调用 **passwordEncoder.encode()** 方法对密码进行加密，例如注册时对密码进行加密：



```java
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public User register(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        // ...
        return user;
    }
// ...
}
```





## Spring 

### 对Spring IOC的了解？

**IOC（Inversion of Control:控制反转，也叫依赖注入）** 是一种设计思想，而不是一个具体的技术实现。IOC 的思想就是将原本在程序中手动创建对象的控制权，交由 Spring 框架来管理。不过， IOC 并非 Spring 特有，在其他语言中也有应用。

**为什么叫控制反转？**

- **控制**：指的是对象创建（实例化、管理）的权力
- **反转**：控制权交给外部环境（Spring 框架、IOC 容器）

![image-20240417122414918](.\assets\image-20240417122414918.png)

假设一个 Car 类需要一个 Engine 的对象，那么一般需要需要手动 new 一个 Engine，利用 IoC 就只需要定义一个私有的 Engine 类型的成员变量，容器会在运行时自动创建一个 Engine 的实例对象并将引用自动注入给成员变量。

将对象之间的相互依赖关系交给 IOC容器来管理，并由 IOC容器完成对象的注入。这样可以很大程度上简化应用的开发，把应用从复杂的依赖关系中解放出来。 IOC容器就像是一个工厂一样，当我们需要创建一个对象的时候，只需要配置好配置文件/注解即可，完全不用考虑对象是如何被创建出来的。

在实际项目中一个 Service 类可能依赖了很多其他的类，假如我们需要实例化这个 Service，你可能要每次都要搞清这个 Service 所有底层类的构造函数，这可能会把人逼疯。如果利用 IOC的话，你只需要配置好，然后在需要的地方引用就行了，这大大增加了项目的可维护性且降低了开发难度。

在 Spring 中， IOC容器是 Spring 用来实现 IOC的载体， **IOC容器实际上就是个 Map（key，value），Map 中存放的是各种对象。**

Spring 时代我们一般通过 XML 文件来配置 Bean，后来开发人员觉得 XML 文件来配置不太好，于是 SpringBoot 注解配置就慢慢开始流行起来。



### [将一个类声明为 Bean 的注解有哪些?](#将一个类声明为-bean-的注解有哪些)

- `@Component`：通用的注解，可标注任意类为 `Spring` 组件。如果一个 Bean 不知道属于哪个层，可以使用`@Component` 注解标注。
- `@Repository` : 对应持久层即 Dao 层，主要用于数据库相关操作。
- `@Service` : 对应服务层，主要涉及一些复杂的逻辑，需要用到 Dao 层。
- `@Controller` : 对应 Spring MVC 控制层，主要用于接受用户请求并调用 `Service` 层返回数据给前端页面。

### [@Component 和 @Bean 的区别是什么？](#component-和-bean-的区别是什么)

- `@Component` 注解作用于类，而`@Bean`注解作用于方法。
- `@Component`通常是通过类路径扫描来自动侦测以及自动装配到 Spring 容器中（我们可以使用 `@ComponentScan` 注解定义要扫描的路径从中找出标识了需要装配的类自动装配到 Spring 的 bean 容器中）。`@Bean` 注解通常是我们在标有该注解的方法中定义产生这个 bean,`@Bean`告诉了 Spring 这是某个类的实例，当我需要用它的时候还给我。
- `@Bean` 注解比 `@Component` 注解的自定义性更强，而且很多地方我们只能通过 `@Bean` 注解来注册 bean。比如当我们引用第三方库中的类需要装配到 `Spring`容器时，则只能通过 `@Bean`来实现



### [注入 Bean 的注解有哪些？](#注入-bean-的注解有哪些)

Spring 内置的 `@Autowired` 以及 JDK 内置的 `@Resource` 和 `@Inject` 都可以用于注入 Bean。

| Annotation   | Package                            | Source       |
| ------------ | ---------------------------------- | ------------ |
| `@Autowired` | `org.springframework.bean.factory` | Spring 2.5+  |
| `@Resource`  | `javax.annotation`                 | Java JSR-250 |
| `@Inject`    | `javax.inject`                     | Java JSR-330 |

`@Autowired` 和`@Resource`使用的比较多一些。

### 依赖注入的相关注解？

`@Autowired`：自动按类型注入，如果有多个匹配则按照指定 Bean 的 id 查找，查找不到会报错。

`@Resource` ：直接按照 Bean 的 id 注入，只能注入 Bean 类型。

`@Qualifier`：在自动按照类型注入的基础上再按照 Bean 的 id 注入，给变量注入时必须搭配 `@Autowired`，给方法注入时可单独使用。

`@Value` ：用于注入基本数据类型和 String 类型。

###  Bean 的生命周期？

在 IOC容器的初始化过程中会对 Bean 定义完成资源定位，加载读取配置并解析，最后将解析的 Bean 信息放在一个 HashMap 集合中。当 IOC容器初始化完成后，会进行对 Bean 实例的创建和依赖注入过程，注入对象依赖的各种属性值，在初始化时可以指定自定义的初始化方法。经过这一系列初始化操作后 Bean 达到可用状态，接下来就可以使用 Bean 了，当使用完成后会调用 destroy 方法进行销毁，此时也可以指定自定义的销毁方法，最终 Bean 被销毁且从容器中移除。

XML 方式通过配置 bean 标签中的 init-Method 和 destory-Method 指定自定义初始化和销毁方法。

注解方式通过 `@PreConstruct` 和 `@PostConstruct` 注解指定自定义初始化和销毁方法。



### Bean 的作用范围？

通过 scope 属性指定 bean 的作用范围，包括：

① singleton：单例模式，是默认作用域，不管收到多少 Bean 请求每个容器中只有一个唯一的 Bean 实例。

② prototype：原型模式，和 singleton 相反，每次 Bean 请求都会创建一个新的实例。

③ request：每次 HTTP 请求都会创建一个新的 Bean 并把它放到 request 域中，在请求完成后 Bean 会失效并被垃圾收集器回收。

④ session：和 request 类似，确保每个 session 中有一个 Bean 实例，session 过期后 bean 会随之失效。

⑤ global session：当应用部署在 Portlet 容器时，如果想让所有 Portlet 共用全局存储变量，那么该变量需要存储在 global session 中。

### 如何通过 XML 方式创建 Bean？

默认无参构造方法，只需要指明 bean 标签中的 id 和 class 属性，如果没有无参构造方***报错。

静态工厂方法，通过 bean 标签中的 class 属性指明静态工厂，factory-method 属性指明静态工厂方法。

实例工厂方法，通过 bean 标签中的 factory-bean 属性指明实例工厂，factory-method 属性指明实例工厂方法。



### 如何通过注解创建 Bean？

**`@Component` 把当前类对象存入 Spring 容器中**，相当于在 xml 中配置一个 bean 标签。value 属性指定 bean 的 id，默认使用当前类的首字母小写的类名。

`@Controller`，`@Service`，`@Repository` 三个注解都是 `@Component` 的衍生注解，作用及属性都是一模一样的。只是提供了更加明确语义，`@Controller` 用于表现层，`@Service`用于业务层，`@Repository`用于持久层。如果注解中有且只有一个 value 属性要赋值时可以省略 value。

如果想将第三方的类变成组件又没有源代码，也就没办法使用 `@Component` 进行自动配置，这种时候就要使用 `@Bean` 注解。被 `@Bean` 注解的方法返回值是一个对象，将会实例化，配置和初始化一个新对象并返回，这个对象由 Spring 的 IoC 容器管理。name 属性用于给当前 `@Bean` 注解方法创建的对象指定一个名称，即 bean 的 id。当使用注解配置方法时，如果方法有参数，Spring 会去容器查找是否有可用 bean对象，查找方式和 `@Autowired` 一样。

### 如何通过注解配置文件？

`@Configuration` 用于指定当前类是一个 spring 配置类，当创建容器时会从该类上加载注解，value 属性用于指定配置类的字节码。

`@ComponentScan` 用于指定 Spring 在初始化容器时要扫描的包。basePackages 属性用于指定要扫描的包。

`@PropertySource` 用于加载 `.properties` 文件中的配置。value 属性用于指定文件位置，如果是在类路径下需要加上 classpath。

`@Import` 用于导入其他配置类，在引入其他配置类时可以不用再写 `@Configuration` 注解。有 `@Import` 的是父配置类，引入的是子配置类。value 属性用于指定其他配置类的字节码。

### BeanFactory、FactoryBean 和 ApplicationContext 的区别？

BeanFactory 是一个 Bean 工厂，使用简单工厂模式，是 Spring IOC容器顶级接口，可以理解为含有 Bean 集合的工厂类，作用是管理 Bean，包括实例化、定位、配置对象及建立这些对象间的依赖。BeanFactory 实例化后并不会自动实例化 Bean，只有当 Bean 被使用时才实例化与装配依赖关系，属于延迟加载，适合多例模式。

FactoryBean 是一个工厂 Bean，使用了工厂方法模式，作用是生产其他 Bean 实例，可以通过实现该接口，提供一个工厂方法来自定义实例化 Bean 的逻辑。FactoryBean 接口由 BeanFactory 中配置的对象实现，这些对象本身就是用于创建对象的工厂，如果一个 Bean 实现了这个接口，那么它就是创建对象的工厂 Bean，而不是 Bean 实例本身。

ApplicationConext 是 BeanFactory 的子接口，扩展了 BeanFactory 的功能，提供了支持国际化的文本消息，统一的资源文件读取方式，事件传播以及应用层的特别配置等。容器会在初始化时对配置的 Bean 进行预实例化，Bean 的依赖注入在容器初始化时就已经完成，属于立即加载，适合单例模式，一般推荐使用。



### Spring MVC执行流程？

1. 用户发送请求至前端控制器**Dispatcher Servlet**
2. **Dispatcher Servlet**收到请求调用处理器映射器**Handler Mapping**
3. 处理器映射器根据**请求url**找到具体的**处理器**，生成处理器执行链**HandlerExecutionChain**(包括处理器对象和处理器拦截器)一并返回给**Dispatcher Servlet**
4. **Dispatcher Servlet**根据处理器**Handler**获取处理器适配器**HandlerAdapter**执行**Handler Adapter**处理一系列的操作，如：**参数封装**，**数据格式转换**，**数据验证**等操作，执行处理器**Handler Controller**，也叫**页面控制器**
5. Handler执行完成返回**ModelAndView**，**HandlerAdapter**将Handler执行结果ModelAndView返回到**Dispatcher Servlet**
6. **Dispatcher Servlet**将**ModelAndView**传给**View Reslover**视图解析器
7. **View Reslover**解析后返回具体**View**给**Dispatcher Servlet**
8. **Dispatcher Servlet**对**View**进行**渲染视图**（即将模型**数据model填充至视图中**）
9. **DispatcherServlet响应用户**



以上总结：

用户请求 => Dispatcher Servlet => Handler Controller(页面控制器处理URL)  => ModelAndView=>

 View Reslover  =>  View  => Dispatcher Servlet 渲染 View 并相应用户的请求



## Mybatis:高级映射（一对一，一对多，多对多）？



## HashSet能去重两个重写了类equals方法，而属性相同的对象吗？

HashSet是Java集合Set的一个实现类，Set是一个接口，其实现类除HashSet之外，还有TreeSet，并继承了Collection。

HashSet是Java中非常常用的集合类，它基于哈希表（HashMap）实现，能够快速完成元素的插入、删除和查找等操作。HashSet不允许重复元素，即每个元素在集合中都是唯一的。

为了实现这一特性，HashSet依赖于两个重要的方法：equals()和hashCode()。这两个方法对于HashSet的正常运作至关重要。

HashSet**底层**是通过**HashMap**实现的，HashMap的数据存储是通过**数组+链表/红黑树**实现的，存储大概流程是**通过hash函数计算在数组中存储的位置，如果该位置已经有值了，判断key是否相同，相同则覆盖，不相同则放到元素对应的链表中，如果链表长度大于8，就转化为红黑树，如果容量不够，则需扩容**（注：这只是大致流程）。





## SQL优化手段？

![img](https://img-blog.csdnimg.cn/a9e80157a5594295a4a1a8a0df4f2a68.png)

## HTTP 重定向code？

- 永久重定向：301，308
- 临时重定向：302，303， 307
- 其他重定向：304



## 介绍Netty 和nio、nio的组件，Netty的线程



Netty 是一个基于 NIO 的高性能、异步事件驱动的网络应用框架，提供了更简洁、更灵活的 API，使得开发网络应用程序更加容易。Netty 构建在 NIO 的基础上，并对其进行了封装和优化，提供了

1. **简单易用**：Netty 提供了高级别的抽象和简洁的 API，简化了网络应用程序的开发过程。
2. **高性能**：Netty 的异步事件驱动模型和优化的实现使得它能够处理高并发和大规模的网络通信。
3. **可扩展性**：Netty 提供了灵活的架构和组件，支持插件式开发和定制，可以根据需要扩展和定制功能。
4. **协议支持**：Netty 内置了对多种常见网络协议的支持，如 HTTP、WebSocket、TCP、UDP 等，同时也支持自定义协议的开发。



## 怎么解决“粘包”问题？

粘包的问题出现是因为不知道一个用户消息的边界在哪，如果知道了边界在哪，接收方就可以通过边界来划分出有效的用户消息。

一般有三种方式分包的方式：

- 固定长度的消息；
- 特殊字符作为边界；
- 自定义消息结构。



### 固定长度的消息

这种是最简单方法，即每个用户消息都是固定长度的，比如规定一个消息的长度是 64 个字节，当接收方接满 64 个字节，就认为这个内容是一个完整且有效的消息。

但是这种方式灵活性不高，**实际中很少用**。



### 特殊字符作为边界

我们可以在两个用户消息之间插入一个特殊的字符串，这样接收方在接收数据时，读到了这个特殊字符，就把认为已经读完一个完整的消息。

HTTP 是一个非常好的例子。

**HTTP 通过设置回车符、换行符作为 HTTP 报文协议的边界解决粘包问题**。

有一点要注意，这个作为边界点的特殊字符，如果刚好消息内容里有这个特殊字符，我们要对这个字符转义，避免被接收方当作消息的边界点而解析到无效的数据。



### 自定义消息结构

我们可以自定义一个消息结构，由**包头**和数据组成，其中**包头包是固定大小**的，而且包头里有一个字段来说明紧随其后的数据有多大。

比如这个消息结构体，首先 4 个字节大小的变量来表示数据长度，真正的数据则在后面。

```c
struct { 
    u_int32_t message_length; 
    char message_data[]; 
} message;
```

当接收方接收到包头的大小（比如 4 个字节）后，就解析包头的内容，于是就可以知道数据的长度，然后接下来就继续读取数据，直到读满数据的长度，就可以组装成一个完整到用户消息来处理了。









### 谈谈JUC并发编程中的锁 ？

乐观锁和悲观锁只是一种思想，而**不是具体的锁**



#### 乐观锁

​		直接去操作同步资源，是一种无锁算法，得之我幸失之我命。最常见的是**CAS**（Compare And Swap）自旋算法，适合**读操作比较多**的场景。



#### 悲观锁

​		当操作一个同步资源后，会立即对该资源进行加锁。常见的`synchronized`和`Lock`的实现类都是悲观锁。适合**写操作比较多**的场景。



#### CAS算法

- JVM中CAS是通过**UnSafe类**来调用**操作系统底层的CAS**指令实现。
- CAS基于乐观锁思想来设计的，其不会引发阻塞，**synchronized**会导致阻塞。

java.util.concurrent.atomic包下的原子类都使用了CAS算法



- **CAS的缺点**
  （1）ABA问题
  如果一个线程t1正修改共享变量的值A，但还没修改，此时另一个线程t2获取到CPU时间片，将共享变量的值A修改为B，然后又修改为A，此时线程t1检查发现共享变量的值没有发生变化，但是实际上却变化了。
  解决办法： 使用版本号，在变量前面追加上版本号，每次变量更新的时候把版本号加1，那么A－B－A 就会变成1A-2B-3A。从Java1.5开始JUC包里提供了一个类AtomicStampedReference来解决ABA问题。AtomicStampedReference类的compareAndSet方法作用是首先检查当前引用是否等于预期引用，并且当前版本号是否等于预期版本号，如果全部相等，则以原子方式将该引用和该标志的值设置为给定的更新值

  （2）循环时间长开销会比较大：自旋重试时间，会给CPU带来非常大的执行开销

  （3）只能保证一个共享变量的原子操作，不能保证同时对多个变量的原子性操作



- **CAS使用注意事项**
  （1）**CAS需要和volatile配合使用**

  ​		CAS只能保证变量的原子性，不能保证变量的内存可见性。CAS获取共享变量的值时，需要和volatile		配合使用，来保证共享变量的可见性

  

​	   （2）**CAS适用于并发量不高、多核CPU的情况**

​				CPU多核情况下可以同时执行，如果不合适就失败。而并发量过高，会导致自旋重试耗费大量的CPU资源







#### synchronized锁升级



​		在JDK 1.6之后，JVM为了提高锁的获取与释放效率，对**synchronized**的实现进行了优化，引入了**偏向锁和轻量级锁**，从此以后Java内置锁的状态就有了**4种**(**无锁、偏向锁、轻量级锁和重量级锁**)，并且4种状态会随着竞争的情况逐渐升级，而且是不可逆的过程，即不可降级，也就是说只能进行锁升级(从低级别到高级别)级别由低到高依次为:**无锁一>偏向锁一>轻量级锁一>重量级锁**。

synchronized也叫对象锁，通常是跟对象绑定的。synchronized(方法),synchronized(对象)

一个对象在内存中的布局,主要包括四个部分：

1. 8个字节的**MarkWord**，(**MarkWord**里面包含了其它的东西，比如GC标记，**锁类型**)
2. 4个字节的**ClassPoint**(此指针指向的Class)，默认是开启指针压缩所以是四个字节，关闭指针压缩后是八个字节
3. 实例对象中的成员属性大小
4. 字节填充(有的JVM需要8字节对齐，如果上面的字节相加后不能被8整除，则需要在此补齐)





小案例,方便理解**锁升级**:

1. 无锁：比如社团有一间教室 上自习 大家都可以用 没有财产问题 就是无锁状态
2. 偏向锁：后来社团添置了打印机投影仪之类的物品，不能再敞开着大门了，团委老师就安装了一把锁，但是社团教室只有 小韩 一个同学来上自习，团委老师就把钥匙给他保管，因为下次教室还是他用，用完了钥匙就不用还给团委老师了，这就是偏向锁，节省了资源不用来回跑团委，如果小韩同学再也不用教室钥匙了，或者小韩不用的时候 另一位同学用，就把钥匙给另一位同学就行。
3. 轻量锁：后面小陈也想用这个自习室，但是自己没有钥匙，就去找团委老师要，团委老师把钥匙放出去没收回来，看来大家都想用这个钥匙，以后谁在用就往团委借钥匙吧。 轻量锁就比原来偏向锁麻烦些了 偏向锁只需要借一次钥匙，轻量级锁次次都要借钥匙还要还钥匙，轻量级锁的获取及释放依赖多次 CAS 原子指令，而偏向锁只需要在置换 ThreadID 的时候依赖一次 CAS 原子指令即可。
4. 重量级锁：小李 小张 小红 也都都想用钥匙，一开始团委老师说你们先等等，一个一个来，下一节课再来团委看看有没有钥匙，小红来了十次都没借到钥匙，要把自己气死了，直接去找院长，院长听了团委老师的管理方式，气不打一出来，你把钥匙随便给学生，出了问题你负责吗？以后谁也别借教室钥匙了，整个学院的东西，谁要用都找我借，教室门以后不用锁了，我直接把学院大门锁上，谁要用直接找我要学院的钥匙，用完把学院的大门锁上。 （重量级锁是指当有一个线程获取锁之后，其余所有等待获取该锁的线程都会处于阻塞状态。简言之，就是所有的控制权都交给了操作系统，由操作系统来负责线程间的调度和线程的状态变更。而这样会出现频繁地对线程运行状态的切换，线程的挂起和唤醒，从而消耗大量的系统资。）

![image-20240427125938483](.\assets\image-20240427125938483.png)





#### 重入锁(递归锁)



一个锁可以多次访问自己的**互斥量**,并不会出现阻塞自己的情况



![image-20240427131659889](.\assets\image-20240427131659889.png)









#### ReentrantLock锁(重入锁)



ReentrantLock锁更加灵活,需要显示手动上锁和解锁,锁粒度更细,纯java实现

而Synchronized锁是隐式上锁和解锁,基于JVM C++实现 





#### 公平锁和非公平锁



公平锁和非公平锁不是具体的锁，而是一种实现的思想。



公平锁：公平锁会按照锁的顺序依次获锁（访问互斥量的资格）

非公平锁：





#### 为什么有公平锁和非公平锁之后，默认默认使用非公平锁？



主要是考量了**线程切换**的开销，性能和吞吐量。恢复挂起的线程到获得真正的锁，这有一定的时间差，存在开销。

所以非公平锁能充分利用CPU的时间片，尽量减少了CPU空闲时间。



#### 使用非公平锁会有什么问题?



公平锁保证的获得锁的顺序公平性。而非公平锁则没有顺序，因此非公平锁会存在等待获取锁的“人”会**一直等待获取锁**，也就是所谓的“**锁饥饿**”。





#### 共享锁和排它锁



1. **排它锁**

   ​		又称独占锁，获得了以后**既能读又能写**，其他没有获得锁的线程不能读也不能写，典型的synchronized就是排它锁

2. **共享锁**
           共享锁又称**读锁**，获得了共享锁以后**可以查看但无法修改和删除数据**，其他线程也能获得共享锁，也可以查看但不能修改和删除数据



存在多线线程时，优先级使用级别：要么多读，要么一写。（如果有一个线程占用了读锁，那么要等所有的读锁线程完成后才能申请得到写锁；如果有一个线程占用了写锁，那么只要等该写锁线程执行完成后，就可以申请读锁或者写锁）



#### synchronized 和 Lock的区别

1. **synchronized**锁是Java内置关键字，基于C++；Lock类是纯Java实现；
2. synchronized**无法判断**是否获取锁的状态，Lock**可以判断**是否获取到锁；
3. synchronized会**自动释放锁**(a 线程执行完同步代码会释放锁;b 线程执行过程中发生异常会释放锁)，Lock需在finally中手工释放锁(unlock()方法释放锁)，否则容易造成线程死锁;
4. 用synchronized关键字的两个线程1和线程2，如果当前线程1获得锁，线程2线程等待。如果线程1阻塞，则会一直等待下去，而Lock锁就不一定会等待下去，如果尝试获取不到锁，线程可以不用一直等待就结束了;
5. **synchronized的锁可重入、不可中断、非公平，而Lock锁可重入、可中断、可公平(两者皆可)**






# 大杂烩

## Maven 中 dependency 的作用域问题

Maven 项目中 dependency 的作用域是指 dependency 元素中的 scope 属性，它用来控制依赖项在不同的构建阶段和类路径下的可见性。Maven 有以下几种作用域：

compile：默认的作用域，表示依赖项在所有的构建阶段和类路径下都可用。
provided：表示依赖项在编译和测试阶段可用，但在运行时不需要，因为容器或平台已经提供了该依赖项。
runtime：表示依赖项在运行和测试阶段可用，但在编译时不需要。
test：表示依赖项只在测试阶段和测试类路径下可用。
system：表示依赖项类似于 provided，但是需要显式指定依赖项的路径，而不是从仓库中获取。
import：表示依赖项只用于从其他 POM 中导入 dependencyManagement 部分，该作用域只能在 dependencyManagement 中使用。

Maven 项目中 dependency 的作用域就是告诉 Maven 什么时候需要用到哪些依赖项。依赖项就是项目中用到的其他的 JAR 包。Maven 有不同的构建阶段，比如编译、测试、运行等，不同的构建阶段可能需要用到不同的依赖项。比如，有些依赖项只在测试时需要，有些依赖项在运行时不需要，因为系统已经提供了。所以，我们可以给每个依赖项设置一个作用域，让 Maven 知道什么时候该用哪个依赖项，什么时候不用。这样可以节省空间和时间，也可以避免一些错误。

已收到消息. 好的，我尽量用简单的语言来解释。Maven 项目中 dependency 的作用域就是告诉 Maven 什么时候需要用到哪些依赖项。依赖项就是项目中用到的其他的 JAR 包。Maven 有不同的构建阶段，比如编译、测试、运行等，不同的构建阶段可能需要用到不同的依赖项。比如，有些依赖项只在测试时需要，有些依赖项在运行时不需要，因为系统已经提供了。所以，我们可以给每个依赖项设置一个作用域，让 Maven 知道什么时候该用哪个依赖项，什么时候不用。这样可以节省空间和时间，也可以避免一些错误。

## SpringBoot 自动装配原理

您好，这是必应。根据搜索结果，Spring Boot 2 和 Spring Boot 3 的自动装配原理没有本质的区别，都是基于 @EnableAutoConfiguration 注解和 spring.factories 文件来实现的。

Spring Boot 的自动装配原理大致如下：

-   在启动类上使用 @SpringBootApplication 注解，该注解包含了 @EnableAutoConfiguration 注解，该注解会导入 AutoConfigurationImportSelector 类。
-   AutoConfigurationImportSelector 类会扫描类路径下的 spring.factories 文件，该文件包含了很多官方提供的自动配置类的全限定名。
-   根据条件注解（如 @ConditionalOnClass、@ConditionalOnMissingBean 等）来判断哪些自动配置类生效，然后将这些类加载到 Spring 容器中。
-   这些自动配置类会根据项目的依赖和配置来创建一些默认的 Bean 对象，如 TomcatServletWebServerFactory、DataSource 等。

**通俗解释**
Spring Boot 的自动装配就是让 Spring Boot 帮我们创建一些常用的 Bean 对象，比如数据库连接、web 服务器等，这样我们就不用自己写配置类了。Spring Boot 是怎么做到的呢？它是通过读取一个叫 spring.factories 的文件，这个文件里面列出了很多 Spring Boot 提供的配置类，每个配置类都有一些条件，只有当条件满足的时候，才会生效。比如，如果你的项目里有 Tomcat 的 jar 包，那么就会生效一个叫 TomcatServletWebServerFactory 的配置类，这个类会帮你创建一个 Tomcat 的 web 服务器。这样，你就可以直接运行你的 web 应用了，不用自己配置 Tomcat。这就是 Spring Boot 自动装配的原理。

## springbootApplication run 启动流程原理

> 简略

1. 首先从 main 方法找到 run()方法，在执行 run()方法之前 new 一个 SpringApplication 对象，初始化一些基本的环境变量、资源、构造器、监听器等。
2. 进入 run()方法，创建应用监听器 SpringApplicationRunListeners 开始监听，加载 SpringBoot 配置环境(ConfigurableEnvironment)，然后把配置环境(Environment)加入监听对象中。
3. 然后加载应用上下文(ConfigurableApplicationContext)，当做 run 方法的返回对象，配置应用上下文对象、配置基本属性和刷新应用上下文。
4. 最后创建 Spring 容器，refreshContext(context)，实现 starter 自动化配置和 bean 的实例化等工作。

> 稍详细

1. 首先从 main 方法找到 run()方法，在执行 run()方法之前 new 一个 SpringApplication 对象，初始化一些基本的环境变量、资源、构造器、监听器等。
2. 进入 run()方法，创建应用监听器 SpringApplicationRunListeners 开始监听，加载 SpringBoot 配置环境(ConfigurableEnvironment)，然后把配置环境(Environment)加入监听对象中。
3. 然后打印 Banner 信息，根据是否为 web 环境创建相应的应用上下文(ConfigurableApplicationContext)，当做 run 方法的返回对象。
4. 然后准备应用上下文，将环境、参数、Banner 等信息保存到容器中，触发监听器的相应事件。
5. 然后刷新应用上下文，完成组件的扫描、创建、加载等工作，触发监听器的相应事件。
6. 然后在容器创建完成之后执行 ApplicationRunner 接口和 CommandLineRunner 接口的实现类。
7. 最后返回应用上下文对象，表示启动完成。



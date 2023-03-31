# 重写 compareTo 方法自定义规则

```java
public int compareTo(Student o) { return o.score - this.score; }
```

中 o 和 this 的区别

>这段代码是用于比较两个Student对象的分数属性的，它实现了Comparable接口的compareTo()方法。在这个方法中，o表示作为参数传入的另一个Student对象，this表示当前的Student对象。如果this.score小于o.score，就返回一个负数，表示当前对象在字典顺序上小于参数对象；如果this.score大于o.score，就返回一个正数，表示当前对象在字典顺序上大于参数对象；如果this.score等于o.score，就返回0，表示两个对象相等。你可以参考这些网站123，它们介绍了compareTo()方法的用法和规则。

# 重写 compareTo 方法自定义规则

```java
public int compareTo(Student o) { return o.score - this.score; }
```

o 和 this 的区别

o:参数对象
this:当前对象

> return this.score-o.score;//asc
> return o.score-this.score;//desc

> 这段代码是用于比较两个 Student 对象的分数属性的，它实现了 Comparable 接口的 compareTo()方法。在这个方法中，o 表示作为参数传入的另一个 Student 对象，this 表示当前的 Student 对象。如果 this.score 小于 o.score，就返回一个负数，表示当前对象在字典顺序上小于参数对象；如果 this.score 大于 o.score，就返回一个正数，表示当前对象在字典顺序上大于参数对象；如果 this.score 等于 o.score，就返回 0，表示两个对象相等

compareTo()方法的返回值决定了两个对象的顺序。如果返回一个正数，表示当前对象大于参数对象；如果返回一个负数，表示当前对象小于参数对象；如果返回 0，表示两个对象相等。所以，如果你想按照升序排序，你应该让分数高的对象大于分数低的对象，也就是说，返回 this.score - o.score。如果你想按照降序排序，你应该让分数低的对象大于分数高的对象，也就是说，返回 o.score - this.score。

o.score 的分不一定要低，这取决于你传入的参数对象是什么。如果你传入的参数对象的分数高于当前对象的分数，那么 o.score 就高于 this.score，反之亦然。compareTo()方法只是根据你给定的两个对象的分数差值来返回一个整数，表示它们的顺序关系。

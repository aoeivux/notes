# comparator 方法自定义排序规则

```java
 @Override
            public int compare(Integer o1, Integer o2) {
            //下面这么写，结果是降序
                if(o1 < o2){
                    return 1;
                }else if(o1 > o2){
                    return -1;
                }
                return 0;
            }
```

> 0、1 表示不排序，并按照此时此刻 o1 和 o2 的大小关系进行排序。

> o1 表示后面的数，o2 表示前面的数,例如:[1,2,3,4],o1=2, o2=1。

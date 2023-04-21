# Java 的集合原理

-   Java 集合类主要分为 Collection 和 Map 两个根接口，Collection 又分为 List、Set 和 Queue 三个子接口，Map 又分为 SortedMap 和 NavigableMap 两个子接口。
-   List 接口是一个有序、可重复的集合，它的主要实现类有 ArrayList、LinkedList 和 Vector。ArrayList 是基于动态数组实现，LinkedList 是基于双向链表实现，Vector 是基于动态数组实现但是线程安全的。
-   Set 接口是一个无序、不可重复的集合，它的主要实现类有 HashSet、LinkedHashSet 和 TreeSet。HashSet 是基于哈希表实现，LinkedHashSet 是基于哈希表和双向链表实现，TreeSet 是基于红黑树实现。
-   Queue 接口是一个先进先出的集合，它的主要实现类有 ArrayDeque、LinkedList 和 PriorityQueue。ArrayDeque 是基于动态数组实现，LinkedList 是基于双向链表实现，PriorityQueue 是基于堆实现。
-   Map 接口是一个存储键值对的集合，它的主要实现类有 HashMap、LinkedHashMap、TreeMap 和 Hashtable。HashMap 是基于哈希表实现，LinkedHashMap 是基于哈希表和双向链表实现，TreeMap 是基于红黑树实现，Hashtable 是基于哈希表实现但是线程安全的。

## Java集合框架体系
Java集合可以分为`Collection`和`Map`两大体系：
1. [`interface java.util.Collection<E>`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Collection.html). `Collection`接口用于存储一个一个的数据，也称**单列数据集合**
   1. List 子接口，用来存储有序的、可以重复的数据(主要用来替换数组，可以理解为“动态”数组)
      * 实现类： ArrayList(主要实现类)、 LinkedList、 Vector
   2. Set 子接口，用来存储无序的、不可重复的数据
      * 实现类： HashSet(主要实现类)、 LinkedHashSet、 TreeSet
2. [`interface java.util.Map<K, V>`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Map.html) `Map`接口用于存储具有映射关系"Key-Value"对的集合，即一对一的数据，也称**双列数据集合“。
   * HashMap(主要实现类)、 LinkedHashMap、 TreeMap、 Hashtable、 Properties

![](images/1680779187-集合框架全图.png)

* [Java 8 Collection Hierarchy](https://www.falkhausen.de/Java-8/java.util/Collection-Hierarchy.html)
* [Java 8 Map Hierarchy](https://www.falkhausen.de/Java-8/java.util/Map-Hierarchy.html)

## 1. Iterator
* [`interface java.util.Iterator<E>`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Iterator.html)

在程序开发中，经常需要遍历集合中的所有元素。针对这种需求， JDK 专门提供了一个接口`java.util.Iterator`。 `Iterator`接口也是 Java 集合中的一员，但它与 Collection、 Map 接口有所不同。
* Collection接口与Map接口主要用于存储元素
* Iterator，被称为迭代器接口，本身并不提供存储对象的能力，主要用于遍历Collection中的元素
  
Collection接口继承了java.lang.Iterable接口，该接口有一个iterator()方法，那么所有实现了 Collection 接口的集合类都有一个 iterator()方法，用以返回一个实现了 Iterator 接口的对象。


### for-each 循环
foreach 循环（也称增强 for 循环）是 JDK5.0 中定义的一个高级 for 循环，专门用来遍历数组和集合的。
```java
for(元素的数据类型 局部变量 : Collection 集合或数组){
//操作局部变量的输出操作
}
//这里局部变量就是一个临时变量，自己命名就可以

for (int num : nums) {
    System.out.println(num);
}
```


## 2. `List`接口
`java.util.Collectio`n:存储一个一个的数据
   |-----子接口：List:存储有序的、可重复的数据 ("动态"数组)
      |---- ArrayList:List的主要实现类；线程不安全的、效率高；底层使用Object[]数组存储
            在添加数据、查找数据时，效率较高；在插入、删除数据时，效率较低
      |---- LinkedList:底层使用双向链表的方式进行存储；在对集合中的数据进行频繁的删除、插入操作时，建议使用此类
            在插入、删除数据时，效率较高；在添加数据、查找数据时，效率较低；
      |---- Vector:List的古老实现类；线程安全的、效率低；底层使用Object[]数组存储


### 2.1 List接口的特点
* 鉴于Java中**数组**用来存储数据的局限性，我们通常使用`java.util.List`替代**数组**。
* List集合类中元素有序、且可重复，集合中的每个元素都有其对应的顺序索引。


### 2.2 List接口方法
1. 插入元素
   1. void add(int index, Object obj);
   2. boolean addAll(int index, Collection eles);
2. 获取元素
   1. Object get(int index);
   2. List subList(int fromIndex, int toIndex);
3. 获取元素索引
   1. int indexOf(Object obj);
   2. int lastIndexOf(Object obj);
4. 删除和替换元素
   1. Object remove(int index);
   2. Object set(int index, Object ele);


### 2.3 List接口主要实现类：ArrayList
1. ArrayList是List接口的主要实现类
2. 本质上，ArrayList是对象引用的一个“变长”数组
3. `Arrays.asList(...)`方法返回的List集合，既不是ArrayList实例，也不是Vector实例，而是一个固定长度的List集合


### 2.4 List接口的实现类：LinkedList
对于频繁的插入或删除元素的操作，建议使用`LinkedList`类，效率较高。这是由底层采用链表（双向链表）结构存储数据决定的。


### 2.5 List接口的实现类: Vector
1. Vector 是一个古老的集合， JDK1.0 就有了。大多数操作与 ArrayList 相同，区别之处在于 Vector 是线程安全的。


### 2.6 总结
1. 在各种`List`中，最好把`ArrayList`作为默认选择;
2. 当插入、删除频繁时，使用`LinkedList`; 
3. `Vector`总是比`ArrayList`慢，所以尽量避免使用。

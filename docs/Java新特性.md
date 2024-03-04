# Java新特性
![](images/NewFeatures.Java8.png)

Java版本发布特点: 小步快跑，快速迭代
* Java5.0   最重要的里程碑式版本
* Java8.0   第二重要的里程碑式版本
* Java9.0   从9.0开始，每半年更新一次
* Java11.0  LTS, 2018.9
* Java17.0  LTS, 2021.9

JEP(JDK Enhancement Proposals): JDK改提案。每当有新的设想的时候，JEP可以提出非正式的规范(specification)，被正式认可的JEP正式写进JDK的发展路线图并分配版本号。

LTS(Long-term Support): 长期支持。


### 如何学习新特性？
1. 新的语法规则(多关注)
   1. 比如自动装箱、自动拆箱，注解，enum，Lambda表达式，方法引用，switch表达式，try-catch变化，record等
2. API层面: 增加，过时，删除API
   1. StringBuilder, ArrayList, 新的日期时间的API, Optional
3. 底层的优化, JVM参数的调整, GC的变化, 内存结构(永久代-->元空间)


## 1. Lambda表达式
* `->`: Lambda操作符，箭头操作符。
* `->`的左侧: Lambda的形参列表，对应着要重写的接口中的抽象方法的形参列表。
* `->`的右侧: Lambda体，对应着接口的实现类要重写的方法的方法体。

`Lambda形参列表 -> Lambda体`

### Lambda表达式的本质
```java
Comparator<Integer> comparator1 = (o1, o2) -> {
            System.out.println(o1);
            System.out.println(o2);
            return Integer.compare(o1, o2);
        };
```
1. 一方面，Lambda表达式作为**接口**实现类的对象 --> 万事万物皆对象
   * 这些接口只有一个方法
2. 两一方面，Lambda表达式是一个匿名函数

### 函数式接口
1. 如果接口中只有一个抽象方法，则此接口就被称为函数式接口。
2. 因为只有给函数式接口提供实现类的对象时，我们才可以使用Lambda表达式。`@FunctionalInterface`

### JDK API中函数式接口的包
> Functional interfaces provide target types for lambda expressions and method references. Each functional interface has a single abstract method, called the functional method for that functional interface, to which the lambda expression's parameter and return types are matched or adapted.
* [Package java.util.function](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/function/package-summary.html)

### 常见的函数式接口: 四大核心函数式接口
1. Consumer<T>: 消费型接口, `void accept(T t)`
2. Supplier<T>: 供给型接口, `T get()`
3. Function<T, R>: 函数型接口, `R apply(T t)`
4. Predicate<T>: 判断型接口, `boolean test(T t)`

### Lambda表达式语法规则总结
* 左边: Lambda的形参列表，参数的类型可以省略。如果形参只有一个，则()也可以省略
* 右边: Lambda体，对应着重写的方法的方法提。如果方法体中只有一行执行语句，则{}可以省略，如果有return关键字，则必须一并省略。
* ⚠️Code Reference: package `com.atguigu.lambda`


## 2. 方法引用 & 构造器引用
### 2.1 方法引用
1. 可以看作是基于Lambda表达式的进一步刻画。
2. 当需要提供一个函数式接口时，可以使用Lambda表达式提供此实例
   1. 当满足一定条件时，可以用方法引用or构造器引用替换Lambda表达式。
3. 方法引用的本质: **方法引用**作为了**函数式接口的实例** --> "万事万物皆对象"
   1. `Comparator<Integer> comparator = Integer::compare;`
4. 格式: 类(or对象)::方法名
   1. 对象::实例方法
      * 要求: 函数式接口中的抽象`方法a`与其内部实现时调用的**对象的**某个`方法b`的形参列表和返回值类型都相同(or一致)。此时可以考虑使用方法b实现对方法a的替换和覆盖。此替换或覆盖即为方法引用。
      * ⚠️注意: 此方法b是非静态方法，需要对象调用。
   2. 类::静态方法
      * 要求: 函数式接口中的抽象`方法a`与其内部实现时调用的**类**的某个`静态方法b`的形参列表和返回值类型都相同(or一致)。此时可以考虑使用方法b实现对方法a的替换和覆盖。此替换或覆盖即为方法引用。
      * ⚠️注意: 此方法b是**静态方法**，需要类调用。
   3. 类::实例方法
      * 要求: 函数式接口中的抽象`方法a`与其内部实现时调用的**对象的**某个`方法b`的返回值类型一致，同时，抽象方法a的第一个参数作为方法b的调用者，且抽象方法a的后N-1个参数与方法b的N-1个参数的类型相同or一致，则可以考虑使用方法b实现对抽象方法a的替换/覆盖。此替换/覆盖即为方法引用。
      * ⚠️注意: 此方法b是非静态方法，需要对象调用。但是形式上，写成对象a所属的类
5. :white_check_mark: Code Reference: package `com.atguigu.reference`


### 2.2 构造器引用
1. 将构造器引用看作特殊的方法引用。
2. 格式: `类名::new`
3. 说明:
   1. 调用了类名对应的类中的某一个确定的构造器
   2. 具体调用的是类中的哪一个构造器？取决于函数式接口的抽象方法的形参列表。
4. :white_check_mark: Code Reference: package `com.atguigu.reference`

### 3. 数组引用
1. 格式: `数组类型[]::new`
2. :white_check_mark: Code Reference: package `com.atguigu.reference`


## 3. Stream API

## 4. 新的语法

[toc]

# Java反射机制

## 1. 反射(Reflection)的概念
### 1.1 反射的出现背景
在Java程序中，所有的对象都有两种类型：**编译时类型**和**运行时类型**，而在很多时候，对象的编译时类型和运行时类型不一致。
比如
```java
Object obj = new String("Hello");
obj.getClass();
```
在上述代码片段中，在定义变量`obj`时，它的声明类型是`Object`，但是程序需要调用该对象运行时类型的方法时，该方法不是`Object`中的方法，那么该如何解决？
1. **方案1:** 在编译和运行时都完全知道类型的具体信息，在这种情况下，可以直接先使用`instanceof`运算符进行判断，再使用强制类型转换符将其转换成运行时类型的变量即可。
2. **方案2:** 编译时根本无法预知该对象和类的真实信息，程序只能依靠运行时信息来发现该对象和类的真实信息，这就必须使用反射。

### 1.2 反射概述
反射(Reflection)是被是为**动态语言**的关键，反射机制运行程序在运行期间借助Reflection API取得**任何类**的内部信息，并能直接操作任意对象的内部属性及方法。

加载完类之后，在堆内存的方法区中就产生了一个`Class`类型的对象（一个类 只有一个Class对象），这个对象就包含了完整的类的结构信息。我们可以通过这个对象看到类的结构。

### 1.3 Java反射机制提供的功能
* 在运行时判断任意一个对象所属的类
* 在运行时构造任意一个类的对象
* 在运行时判断任意一个类所具有的成员变量和方法
* 在运行时调用任意一个对象的成员变量和方法
* 在运行时获取范型信息
* 在运行时处理注解
* 生成动态代理

### 1.4 反射相关的主要API
* `java.lang.Class`: 代表一个类
* `java.lang.reflect.Method`: 代表类的方法
* `java.lang.reflect.Field`: 代表类的成员变量 
* `java.lang.reflect.Constructor`: 代表类的构造器


## 2. 理解Class类并获取Class实例
### 2.1 理论上
在`Object`类中定义了以下方法，此方法被所有子类继承:
```java
public final Class getClass()
```
以上方法返回值的类型是一个`Class`类，此类是Java反射的源头，时加上所谓反射，从程序运行的结果来看，就是可以通过对象反射求出类的名称。

对每个类而言，JRE都为其保留一个不变的Class类型的对象。一个Class对象包含了特定某个结构(class/interface/enum/annotation/primitive type/void/[])的有关信息。
* Class本身也是一个类
* Class对象只能由系统建立对象
* 一个加载的类在JVM中只会有一个Class实例
* 一个Class对象对应的是一个加载到JVM中的.class文件
* 每个类的实例都会记得自己是由哪个Class实例所生成的
* 通过Class可以完整地得到一个类中的所有被加载的结构
* Class类是Reflection的根源，针对任何你想动态加载、运行的类，唯有先获得相应的Class对象

### 2.2 内存结构上
![](images/Reflection_Mem.png)

### 2.3 获取Class类的实例(四种方法)
1. 方法1: 要求编译期间已知类型
   * 前提: 若已知具体的类型，通过类的class属性获取，该方法最为安全可靠，程序性能最高。
   * `Class clazz = String.class`
2. 获取对象的运行时类型
   * 前提: 已知某个类的实例，调用该实例的`getClass()`方法获取Class对象
   * `Class clazz = "www.atguigu.com".getClass()` 获取String对象的类
3. 可以获取编译期间未知的类型
   * 前提: 已知一个类的全类名，且在该类的类路径下，可以通过Class类的静态方法`forName()`获取，可能抛出`ClassNotFoundException`
   * `Class clazz = Class.forName("java.lang.String")`
4. 其他方式(不做要求)
   * 前提: 可以用系统类加载器对象或自定义加载器对象加载指定路径下的类型
   * `ClassLoader classLoader = this.getClass().getClassLoader(); Class clazz = classLoader.loadClass("类的全类名")`

### 2.4 哪些类型可以有Class对象
简而言之，所有Java类型！
1. class: 外部类，成员(成员内部类，静态内部类), 局部内部类，匿名内部类
2. interface: 接口
3. []: 数组
4. enum: 枚举
5. annotation: 注解，`@interface`
6. primitive type: 基本数据类型
7. void: 

```java
    @Test
    public void test2() {
        Class c1 = Object.class;
        Class c2 = Comparable.class;
        Class c3 = String[].class;
        Class c4 = int[][].class;
        Class c5 = ElementType.class;
        Class c6 = Override.class;
        Class c7 = int.class;
        Class c8 = void.class;
        Class c9 = Class.class;

        int[] a = new int[10];
        int[] b = new int[100];
        Class c10 = a.getClass();
        Class c11 = b.getClass();
        // 只要元素类型与维度一样，就是同一个Class
        System.out.println(c10 == c11);
    }
```

### 2.5 Class类的常用方法
* [java.lang.Class<T>](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Class.html)
* [Package java.lang.reflect](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/reflect/package-summary.html)


## 问答
1. 面向对象中创建对象，调用指定结构(属性, 方法)等功能，可以不实用反射，也可以使用发射。请问有什么区别？
   1. 不实用反射，我们需要考虑封装性。比如：出了Person类之后，就不能调用Person类中私有的结构
   2. 使用反射，我们可以调用运行时类中任意的构造器、属性、方法。包括了私有的属性、方法、构造器。
2. 以前创建对象并调用方法的方式，与现在通过反射创建对象并调用方法对比的话，哪种用的多？
   1. 从程序员开发者角度来讲，在开发中主要是完成业务代码，对于相关的对象、方法的调用都是确定的，所有使用非反射的方式多一些。
   2. 因为反射体现了动态性(可以在运行时动态地获取对象所属的类，动态地调用相关的方法)，所以在设计框架的时候，会大量的使用发射。意味着，如果大家要学习框架源码，那么就需要学习反射。
   3. 框架 = 注解 + 反射 + 设计模式
3. 单例模式的饿汉式和懒汉式中，私有化类的构造器了！此时通过反射，可以创建单例模式中类的多个对象吗？ Yes! (暴力反射)
4. 通过反射，可以调用类中的私有的结构，是否与面向对象的封装性有冲突？是不是Java语言设计存在Bug？ 不存在bug! 自圆其说
   1. 封装性：体现的是是否建议调用内部API的问题，比如private声明的结构，意味着不建议调用
   2. 反射：体现的是能否调用的问题。因为类的完整结构都加载到了内存中，所以我们有能力进行调用。


## Reference
* [188 反射机制 反射、Class的理解与获取Class实例的方式](https://www.youtube.com/watch?v=mV-ZP2WIfyE&list=PLmOn9nNkQxJG_AbAUeyAPH3fO0i_APAM9&index=188)
# 范型(Generic Type)

![](images/Chap13_Generic_01.png)

## 1. 范型概述
集合类在设计阶段/声明阶段不能确定这个容器到底实际存的是什么类型的对象，所以在JDK5.0之前只能把元素类型设计成`Object`，JDK5.0时Java引入了**参数化类型**(Parameterized type)的概念，允许我们在创建集合时指定集合元素的类型。比如`List<String>`，这表明`List`只能保存字符串类型的对象。

范型即为"类型参数"。

Java泛型可以保证如果程序在**编译时**没有发出警告，运行时就不会产生`ClassCastException`异常。即，**把不安全的因素在编译期间就排除了，而不是运行期**；既然通过了编译，那么类型一定是符合要求的，就避免了类型转换。同时，代码更加简洁、健壮。把一个集合中的内容限制为一个特定的数据类型，这就是**generic**背后的核心思想。

### 1.1 什么是泛型？
所谓**泛型**，就是允许在定义类、接口时通过一个`标识`表示类中某个`属性的类型`或者是某个方法的`返回值或参数的类型`。这个类型参数将在使用时（例如，继承或实现这个接口、创建对象或调用方法时）确定（即传入实际的类型参数，也称为类型实参）。

### 1.2 在集合中使用泛型之前可能存在的问题
1. 问题1：**类型不安全**。因为add()的参数是Object类型，意味着任何类型的对象都可以添加成功
2. 问题2：**需要使用强转操作**，繁琐。还有可能导致`ClassCastException`异常。

## 2. 在集合、比较器中使用泛型 (重点)
* 范型在集合中的使用: `com.ylqi007.chap13generic.use.CollectionMapTest`
* 范型在`Comparable`中的使用: `com.ylqi007.chap13generic.use.exer1.Employee`
* 范型在`Comparator`中的使用: `com.ylqi007.chap13generic.use.exer1.EmployeeTest.test2`

### 2.1 使用说明
> 集合框架在声明接口和其实现类时，使用了泛型（jdk5.0），在实例化集合对象时，
> * 如果没有使用泛型，则认为操作的是Object类型的数据。
> * 如果使用了泛型，则需要指明泛型的具体类型。一旦指明了泛型的具体类型，则在集合的相关的方法中，凡是使用类的泛型的位置，都替换为具体的泛型类型。


## 3. 自定义范型结构
### 3.1 范型的基础说明
1. `<T>`: 这种语法形式就叫做范型。
   * `<类型>`的形式称为**类型参数**，这里**类型**习惯上使用`T`表示，是Type的缩写。
   * `T`代表了未知的数据类型，我们可以指定为`String, Integer`等等。
   * 类比方法的参数的概念，我们把`T`称为"类型参数"，将`?`称为类型实参，有助于我们理解范型。
   * 这里的`T`，可以替换成`K, V`等任意字母。
2. 在哪里可以声明类型变量`<T>`
   * 声明类or接口时，在类名or接口名后面声明范型类型，我们将这样的类or接口称为**范型类**or**范型接口**。
   * **范型类:** `[修饰符] class 类名<类型变量列表> [extends 父类] [implements 接口们]`
     * `public class ArrayList<E>`
   * **范型接口:** `[修饰符] interface 接口名<类型变量列表> [implements 接口们]`
     * `public interface Map<K, V>`
3. 在声明方法时，在`[修饰符]`与返回值类型之间声明类型变量，我们把声明了类型变量的方法，称为**范型方法**。
   * **范型方法:** `[修饰符] <类型变量列表> 返回值类型 方法名([形参列表]) [throws 异常列表] {}`
     * `public static <T> list<T> asList(T...t)`

### 3.2 自定义范型类或范型接口
当我们在类或接口中定义某个成员时，该成员的相关类型是不确定的，而这个类型需要在使用这个类或接口时才可以确定，那么我们可以使用泛型类、泛型接口。

#### 3.2.1 说明
1. 我们在声明完自定义泛型类以后，可以在类的内部（比如：属性、方法、构造器中）使用类的泛型。
   * `com.ylqi007.chap13generic.selfdefine.Order`中的属性`T t`
2. 我们在创建自定义泛型类的对象时，可以指明泛型参数类型。一旦指明，内部凡是使用类的泛型参数的位置，都具体化为指定的类的泛型类型。
3. 如果在创建自定义泛型类的对象时，没有指明泛型参数类型，那么泛型将被擦除，泛型对应的类型均按照 Object 处理，但不等价于Object。
    * `com.ylqi007.chap13generic.selfdefine.GenericTest.test2`
    * ✅ 经验：泛型要使用一路都用。要不用，一路都不要用。
4. 泛型的指定中必须使用引用数据类型。不能使用基本数据类型，此时只能使用包装类替换。
   * `com.ylqi007.chap13generic.selfdefine.GenericTest.test2`
5. 除创建泛型类对象外，子类继承泛型类时、实现类实现泛型接口时，也可以确定泛型结构中的泛型参数。
   1. 如果我们在给泛型类提供子类时，子类也不确定泛型的类型，则可以继续使用泛型参数。
      * `com.ylqi007.chap13generic.selfdefine.SubOrder3`
   2. 我们还可以在现有的父类的泛型参数的基础上，新增泛型参数。
      * `com.ylqi007.chap13generic.selfdefine.SubOrder4`
      * `com.ylqi007.chap13generic.selfdefine.SubOrder5`

#### 3.2.2 ⚠️注意
1. 泛型类可能有多个参数，此时应将多个参数一起放在尖括号内。比如：`<E1,E2,E3>`
2. JDK7.0 开始，泛型的简化操作: `ArrayList flist = new ArrayList<>();`, 类型推断
3. 如果泛型结构是一个接口或抽象类，则不可创建泛型类的对象。
4. 不能使用`new E[]`(❌)。 但是可以: `E[] elements = (E[])new Object[capacity]`(✅)
   * 参考: `ArrayList`源码中声明: `Object[] elementData`，而非泛型参数类型数组。
5. 在类/接口上声明的泛型，在本类或本接口中即代表某种类型，但不可以在静态方法中使用**类的泛型**。
6. 异常类不能是带泛型的。

### 3.3 自定义范型方法
如果我们定义类、接口时没有使用<泛型参数>，但是某个方法形参类型不确定时，这个**方法可以单独定义**`<泛型参数>`。

#### 3.3.1 说明
泛型方法的格式: `[访问权限] <泛型> 返回值类型 方法名([泛型标识 参数名称]) [抛出的异常]{
}`
* 方法，也可以被泛型化，与其所在的类是否是泛型类没有关系。
* 泛型方法中的泛型参数在方法被调用时确定。
* 泛型方法可以根据需要，声明为 static 的。

#### 3.3.2 举例
* `com.ylqi007.chap13generic.selfdefine.Order.method`
* `com.ylqi007.chap13generic.selfdefine.Order.copyFromArrayToList`: 范型方法可以是static


## 4. 应用
### 4.1 DAO
* ORM: Object Relational Mapping
  * 数据库中的一个table与Java application中的一个类对应

## 4. 使用说明
集合框架在声明接口和其实现类时，使用了范型(JDK5.0)。
在实例化集合对象时，
* 如果没有使用范型，则认为操作是Object类型的数据。
* 如果使用了范型，则需要指明范型的具体类型。一旦指明了范型的具体类型，则在集合的相关方法中，凡是使用类的范型的地方都具体化为指定的类的泛型类型

## Type Inference(类型推断)
> **Type inference** is a Java compiler's ability to look at each method invocation and corresponding declaration to determine the type argument (or arguments) that make the invocation applicable.


## 5. 范型在继承中的体现
* 如果`B`是`A`的一个子类型(子类or子接口)，而`G<E>`是具有范型声明的类or接口，但`G<B>`并不是`G<A>`的子类性，它们是并列的，没有任何子父类的关系。
  * Code reference: `com.ylqi007.chap13generic.more.GenericTest.test2`
  * 比如，`ArrayList<Object>`和`ArrayList<String>`是两个不同的类，是并列关系，并没有子父类关系。
* 类SuperA是类A的父类或接口，`SuperA<G>`与`A<G>`的关系: `SuperA<G>`与`A<G>`有继承或实现的关系。
  * Code reference: `com.ylqi007.chap13generic.more.GenericTest.test4`
  * 比如，`List<String>`和`ArrayList<String>`之间存在继承关系。


## 6. 通配符的使用
当我们声明一个变量/形参时，这个变量/形参的类型是一个范型类or范型接口，例如: `Comparator`类型，但是我们仍无法确定这个范型类or范型接口的类型变量的具体类型，此时可以考虑使用类型通配符`?`。

### 6.1 通配符的理解
比如: `List<?>`, `Map<?, ?>`。`List<?>`是`List<String>`,`List<Object>`等各类范型List的父类。

### 6.2 通配符的读/写
**写操作:** 将任意元素加入到其中是类型不安全的。
```java
Collection<?> collection = new ArrayList<String>();
collection.add(new Object());   // 编译时错误
```
* 因为我们不知道`collection`的元素类型，我们不能向其中添加对象。`add()`方法有类型参数`E`作为集合的元素类型。我们传给`add()`的任何参数都必须是一个未知类型的子类。因为我们不知道那是什么类型，所以我们无法传任何东西进去。
* 唯一可以插入的元素是`null`，因为它是所有引用类型的默认值。
* Code reference: `com.ylqi007.chap13generic.more.GenericTest1.test1`

**读操作:** 读取`List<?>`的对象`list`中的元素时，永远是安全的，因为不管`list`的真实类型是什么，它包含的都是 `Object`。
* Code reference: `com.ylqi007.chap13generic.more.GenericTest1.test2`

### 6.3 ⚠️ 使用注意点
1. 编译错误: 不能用在泛型方法声明上，返回值类型前面`<>`不能使用`?`
   * `public static <?> void test(ArrayList<?> list){ }`
2. 编译错误：不能用在泛型类的声明上
   * `class GenericTypeClass<?>{ }`
3. 编译错误：不能用在创建对象上，右边属于创建集合对象
   * `ArrayList<?> list2 = new ArrayList<?>();`

### 6.4 有限制的通配符
`<?>`
* 允许所有泛型的引用调用

通配符指定上限: `<? extends 类/接口>`
* 使用时指定的类型必须是继承某个类，或者实现某个接口，即`<=`

通配符指定下限: `<? super 类/接口>`
* 使用时指定的类型必须是操作的类或接口，或者是操作的类的父类或接口的父接口，即`>=`

说明:
1. `<? extends Number>` //(无穷小 , Number]
   * //只允许泛型为 Number 及 Number 子类的引用调用
2. `<? super Number>` //[Number , 无穷大)
   * //只允许泛型为 Number 及 Number 父类的引用调用
3. `<? extends Comparable>`
   * //只允许泛型为实现 Comparable 接口的实现类的引用调用


## Reference
* [The Java Tutorials: Type Inference](https://docs.oracle.com/javase/tutorial/java/generics/genTypeInference.html)
* [The Java Tutorials: Lesson: Generics (Updated)](https://docs.oracle.com/javase/tutorial/java/generics/index.html)

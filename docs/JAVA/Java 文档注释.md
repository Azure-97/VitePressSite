# Java 文档注释

Java 支持三种注释方式：

* 单行注释
* 多行注释
* 文档注释

前两种分别是 **//** 和 **/\* \*/**，第三种被称作文档注释，它以 **/\*\*** 开始，以 **\*/** 结束。


## javadoc 标签



| **标签**      | **描述**                                               | **示例**                                                        |
| ------------- | ------------------------------------------------------ | --------------------------------------------------------------- |
| @author       | 标识一个类的作者                                       | @author description                                             |
| @deprecated   | 指名一个过期的类或成员                                 | @deprecated description                                         |
| {@docRoot}    | 指明当前文档根目录的路径                               | Directory Path                                                  |
| @exception    | 标志一个类抛出的异常                                   | @exception exception-name explanation                           |
| {@inheritDoc} | 从直接父类继承的注释                                   | Inherits a comment from the immediate surperclass.              |
| {@link}       | 插入一个到另一个主题的链接                             | {@link name text}                                               |
| {@linkplain}  | 插入一个到另一个主题的链接，但是该链接显示纯文本字体   | Inserts an in-line link to another topic.                       |
| @param        | 说明一个方法的参数                                     | @param parameter-name explanation                               |
| @return       | 说明返回值类型                                         | @return explanation                                             |
| @see          | 指定一个到另一个主题的链接                             | @see anchor                                                     |
| @serial       | 说明一个序列化属性                                     | @serial description                                             |
| @serialData   | 说明通过writeObject( ) 和 writeExternal( )方法写的数据 | @serialData description                                         |
| @serialField  | 说明一个ObjectStreamField组件                          | @serialField name type description                              |
| @since        | 标记当引入一个特定的变化时                             | @since release                                                  |
| @throws       | 和 @exception标签一样.                                 | The @throws tag has the same meaning as the @exception tag.     |
| {@value}      | 显示常量的值，该常量必须是static属性。                 | Displays the value of a constant, which must be a static field. |
| @version      | 指定类的版本                                           | @version info                                                   |


## 文档注释


在开始的 **/\*\*** 之后，第一行或几行是关于类、变量和方法的主要描述。

之后，你可以包含一个或多个各种各样的 **@** 标签。每一个 **@** 标签必须在一个新行的开始或者在一行的开始紧跟星号 **\***。

多个相同类型的标签应该放成一组。例如，如果你有三个 **@see** 标签，可以将它们一个接一个的放在一起。

下面是一个类的稳定注释的实例：

```java
/*** 这个类绘制一个条形图
* @author runoob
* @version 1.2
*/
```

#  一文彻底弄懂 fail-fast、fail-safe 机制（带你撸源码）

## 什么是 fail-fast（快速失败）？

Fail-fast 机制是**Java 集合**(Collection) 中的一种**错误机制**。在用迭代器遍历一个集合对象时，如果遍历过程中对集合对象的结构进行了修改（增加、删除），则会抛出**Concurrent Modification Exception**（**并发修改异常**）。

所以，在多线程环境下，是很容易抛出 Concurrent Modification Exception 的，比如线程 1 正在对集合进行遍历，此时线程 2 对集合进行修改（增加、删除）。但是，单线程就不会抛出吗？很明显，单线程也会有类似的情况，比如 main 线程在遍历时对集合进行修改（增加、删除、修改），那么 main 线程就会抛出 Concurrent Modification Exception 异常。

## Fail-fast 的原理

先看几个问题，让我们带着问题去思考。

下面几种情况，你觉得哪些会发生 fail-fast 抛出并发修改异常？

Q 1:

```java
List<String> list = new ArrayList<>();
list.add("1");
list.add("2");
list.add("3");
list.add("4");
Iterator<String> iter = list.iterator();
while (iter.hasNext()) {
    String tmp = iter.next();
    System.out.println(tmp);
    if (tmp.equals("1")) {
        list.remove("1");
    }
}
```

Q 2:

```java
List<String> list = new ArrayList<>();
list.add("1");
list.add("2");
list.add("3");
list.add("4");
Iterator<String> iter = list.iterator();
while (iter.hasNext()) {
    String tmp = iter.next();
    System.out.println(tmp);
    if (tmp.equals("3")) {
        list.remove("3");
    }
}
```

Q 3:

```java
List<String> list = new ArrayList<>();
list.add("1");
list.add("2");
list.add("3");
list.add("4");
Iterator<String> iter = list.iterator();
while (iter.hasNext()) {
    String tmp = iter.next();
    System.out.println(tmp);
    if (tmp.equals("4")) {
        list.remove("4");
    }
}
```

Q 4：

```java
List<String> list = new ArrayList<>();
list.add("1");
list.add("2");
list.add("3");
list.add("4");
for (String i : list) {
    if ("1".equals(i)) {
        list.remove("1");
    }
}
```

Q 5:

```java
List<String> list = Arrays.asList("1", "2", "3", "4");
for (String i : list) {
    if ("1".equals(i)) {
        list.remove("1");
    }
}
```

Q 6：

```java
List<String> list = new ArrayList<>();
list.add("1");
list.add("2");
list.add("3");
list.add("4");
Iterator<String> iter = list.iterator();
while (iter.hasNext()) {
    String tmp = iter.next();
    System.out.println(tmp);
    if (tmp.equals("1")) {
        iter.remove("1");
    }
}
```

**答案是：1、3、4**。

你答对了吗？

既然要讲原理，那就来撸源码吧！

撸一下 ArrayList 的源码！

因为我们是在遍历的时候对集合修改会发生 fail-fast，遍历集合一般都用迭代器，那就先看看迭代器的源码~

```java
private class Itr implements Iterator<E> {
    int cursor;       // index of next element to return
    int lastRet = -1; // index of last element returned; -1 if no such
    int expectedModCount = modCount;

    public boolean hasNext() {
        return cursor != size;
    }

    @SuppressWarnings("unchecked")
    public E next() {
        checkForComodification();
        int i = cursor;
        if (i >= size)
            throw new NoSuchElementException();
        Object[] elementData = ArrayList.this.elementData;
        if (i >= elementData.length)
            throw new ConcurrentModificationException();
        cursor = i + 1;
        return (E) elementData[lastRet = i];
    }

    public void remove() {
        if (lastRet < 0)
            throw new IllegalStateException();
        checkForComodification();

        try {
            ArrayList.this.remove(lastRet);
            cursor = lastRet;
            lastRet = -1;
            expectedModCount = modCount;
        } catch (IndexOutOfBoundsException ex) {
            throw new ConcurrentModificationException();
        }
    }

    final void checkForComodification() {
        if (modCount != expectedModCount)
            throw new ConcurrentModificationException();
    }
}
```

可以看到，罪魁祸首是 checkForComodification 这个方法，它是在 modCount != expectedModCount 的时候抛出了那该死的异常，而在 next 方法中第一句就是 checkForComodification，所以遍历集合才会可能抛出并发修改异常。

而且，在创建一个迭代器后，expectedModCount 的初始值就是 modCount 了，对集合修改只会改变 modCount，expectedModCount 只会在迭代器的 remove 方法中被修改为 modCount，稍后详细讲解迭代器的方法。

再看看 ArrayList 的一些方法！

Remove：

```java
public boolean remove(Object o) {
    if (o == null) {
        for (int index = 0; index < size; index++)
            if (elementData[index] == null) {
                fastRemove(index);
                return true;
            }
    } else {
        for (int index = 0; index < size; index++)
            if (o.equals(elementData[index])) {
                fastRemove(index);
                return true;
            }
    }
    return false;
}


private void fastRemove(int index) {
    modCount++;
    int numMoved = size - index - 1;
    if (numMoved > 0)
        System.arraycopy(elementData, index+1, elementData, index,
                         numMoved);
    elementData[--size] = null; // clear to let GC do its work
}
```

FastRemove 中对 modCount++了，所以后面 modCount 会和 expectedModCount 不相等，进而抛出并发修改异常。

Add：

```java
public boolean add(E e) {
    ensureCapacityInternal(size + 1);  // Increments modCount!!
    elementData[size++] = e;
    return true;
}
```

在 ensureCapacityInternal 方法里对 modCount++了。

Set：

```java
public E set(int index, E element) {
    rangeCheck(index);

    E oldValue = elementData(index);
    elementData[index] = element;
    return oldValue;
}
```

可以看出 set 方法并没有对 modCount++，所以对集合的某个元素进行修改并不会 fail-fast。

分析了一波源码后，来看看 Q 1、Q 2、Q 3，发现都调了 list 的 remove 方法，那么 modCount 必然不等于 expectedModCount 了，那么为什么 Q 2 没有发生 fail-fast 呢？其实这种情况在**阿里巴巴 Java 开发手册**（pdf 可以关注我的公众号“养猪的程序猿”免费获取，回复阿里巴巴 Java 开发手册）也有提到：

![image-20201003012527249](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/729f404eef9448c5b57edd4408965ae0~tplv-k3u1fbpfcp-zoom-in-crop-mark:1512:0:0:0.awebp)

其中的反例很意外，**“1”的时候没有抛出异常**，**改成“2”后就会抛出异常**！为什么？？其实这种情况与 Q 2 类似，都是 remove 倒数第二个元素，然而这时就没有抛出异常了，别急，我们再来看看源码！

迭代器类里有这俩变量：

```java
int cursor;       // index of next element to return
int lastRet = -1; // index of last element returned; -1 if no such
```

Cursor 是下一个要返回的变量的下标，lastRet 是上一个返回过的变量的下标。

```java
public boolean hasNext() {
    return cursor != size;
}
```

HasNext 方法告诉我们只有在下一个变量的下标不等于 size 的时候会告诉我们集合还有下一个元素。

但是！！在 remove 的时候，size- -了，那么对于 Q 2，删除“3”这个元素后，size 变为 3，而此时 cursor 也是 3，那么再走到 hasNext 时，就发现 cursor 和 size 相等了，那么就会退出遍历，“4”压根就不会被遍历到，让运行结果给我们验证一下：

![image-20201003014032974](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/6dfb866e295e4edb8fb778aebdf718e6~tplv-k3u1fbpfcp-zoom-in-crop-mark:1512:0:0:0.awebp)

所以 Q 2 没有抛出异常，因为 remove 后就退出了，还没来得及走到 next 方法呢~

那 Q 3 呢？Q 3 是删除“4”，也就是最后一个元素，按理说删了最后一个元素不就退出了吗？走不到下一次的 next 方法呀？

错，删完“4”并没有就直接退出哦！思考一下，remove 后 size 变成了 3，但此时 cursor 是 4，那么走到 hasNext 时，发现 4!=3，就会再次进入循环，那么结果就可想而知了，走到 next 方法就结束了它罪恶的一生……

看看运行结果吧：

![image-20201003014948342](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/3f9bd88667b348cb9a59f80c6749cfa9~tplv-k3u1fbpfcp-zoom-in-crop-mark:1512:0:0:0.awebp)

再来看看 Q 4，发现和前三个没什么不同，只是用增强 for 循环遍历的，也会抛出异常，为啥？

先用 javac 编译下面的代码：

```java
import java.util.ArrayList;
import java.util.List;

public class Main{
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        for (String i : list) {
            if ("1".equals(i)) {
                list.remove("1");
            }
        }
    }
}
```

得到. Class 文件：

```java
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.util.ArrayList;
import java.util.Iterator;

public class Main {
    public Main() {
    }

    public static void main(String[] var0) {
        ArrayList var1 = new ArrayList();
        var1.add("1");
        var1.add("2");
        var1.add("3");
        var1.add("4");
        Iterator var2 = var1.iterator();

        while(var2.hasNext()) {
            String var3 = (String)var2.next();
            if ("1".equals(var3)) {
                var1.remove("1");
            }
        }

    }
}
```

发现和 Q 1 几乎一样，所以使用增强 for 循环遍历集合和用迭代器实质是一样的。

再来看 Q 5，发现其他没什么区别，只是用了 Array.AsList () 方法生成的集合。

先看运行结果：![image-20201003020039543](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/a9b027062a5747138bfa2e8bd4739020~tplv-k3u1fbpfcp-zoom-in-crop-mark:1512:0:0:0.awebp)

咦？你骗我，这不是抛了异常吗？

……仔细看，抛出的是 UnsupportedOperationException，并非我们今天的主角——并发修改异常。

为什么？

看看 Array. AsList 源码就知道了：

![image-20201003020228893](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/e324df4a69d24261be58b32aee0e5861~tplv-k3u1fbpfcp-zoom-in-crop-mark:1512:0:0:0.awebp)

**发现 asList 生成的 ArrayList 是个静态内部类，并非 java. Util. ArrayList**，我们看看他有啥方法，哦不，他没有 ArrayList 的 add remove 那些方法，那就看他的父类 AbstractList 有啥方法吧……

```java
public void add(int index, E element) {
    throw new UnsupportedOperationException();
}

public E remove(int index) {
    throw new UnsupportedOperationException();
}

```

所以，根因还是 asList 生成的 ArrayList 并没有重写这些方法，导致调用这些方法就会抛出 UnsupportedOperationException。

敲黑板！！从这里也可以看出，**我们不能对 asList 生成的 ArrayList 进行增删改，况且别人底层直接就是用 final 修饰的数组……所以在开发时要避开这个坑**。

Q 6 就是正确的操作了。因为迭代器的 remove 方法会复位 expectedModCount 为 modCount，所以就不会出现不相等的情况了。

## 如何避免 fail-fast 抛异常？

1. 如果非要在遍历的时候修改集合，那么建议用迭代器的 remove 等方法，而不是用集合的 remove 等方法。(老实遵守阿里巴巴 java 开发规范……)

2. 如果是并发的环境，那还要对 Iterator 对象加锁；也可以直接使用 Collections. SynchronizedList。

3. CopyOnWriteArrayList（采用 fail-safe）

## 什么是 fail-safe（安全失败）？

ArrayList 使用 fail-fast 机制自然是因为它增强了数据的安全性。但在某些场景，我们可能想**避免 fail-fast 机制**抛出的异常，这时我们就要将 ArrayList 替换为使用 fail-safe 机制的 CopyOnWriteArrayList。

采用安全失败机制的集合容器，在 Iterator 的实现上没有设计抛出 ConcurrentModificationException 的代码段，从而避免了 fail-fast。

最后介绍下典型采用 fail-safe 的容器——CopyOnWriteArrayList~

写时复制，简单理解就是，当我们往一个容器添加元素的时候，先将当前容器复制出一个新的容器，然后新的容器里添加元素，添加完元素之后，再将原容器的引用指向新的容器。这样做的好处是我们可以对 CopyOnWrite 容器进行并发的读，而不需要加锁，因为当前容器不会添加任何元素。所以 CopyOnWrite 容器也是一种读写分离的思想，读和写不同的容器。

```java
public boolean add(E e) {
    final ReentrantLock lock = this.lock;
    lock.lock();
    try {
        Object[] elements = getArray();
        int len = elements.length;
        Object[] newElements = Arrays.copyOf(elements, len + 1);
        newElements[len] = e;
        setArray(newElements);
        return true;
    } finally {
        lock.unlock();
    }
}

final void setArray(Object[] a) {
    array = a;
}
```

发现在添加的时候是需要加锁的，否则多线程写的时候会复制出 N 个副本出来……

读的时候不需要加锁，如果读的时候有多个线程正在向 ArrayList 添加数据，读还是会读到旧的数据，因为写的时候不会锁住旧的 ArrayList。

CopyOnWrite 的应用场景：CopyOnWrite 并发容器用于读多写少的并发场景。比如白名单，黑名单，商品类目的访问和更新场景。

CopyOnWrite 的缺点：CopyOnWrite 容器有很多优点，但是同时也存在两个问题，即内存占用问题和数据一致性问题。所以在开发的时候需要注意一下：

**内存占用问题**。因为 CopyOnWrite 的写时复制机制，所以在进行写操作的时候，内存里会同时驻扎两个对象的内存，旧的对象和新写入的对象（注意: 在复制的时候只是复制容器里的引用，只是在写的时候会创建新对象添加到新容器里，而旧容器的对象还在使用，所以有两份对象内存）。如果这些对象占用的内存比较大，比如说 200 M 左右，那么再写入 100 M 数据进去，内存就会占用 300 M，那么这个时候很有可能造成频繁的 Yong GC 和 Full GC。

针对内存占用问题，可以通过压缩容器中的元素的方法来减少大对象的内存消耗，比如，如果元素全是 10 进制的数字，可以考虑把它压缩成 36 进制或 64 进制。或者不使用 CopyOnWrite 容器，而使用其他的并发容器，如 ConcurrentHashMap。

**数据一致性问题**。CopyOnWrite 容器只能保证数据的最终一致性，不能保证数据的实时一致性。所以如果你希望写入的的数据，马上能读到，请不要使用 CopyOnWrite 容器。

## 总结

迭代器的快速失败行为无法得到保证（上面的 Q 2，血淋淋的教训……），因为一般来说，不可能对是否出现不同步并发修改做出任何硬性保证。快速失败迭代器会尽最大努力抛出 ConcurrentModificationException。因此，为提高这类迭代器的正确性而编写一个依赖于此异常的程序是错误的做法：迭代器的快速失败行为应该仅用于检测 bug。
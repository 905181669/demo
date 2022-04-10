/**
 * @author: luozijian
 * @date: 7/6/21 10:02:16
 * @description:
 */
package com.example.reference;

/**
 * 1.思考：java是值传递还是引用传递？
 *
 * 误区：传递基础类型时是值传递，传递对象类型时是引用传递；
 * 误区的原因是没有理解透彻值传递和引用传递的区别。
 *
 * 值传递：实参传递给形参的是一份拷贝，形参改变后，实参不会改变；
 * 引用传递：形参改变，会导致实参也改变；
 *
 * 为什么会有误区，是因为在java中，很多人误认为传递对象的引用给形参时，形参调用set()方法修改了heap中对象的属性，就误认为
 * java传递对象引用时是引用传递，其实这是两个不同的概念，一个是实参和形参本身有没有被改变，一个是指向的对象被改变，因此得出
 * 了这个错误的结论；
 *
 * 结论：java是值传递，当传递对象引用时，复制的是引用指向对象的地址值给形参
 *
 *
 *
 * 2.思考：ThreadLocal的Entry弱引用原理
 *
 * Entry extends WeakReference<ThreadLocal<?>>
 * Entry[] table;
 * 拉链法，斐波那契数，黄金分割点，Doug Lea，table = new Entry[16],
 * HASH_INCREMENT=0x61c88647 = 1640531527(10进制)
 *
 * 为什么Doug Lea喜欢用2的n次方作为容器的size？
 *  0001
 * &0111 (2^3-1 = 7)
 * =0001
 *
 * &运算比%取模运算更高效
 *
 * 通过理论与实践，当我们用0x61c88647作为魔数累加为
 * 每个ThreadLocal分配各自的ID也就是threadLocalHashCode再与2的幂取模，得到的结果分布很均匀。
 *
 *
 * 3.释放内存过程：
 * ThreadLocal.remove()-->Entry.clear()-->tab[i].referent = null -->expungeStaleEntry(i)-->
 * tab[i].value = null, tab[i] = null;
 *
 * 这样堆中的ThreadLocal对象和value对象都没有引用，下一次gc就会被回收；
 * 其实tab[i].referent = null这代码没有也没关系，只要把ThreadLocal对象的强引用去掉，即使tab[i]这个弱引用还引用它，
 * 下次gc ThreadLocal对象也会被回收
 *
 * 4.get过程：
 * threadLocal.get()-->获取当前threadLocal的hashCode，然后 hashCode & (Entry数组长度-1)获取下标i，
 * 再判断tab[i].key == 当前threadLocal，是则返回tab[i].value,不是i++，拉链法寻找下一个tab slot
 *
 * 5.set过程：
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
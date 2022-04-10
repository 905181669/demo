/**
 * @author: luozijian
 * @date: 9/5/21 11:46:57
 * @description:
 */
package com.example.hystrix;

/**

 1.Hystrix熔断器执行机制

 Hystrix为每个commandKey都维护了一个熔断器，保持着对应的熔断器，所以当new XXXHystrixCommand()的时候依然能够保持着原来熔断器的状态。
 HystrixCommand执行都会调用 circuitBreaker.attemptExecution()；在attemptExecution里判断是否开启了熔断器

 https://www.jianshu.com/p/d978126bc1af


 2.熔断器状态转换
 原文链接：https://blog.csdn.net/manzhizhen/article/details/80296655
 CLOSE-->OPEN:
 时间窗口内（默认10秒）请求量大于请求量阈值（即circuitBreakerRequestVolumeThreshold，默认值是20），
 并且该时间窗口内错误率大于错误率阈值（即circuitBreakerErrorThresholdPercentage，默认值为50，表示50%），
 那么断路器的状态将由默认的CLOSED状态变为OPEN状态。看代码可能更直接：


 OPEN ->HALF_OPEN:
 当进入OPEN状态后，会进入一段睡眠窗口，即只会OPEN一段时间，所以这个睡眠窗口过去，就会“自动”从OPEN状态变成HALF_OPEN状态，
 这种设计是为了能做到弹性恢复，这种状态的变更，并不是由调度线程来做，而是由请求来触发

 HALF_OPEN --> CLOSE:


 HALF_OPEN --> OPEN:



 3.
 构造函数中的很多初始化工作只会集中在创建第一个Command时来做，后续创建的Command对象主要是从静态Map中取对应的实例来赋值



 4. 动态加载配置，通过Archaius来实现


 */

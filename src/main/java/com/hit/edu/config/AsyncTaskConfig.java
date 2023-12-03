package com.hit.edu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author: Li dong
 * @date: 2023/2/11 20:20
 * @description:
 * 分析下线程池处理的程序是CPU密集型还是IO密集型
 * CPU密集型：corePoolSize = CPU核数 + 1
 *
 * IO密集型：corePoolSize = CPU核数 * 2
 *
 * 2、maximumPoolSize：最大线程数
 * 当线程数>=corePoolSize，且任务队列已满时。线程池会创建新线程来处理任务。
 * 当线程数=maxPoolSize，且任务队列已满时，线程池会拒绝处理任务而抛出异常。
 *
 *
 * 3、keepAliveTime：线程空闲时间
 * 当线程空闲时间达到keepAliveTime时，线程会退出，直到线程数量=corePoolSize。
 * 如果allowCoreThreadTimeout=true，则会直到线程数量=0。
 *
 *
 * 4、queueCapacity：任务队列容量（阻塞队列）
 * 当核心线程数达到最大时，新任务会放在队列中排队等待执行
 *
 *
 * 5、allowCoreThreadTimeout：允许核心线程超时
 * 6、rejectedExecutionHandler：任务拒绝处理器
 * 两种情况会拒绝处理任务：
 *
 * 当线程数已经达到maxPoolSize，且队列已满，会拒绝新任务。
 * 当线程池被调用shutdown()后，会等待线程池里的任务执行完毕再shutdown。如果在调用shutdown()和线程池真正shutdown之间提交任务，会拒绝新任务。
 * 线程池会调用rejectedExecutionHandler来处理这个任务。如果没有设置默认是AbortPolicy，会抛出异常。
 *
 * ThreadPoolExecutor 采用了策略的设计模式来处理拒绝任务的几种场景。
 *
 * 这几种策略模式都实现了RejectedExecutionHandler 接口。
 *
 * AbortPolicy 丢弃任务，抛运行时异常。
 * CallerRunsPolicy 执行任务。
 * DiscardPolicy 忽视，什么都不会发生。
 * DiscardOldestPolicy 从队列中踢出最先进入队列（最后一个执行）的任务。
 *
 * tasks ：每秒的任务数，假设为500~1000
 *
 * taskcost：每个任务花费时间，假设为0.1s
 *
 * responsetime：系统允许容忍的最大响应时间，假设为1s
 *
 * 做几个计算
 *
 * corePoolSize = 每秒需要多少个线程处理？
 *
 * threadcount = tasks/(1/taskcost) = tasks*taskcout = (500 ~ 1000)*0.1 = 50~100 个线程。
 *
 * corePoolSize设置应该大于50。
 *
 * 根据8020原则，如果80%的每秒任务数小于800，那么corePoolSize设置为80即可。
 *
 * queueCapacity = (coreSizePool/taskcost)*responsetime
 *
 * 计算可得 queueCapacity = 80/0.1*1 = 800。意思是队列里的线程可以等待1s，超过了的需要新开线程来执行。
 *
 * 切记不能设置为Integer.MAX_VALUE，这样队列会很大，线程数只会保持在corePoolSize大小，当任务陡增时，不能新开线程来执行，响应时间会随之陡增。
 *
 * maxPoolSize 最大线程数在生产环境上我们往往设置成corePoolSize一样，这样可以减少在处理过程中创建线程的开销。
 *
 * rejectedExecutionHandler：根据具体情况来决定，任务不重要可丢弃，任务重要则要利用一些缓冲机制来处理。
 *
 * keepAliveTime和allowCoreThreadTimeout采用默认通常能满足。
 *
 * 以上都是理想值，实际情况下要根据机器性能来决定。如果在未达到最大线程数的情况机器cpu load已经满了，则需要通过升级硬件和优化代码，降低taskcost来处理。
 */
@Configuration
public class AsyncTaskConfig {
    /**
     * IO密集型任务  = 一般为2*CPU核心数（常出现于线程中：数据库数据交互、文件上传下载、网络数据传输等等）
     * CPU密集型任务 = 一般为CPU核心数+1（常出现于线程中：复杂算法）
     * 混合型任务  = 视机器配置和复杂度自测而定
     */
    @Bean(name = "asyncTaskExecutor")
    public ThreadPoolTaskExecutor asyncTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //1: 核心线程数目
        executor.setCorePoolSize(4);
        //2: 指定最大线程数,只有在缓冲队列满了之后才会申请超过核心线程数的线程
        executor.setMaxPoolSize(10);
        //3: 队列中最大的数目
        executor.setQueueCapacity(200);
        //4: 线程名称前缀
        executor.setThreadNamePrefix("CustomTask-");
        //5:当pool已经达到max size的时候，如何处理新任务
        // CallerRunsPolicy: 会在execute 方法的调用线程中运行被拒绝的任务,如果执行程序已关闭，则会丢弃该任务
        // AbortPolicy: 抛出java.util.concurrent.RejectedExecutionException异常
        // DiscardOldestPolicy: 抛弃旧的任务
        // DiscardPolicy: 抛弃当前的任务
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //6: 线程空闲后的最大存活时间(默认值 60),当超过了核心线程出之外的线程在空闲时间到达之后会被销毁
        executor.setKeepAliveSeconds(60);
        //7:线程空闲时间,当线程空闲时间达到keepAliveSeconds(秒)时,线程会退出,直到线程数量等于corePoolSize,如果allowCoreThreadTimeout=true,则会直到线程数量等于0
        executor.setAllowCoreThreadTimeOut(false);
        executor.initialize();
        return executor;
    }
}

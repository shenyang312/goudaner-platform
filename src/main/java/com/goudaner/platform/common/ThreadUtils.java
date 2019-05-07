package com.goudaner.platform.common;

import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author quxiqi
 * @email quxiqi@zskuaixiao.com
 * @description
 * @date 2019/4/20 17:31
 **/
@Component
public class ThreadUtils {

    private static class ScheduledThreadPoolExecutorHolder {
        private static final AtomicInteger COUNT = new AtomicInteger(0);
        private static final ScheduledThreadPoolExecutor SCHEDULED_THREAD_POOL_EXECUTOR =
                new ScheduledThreadPoolExecutor(
                        4,
                        r -> {
                            Thread thread = new Thread(r);
                            thread.setName("scheduled-" + COUNT.incrementAndGet());
                            return thread;
                        });
    }

    /**
     * 创建IO密集型临时线程池
     * @param queueLen
     * @param namePre
     * @return
     */
    public static ThreadPoolExecutor genTempIoThreadPoolExecutor(Integer queueLen, String namePre){
        return genTempThreadPoolExecutor(queueLen, namePre, Mode.IO);
    }

    /**
     * 创建CPU密集型临时线程池
     * @param queueLen
     * @param namePre
     * @return
     */
    public static ThreadPoolExecutor genTempCpuThreadPoolExecutor(Integer queueLen, String namePre){
        return genTempThreadPoolExecutor(queueLen, namePre, Mode.CPU);
    }

    /**
     * 创建临时线程池
     * @param queueLen 队列长度
     * @param namePre 线程池名称前缀
     * @return CompletionService
     */
    private static ThreadPoolExecutor genTempThreadPoolExecutor(Integer queueLen, String namePre, Mode mode) {
        AtomicInteger count = new AtomicInteger(0);
        return new ThreadPoolExecutor(
                queueLen == null ? mode.threadNum : 0,
                // 0,
                mode.threadNum,
                1,
                TimeUnit.MINUTES,
                queueLen == null
                        ? new LinkedBlockingQueue<>()
                        : new ArrayBlockingQueue<>(queueLen),
                r -> {
                    Thread thread = new Thread(r);
                    thread.setName(namePre + count.incrementAndGet());
                    return thread;
                },
                new ThreadPoolExecutor.CallerRunsPolicy()
        );
    }

    // 回收资源
    @PreDestroy
    public void destroy() {
        ScheduledThreadPoolExecutorHolder.SCHEDULED_THREAD_POOL_EXECUTOR.shutdown();
    }

    /**
     * 模式
     */
    public enum Mode {
        /**
         * IO密集型任务
         */
        IO(2 * Runtime.getRuntime().availableProcessors() + 1),
        /**
         * CPU密集型任务
         */
        CPU(Runtime.getRuntime().availableProcessors() + 1),
        ;
        /**
         * 最大线程数
         */
        public final int threadNum;

        Mode(int threadNum) {
            this.threadNum = threadNum;
        }
    }

    public static ScheduledExecutorService scheduledExecutorService() {
        return ScheduledThreadPoolExecutorHolder.SCHEDULED_THREAD_POOL_EXECUTOR;
    }
}

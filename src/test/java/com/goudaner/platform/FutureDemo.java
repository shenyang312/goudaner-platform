package com.goudaner.platform;

import java.util.concurrent.*;

public class FutureDemo {
        public static void main(String[] args) {
            ExecutorService executor = Executors.newCachedThreadPool();
            Task task = new Task();
//            Future<Integer> result = executor.submit(task);
//            executor.shutdown();
//            Task task = new Task();
            FutureTask<String> futureTask = new FutureTask<>(task);
            executor.submit(futureTask);
            executor.shutdown();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }

            System.out.println("主线程在执行任务");

            try {
                System.out.println("task运行结果"+futureTask.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            System.out.println("所有任务执行完毕");
        }
}
class Task implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println("子线程在进行计算");
        Thread.sleep(3000);
        int sum = 0;
        for(int i=0;i<100;i++)
            sum += i;
        return "啥玩意";
    }
}

package com.goudaner.platform;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureCook {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        long startTime = System.currentTimeMillis();
        // 第一步 网购厨具
        Callable<Chuju> onlineShopping = () -> {
            System.out.println("第一步：下单");
            System.out.println("第一步：等待送货");
            Thread.sleep(5000);  // 模拟送货时间
            System.out.println("第一步：快递送到");
            throw new RuntimeException("是可以拿到线程池中的异常");
//            return new Chuju();
        };
        FutureTask<Chuju> task = new FutureTask<Chuju>(onlineShopping);
        new Thread(task).start();
        // 第二步 去超市购买食材
        Thread.sleep(2000);  // 模拟购买食材时间
        Shicai shicai = new Shicai();
        System.out.println("第二步：食材到位");
        // 第三步 用厨具烹饪食材
        if (!task.isDone()) {  // 联系快递员，询问是否到货
            System.out.println("第三步：厨具还没到，心情好就等着（心情不好就调用cancel方法取消订单）");
        }
        try{
            Chuju chuju = task.get();
            System.out.println("第三步：厨具到位，开始展现厨艺");
            cook(chuju, shicai);

            System.out.println("总共用时" + (System.currentTimeMillis() - startTime) + "ms");
        }catch (Exception e){
            System.out.println("正常回滚呗");
        }

    }

    //  用厨具烹饪食材
    static void cook(Chuju chuju, Shicai shicai) {}

    // 厨具类
    static class Chuju {}

    // 食材类
    static class Shicai {}

}

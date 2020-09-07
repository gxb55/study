package com.prepare.change.thread;

import java.util.concurrent.*;

/**
 * @ClassName ThreadPool
 * @Author guoxiaobing
 * @Date 2020/8/13 11:03
 * @Version 1.0
 * @Description 线程池的demo
 */
public class ThreadPool implements Callable<String>{
  public static void main(String[] args) throws ExecutionException, InterruptedException {
      ExecutorService executorService = Executors.newFixedThreadPool(1);
      executorService.execute(()->{
          System.out.println(Thread.currentThread().getName());
      });
      executorService.shutdown();

  /*    int corePoolSize,
      int maximumPoolSize,
      long keepAliveTime,
      TimeUnit unit,
      BlockingQueue<Runnable> workQueue,
      ThreadFactory threadFactory,
      RejectedExecutionHandler handler*/

      ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 2, 1L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(5),
             Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());
      threadPoolExecutor.execute(()->{

      });
      threadPoolExecutor.shutdown();
      FutureTask<String> future = new FutureTask<String>(()->{
          System.out.println(111);
          System.out.println(111);
          System.out.println(111);
          Thread.sleep(1000);
          return "gxb";
      });

      Thread thread = new Thread(future);
      thread.start();
      String s = future.get();
      System.out.println(s);

  }

    @Override
    public String call() throws Exception {
        return null;
    }
}

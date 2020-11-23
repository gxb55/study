package com.atguigu.two;

/**
 * @program: 0311
 * @description: 线程操作资源类
 * @author: guoxiaobing
 * @create: 2020-03-21 19:42
 */
public class ThreadDemo0321 {
    public static void main(String[] args) throws Exception{
        System.out.println(12);
        aa aa= new aa();
        for(int i=0;i<200;i++){
            new Thread(()->{
                for(int j =0;j<100;j++){
                    aa.add();
                }
            }).start();
        }
        Thread.sleep(5000);
        System.out.println(aa.a);
    }
}
class aa{
   volatile int a = 0;
    public synchronized void add(){
        a++;
    }
}
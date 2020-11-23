package com.atguigu.two;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: study
 * @description: lock的condition详细讲解 以及demo 可以实现精确唤醒
 * @author: guoxiaobing
 * @create: 2020-02-01 15:52
 */
public class SyncAndReentrantLockDemo {
    /**
     * 题目 三个线程分顺序调用 实现 A-》B-》C
     * AA打印5次；
     * BB打印10次；
     * CC打印15次；
     * @param args
     */
    public static void main(String[] args) {
        share share = new share();
        new Thread(()->{
            for(int i=0;i<10;i++){
                //share.print(5,"AAA");
                share.printAll(5,"AAA",1,2);
            }

        },"t1").start();

        new Thread(()->{
            for(int i=0;i<10;i++){
               // share.print1(10,"BBB");
                share.printAll(10,"BBB",2,3);
            }

        },"t2").start();

        new Thread(()->{
            for(int i=0;i<10;i++){
                share.printAll(15,"CCC",3,1);
            }
        },"t3").start();
    }

}
class share{
    private Lock lock = new ReentrantLock();
    private int n = 1;
    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();
    private Condition conditionC = lock.newCondition();

    public void print(int num,String data){
        try{
            lock.lock();
            while (n!=1){
                conditionA.await();
            }
            for(int i=0;i<num;i++){
                System.out.println(data);
            }
            n=2;
            conditionB.signal();
        }catch (Exception e){

        }finally {
            lock.unlock();
        }

    }

    public void print1(int num,String data){
        try{
            lock.lock();
            while (n!=2){
                conditionB.await();
            }
            for(int i=0;i<num;i++){
                System.out.println(data);
            }
            n=3;
            conditionC.signal();
        }catch (Exception e){

        }finally {
            lock.unlock();
        }

    }
    public void print2(int num,String data){
        try{

            lock.lock();
            while (n!=3){
                conditionC.await();
            }
            for(int i=0;i<num;i++){
                System.out.println(data);
            }
            n=1;
            conditionA.signal();


        }catch (Exception e){

        }finally {
            lock.unlock();
        }

    }
//k 当前线程 p期待线程            5 aa 1 2
    public void printAll(int num,String data,int k,int p){
        try{
            lock.lock();
            while (n!=k){
                switch (k){
                    case 1: conditionA.await() ;
                    break;
                    case 2: conditionB.await();
                        break;
                    case 3: conditionC.await();
                        break;
                }
            }
            for(int i=0;i<num;i++){
                System.out.println(data);
            }
            n=p;
            switch (p){
                case 1: conditionA.signal();
                    break;
                case 2: conditionB.signal();
                    break;
                case 3: conditionC.signal();
                    break;
            }

        }catch (Exception e){

        }finally {
            lock.unlock();
        }

    }
}
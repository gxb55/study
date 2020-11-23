package com.atguigu.three;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: 2
 * @description: AbstractQueuedSynchronizer
 * @author: guoxiaobing
 * @create: 2020-11-15 12:46
 */
public class AQSDemo {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        lock.unlock();
    }
}
/**
 * abstractQueuedSynchronize 总结 解锁加锁都是依赖于sync，lock.lock(); 内部调用的是sync.lock(); 现在模拟三个线程来抢占锁 A B C； aqs其实就是队列 + 状态指示
 * 0：没有线程，当前线程可以获取锁；1：有线程，当前线程无法获取锁 A: NonfairSync -》lock 为例来讲解：
 *
 * final void lock() { if (compareAndSetState(0, 1)) setExclusiveOwnerThread(Thread.currentThread()); else acquire(1); }
 *
 * A线程来抢占的时候由于没有人占用state = 0，他就更换为1，当前占用的线程设置为自己。A线程开始办理也，同时将state修改为1 B线程来的时候交换失败，进入到acquire(1);方法
 *
 * public final void acquire(int arg) { if (!tryAcquire(arg) && acquireQueued(addWaiter(Node.EXCLUSIVE), arg))
 * selfInterrupt(); }
 *
 * tryAcquire(arg) protected boolean tryAcquire(int arg) { throw new UnsupportedOperationException(); }
 * 顶级父类，子类不实现就报错，这里我们以 NonfairSync为例，去NonfairSync类中找到tryAcquire 方法 acquires = 1 ；getState() 由于有人占用这里就是1
 * ；tryAcquire(arg)返回false取反为ture，继续往下判断， final boolean nonfairTryAcquire(int acquires) { final Thread current =
 * Thread.currentThread(); int c = getState(); if (c == 0) { if (compareAndSetState(0, acquires)) {
 * setExclusiveOwnerThread(current); return true; } } else if (current == getExclusiveOwnerThread()) { int nextc = c +
 * acquires; if (nextc < 0) // overflow throw new Error("Maximum lock count exceeded"); setState(nextc); * return true;
 * } return false; }
 *
 * addWaiter(Node.EXCLUSIVE) 方法继续判断其实就是入队，参数为null B节点走到这里的时候会进入到enq(node); 方法；C节点进入到这里会走 if里面，因为B过来的时候队列还不存在 C节点通过，
 * node.prev = pred; pred.next = node; compareAndSetTail(pred, node) 这三行将C加入到队列中区 新增节点的 private Node addWaiter(Node
 * mode) { Node node = new Node(Thread.currentThread(), mode); // Try the fast path of enq; backup to full enq on
 * failure Node pred = tail; if (pred != null) { node.prev = pred; if (compareAndSetTail(pred, node)) { pred.next =
 * node; return node; } } enq(node); return node; }
 *
 *
 * B节点： for 死循环 tail首次为null，设置头结点，头结点和尾结点是同一个； node.prev = t; t.next = node;，同时 compareAndSetTail(t, node)
 * 即将B加入到队列中去，并且尾结点执行B private Node enq(final Node node) { for (;;) { Node t = tail; if (t == null) { // Must initialize
 * if (compareAndSetHead(new Node())) tail = head; } else { node.prev = t; if (compareAndSetTail(t, node)) { t.next =
 * node; return t; } } } }
 *
 *
 * acquireQueued(addWaiter(Node.EXCLUSIVE), arg)) 最外层的是这样的方法
 *
 *
 * final boolean acquireQueued(final Node node, int arg) { boolean failed = true; try { boolean interrupted = false; for
 * (;;) { final Node p = node.predecessor(); if (p == head && tryAcquire(arg)) { setHead(node); p.next = null; // help
 * GC failed = false; return interrupted; } if (shouldParkAfterFailedAcquire(p, node) && parkAndCheckInterrupt())
 * interrupted = true; } } finally { if (failed) cancelAcquire(node); } }
 *
 * 内部调用 B进入的时候由于头结点是辅助节点所以，会再次去抢占，但是C的时候sign直接就是-1就返回了结束了
 * java.util.concurrent.locks.AbstractQueuedSynchronizer#shouldParkAfterFailedAcquire(java.util.concurrent.locks.AbstractQueuedSynchronizer.Node,
 * java.util.concurrent.locks.AbstractQueuedSynchronizer.Node) 这里就会暂定了 private final boolean parkAndCheckInterrupt() {
 * LockSupport.park(this); return Thread.interrupted(); }
 *
 * java.util.concurrent.locks.AbstractQueuedSynchronizer#acquireQueued(java.util.concurrent.locks.AbstractQueuedSynchronizer.Node,
 * int) 线程阻塞在这里，然后A执行完之后，B就会被唤醒就会在这里继续执行。执行的时候将B之前的傀儡节点删除，将B当做傀儡节点并且让其状态修改，node里面的thread设置为null 之前的辅助节点失去全部的引用gc会回收
 *
 *
 *
 *
 * public void unlock() { sync.release(1); } unparkSuccessor(h); public final boolean release(int arg) { if
 * (tryRelease(arg)) { Node h = head; if (h != null && h.waitStatus != 0) unparkSuccessor(h); return true; } return
 * false; }
 *
 * protected final boolean tryRelease(int releases) { int c = getState() - releases; if (Thread.currentThread() !=
 * getExclusiveOwnerThread()) throw new IllegalMonitorStateException(); boolean free = false; if (c == 0) { free = true;
 * setExclusiveOwnerThread(null); } setState(c); return free; }
 *
 * 释放节点
 *
 *
 *
 *
 *
 *
 *
 *
 */

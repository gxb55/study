package com.prepare.change.demo;

/** @ClassName Demo6 @Author guoxiaobing @Date 2020/8/31 19:31 @Version 1.0 @Description TODO */
public class Demo6 {
  public static void main(String[] args) {

  }

  public ListNode detectCycle(ListNode head) {
    ListNode slow = head;
    ListNode fast = head;

    while (fast != null && fast.next != null) {
      fast = fast.next.next;
      slow = slow.next;

      if (fast == slow) { // 利用快慢指针找相遇点
        ListNode slow2 = head; // 设置以相同速度的新指针从起始位置出发
        while (slow2 != slow) { // 未相遇循环。
          slow = slow.next;
          slow2 = slow2.next;
        }
        return slow;
      }
    }
    return null;
  }
}

class ListNode {
  int val;
  ListNode next;

  ListNode(int x) {
    val = x;
    next = null;
  }

}

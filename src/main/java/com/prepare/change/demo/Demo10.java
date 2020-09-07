package com.prepare.change.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/** @ClassName Demo10 @Author guoxiaobing @Date 2020/9/4 9:33 @Version 1.0 @Description 排列组合 */
public class Demo10 {
  public static void main(String[] args) {
    Demo10 demo10 = new Demo10();
    int[] arr = {1, 2, 3, 4, 5, 6};
    //demo10.combination(arr, new Stack<>(), 3, new ArrayList<>());
    demo10.combinationAll(arr, new Stack<>(), 3,0);
  }

  public void combination(int[] arr, Stack<Integer> stack, int k, List<Integer> indexList) {
    if (stack.size() == k) {
      System.out.println(stack.toString());
      return;
    }
    for (int i = 0; i < arr.length; i++) {
      if (!indexList.contains(i)) {
        indexList.add(i);
        stack.add(arr[i]);
        combination(arr, stack, k, indexList);
        stack.pop();
        indexList.remove(indexList.size() - 1);
      }
    }
  }

  public void combinationAll(int[] arr, Stack<Integer> stack, int k,int num) {
    if (stack.size() == k) {
        num++;
      System.out.println(stack.toString()+"---------  "+num);
      return;
    }
    for (int i = 0; i < arr.length; i++) {
      stack.add(arr[i]);
      combinationAll(arr, stack, k,num);
      stack.pop();
    }
  }
}

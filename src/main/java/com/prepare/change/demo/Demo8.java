package com.prepare.change.demo;

import java.util.Arrays;

/** @ClassName Demo8 @Author guoxiaobing @Date 2020/9/2 17:29 @Version 1.0 @Description TODO */
public class Demo8 {
  public static void main(String[] args) {
    Demo8 demo8 = new Demo8();
    int[] arr = {98, 95, 54, 62, 10, 23, 0, 0, 46, 13};
   // demo8.bubbleSort(arr);
     // demo8.quickSort(arr,0,arr.length-1);
    System.out.println(Arrays.toString(arr));
    int[] arr1 = {2,3,4,5,6};
    demo8.binarySearch(arr1,1);

  }

  /**
   * 给定二叉搜索树(BST)，找到BST中两个给定节点的最低公共祖先(LCA)。
   * 根据Wikipedia对LCA的定义:“在两个节点p和q之间定义的最低公共祖先是T中同时具有p和q作为后代的最低节点(在这里，我们允许一个节点作为其自身的后代)。”
   *
   * <p>解决 迭代实现 由于二叉搜索树是有序的，左子树的所有节点均小于根节点，右子树的所有节点均大于根节点，所以当某个时刻，根节点的值大于p而小于q，说明此时的根节点即为最近公共祖先。
   *
   * @param root
   * @param left
   * @param right
   * @return
   */
  public TreeNode getNode(TreeNode root, TreeNode left, TreeNode right) {
    if (root == null) {
      return null;
    }
    while (root != null) {
      if (root.val > left.val && root.val > right.val) {
        root = root.left;
      } else if (root.val < left.val && root.val < right.val) {
        root = root.right;
      } else {
        return root;
      }
    }
    return null;
  }

  public void bubbleSort(int[] arr) {
    if (arr == null || arr.length == 0) {
      return;
    }
    int temp;
    boolean falg = true;
    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < arr.length - i - 1; j++) {
        if (arr[j] > arr[j + 1]) { // 降序
          temp = arr[j + 1];
          arr[j + 1] = arr[j];
          arr[j] = temp;
          falg = false;
        }
      }
      if (falg) {
        break;
      } else {
        falg = true;
      }
    }
  }

  public void quickSort(int[] arr,int left,int right){
      if(left>=right){
          return;
      }
      int i = left;
      int j= right;
      int temp;
      int val =arr[left];
      while (j>i){
          while (arr[j]>=val && j>i){
              j--;
          }
          while (arr[i]<=val && j>i){
              i++;
          }
          if(j>i){
            temp =arr[j];
            arr[j]=arr[i];
            arr[i]=temp;
          }
      }
      arr[left] = arr[i];
      arr[i] = val;
      System.out.println(Arrays.toString(arr));
      quickSort(arr,left,i-1);
      quickSort(arr,i+1,right);

  }

  public int binarySearch(int[] arr,int x){
     /* if(x<arr[0] || x>arr[arr.length-1]){
          return -1;
      }*/
      int left =0;
      int right = arr.length-1;
      while (right>=left){
          int mid = (left+right)/2;
          if(arr[mid]>x){
              right = mid-1;
          }else if (x>arr[mid]){
              left = mid+1;
          }else{
              return mid;
          }
      }
      return -1;
  }
}

class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;

  TreeNode(int x) {
    val = x;
  }
}

class HeroNode {
  private int id;
  private String name;
  private HeroNode left;
  private HeroNode right;
    }

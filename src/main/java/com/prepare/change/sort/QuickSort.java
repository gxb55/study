package com.prepare.change.sort;

import java.util.Arrays;

/**
 * @ClassName QuickSort
 * @Author guoxiaobing
 * @Date 2020/8/15 11:17
 * @Version 1.0
 * @Description 快速排序递归实现
 */
public class QuickSort {
  public static void main(String[] args) {
    int[] arr = {4,8,9,-1,4,89,56,12,45,99,100,2,1,86};
    quickSort(arr,0,arr.length-1);
    System.out.println(Arrays.toString(arr));
  }
  public static void quickSort(int[] arr,int left,int right){
      if(left>right){
          return;
      }
      int i = left;
      int j= right;
      int val =arr[i];
      int temp;
      while (j>i){
          while (j>i && val<=arr[j]){
              j--;
          }
          while (j>i && val>=arr[i]){
              i++;
          }
          if(j>i){
             temp= arr[j];
             arr[j] =arr[i];
             arr[i]=temp;
          }
      }
      arr[left] = arr[i];
      arr[i]= val;
      quickSort(arr,i+1,right);
      quickSort(arr,left,i-1);

  }
}

package com.prepare.change.sort;

import java.util.Arrays;

/**
 * @ClassName HeapSort
 * @Author guoxiaobing
 * @Date 2020/9/14 18:48
 * @Version 1.0
 * @Description 堆排序。大顶堆 小顶堆
 */
public class HeapSort {
  public static void main(String[] args) {
      int[] arr = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,33};
      sort(arr);
  }

  public static void sort(int[] arr){
      int len = arr.length;
      for(int i=arr.length/2+1;i>=0;i--){
          makeHeap(arr,i,arr.length);
      }
      System.out.println(Arrays.toString(arr));
      int temp ;
      while (len>1){
          temp =arr[len-1];
          arr[len-1]=arr[0];
          arr[0]=temp;
          makeHeap(arr,0,len-1);
          len--;
      }

      /*for(int i=arr.length-1;i>=0;i--){
          temp=arr[i];
          arr[i]=arr[0];
          arr[0]=temp;
          makeHeap(arr,0,i);
      }*/
      System.out.println(Arrays.toString(arr));
  }
  public static void makeHeap(int[] arr,int index,int len){
      int val = arr[index];
      for(int i=index*2+1;i<len;i=i*2+1){
         if(i+1<len && arr[i]<arr[i+1]){
             i++;
         }
         if(arr[i]>val){
             arr[index]=arr[i];
             index =i;
         }else{
             break;
         }
      }
      arr[index] = val;

  }
}

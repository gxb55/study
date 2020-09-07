package com.prepare.change.back;

import java.util.Arrays;

/**
 * @ClassName ShellSort
 * @Author guoxiaobing
 * @Date 2020/7/27 11:31
 * @Version 1.0
 * @Description 希尔排序
 */
public class ShellSort {
  public static void main(String[] args) {
    //
      //int arr[] = {800,4, 6, 8, 5, 9,-1};
      // 创建要给80000个的随机的数组
      long t =System.currentTimeMillis();
     int[] arr = new int[8000000];
      for (int i = 0; i < 8000000; i++) {
          arr[i] = (int) (Math.random() * 8000000); // 生成一个[0, 8000000) 数
      }
     // BubbleSort(arr);
      sort(arr);
      //System.out.println(Arrays.toString(arr));
      System.out.println(System.currentTimeMillis()-t);//2450
  }

  public static void sort(int[] arr){
      for(int gap =arr.length/2;gap>0;gap/=2){
          for(int i= gap;i<arr.length;i++){
              int val =arr[i];
              int index =i;
              while (index-gap>=0 && arr[index-gap]>val){//降序
                  arr[index] =arr[index-gap];
                  index-=gap;
              }
              arr[index] =val;
          }
      }
  }
  public static void BubbleSort(int[] arr){
      int temp;
      boolean flag = false;
      for(int i=0;i<arr.length;i++){
          for(int j=0;j<arr.length-i-1;j++){
              if(arr[j]>arr[j+1]){//升序
               temp =arr[j];
               arr[j]=arr[j+1];
               arr[j+1]=temp;
               flag =true;
              }
          }
          if(!flag){
              break;
          }else{
              flag= false;
          }

      }
  }
}

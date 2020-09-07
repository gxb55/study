package com.prepare.change.sort;

import java.util.Arrays;

/**
 * @ClassName allSort
 * @Author guoxiaobing
 * @Date 2020/8/24 16:50
 * @Version 1.0
 * @Description 各种排序一次成功
 */
public class allSort {
  public static void main(String[] args) {
      int[] arr ={0,1,2,5,6,65,23,12,89,1002,1002};
      //selectSort(arr);
     // insertSort(arr);
      shellSort(arr);
      System.out.println("10003".equals(10003));
      System.out.println(Arrays.toString(arr));
  }

    /**
     * 选择排序，选出最大的或者最小的然后放到指定的位置即可
     * @param arr
     */
  public static void selectSort(int[] arr){
      for(int i=0;i<arr.length;i++){
          int index = i;
          int val =arr[index];
          for(int j=i+1;j<arr.length;j++){
                if(arr[j]>val){//降序
                    index=j;
                    val =arr[j];
                }
          }
          if(i!=index){
              arr[index]=arr[i];
              arr[i]=val;
          }
      }
  }

    /**
     * 插入排序，单层循环，值往前推
     * @param arr
     */
    public static void insertSort(int[] arr){
        for(int i=1;i<arr.length;i++){
            int index = i;
            int val =arr[index];
           while (index-1>=0 && val<arr[index-1]){
               arr[index]=arr[index-1];
               index--;
           }
           if(i!=index){
               arr[index] =val;
           }
        }
    }

    public static void shellSort(int[] arr){
        for(int gap =arr.length/2;gap>0;gap=gap/2){
            for(int i=gap;i<arr.length;i++){
                int index = i;
                int val = arr[i];
                while (index-gap>=0 && val>arr[index-gap]){//降序
                    arr[index] = arr[index-gap];
                    index -= gap;
                }
                if(index!=i){
                    arr[index]=val;
                }
            }

        }
    }
}

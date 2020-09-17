package com.prepare.change.back;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName FibonacciSearch
 * @Author guoxiaobing
 * @Date 2020/9/8 10:46
 * @Version 1.0
 * @Description 斐波那锲数列
 */
public class FibonacciSearch {
  public static void main(String[] args) {
      FibonacciSearch search = new FibonacciSearch();
      List<Integer> list = new ArrayList<>();
     // search.getList(15,list);
     // System.out.println(list.toString());
      int[] arr = {1,3,5,7,9,12,14,20,137};
      int res = search.fiboSearch(arr, 14);
      int binarySearch = search.binarySearch(arr, 14);
      System.out.println(res);
      System.out.println(binarySearch);
  }

  public void getList(int n, List<Integer> list){
      if(n!=list.size()){
          if(list.size()==0){
              list.add(1);
          }else if(list.size()==1){
              list.add(1);
          }else {
              list.add(list.get(list.size()-1)+list.get(list.size()-2));
          }
          getList(n,list);
      }
      return;
  }

  public int fiboSearch(int[] arr,int x){
      if(x>arr[arr.length-1] || x<arr[0]){
          return -1;
      }
      int low = 0;
      int heigh = arr.length-1;
      int k = 0;
      List<Integer> list = new ArrayList<>();
      getList(15,list);
      while (list.get(k)-1<heigh){
          k++;
      }
      int[] ints = Arrays.copyOf(arr, list.get(k) - 1);
      for(int i=heigh+1;i<ints.length;i++){
          ints[i]=arr[heigh];
      }

      while (heigh>=low){
          int mid = low + list.get(k-1)-1;
          if(x>ints[mid]){
              k-=2;
              low=mid+1;
          }else if (x<ints[mid]){
              k-=1;
              heigh=mid-1;
          }else {
              if (mid>heigh){
                  return heigh;
              }else{
                  return mid;
              }
          }
      }
      return -1;
  }

  public int loopSearch(int[] arr,int x){
      for (int i=0;i<arr.length;i++){
          if(x==arr[i]){
              return i;
          }
      }
      return -1;
  }

  public int binarySearch(int[] arr,int x){
      int low = 0;
      int high = arr.length-1;;
      while (high>=low){
          int mid = (low+high)/2;
          if(x>arr[mid]){
              low = mid+1;
          }else if (x<arr[mid]){
              high=mid-1;
          }else{
              return mid;
          }
      }
      return -1;
  }
}

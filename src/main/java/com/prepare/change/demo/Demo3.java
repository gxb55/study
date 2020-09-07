package com.prepare.change.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName Demo3
 * @Author guoxiaobing
 * @Date 2020/8/20 14:48
 * @Version 1.0
 * @Description 组成做大数字
 */
public class Demo3 {
  public static void main(String[] args) {
    int[] arr ={123,546,80,32,32};
    long max = 0;
    List<Long> list = new ArrayList<>();
    List<Integer> indexList = new ArrayList<>();
    getMaxNum(arr,new ArrayList<>(),list,indexList);
    for(Long integer:list){
        max =Math.max(integer,max);
    }
    System.out.println(max);
  }
  public static void getMaxNum(int[] arr, List<Integer> list,List<Long> resultList,List<Integer> indexList){
      if(arr.length == list.size()){
          /*StringBuilder stringBuilder = new StringBuilder();
          for (int i=0;i<list.size();i++){
              stringBuilder.append(list.get(i));
          }
          resultList.add(Long.parseLong(stringBuilder.toString()));*/
          System.out.println(Arrays.toString(list.toArray()));
          return ;
      }

      for(int i=0;i<arr.length;i++){
          if(!indexList.contains(i)){
              list.add(arr[i]);
              indexList.add(i);
              getMaxNum(arr,list,resultList,indexList);
              list.remove(list.size()-1);
              indexList.remove(indexList.size()-1);
          }
      }
  }
}

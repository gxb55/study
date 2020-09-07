package com.prepare.change.demo;

/**
 * @ClassName Demo2
 * @Author guoxiaobing
 * @Date 2020/8/20 11:10
 * @Version 1.0
 * @Description 数组单调递增，或者递减求做大的值setQrcodeDefaultValue
 */
public class Demo2 {
  public static void main(String[] args) {
      int[] arr ={1,2,5,8,3,2,6,1,9};
      System.out.println(getMax(arr));
  }

  public static int getMax(int[] arr){
      int[] left = new int[arr.length];
      int[] right = new int[arr.length];

      for(int i=0;i<left.length;i++){
          left[i]=1;
          for(int j=0;j<i;j++){
              if(arr[i]>arr[j]){//右边的大于左边的
                  left[i]=Math.max(left[i],left[j]+1);
              }
          }
      }
      for( int i=right.length-1;i>=0;i--){
          right[i]=1;
            for(int j=right.length-1;j>=i;j--){
                if(arr[i]>arr[j]){
                    right[i]=Math.max(right[i],right[j]+1);
                }
            }
      }
      int max =0;
      for(int i=0;i<left.length;i++){
          max=Math.max(max,left[i]+right[i]);
      }
      return max-1;
  }
}

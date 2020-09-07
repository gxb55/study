package com.prepare.change.demo;

/** @ClassName Demo5 @Author guoxiaobing @Date 2020/8/22 17:07 @Version 1.0 @Description 到达终点 */

/*给定一个数组，从第一个开始，正好走到数组最后，所使用的最少步骤数。要求：
第一步从第一元素开始，第一步小于<len/2（len为数组的长度）。从第二步开始，只能以所在成员的数字走相应的步数，不能多也不能少, 如果目标不可达返回-1，输出最少的步骤数，不能往回走。
输入7 5 9 4 2 6 8 3 5 4 3 9输出2
输入1 2 3 7 1 5 9 3 2 1输出-1*/
public class Demo5 {
  public static void main(String[] args) {
    int[] arr = {7, 5, 9, 4, 2, 6, 8, 3, 9, 4, 3, 9,6, 8, 3, 5, 4, 3};
    //int[] arr = {1, 2, 3 ,7, 1 ,5 ,9, 3 ,2, 1};
      int leng = getLeng(arr);
      System.out.println(leng);
  }


    private static int getLeng(int[] arr) {
      int sum = -1;
        for(int j=arr.length/2;j>0;j--){
            int index =j;
            int i=1;
            j = arr[j-1];
            while (j<arr.length){
                if(i==1){
                    j=index+j;
                }else{
                    j=arr[j-1]+j;
                }
                i++;

            }

            if(j == arr.length){
                if(sum==-1){
                    sum=i;
                }else {
                    sum = Math.min(sum,i);
                }
            }
            j=index;
        }
        return sum;
    }

}

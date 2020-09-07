package com.prepare.change.demo;

import java.util.Stack;

/**
 * @ClassName Demo4 @Author guoxiaobing @Date 2020/8/20 15:18 @Version 1.0 @Description 特殊计算
 * 特殊符号代替普通的计算方式比如x#y = 2*x+y，x$y = x+3y，#优先级高于$
 *
 * <p>比如输入5#2$6 输出结果就是30，因为先算5#2 = 12，再算12$6=30
 *
 * <p>* 1.两个栈，一个数栈s1，一个符号栈s2，s1直接入栈， *
 * 2.s2如果是空的或者新加入的优先级比s2栈顶的运算符优先级高则直接入栈； *
 * 3.如果新加入的运算符优先级小于s2栈顶的运算符的优先级，则从数栈中取出两个数先取出的是num1 后取出的是num2 让num2 运算符 num1 计算出结果 *
 * 4.将运算符全部出栈，然后如同第三步一样的计算最后得出结果。
 */
public class Demo4 {
  public static void main(String[] args) {
      String str = "5#2$6";
      long sum = getSum(str);
      System.out.println(sum);
  }
  public static long getSum(String str){
      Stack<Integer> numStack = new Stack<>();
      Stack<String> operStack = new Stack<>();
      StringBuilder strNum = new StringBuilder();
      for(int i=0;i<str.length();i++){
          char c = str.charAt(i);
          strNum = new StringBuilder();
          if(c>=48 && c<=57){
              while (i<str.length() && str.charAt(i)>=48 && str.charAt(i)<=57){
                      strNum.append(str.charAt(i));
                  i++;
              }
              i--;
              numStack.add(Integer.parseInt(strNum.toString()));
          }else{
              if(operStack.isEmpty() || compareOper(String.valueOf(c)) > compareOper(operStack.peek())){
                  operStack.add(String.valueOf(c));
              }else{
                  Integer num1 = numStack.pop();
                  Integer num2 = numStack.pop();
                  if(compareOper(String.valueOf(c)) ==1){
                      numStack.push(2*num2+num1);
                  }else{
                      numStack.push(num2+num1*3);
                  }
          }



          /*if(c>=48 && c<=57 && flag ){
            strNum.append(c);
          }else{
              numStack.add(Integer.parseInt(strNum.toString()));
              strNum = new StringBuilder();
              flag = false;
          }

          if(!flag){
              flag =true;

              }*/
          }
      }
      while (!operStack.isEmpty()){
          String pop = operStack.pop();
          Integer num1 = numStack.pop();
          Integer num2 = numStack.pop();
          if(compareOper(pop) ==1){
              numStack.push(2*num2+num1);
          }else{
              numStack.push(num2+num1*3);
          }
      }
      return numStack.pop();
  }

  public static int compareOper(String str) {
    int i = 0;
    switch (str) {
      case "#":
        i = 1;
        break;
      case "$":
        i = 2;
        break;
      default:
        break;
    }
    return i;
  }
}

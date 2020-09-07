package com.prepare.change.demo;

import java.util.*;

/**
 * @ClassName Demo7
 * @Author guoxiaobing
 * @Date 2020/9/2 9:53
 * @Version 1.0
 * @Description TODO
 */
public class Demo7 {
  public static void main(String[] args) {
    List list = new ArrayList();
    list.add(1);
    list.add(2);
    list.add(3);
    System.out.println(list.toString());
  }

    /**
     * 给定一个只包含括号的字符串，判断字符串是否有效。其中，括号种类包括：'(' ')' '{' '}' '[' ']'。
     * 有效字符串需满足：1）左括号必须用相同类型的右括号闭合；2）左括号必须以正确的顺序闭合，注意空字符串可被认为是有效字符串。
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        Map<Character,Character> map = new HashMap<Character,Character>(){{
            put('{','}'); put('[',']'); put('(',')'); put('?','?');
        }};
        if(s.length() > 0 && !map.containsKey(s.charAt(0))) return false;
        LinkedList<Character> stack = new LinkedList<Character>() {{ add('?'); }};
        for(Character c : s.toCharArray()){
            if(map.containsKey(c)) stack.addLast(c);
            else if(map.get(stack.removeLast()) != c) return false;
        }
        return stack.size() == 1;
    }

    public int getCount(int money){
        int res = 1024 - money;
        int count = 0;
        int[] arr = {1,4,16,64};
        int index = 3;
        while (res!=0){
            count = count + res/arr[index];
            res =res%arr[index];
            index--;
        }
        return count;
    }

}

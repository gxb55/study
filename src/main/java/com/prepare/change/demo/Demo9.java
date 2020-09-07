package com.prepare.change.demo;

import java.util.*;

/** @ClassName Demo9 @Author guoxiaobing @Date 2020/9/3 14:06 @Version 1.0 @Description TODO */
public class Demo9 {
  public static void main(String[] args) {
      Demo9 demo9 = new Demo9();
      int[] arr = {-1, 0 ,1 ,2, -1 ,-4};
      ArrayList<ArrayList<Integer>> arrayLists = demo9.threeSum(arr);
      for(List li:arrayLists){
          System.out.println(li.toString());
      }
      TreeNode root = new TreeNode(8);
      TreeNode left = new TreeNode(6);
      TreeNode right = new TreeNode(10);
      root.left = left;
      root.right =right;
      left.left = new TreeNode(5);
      left.right = new TreeNode(7);

      right.left = new TreeNode(9);
      right.right = new TreeNode(11);

      ArrayList<ArrayList<Integer>> print = demo9.Print(root);
      for(List li:print){
          System.out.println(li.toString());
      }
  }

  public ArrayList<ArrayList<Integer>> threeSum(int[] num) {
      ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
    if (num == null || num.length == 0) {
      return list;
    }
      Arrays.sort(num);
    for (int i = 0; i < num.length; i++) {
      for(int j=i+1;j<num.length;j++){
          for(int k=j+1;k<num.length;k++){
              ArrayList<Integer> numList = new ArrayList();
            if(num[j]+num[i]+num[k]==0){
                numList.add(num[j]);
                numList.add(num[i]);
                numList.add(num[k]);
                numList.sort(new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        return o1-o2>=0?1:-1;
                    }
                });
                if(!list.contains(numList)){
                    list.add(numList);
                }
            }
          }
      }
    }
    return list;
  }

    ArrayList<ArrayList<Integer> > Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer> > list = new ArrayList<ArrayList<Integer> >();
        if(pRoot == null){
            return list;
        }
        Queue<TreeNode> stack = new LinkedList<>();
        stack.add(pRoot);
        while (!stack.isEmpty()){
            ArrayList<Integer> cList = new ArrayList<Integer>();
            int num = stack.size();
            for(int i=0;i<num;i++){
                TreeNode pop = stack.poll();
                cList.add(pop.val);
                if(pop.left!=null){
                    stack.add(pop.left);
                }
               if(pop.right!=null){
                   stack.add(pop.right);
               }

            }
            System.out.println(cList.toString());
            list.add(cList);
        }
        return list;

    }
}


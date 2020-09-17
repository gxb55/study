package com.prepare.change.back;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @ClassName HuffmanTree
 * @Author guoxiaobing
 * @Date 2020/9/9 14:58
 * @Version 1.0
 * @Description 霍夫曼树的构建
 */
public class HuffmanTree {
  public static void main(String[] args) {
    int[] arr = {13,7,8,3,29,6,1};
      createTree(arr);

  }
  public static void createTree(int[] arr){
    Arrays.sort(arr);
   List<NodeTree> list = new ArrayList<>();
    for (int i = 0; i <arr.length ; i++) {
        list.add(new NodeTree(i));
    }
    while (list.size()>1){
        NodeTree left = list.get(0);
        NodeTree right = list.get(1);
        NodeTree root = new NodeTree(left.id+right.id);
        root.left = left;
        root.right =right;

        list.add(root);
        list.remove(left);
        list.remove(right);
         list.sort(new Comparator<NodeTree>() {
             @Override
             public int compare(NodeTree o1, NodeTree o2) {
                 return o1.id-o2.id>=0? 1:-1;
             }
         });
    }
    System.out.println(list.get(0));
  }
}

class NodeTree {
    int id;
    NodeTree left;
    NodeTree right;

    public NodeTree(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public NodeTree getLeft() {
        return left;
    }

    public void setLeft(NodeTree left) {
        this.left = left;
    }

    public NodeTree getRight() {
        return right;
    }

    public void setRight(NodeTree right) {
        this.right = right;
    }
}

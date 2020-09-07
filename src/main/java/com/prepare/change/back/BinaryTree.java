package com.prepare.change.back;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/** @ClassName BinaryTree @Author guoxiaobing @Date 2020/7/27 14:01 @Version 1.0 @Description 树 */
public class BinaryTree {
  public static Node pre;

  public static void main(String[] args) {
    Node node1 = new Node(1);
    Node node2 = new Node(2);
    Node node3 = new Node(3);
    node1.setLeft(node2);
    node1.setRight(node3);

    Node node4 = new Node(4);
    node2.setLeft(node4);
    node2.setRight(new Node(5));
    node4.setLeft(new Node(8));
    node4.setRight(new Node(9));

    node3.setLeft(new Node(6));
    node3.setRight(new Node(7));

    //  preOrder(node1);
    // infixOrder(node1);
    // postOrder(node1);
    // levelOrder(node1);
    /*System.out.println("删除前 先序便利");
    preOrderLoop(node1);
    beforeDelNode(node1, 6);
    System.out.println("删除6后 先序便利");
    preOrderLoop(node1);*/

    /* postOrder(node1);
    Node node = postSearchNode(3, node1);
    System.out.println(node);*/

    int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    Node node = arrToTree(0, arr);
    cluePreTree(node);
    cluePreTreeLoop(node);
  }
  // preOrder  infixOrder  postOrder

  /**
   * 先序便利 根左右
   *
   * @param node
   */
  public static void preOrder(Node node) {
    if (node == null) {
      return;
    }
    System.out.println(node);
    preOrder(node.getLeft());
    preOrder(node.getRight());
  }

  /**
   * 中序便利 左根右
   *
   * @param node
   */
  public static void infixOrder(Node node) {
    if (node == null) {
      return;
    }
    infixOrder(node.getLeft());
    System.out.println(node);
    infixOrder(node.getRight());
  }

  /**
   * 后续便利 左 右 根
   *
   * @param node
   */
  public static void postOrder(Node node) {
    if (node == null) {
      return;
    }
    postOrder(node.getLeft());
    postOrder(node.getRight());
    System.out.println(node);
  }

  /** 层次便利 */
  public static void levelOrder(Node node) {
    if (node == null) {
      return;
    }
    Queue<Node> queue = new LinkedList<>(); // 队列先进先出 跟list的区别是
    queue.add(node);
    while (!queue.isEmpty()) {
      Node poll = queue.poll();
      System.out.println(poll);
      if (poll.getLeft() != null) {
        queue.add(poll.getLeft());
      }
      if (poll.getRight() != null) {
        queue.add(poll.getRight());
      }
    }
  }

  /**
   * 循环便利
   *
   * @param node
   */
  public static void preOrderLoop(Node node) {
    if (node == null) {
      return;
    }
    Stack<Node> stack = new Stack<>();
    stack.add(node);
    while (!stack.isEmpty()) {
      Node pop = stack.pop();
      System.out.println(pop);
      if (pop.getRight() != null) {
        stack.add(pop.getRight());
      }
      if (pop.getLeft() != null) {
        stack.add(pop.getLeft());
      }
    }
  }

  /**
   * 中序便利 左根右 循环是用栈先进后出 所以放入的顺序就是 右 左
   *
   * @param node
   */
  public static void infixOrderLoop(Node node) {
    if (node == null) {
      return;
    }
    Stack<Node> stack = new Stack<>();
    Node temp = node;
    while (!stack.isEmpty() && temp != null) { //
      while (temp != null) {
        stack.add(temp);
        temp = temp.getLeft();
      }
      temp = stack.pop();
      System.out.println(temp);
      if (temp.getRight() != null) {
        stack.add(temp.getRight());
      } else {
        temp = null;
      }
    }
  }

  public static void postOrderLoop(Node node) {
      if(node == null){
          return;
      }
      Stack<Node> stack = new Stack<>();
      Node temp =node;
      while (!stack.isEmpty() && temp!=null){
          while (temp!=null){
              stack.add(temp);
              temp=temp.getLeft();
          }

      }
  }

        public static void beforeDelNode(Node root, int no) {
    if (root == null) {
      System.out.println("空数哦");
      return;
    }
    if (root.getId() == no) {
      root = null;
      return;
    }
    delNode(root, no);
  }

  private static void delNode(Node root, int no) {
    if (root == null) {
      return;
    }
    if (root.getLeft() != null && root.getLeft().getId() == no) {
      root.setLeft(null);
      return;
    }
    if (root.getRight() != null && root.getRight().getId() == no) {
      root.setRight(null);
      return;
    }
    delNode(root.getLeft(), no);
    delNode(root.getRight(), no);
  }

  private static Node proSearchNode(int no, Node root) {
    if (root == null) {
      return null;
    }
    System.out.println("先序查找");
    if (root.getId() == no) {
      return root;
    }
    Node temp;
    temp = proSearchNode(no, root.getLeft());
    if (temp != null) {
      return temp;
    }
    temp = proSearchNode(no, root.getRight());
    return temp;
  }

  private static Node infixSearchNode(int no, Node root) {
    if (root == null) {
      return null;
    }
    System.out.println("中序查找");
    Node temp;
    temp = infixSearchNode(no, root.getLeft());
    if (temp != null) {
      return temp;
    }
    if (root.getId() == no) {
      return root;
    }

    temp = infixSearchNode(no, root.getRight());
    return temp;
  }

  private static Node postSearchNode(int no, Node root) {
    if (root == null) {
      return null;
    }
    System.out.println("先序便利查找");
    Node temp = postSearchNode(no, root.getLeft());
    if (temp != null) {
      return temp;
    }
    temp = postSearchNode(no, root.getRight());
    if (temp != null) {
      return temp;
    }
    if (root.getId() == no) {
      return root;
    }
    return null;
  }

  public static Node arrToTree(int index, int[] arr) {
    if (arr == null || arr.length == 0) {
      System.out.println("空数组");
      return null;
    }
    if (index < arr.length) {
      // 当前元素
      Node node = new Node(arr[index]);
      // 左
      if (index * 2 + 1 < arr.length) {
        Node node1 = arrToTree(index * 2 + 1, arr);
        node.setLeft(node1);
      }
      // 右
      if (index * 2 + 2 < arr.length) {
        Node node1 = arrToTree(index * 2 + 2, arr);
        node.setRight(node1);
      }
      return node;
    }
    return null;
  }

  /** 线索化二叉树 左子树指向前一个节点 右子树指向后一个节点 */
  public static void cluePreTree(Node node) {
    if (node == null) {
      return;
    }
    cluePreTree(node.getLeft());
    if (node.getLeft() == null) {
      node.setLeftType(1);
      node.setLeft(pre);
    }
    if (pre != null && pre.getRight() == null) {
      pre.setRightType(1);
      pre.setRight(node);
    }
    pre = node;
    cluePreTree(node.getRight());
  }

  public static void cluePreTreeLoop(Node node) {
    if (node == null) {
      return;
    }
    if (node.getLeftType() == 0) {
      cluePreTreeLoop(node.getLeft());
    }
    System.out.println(node);
    if (node.getRightType() == 0) {
      cluePreTreeLoop(node.getRight());
    }
  }
}

class Node {
  private int id;
  private Node left;
  private Node right;
  private int leftType = 0; // 0 原有节点；1线索化的节点
  private int rightType = 0; // 0 原有节点；1线索化的节点

  public Node(int id) {
    this.id = id;
  }

  public Node getLeft() {
    return left;
  }

  public void setLeft(Node left) {
    this.left = left;
  }

  public Node getRight() {
    return right;
  }

  public void setRight(Node right) {
    this.right = right;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getLeftType() {
    return leftType;
  }

  public void setLeftType(int leftType) {
    this.leftType = leftType;
  }

  public int getRightType() {
    return rightType;
  }

  public void setRightType(int rightType) {
    this.rightType = rightType;
  }

  @Override
  public String toString() {
    return "Node{" + "id=" + id + '}';
  }
}

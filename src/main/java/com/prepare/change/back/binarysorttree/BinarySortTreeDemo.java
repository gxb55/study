package com.prepare.change.back.binarysorttree;

/**
 * @ClassName BinarySortTreeDemo
 * @Author guoxiaobing
 * @Date 2020/9/16 9:42
 * @Version 1.0
 * @Description 二叉排序树 binary sort tree
 */
public class BinarySortTreeDemo {
  public static void main(String[] args) {
      BinarySortTree binarySortTree = new BinarySortTree();
      int[] arr = {7,3,10,12,5,1,9,2,4};
      // 左《根 《右 从小到大的顺序来排列
      for(int i=0;i<arr.length;i++){
          binarySortTree.add(arr[i]);
      }
      binarySortTree.infixOrder();
      /**
       * 删除从大的方向分为三种
       * 在此的基础上面我们需要找到两个节点，分别是要删除的节点即targetNode 和要删除节点的父节点，即parentNode，每次通过操作他们两个的关系来删除节点
       * 1.删除叶子节点，直接删除
       *    parentNode.left =null 或者 parentNode.right =null
       * 2.删除的节点有一个叶子节点 即targetNode有一个叶子节点
       *    2.1 targetNode有一个左叶子节点
       *     1.targetNode在parentNode的左子节点上   parentNode.left=targetNode.left
       *     2.targetNode在parentNode的右子节点上   parentNode.right=targetNode.left
       *   2.2 targetNode 有一个右叶子节点
       *      1.targetNode在parentNode的左子节点上   parentNode.left=targetNode.right
       *      2.targetNode在parentNode的右子节点上   parentNode.right=targetNode.right
       * 3.删除的节点有两个节点
       *    1.找到target节点的左子树最大的值，删除最大的值，并且将值赋给targetNode
       *    2.找到target节点的右子树最下的值，删除最大的值，并且将值赋给targetNode
       */
       binarySortTree.delNode(7);
       System.out.println("删除后是：");
       binarySortTree.infixOrder();
  }
}
class BinarySortTree{
    private Node root  = null;

    /**
     * 新增节点
     * @param index
     */
    public void add(int index){
        if(root==null){
            root = new Node(index);
        }else{
            root.add(index);
        }
    }

    /**
     * 中序便利
     */
    public void infixOrder(){
        if(root==null){
            return;
        }else{
            root.infixOrder();
        }
    }

    /**
     * 查找要删除的当前节点
     * @param index
     * @return
     */
    public Node searchTargetNode(int index){
        if(root==null){
            return null;
        }
        return root.searchTargetNode(index);
    }

    /**
     * 查找要删除节点的父节点
     * @param index
     * @return
     */
    public Node searchParentNode(int index){
        if(root==null){
            return null;
        }
        return root.searchParentNode(index);
    }
    public void delNode(int index){
        Node targetNode = searchTargetNode(index);
        Node parentNode = searchParentNode(index);
      /*  System.out.println("要删除的节点： "+targetNode);
        System.out.println("要删除节点的父节点： "+parentNode);*/
        if(targetNode==null){
            return;
        }
        //如果我们发现当前这颗二叉排序树只有一个结点
        if(root.left == null && root.right == null) {
            root = null;
            return;
        }



        if(targetNode.left==null && targetNode.right==null){//叶子节点
            if(parentNode==null){//没有找到父节点，说明整个树就只有这一个节点
                root=null;
                return;
            }
            if(parentNode.left.value == index){
                parentNode.left=null;
            }else if (parentNode.right.value == index){
                parentNode.right=null;
            }else{
                return;
            }
        }else if (targetNode.left!=null && targetNode.right!=null){//既有左子树 又有右子树
               int min =  getMinRightNode(targetNode.right);
               targetNode.value=min;
        }else{//有一个叶子节点
            if(targetNode.left==null && targetNode.right!=null){//有一个左叶子节点
                if(parentNode.left.value==index){
                    parentNode.left= targetNode.right;
                }
                if(parentNode.right.value==index){
                    parentNode.right=targetNode.right;
                }
            }else if (targetNode.right==null && targetNode.left!=null){
                if(parentNode.left.value==index){
                    parentNode.left= targetNode.left;
                }
                if(parentNode.right.value==index){
                    parentNode.right=targetNode.left;
                }
            }
        }
    }

    private int getMinRightNode(Node right) {
        Node temp = right;
        while (temp.left!=null){
            temp = temp.left;
        }
        delNode(temp.value);//将右子树最小值的节点删除掉
        return temp.value;
    }
}

class Node{
    int value;
    Node left;
    Node right;

    /**
     * 新增节点，使用递归来新增符合二叉排序树的要求，左《根《右，每次递归，如果比当前大则往右递归，如果比当前小则往左递归
     * @param index
     */
    public void add(int index){
        if(this.value<=index){
            if(this.right==null){
                this.right=new Node(index);
            }else{
                this.right.add(index);
            }
        }else{ //值比当前的值小，则判断左子树是否为空，为空则直接新增，否则递归
            if(this.left==null){
                this.left = new Node(index);
            }else{
                this.left.add(index);
            }
        }
    }

    /**
     * 中序便利即左 根 右 这样的话其实就是按照升序来排列的
     */
    public void infixOrder(){
        if(this.left!=null){
            this.left.infixOrder();
        }
        System.out.println(this);
        if(this.right!=null){
            this.right.infixOrder();
        }
    }

    /**
     * 查找要删除的节点
     * @param index
     * @return
     */
    public Node searchTargetNode(int index){
        if(this.value==index){
            return this;
        }
        if(index>=this.value){
            if(this.right!=null){
                return this.right.searchTargetNode(index);
            }else{
                return null;
            }
        }else{
            if(this.left!=null){
                return  this.left.searchTargetNode(index);
            }else{
                return null;
            }
        }
    }

    public Node searchParentNode(int index){
        if((this.left!=null&& this.left.value==index)||(this.right!=null&&this.right.value==index)){//找到了父节点，将节点返回，
            return this;
        }
        if(index>=this.value){
            if(this.right!=null){
                return  this.right.searchParentNode(index);
            }else{
                return null;
            }
        }else{
            if(this.left!=null){
                return  this.left.searchParentNode(index);
            }else{
                return null;
            }
        }
    }
    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    public Node(int value) {
        this.value = value;
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
}

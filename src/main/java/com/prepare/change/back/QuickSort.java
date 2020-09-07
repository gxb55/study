package com.prepare.change.back;

import java.util.Arrays;

/**
 * @ClassName QuickSort
 * @Author guoxiaobing
 * @Date 2020/8/8 14:04
 * @Version 1.0
 * @Description 快速排序
 */
public class QuickSort {
    private QuickSort(){};
    private volatile static QuickSort sort;

  public static QuickSort getInstance() {
    if (sort == null) {//两个线程都走到了这里，创建完线程后volatile可以保证sort可见，再次判断无误则创建
      synchronized (QuickSort.class) {
        if (sort == null) {
          sort = new QuickSort();
        }
      }
    }
    return sort;
  }

  public static void main(String[] args) {
    int arr[] = {14, 20, 11, 2, 6, 89, -4, -4, 568, 20, 31, 89, 66};
    // quickSort(arr,0,arr.length-1);
    // shellSort(arr);
    // insertSort(arr);
    bubbleSort(arr);
    System.out.println(Arrays.toString(arr));
    System.out.println("双向循环链表的一些操作");
    LinkedNodeDemo demo = new LinkedNodeDemo();
    demo.add(new LinkedNode(0));
    demo.add(new LinkedNode(1));
    demo.add(new LinkedNode(2));
    demo.nodeLoop();
  }

    /**
     * 从右边开始保证每次交换的时候i都是小于value的
     * @param arr
     * @param left
     * @param right
     */
  public static void quickSort(int[] arr,int left,int right){
      if(left>right){
          return;
      }
      int i= left;
      int j=right;
      int val = arr[i];
      int temp;
      while (j>i){
          while (j>i && val<=arr[j]){
              j--;
          }
          while (j>i && val>=arr[i]){
              i++;
          }
          if(j>i){
              temp =arr[j];
              arr[j]=arr[i];
              arr[i]=temp;
          }
      }
      //交换i的位置
      arr[left]=arr[i];
      arr[i]=val;
      quickSort(arr,left,i-1);
      quickSort(arr,i+1,right);
  }

    /**
     * 希尔排序的方法,分组的思想来做,每次往后推一个找到对应的位置将值赋值
     * @param arr
     */
  public static void shellSort(int[] arr){
      if(arr.length==0){
          return;
      }
      for(int gap = arr.length/2;gap>0;gap/=2){
          for(int i=gap;i<arr.length;i++){
                int index =i;
                int val =arr[index];
                while (index-gap>=0 && arr[index-gap]>val){//降序
                    arr[index]=arr[index-gap];
                    index-=gap;
                }
                arr[index]=val;
          }
      }
  }

    /**
     * 插入排序，希尔排序的简单版
     * @param arr
     */
  public static void insertSort(int[] arr){
      if(arr.length==1){
          return;
      }
      for (int i=1;i<arr.length;i++){
          int index =i;
          int val = arr[i];
          while (index-1>=0 && val>arr[index-1] ){//降序
              arr[index]=arr[index-1];
              index--;
          }
          arr[index]=val;
      }
  }

  public static void bubbleSort(int[] arr){
      if(arr.length==1){
          return;
      }
      int temp;
      boolean flag = false;
      for(int i=1;i<arr.length;i++){
          for(int j=0;j<arr.length-i;j++){
              if(arr[j]>arr[j+1]){//升序
                  temp=arr[j];
                  arr[j]=arr[j+1];
                  arr[j+1]=temp;
                  flag = true;
              }
          }
          if(!flag){
              break;
          }else{
              flag = false;
          }
      }
  }


}
class LinkedNodeDemo{
    private LinkedNode head = null;
    public void add(LinkedNode node){
        if(this.head == null){
            this.head=node;
            this.head.setNext(head);
            this.head.setPre(head);
            return;
        }
        LinkedNode temp = head;
        while (temp.getNext()!=head){
            temp = temp.getNext();
        }
        node.setNext(temp.getNext());
        temp.setPre(node);

        temp.setNext(node);
        node.setPre(temp);

    }

    public void del(int no){
        if(head == null){
            return;
        }
       /* if(head.getNo()==no){
            head.getPre().setNext(head.getNext());
            head.getNext().setPre(head.getPre());
            return;
        }*/
        LinkedNode temp = head;
        while ( temp.getNext()!=head){
                if(temp.getNext().getNo()==no){

                }
        }
    }

    public void addByOrder(LinkedNode node){
        if(this.head == null){
            this.head=node;
            this.head.setNext(head);
            this.head.setPre(head);
            return;
        }
        LinkedNode temp = head;
        if(node.getNo()<head.getNo()){
            while (temp.getNext()!=head){
                temp=temp.getNext();
            }
            temp.setNext(node);
            node.setNext(temp.getNext());
            head.setPre(node);
            node.setPre(temp);

            return;
        }

        while (temp.getNo()>=node.getNo() && temp.getNext().getNo()<=node.getNo()){
            temp = temp.getNext();
        }
        //说明这个数是要加在
        node.setNext(temp.getNext());
        temp.setPre(node);

        temp.setNext(node);
        node.setPre(temp);

    }
    public void nodeLoop(){
        if(head ==null){
            return;
        }
        LinkedNode temp = head;
        while (temp.getNext()!=head){
            System.out.println(temp);
            temp =temp.getNext();
        }
        System.out.println(temp);
    }
}
class LinkedNode{
    private int no;
    private LinkedNode next;
    private LinkedNode pre;

    public LinkedNode(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public LinkedNode getNext() {
        return next;
    }

    public void setNext(LinkedNode next) {
        this.next = next;
    }

    public LinkedNode getPre() {
        return pre;
    }

    public void setPre(LinkedNode pre) {
        this.pre = pre;
    }

    @Override
    public String toString() {
        return "LinkedNode{" +
                "no=" + no +
                '}';
    }
}

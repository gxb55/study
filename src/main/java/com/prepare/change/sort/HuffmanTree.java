package com.prepare.change.sort;

/**
 * @ClassName HuffmanTree
 * @Author guoxiaobing
 * @Date 2020/9/14 19:36
 * @Version 1.0
 * @Description 霍夫曼压缩
 */

import java.util.*;

/**
 * 霍夫曼压缩的主要思想就是把八位一组的改成按照霍夫曼编码的格式
 *
 */
public class HuffmanTree {
    static Map<Byte,String> huffmanCodes = new HashMap<>();
    static StringBuilder stringBuilder = new StringBuilder();

  public static void main(String[] args) {
      String content = "i like like like java do you like a java";
      Map<Byte,Integer> map = getMap(content);
      System.out.println(map.toString());
      Node node = getNode(map);
      preOrder(node);
      getCodes(node);
      System.out.println(huffmanCodes.toString());
      byte[] zip = zip(content.getBytes(), huffmanCodes);
      System.out.println(zip.length);
  }
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        StringBuilder huffmanBuilder = new StringBuilder();
        for (byte x:bytes){
            huffmanBuilder.append(huffmanCodes.get(x));
        }
        int len ;
        if(huffmanBuilder.length()%8==0){
            len= huffmanBuilder.length()/8;
        }else{
            len= huffmanBuilder.length()/8 +1;
        }
        byte[] bytes1 = new byte[len];
        int index = 0;
        for(int i=0;i<huffmanBuilder.length();i+=8){
            String strByte;
            if(i+8 > huffmanBuilder.length()) {//不够8位
                strByte = huffmanBuilder.substring(i);
            }else{
                strByte = huffmanBuilder.substring(i, i + 8);
            }
            //将strByte 转成一个byte,放入到 huffmanCodeBytes
            bytes1[index] = (byte)Integer.parseInt(strByte, 2);
            index++;
        }
        return bytes1;
    }
  public static void getCodes(Node node){
      getCodes(node.left,stringBuilder,"0");
      getCodes(node.right,stringBuilder,"1");
  }
  public static void getCodes(Node node,StringBuilder stringBuilder,String code){
      if(node == null){
          return;
      }
      StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
      stringBuilder2.append(code);
      if(node.getVal()!=null){//叶子节点了
          huffmanCodes.put(node.getVal(),stringBuilder2.toString());
      }else{
          getCodes(node.left,stringBuilder2,"0");
          getCodes(node.right,stringBuilder2,"1");
      }
  }
  public static Map<Byte,Integer> getMap(String content){
      Map<Byte,Integer> map = new HashMap();
      byte[] bytes = content.getBytes();
      for(int i=0;i<bytes.length;i++){
          if(map.containsKey(bytes[i])){
              map.put( bytes[i],map.get(bytes[i])+1);
          }else{
              map.put( bytes[i],1);
          }
      }
      return map;
  }
  public static Node getNode(Map<Byte,Integer> map){
      List<Node> nodeList = new ArrayList<>();
      for(Map.Entry<Byte,Integer> entry:map.entrySet()){
          nodeList.add(new Node(entry.getKey(),entry.getValue()));
      }
      while (nodeList.size()>1){

          nodeList.sort(new Comparator<Node>() {
              @Override
              public int compare(Node o1, Node o2) {
                  return o1.weigh>=o2.weigh?1:-1;
              }
          });
          Node left = nodeList.get(0);
          Node right = nodeList.get(1);
          Node root = new Node(left.getWeigh()+right.getWeigh());
          root.setLeft(left);
          root.setRight(right);
          nodeList.remove(left);
          nodeList.remove(right);
          nodeList.add(root);

      }
      return nodeList.get(0);
  }

  public static void preOrder(Node node){
      if (node==null){
          return;
      }
      System.out.println(node);
      preOrder(node.left);
      preOrder(node.right);
  }
}
class Node{
    public Byte val;
    public Integer weigh;
    public Node left;
    public Node right;

    public Node(Byte val, Integer weigh) {
        this.val = val;
        this.weigh = weigh;
    }

    public Node(Integer weigh) {
        this.weigh = weigh;
    }

    public Byte getVal() {
        return val;
    }

    public void setVal(Byte val) {
        this.val = val;
    }

    public Integer getWeigh() {
        return weigh;
    }

    public void setWeigh(Integer weigh) {
        this.weigh = weigh;
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

    @Override
    public String toString() {
        return "Node{" +
                "val=" + val +
                ", weigh=" + weigh +
                '}';
    }
}

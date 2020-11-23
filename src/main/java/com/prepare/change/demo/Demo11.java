package com.prepare.change.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @program: 2
 * @description:
 * @author: guoxiaobing
 * @create: 2020-11-07 08:56
 */
public class Demo11 {
    public static void main(String[] args) {
        int a=7;
        if(a==5){
            System.out.println(5);
        }else if(a!=6){
            System.out.println(6);
        }else if(a==7){
            System.out.println(7);
        }else if(a!=4){
            System.out.println(4);
        }


        List<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        List<Integer> collect = list.stream().filter(x -> x > 6).collect(Collectors.toList());
        System.out.println(collect.toString());

    }
    private String getReleaseYear(Integer a){
        return "";
    }


    public void test(){
        List<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        List<Integer> collect = list.stream().filter(x -> x > 6).collect(Collectors.toList());
        System.out.println(collect.toString());
        list.stream().collect(Collectors.toMap(this::getReleaseYear, Function.identity(),
                (existing, replacement) -> existing));
    }


}
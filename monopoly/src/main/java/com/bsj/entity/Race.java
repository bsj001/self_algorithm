package com.bsj.entity;

import lombok.Data;

import java.util.HashMap;
import java.util.Scanner;

//赛道类
@Data
public class Race {
    private int num;
    //private Track track;
    private HashMap<Integer,Track> hm;
    
    public Race(boolean flag){
        if(flag){
            System.out.println("请输入赛道的长度：");
            Scanner scanner = new Scanner(System.in);
            this.num = scanner.nextInt();
            HashMap<Integer, Track> hm = new HashMap<>();
            for (int i = 0; i < this.num; i++) {
                System.out.println("第"+(i+1)+"个赛点的类型是(A,B,C,D,E):");
                char ch = scanner.next().charAt(0);
                if('A' == ch){
                    hm.put(i+1,Track.A);
                }else if('B' == ch){
                    hm.put(i+1,Track.B);
                }else if('C' == ch){
                    hm.put(i+1,Track.B);
                }else if('D' == ch){
                    hm.put(i+1,Track.D);
                }else if('E' == ch){
                    hm.put(i+1,Track.E);
                }else {
                    System.out.println("输入有误，请重新输入");
                    i--;
                }
            }
            this.hm = hm;
        }else{
            this.num = 20;
        }
    }
    
    public void listRace(){
        for (int i = 0; i < this.hm.size(); i++) {
            if(i+1 == this.hm.size()){
                System.out.print((i+1) +":"+this.hm.get(i+1));
            }else{
                System.out.print((i+1) +":"+this.hm.get(i+1)+"->");
            }
        }
        System.out.println();
    }
}

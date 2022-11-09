package com.bsj.entity;


import lombok.Data;

//选手类
@Data
public class Player {
    private String name;//选手姓名
    private int prePosition;//上一步的位置
    private int nowPosition;//下一步的位置
    
    private boolean can = true;//判断下一轮是投掷否能够行动
    public Player(String name){
        this.prePosition = 1;
        this.name = name;
        this.nowPosition = 1;
    }
}

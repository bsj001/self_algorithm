package com.bsj.entity;

import java.util.ArrayList;

//障碍类型
public enum Track {
    A{
        public String action(Player player,Race race, ArrayList<Player> players){
                if(player.getNowPosition() != 1){
                    for(Player p:players){
                        //System.out.println("p.getNowPosition()"+p.getNowPosition());
                        //System.out.println("player.getNowPosition()"+player.getNowPosition());
                        if(p.getNowPosition() == player.getNowPosition() && p.getName() != player.getName()){
                            p.setNowPosition(1);
                            return ",将"+p.getName()+"踢回运动节点1";
                        }
                    }
                }
                return "";
        }
    },
    B{
        public String action(Player player,Race race, ArrayList<Player> players){
            player.setNowPosition(player.getNowPosition()-1);
            race.getHm().get(player.getNowPosition()).action(player,race,players);
            return ",回退至节点"+player.getNowPosition();
        }
    },
    C{
        public String action(Player player,Race race, ArrayList<Player> players){
            player.setCan(false);

            if(player.getNowPosition() != 1){
                for(Player p:players){
                    if(p.getNowPosition() == player.getNowPosition() && p.getName() != player.getName()){
                        p.setNowPosition(1);
                        return ",将"+p.getName()+"踢回运动节点1";
                    }
                }
            }
            
            return "";
        }
    },
    D{
        public String action(Player player,Race race, ArrayList<Player> players){
            player.setNowPosition(1);
            return ",回退至节点1";
        }
    },
    E{
        public String action(Player player,Race race, ArrayList<Player> players){
            int nowPosition = 1;

            while(nowPosition++ <= player.getNowPosition()){
                if(race.getHm().get(nowPosition).equals(Track.E)){
                    player.setNowPosition(nowPosition);
                    break;
                }
            }
            
            if(player.getNowPosition() != 1){
                for(Player p:players){
                    //System.out.println("p.getNowPosition()"+p.getNowPosition());
                    //System.out.println("player.getNowPosition()"+player.getNowPosition());
                    if(p.getNowPosition() == player.getNowPosition() && p.getName() != player.getName()){
                        p.setNowPosition(1);
                        return ",将"+p.getName()+"踢回运动节点1";
                    }
                }
            }
            return ",回退至节点"+player.getNowPosition();
        }
    };
    public abstract String action(Player player, Race race, ArrayList<Player> players);
}

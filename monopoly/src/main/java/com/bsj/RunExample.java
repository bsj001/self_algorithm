package com.bsj;

import com.bsj.entity.Player;
import com.bsj.entity.Race;
import com.bsj.entity.Track;

import java.util.*;
import java.util.stream.Collectors;

public class RunExample {
    public static void main(String[] args) {
        //1,创建赛道
        Race race = new Race(false);
        HashMap<Integer, Track> newRace = new HashMap<>();
        newRace.put(1, Track.A);
        newRace.put(2, Track.A);
        newRace.put(3, Track.B);
        newRace.put(4, Track.A);
        newRace.put(5, Track.C);
        newRace.put(6, Track.A);
        newRace.put(7, Track.A);
        newRace.put(8, Track.D);
        newRace.put(9, Track.A);
        newRace.put(10, Track.A);
        newRace.put(11, Track.E);
        newRace.put(12, Track.A);
        newRace.put(13, Track.A);
        newRace.put(14, Track.A);
        newRace.put(15, Track.A);
        newRace.put(16, Track.E);
        newRace.put(17, Track.A);
        newRace.put(18, Track.A);
        newRace.put(19, Track.C);
        newRace.put(20, Track.A);
        race.setHm(newRace);
        race.listRace();

        //2，创建选手，并初始化位置
        ArrayList<Player> players = new ArrayList<>();
        Player p1 = new Player("甲");
        Player p2 = new Player("乙");
        Player p3 = new Player("丙");
        players.add(p1);
        players.add(p2);
        players.add(p3);

        //3,选手的投掷顺序
        //Collections.shuffle(players);


        //4，输出初始条件
        System.out.print("0:");
        for (Player p : players) {
            System.out.print(p.getName());
            System.out.print("在节点1,");
        }
        System.out.print("投掷顺序（");
        for (Player p : players) {
            if (p.equals(players.get(players.size() - 1))) {
                System.out.print(p.getName());
            } else {
                System.out.print(p.getName() + ",");
            }
        }
        System.out.println(")");


        //5,定义必要的变量
        HashMap<Player, Integer> winners = new HashMap<>();//胜利的人员
        int count = 0;//比赛轮数
        int step = 6;//投掷选择的步数

        //6,


        //开始比赛
        while (players.size() != 0) {

            System.out.println("第" + (++count) + "轮");

            //players.get(0).setNowPosition(10);

            //players.get(1).setNowPosition(6);

            //players.get(2).setNowPosition(11);

            //按选手顺序进行比赛
            for (Player p : players) {

                step = 6;

                //判断投掷的最佳数字
                int nowPosition = p.getNowPosition();
                String nowName = p.getName();
                //判断前面是否有人
                while (nowPosition > Math.max(p.getNowPosition() - 6, 1)) {
                    for (Player np : players) {
                        if (!np.getName().equals(nowName)) {
                            if (np.getNowPosition() == nowPosition) {
                                //如果 step == 6,此时前面没有人，把step重新赋值为1
                                if (step == 6) {
                                    step = 1;
                                }
                                //说明后面有人
                                step = 6 - (p.getNowPosition() - nowPosition);//最少的步数
                                break;
                            }
                        }
                    }
                    nowPosition--;
                }


                //判断后面是否有人
                nowPosition = p.getNowPosition() + step;
                while (nowPosition <= Math.min(p.getNowPosition() + 6, race.getNum())) {
                    for (Player np : players) {
                        if (!np.getName().equals(nowName)) {
                            if (np.getNowPosition() == nowPosition) {
                                //说明有人
                                step = Math.max(nowPosition - p.getNowPosition(), step);//在不被踢时，同时踢人的步数
                                break;
                            }
                        }
                    }
                    nowPosition++;
                }

                //实际上的步数
                step = (step + p.getNowPosition()) <= race.getNum() ? step : race.getNum() - p.getNowPosition();

                //System.out.println("*************************");
                //System.out.println("max step=" + step);
                //System.out.println("nowPosition=" + p.getNowPosition());
                //System.out.println("*************************");

                Random random = new Random();
                step = random.nextInt(10000) % step + 1;//临时想的随机的方法
                String result = "";
                //行走
                if (p.isCan()) {
                    nowPosition = p.getNowPosition() + step;
                    if (nowPosition == race.getNum()) {
                        winners.put(p, count);
                        System.out.println(p.getName()+"到达终点");
                        continue;
                    }
                    p.setPrePosition(p.getNowPosition());
                    p.setNowPosition(p.getNowPosition() + step);
                    result = race.getHm().get(nowPosition).action(p, race,players);//到指定位置后的处理方法
                } else {
                    p.setPrePosition(p.getNowPosition());
                    p.setCan(true);
                }


                //输出本轮的选手的比赛结果
                System.out.print(p.getName() + ":投掷选择" + step  + ",结果" + p.getNowPosition() + ",前进至节点" + (!p.isCan()? (p.getPrePosition() + step):p.getNowPosition()));
                //System.out.println(p.toString());
                //如果有
                if (!"".equals(result)) {
                    System.out.println(result);
                }
                System.out.println();
            }
            //break;
            
            //到达之后，加入胜利名单
            for(Player p:winners.keySet()){
                players.remove(p);
            }
        }

        //List<Map.Entry<Player, Integer>> infoIds = new ArrayList<Map.Entry<Player, Integer>>(winners.entrySet());
        //infoIds.sort(new Comparator<Map.Entry<Player, Integer>>() {
        //    public int compare(Map.Entry<Player, Integer> o1, Map.Entry<Player, Integer> o2) {
        //        System.out.println("*****" + o1);
        //        return (o2.getValue() - o1.getValue());
        //        //return (o1.getKey()).toString().compareTo(o2.getKey());
        //    }
        //});

        List<Map.Entry<Player,Integer>> list = new ArrayList<>(winners.entrySet());
        list.sort(Comparator.comparing(Map.Entry::getValue));
        Map<Player,Integer> map2 = new LinkedHashMap<>();
        for(Map.Entry<Player,Integer> entry: list){
            map2.put(entry.getKey(), entry.getValue());
        }
        map2.forEach((o1, o2)-> System.out.println(o1+":"+o2));

        // 正序
        //winners.values().stream().sorted().collect(Collectors.toList()).forEach(System.out::println);
        
        //for(Player p:winners.keySet()){
        //    System.out.println(p.getName()+":"+winners.get(p));
        //}
    }
}

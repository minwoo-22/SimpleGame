package com.example.game.biginsight.thread;

import com.example.game.biginsight.monster.Monster;
import com.example.game.biginsight.parent.PlayerUnit;

public class MonsterAttack implements Runnable{
    private boolean stop;
    PlayerUnit playerUnit;
    Monster monster;

    MonsterAttack(Monster monster, PlayerUnit playerUnit) {
        this.playerUnit = playerUnit;
        this.monster = monster;
        this.stop = false;
    }

    @Override
    public void run() {
        System.out.println("MonsterAttack Thread 시작");
        int hp = playerUnit.getHp();
        while (!stop) {
            monster.attack(playerUnit);
            try {
                Thread.sleep((long) ((1/monster.getAttackSpeed())*1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            hp = playerUnit.getHp();
            System.out.println("플레이어 현재 체력 : " + hp);
            if (hp <= 0) break;
        }
    }

    public void threadStop(boolean stop) {
        this.stop = stop;
    }
}

package com.example.game.biginsight.skill;

import com.example.game.biginsight.monster.Monster;

public class MonsterSkill{

    public static int skill(Monster monster) {
        System.out.println("몬스터의 반격 스킬 발동!");
        return (int) (monster.getPower()*0.7);
    }
}

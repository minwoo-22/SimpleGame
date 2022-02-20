package com.example.game.biginsight.monster;

import com.example.game.biginsight.common.Unit;
import com.example.game.biginsight.skill.monster.MonsterSkill;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Monster extends Unit {
    MonsterSkill skill = new MonsterSkill();

    @Builder
    public Monster(int hp, int power, double attack_speed, int defense) {
        super(hp, power, attack_speed, defense);
    }

    @Override
    public int attack() {
        System.out.println("[몬스터] 공격!");
        return getPower();
    }

    @Override
    public String toString() {
        return "몬스터 정보 : 체력 "+getHp()+"/"+getMax_hp()+", 공격력 "+getPower()+", 공격속도 "+getAttack_speed()
                +", 방어력 "+getDefense();
    }
}

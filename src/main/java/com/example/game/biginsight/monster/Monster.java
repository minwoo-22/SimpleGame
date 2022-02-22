package com.example.game.biginsight.monster;

import com.example.game.biginsight.attribute.MonsterAttribute;
import com.example.game.biginsight.parent.GameUnit;
import com.example.game.biginsight.parent.PlayerUnit;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Monster extends GameUnit implements MonsterAttribute {

    @Builder
    public Monster(int hp, int power, double attackSpeed, int defense) {
        setMax_hp(hp);
        setHp(hp);
        setPower(power);
        setAttackSpeed(attackSpeed);
        setDefense(defense);
    }

    public Monster() {
        setMax_hp(MAX_HP);
        setHp(MAX_HP);
        setPower(POWER);
        setAttackSpeed(ATTACK_SPEED);
        setDefense(DEFENSE);
    }


    @Override
    public String toString() {
        return "몬스터 정보 : 체력 "+getHp()+"/"+getMax_hp()+", 공격력 "+getPower()+", 공격속도 "+getAttackSpeed()
                +", 방어력 "+getDefense();
    }


    public boolean attack(PlayerUnit playerUnit) {
        System.out.println("[몬스터] 공격");
        return playerUnit.takeDamage(getPower());
    }

    @Override
    public boolean takeDamage(int damage) {
        int realDamage = damage - getDefense();
        if (realDamage < 0) realDamage = 0;
        setHp(getHp() - realDamage);
        int ranNum = (int)(Math.random()*99)+1;     // 1~100
        // 30% 확률로 반격
        if (ranNum >= 1 && ranNum <= 30) {
            return false;
        }
        return true;
    }
}

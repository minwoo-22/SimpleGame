package com.example.game.biginsight.parent;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public abstract class GameUnit {
    private int max_hp;
    private int hp;     //Unit의 레벨
    private int power;  //Unit의 공격력
    private double attackSpeed;    //Unit의 공격 속도
    private int defense;    //Unit의 방어력

    public void setMax_hp(int max_hp) {
        this.max_hp = max_hp;
        if (max_hp < 0) this.max_hp = 0;
    }

    public void setHp(int hp) {
        this.hp = hp;
        if (this.hp > max_hp) this.hp = max_hp;
        if (hp < 0) this.hp = 0;
    }

    public void setPower(int power) {
        this.power = power;
        if (power < 0) this.power = 0;
    }

    public void setAttackSpeed(double attackSpeed) {
        attackSpeed = Math.round(attackSpeed*100)/100.0;
        this.attackSpeed = attackSpeed;
        if (attackSpeed < 0.0) this.attackSpeed = 0.0;
    }

    public void setDefense(int defense) {
        this.defense = defense;
        if (defense < 0) this.defense = 0;
    }

    public abstract boolean takeDamage(int damage);


}

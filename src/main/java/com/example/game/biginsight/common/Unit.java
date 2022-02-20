package com.example.game.biginsight.common;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public abstract class Unit {
    private int max_hp;
    private int hp;     //Unit의 레벨
    private int power;  //Unit의 공격력
    private double attack_speed;    //Unit의 공격 속도
    private int defense;    //Unit의 방어력

    public Unit(int hp, int power, double attack_speed, int defense) {
        this.max_hp = hp;
        this.hp = hp;
        this.power = power;
        this.attack_speed = attack_speed;
        this.defense = defense;
    }

    public abstract int attack();

    public void takeDamage(int damage) {
        this.hp -= damage;
    }

    // 레벨업 후 max_hp 상승
    public void hpUp(int hp) {
        this.max_hp += hp;
    }
    // 힐 후 hp 상승
    public void healHp(int hp) {
        this.hp += hp;
        if (this.hp > this.max_hp) this.hp = max_hp;
    }

    public void powerUp(int power) {
        this.power += power;
    }

    public void attackSpeedUp(double attack_speed) {
        this.attack_speed += attack_speed;
    }

    public void defenseUp(int defense) {
        this.defense += defense;
    }

}

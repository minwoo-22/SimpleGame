package com.example.game.biginsight.tribe.common;

import com.example.game.biginsight.common.Unit;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public abstract class Character extends Unit {
    private int max_mp;
    private int level;      //Character의 레벨
    private int mp;         //Character의 mp
    private double evasion;    //Character의 회피율

    public Character(int level, int hp, int mp, int power, double attack_speed, int defense, double evasion) {
        super(hp, power, attack_speed, defense);
        this.level = level;
        this.mp = mp;
        this.max_mp = mp;
        this.evasion = evasion;
    }

    public void levelUp(int mp, double evasion, int hp, int power, double attack_speed, int defense) {
        this.level += 1;
        mpUp(mp);
        evasionUp(evasion);
        hpUp(hp);
        powerUp(power);
        attackSpeedUp(attack_speed);
        defenseUp(defense);
    };

    public void mpUp(int mp) {
        this.max_mp += mp;
    }

    public void evasionUp(double evasion) {
        this.evasion += evasion;
    }

    public void consumeMp(int mp) {
        this.mp -= mp;
    }

    @Override
    public int attack() {
        System.out.println("[캐릭터] 공격!");
        return getPower();
    }

    @Override
    public String toString() {
        return "캐릭터 정보 : 레벨 "+getLevel()+", 체력 "+getHp()+"/"+getMax_hp()+", 마나 "+getMp()+"/"+getMax_mp()+", 공격력 "+getPower()
                +", 공격속도 "+getAttack_speed()+", 방어력 "+getDefense()+", 회피율(%) "+getEvasion();
    }

}

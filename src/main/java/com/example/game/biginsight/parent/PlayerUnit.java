package com.example.game.biginsight.parent;

import com.example.game.biginsight.monster.Monster;
import com.example.game.biginsight.skill.MonsterSkill;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class PlayerUnit extends GameUnit{
    private int max_mp;
    private int level;              //Character의 레벨
    private int mp;                 //Character의 mp
    private double evasion;         //Character의 회피율

    private int powerByWeapon;              //무기에 의한 공격력 증가량
    private double attackSpeedByWeapon;     //무기에 의한 공격속도 증가량

    private int powerBySkill;       //스킬에 의한 공격력 증가량
    private double attackSpeedBySkill;  //스킬에 의한 공격속도 증가량
    private int defenseBySkill;     //스킬에 의한 방어력 증가량
    private double evasionBySkill;  //스킬에 의한 회피력 증가량

    public void setMax_mp(int max_mp) {
        this.max_mp = max_mp;
        if (max_mp < 0) this.max_mp = max_mp;
    }

    public void setMp(int mp) {
        this.mp = mp;
        if (mp < 0) this.mp = 0;
    }

    public void setLevel(int level) {
        this.level = level;
        if (level < 0) this.level = 0;
    }

    public void setEvasion(double evasion) {
        evasion = Math.round(evasion*100)/100.0;
        this.evasion = evasion;
        if (evasion < 0.0) this.evasion = 0.0;
    }

    public void setPowerByWeapon(int power) {
        this.powerByWeapon = power;
        if (power < 0) this.powerByWeapon = 0;
    }

    public void setAttackSpeedByWeapon(double attackSpeed) {
        double attackSpeedByWeapon = Math.round(attackSpeed*100)/100.0;
        this.attackSpeedByWeapon = attackSpeedByWeapon;
    }

    public abstract void levelUp();

    public abstract void putOnWeapon(String weapon);

    public abstract void takeOffWeapon();


    public boolean attack(Monster monster) {
        System.out.println("[캐릭터] 공격");
        boolean check = monster.takeDamage(getPower()+getPowerByWeapon());
        if(!check) {
            takeDamage(MonsterSkill.skill(monster));
        }
        return check;
    }

    @Override
    public boolean takeDamage(int damage) {
        int realDamage = damage - getDefense();
        if (realDamage < 0) realDamage = 0;

        int percentage = (int) Math.round(getEvasion());    // 회피 확률
        int ranNum = (int) ((Math.random()*99)+1);
        if (ranNum >= 1 && ranNum <= percentage) {
            System.out.println(percentage+"% 확률로 [회피]");
            return false;
        }

        setHp(getHp() - realDamage);
        return true;
    }


    @Override
    public String toString() {
        return "캐릭터 정보 : 레벨 "+getLevel()+", 체력 "+getHp()+"/"+getMax_hp()+", 마나 "+getMp()+"/"+getMax_mp()+", 공격력 "+(getPower()
                +getPowerByWeapon()+getPowerBySkill())+", 공격속도 "+(Math.round((getAttackSpeed()+getAttackSpeedByWeapon()+getAttackSpeedBySkill())*100)/100.0)
                +", 방어력 "+(getDefense()+getDefenseBySkill())+", 회피율(%) "+(Math.round(getEvasion()+getEvasionBySkill()));
    }

}

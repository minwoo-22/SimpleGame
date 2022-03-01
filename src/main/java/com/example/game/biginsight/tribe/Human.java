package com.example.game.biginsight.tribe;

import com.example.game.biginsight.attribute.HumanAttribute;
import com.example.game.biginsight.parent.PlayerUnit;
import com.example.game.biginsight.weapon.HumanWeaponTypes;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Setter
@Getter
@Slf4j
public class Human extends PlayerUnit implements HumanAttribute {
    private HumanWeaponTypes humanWeaponTypes;
    private boolean invincibleCheck = false;

    public Human() {
        setLevel(LEVEL);
        setMax_hp(MAX_HP);
        setHp(MAX_HP);
        setMax_mp(MAX_MP);
        setMp(MAX_MP);
        setPower(POWER);
        setAttackSpeed(ATTACK_SPEED);
        setDefense(DEFENSE);
        setEvasion(EVASION);
        setHumanWeaponTypes(HumanWeaponTypes.NONE);
    }

    @Override
    public void levelUp() {
        setLevel(getLevel()+1);
        setMax_hp(getMax_hp()+UP_MAX_HP);
        setHp(getMax_hp());
        setMax_mp(getMax_mp()+UP_MAX_MP);
        setMp(getMax_mp());
        setPower(getPower()+UP_POWER);
        setAttackSpeed(getAttackSpeed()+UP_ATTACK_SPEED);
        setPowerByWeapon((int) (getPower()*getHumanWeaponTypes().getPower()));
        setDefense(getDefense()+UP_DEFENSE);
        setEvasion(getEvasion()+UP_EVASION);
    }

    @Override
    public void putOnWeapon(String weapon) {
        try {
            setPowerByWeapon((int) (getPower() * HumanWeaponTypes.valueOf(weapon).getPower()));
            setHumanWeaponTypes(HumanWeaponTypes.valueOf(weapon));
        } catch (IllegalArgumentException e) {
            log.info(e.getMessage()+" 무기 착용 실패 [전용 무기가 아닙니다.]");
        }
    }

    @Override
    public void takeOffWeapon() {
        setAttackSpeedByWeapon(0);
        setHumanWeaponTypes(HumanWeaponTypes.NONE);
    }

    @Override
    public boolean takeDamage(int damage) {
        if (!invincibleCheck) {
            int realDamage = damage - getDefense();
            if (realDamage < 0) realDamage = 0;

            int percentage = (int) Math.round(getTotalEvasion());    // 회피 확률
            int ranNum = (int) ((Math.random()*99)+1);
            if (ranNum >= 1 && ranNum <= percentage) {
                log.info(percentage+"% 확률로 [회피]");
                return false;
            }

            setHp(getHp() - realDamage);
            return true;
        } else {
            return false;
        }
    }

    public void invincibleOn() {
        this.invincibleCheck = true;
    }

    public void invincibleOff() {
        this.invincibleCheck = false;
    }

    @Override
    public String toString() {
        return "종족(휴먼) - " + super.toString() + ", 무기 " + getHumanWeaponTypes();
    }
}

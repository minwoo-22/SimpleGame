package com.example.game.biginsight.tribe;

import com.example.game.biginsight.attribute.HumanAttribute;
import com.example.game.biginsight.parent.PlayerUnit;
import com.example.game.biginsight.weapon.HumanWeaponTypes;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Human extends PlayerUnit implements HumanAttribute {
    HumanWeaponTypes humanWeaponTypes;

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
            System.out.println(e.getMessage()+" 무기 착용 실패 [전용 무기가 아닙니다.]");
        }
    }

    @Override
    public void takeOffWeapon() {
        setAttackSpeedByWeapon(0);
        setHumanWeaponTypes(HumanWeaponTypes.NONE);
    }

    @Override
    public String toString() {
        return "종족(휴먼) - " + super.toString() + ", 무기 " + getHumanWeaponTypes();
    }
}

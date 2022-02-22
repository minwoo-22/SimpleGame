package com.example.game.biginsight.tribe;

import com.example.game.biginsight.attribute.OrkAttribute;
import com.example.game.biginsight.parent.PlayerUnit;
import com.example.game.biginsight.weapon.OrkWeaponTypes;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ork extends PlayerUnit implements OrkAttribute {
    OrkWeaponTypes orkWeaponTypes;

    public Ork() {
        setLevel(LEVEL);
        setMax_hp(MAX_HP);
        setHp(MAX_HP);
        setMax_mp(MAX_MP);
        setMp(MAX_MP);
        setPower(POWER);
        setAttackSpeed(ATTACK_SPEED);
        setDefense(DEFENSE);
        setEvasion(EVASION);
        setOrkWeaponTypes(OrkWeaponTypes.NONE);
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
        setPowerByWeapon((int) (getPower() * getOrkWeaponTypes().getPower()));
        setAttackSpeedByWeapon(getAttackSpeed() * getOrkWeaponTypes().getAttackSpeed());
        setDefense(getDefense()+UP_DEFENSE);
        setEvasion(getEvasion()+UP_EVASION);
    }

    @Override
    public void putOnWeapon(String weapon) {
        try {
            setPowerByWeapon((int) (getPower() * OrkWeaponTypes.valueOf(weapon).getPower()));
            setAttackSpeedByWeapon(getAttackSpeed() * OrkWeaponTypes.valueOf(weapon).getAttackSpeed());
            setOrkWeaponTypes(OrkWeaponTypes.valueOf(weapon));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage()+" 무기 착용 실패 [전용 무기가 아닙니다.]");
        }
    }

    @Override
    public void takeOffWeapon() {
        setAttackSpeedByWeapon(0);
        setPowerByWeapon(0);
        setOrkWeaponTypes(OrkWeaponTypes.NONE);
    }

    @Override
    public String toString() {
        return "종족(오크) - " + super.toString() + ", 무기 " + getOrkWeaponTypes();
    }
}

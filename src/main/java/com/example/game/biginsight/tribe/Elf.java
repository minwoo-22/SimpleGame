package com.example.game.biginsight.tribe;

import com.example.game.biginsight.attribute.ElfAttribute;
import com.example.game.biginsight.parent.PlayerUnit;
import com.example.game.biginsight.weapon.ElfWeaponTypes;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Elf extends PlayerUnit implements ElfAttribute{
    ElfWeaponTypes elfWeaponTypes;

    public Elf() {
        setLevel(LEVEL);
        setMax_hp(MAX_HP);
        setHp(MAX_HP);
        setMax_mp(MAX_MP);
        setMp(MAX_MP);
        setPower(POWER);
        setAttackSpeed(ATTACK_SPEED);
        setDefense(DEFENSE);
        setEvasion(EVASION);
        setElfWeaponTypes(ElfWeaponTypes.NONE);
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
        setAttackSpeedByWeapon(getAttackSpeed()*getElfWeaponTypes().getAttackSpeed());
        setDefense(getDefense()+UP_DEFENSE);
        setEvasion(getEvasion()+UP_EVASION);
    }

    @Override
    public void putOnWeapon(String weapon) {
        try {
            setAttackSpeedByWeapon(getAttackSpeed() * ElfWeaponTypes.valueOf(weapon).getAttackSpeed());
            setElfWeaponTypes(ElfWeaponTypes.valueOf(weapon));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage()+" 무기 착용 실패 [전용 무기가 아닙니다.]");
        }
    }

    @Override
    public void takeOffWeapon() {
        setAttackSpeedByWeapon(0);
        setElfWeaponTypes(ElfWeaponTypes.NONE);
    }

    @Override
    public String toString() {
        return "종족(엘프) - " + super.toString() + ", 무기 " + getElfWeaponTypes();
    }

}

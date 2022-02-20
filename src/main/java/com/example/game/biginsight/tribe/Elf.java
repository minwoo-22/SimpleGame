package com.example.game.biginsight.tribe;

import com.example.game.biginsight.attribute.ElfAttribute;
import com.example.game.biginsight.tribe.common.Character;
import lombok.Getter;


@Getter
public class Elf extends Character implements ElfAttribute {


    public Elf() {
        super(ElfAttribute.LEVEL, ElfAttribute.MAX_HP, ElfAttribute.MAX_MP, ElfAttribute.POWER
                , ElfAttribute.ATTACK_SPEED, ElfAttribute.DEFENSE, ElfAttribute.EVASION);
    }

    public void levelUp() {
        super.levelUp(ElfAttribute.UP_MAX_MP, ElfAttribute.UP_EVASION, ElfAttribute.UP_MAX_HP
                , ElfAttribute.UP_POWER, ElfAttribute.UP_ATTACK_SPEED, ElfAttribute.UP_DEFENSE);
    }

    @Override
    public String toString() {
        return "종족(엘프) - " + super.toString();
    }
}

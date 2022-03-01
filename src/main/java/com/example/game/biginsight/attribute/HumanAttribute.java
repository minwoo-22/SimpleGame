package com.example.game.biginsight.attribute;

import com.example.game.biginsight.weapon.ElfWeaponTypes;
import com.example.game.biginsight.weapon.HumanWeaponTypes;

public interface HumanAttribute {
    // 기본 능력치
    int LEVEL = 1;
    int MAX_HP = 500;
    int MAX_MP = 170;
    int POWER = 38;
    double ATTACK_SPEED = 0.5;
    int DEFENSE = 9;
    double EVASION = 10.0;      // %
    String WEAPON = HumanWeaponTypes.NONE.name();

    // 레벨업 후 성장치
    int UP_MAX_HP = 50;
    int UP_MAX_MP = 10;
    int UP_POWER = 6;
    double UP_ATTACK_SPEED = 0.02;
    int UP_DEFENSE = 3;
    double UP_EVASION = 0.2;

}

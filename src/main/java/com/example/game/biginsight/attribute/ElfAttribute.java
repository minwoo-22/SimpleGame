package com.example.game.biginsight.attribute;

import com.example.game.biginsight.weapon.ElfWeaponTypes;

public interface ElfAttribute {
    // 기본 능력치
    int LEVEL = 1;
    int MAX_HP = 400;
    int MAX_MP = 200;
    int POWER = 35;
    double ATTACK_SPEED = 0.7;
    int DEFENSE = 7;
    double EVASION = 20.0;      // %
    String WEAPON = ElfWeaponTypes.NONE.name();

    // 레벨업 후 성장치
    int UP_MAX_HP = 30;
    int UP_MAX_MP = 20;
    int UP_POWER = 5;
    double UP_ATTACK_SPEED = 0.05;
    int UP_DEFENSE = 2;
    double UP_EVASION = 0.5;

}

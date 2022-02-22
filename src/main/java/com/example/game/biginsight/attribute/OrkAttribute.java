package com.example.game.biginsight.attribute;

import com.example.game.biginsight.weapon.OrkWeaponTypes;

public interface OrkAttribute {
    // 기본 능력치
    int LEVEL = 1;
    int MAX_HP = 530;
    int MAX_MP = 150;
    int POWER = 40;
    double ATTACK_SPEED = 0.4;
    int DEFENSE = 7;
    double EVASION = 5.0;       // %
    String WEAPON = OrkWeaponTypes.NONE.name();

    // 레벨업 후 성장치
    int UP_MAX_HP = 55;
    int UP_MAX_MP = 10;
    int UP_POWER = 7;
    double UP_ATTACK_SPEED = 0.01;
    int UP_DEFENSE = 2;
    double UP_EVASION = 0.1;

    // 스킬 쿨타임
    int ANGER_COOL_TIME = 15000;
    int FRENZY_COOL_TIME = 300000;

    // 스킬 지속시간
    int ANGER_DURATION = 5000;
    int FRENZY_DURAION = 60000;
}

package com.example.game.biginsight.weapon;

public enum HumanWeaponTypes {
    SHORT_SWORD(0.05), LONG_SWORD(0.1), NONE(0);

    private final double power;

    HumanWeaponTypes(double power) {
        this.power = power;
    }

    public double getPower() {
        return power;
    }
}

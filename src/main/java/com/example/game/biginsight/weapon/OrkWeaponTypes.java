package com.example.game.biginsight.weapon;

public enum OrkWeaponTypes {
    SHORT_AXE(0.1, -0.05), IRON_AXE(0.2, -0.1), NONE(0, 0);

    private final double power;
    private final double attackSpeed;

    OrkWeaponTypes(double power, double attackSpeed) {
        this.power = power;
        this.attackSpeed = attackSpeed;
    }

    public double getPower() {
        return power;
    }

    public double getAttackSpeed() {
        return attackSpeed;
    }
}

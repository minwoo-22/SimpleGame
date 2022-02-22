package com.example.game.biginsight.weapon;

public enum ElfWeaponTypes {
    SHORT_BOW(0.05), IRON_BOW(0.1), NONE(0);

    private final double attackSpeed;

    ElfWeaponTypes(double attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    public double getAttackSpeed() {
        return attackSpeed;
    }


}

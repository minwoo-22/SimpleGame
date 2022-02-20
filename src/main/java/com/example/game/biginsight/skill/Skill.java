package com.example.game.biginsight.skill;

import com.example.game.biginsight.tribe.common.Character;

public abstract class Skill {
    public static void heal(Character character) {
        character.healHp(50);
        character.consumeMp(20);
        System.out.println("heal 시전");
    }

    public static void steam(Character character) {
        character.powerUp((int) (character.getPower()*(0.2)));
        character.consumeMp(30);
        System.out.println("steam 시전");
    }
}

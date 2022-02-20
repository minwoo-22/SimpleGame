package com.example.game.biginsight.skill.character;

import com.example.game.biginsight.skill.Skill;
import com.example.game.biginsight.tribe.Elf;


public class ElfSkill extends Skill {

    public static void elusion(Elf elf) {
        System.out.println("elusion 시전");
        elf.evasionUp(elf.getEvasion()*(0.3));
        elf.consumeMp(20);
    }
}

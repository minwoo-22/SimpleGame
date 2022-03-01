package com.example.game.biginsight.thread.elfskill;

import com.example.game.biginsight.attribute.ElfAttribute;
import com.example.game.biginsight.skill.ElfSkill;
import com.example.game.biginsight.tribe.Elf;

public class ElusionThread extends Thread {
    private Elf elf;

    public ElusionThread(Elf elf) {
        this.elf = elf;
    }

    @Override
    public void run() {
        ElfSkill.elusion(elf);
        try {
            Thread.sleep(ElfSkill.ELUSION_DURATION);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        elf.setEvasionBySkill(0);
        try {
            Thread.sleep(ElfSkill.ELUSION_COOL_TIME-ElfSkill.ELUSION_DURATION);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

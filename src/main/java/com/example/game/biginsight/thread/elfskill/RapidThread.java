package com.example.game.biginsight.thread.elfskill;

import com.example.game.biginsight.attribute.ElfAttribute;
import com.example.game.biginsight.skill.ElfSkill;
import com.example.game.biginsight.tribe.Elf;

public class RapidThread extends Thread implements ElfAttribute {
    private Elf elf;

    public RapidThread(Elf elf) {
        this.elf = elf;
    }

    @Override
    public void run() {
        ElfSkill.rapid(elf);
        try {
            Thread.sleep(RAPID_DURAION);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        elf.setAttackSpeedBySkill(0);
        try {
            Thread.sleep(RAPID_COOL_TIME-RAPID_DURAION);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

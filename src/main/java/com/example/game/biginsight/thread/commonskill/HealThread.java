package com.example.game.biginsight.thread.commonskill;

import com.example.game.biginsight.parent.PlayerUnit;
import com.example.game.biginsight.parent.Skill;

public class HealThread extends Thread {
    private PlayerUnit playerUnit;

    public HealThread(PlayerUnit playerUnit) {
        this.playerUnit = playerUnit;
    }

    @Override
    public void run() {
        Skill.heal(playerUnit);
        try {
            Thread.sleep(Skill.HEAL_COOL_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

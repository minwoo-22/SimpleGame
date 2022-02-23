package com.example.game.biginsight.thread.commonskill;

import com.example.game.biginsight.parent.PlayerUnit;
import com.example.game.biginsight.parent.Skill;

public class SteamThread extends Thread{
    private PlayerUnit playerUnit;

    public SteamThread(PlayerUnit playerUnit) {
        this.playerUnit = playerUnit;
    }

    @Override
    public void run() {
        Skill.steam(playerUnit);
        try {
            Thread.sleep(Skill.STEAM_DURATION);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        playerUnit.setPowerBySkill(0);

        try {
            Thread.sleep(Skill.STEAM_COOL_TIME-Skill.STEAM_DURATION);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

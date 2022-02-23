package com.example.game.biginsight.thread.orkskill;

import com.example.game.biginsight.attribute.OrkAttribute;
import com.example.game.biginsight.skill.OrkSkill;
import com.example.game.biginsight.tribe.Ork;

public class FrenzyThread extends Thread implements OrkAttribute {
    private Ork ork;

    public FrenzyThread(Ork ork) {
        this.ork = ork;
    }

    @Override
    public void run() {
        OrkSkill.frenzy(ork);
        try {
            Thread.sleep(FRENZY_DURAION);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ork.setPowerByFrenzy(0);

        try {
            Thread.sleep(FRENZY_COOL_TIME - FRENZY_DURAION);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

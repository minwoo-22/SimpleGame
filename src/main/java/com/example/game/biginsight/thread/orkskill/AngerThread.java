package com.example.game.biginsight.thread.orkskill;

import com.example.game.biginsight.attribute.OrkAttribute;
import com.example.game.biginsight.skill.OrkSkill;
import com.example.game.biginsight.tribe.Ork;

public class AngerThread extends Thread implements OrkAttribute {
    private Ork ork;

    public AngerThread(Ork ork) {
        this.ork = ork;
    }

    @Override
    public void run() {
        OrkSkill.anger(ork);
        try {
            Thread.sleep(ANGER_DURATION);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ork.setPowerByAnger(0);
        ork.setDefenseBySkill(0);

        try {
            Thread.sleep(ANGER_COOL_TIME - ANGER_DURATION);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

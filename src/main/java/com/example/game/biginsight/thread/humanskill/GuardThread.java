package com.example.game.biginsight.thread.humanskill;

import com.example.game.biginsight.attribute.HumanAttribute;
import com.example.game.biginsight.skill.HumanSkill;
import com.example.game.biginsight.thread.commonskill.HealThread;
import com.example.game.biginsight.tribe.Human;

public class GuardThread extends Thread implements HumanAttribute {
    private Human human;

    public GuardThread(Human human) {
        this.human = human;
    }

    @Override
    public void run() {
        HumanSkill.guard(human);
        try {
            Thread.sleep(GUARD_DURATION);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        human.setDefenseBySkill(0);

        try {
            Thread.sleep(GUARD_COOL_TIME - GUARD_DURATION);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

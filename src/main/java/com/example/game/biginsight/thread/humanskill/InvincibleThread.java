package com.example.game.biginsight.thread.humanskill;

import com.example.game.biginsight.attribute.HumanAttribute;
import com.example.game.biginsight.skill.HumanSkill;
import com.example.game.biginsight.tribe.Human;

public class InvincibleThread extends Thread {
    private Human human;

    public InvincibleThread(Human human) {
        this.human = human;
    }

    @Override
    public void run() {
        HumanSkill.invincible(human);
        try {
            Thread.sleep(HumanSkill.INVINCIBLE_DURAION);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        human.invincibleOff();

        try {
            Thread.sleep(HumanSkill.INVINCIBLE_COOL_TIME - HumanSkill.INVINCIBLE_DURAION);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

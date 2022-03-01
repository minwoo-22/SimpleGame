package com.example.game.biginsight.skill;

import com.example.game.biginsight.attribute.HumanAttribute;
import com.example.game.biginsight.parent.Skill;
import com.example.game.biginsight.tribe.Human;

public class HumanSkill extends Skill implements HumanAttribute {
    // 스킬 쿨타임
    public static final int GUARD_COOL_TIME = 15000;
    public static final int INVINCIBLE_COOL_TIME = 300000;

    // 스킬 지속시간
    public static final int GUARD_DURATION = 5000;
    public static final int INVINCIBLE_DURAION = 60000;

    public static void guard(Human human) {
        int consumeMp = 20+5* human.getLevel();
        if (checkMp(human, consumeMp)) {
            human.setDefenseBySkill((int) (human.getDefense()*0.3));
            human.setMp(human.getMp()-consumeMp);
            System.out.println("Guard 시전");
        }
    }

    public static void invincible(Human human) {
        if (human.getLevel() < 99) {
            System.out.println("99레벨에 도달하지 않아 사용할 수 없습니다.");
            return;
        }
        int consumeMp = 250;
        if (checkMp(human, consumeMp)) {
            human.setMp(human.getMp()-consumeMp);
            human.invincibleOn();
            System.out.println("궁극스킬 Invincible 시전");
        }
    }
}

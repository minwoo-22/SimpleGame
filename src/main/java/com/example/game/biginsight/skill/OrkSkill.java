package com.example.game.biginsight.skill;

import com.example.game.biginsight.attribute.OrkAttribute;
import com.example.game.biginsight.parent.Skill;
import com.example.game.biginsight.tribe.Ork;

public class OrkSkill extends Skill implements OrkAttribute {

    public static void anger(Ork ork) {
        int comsumeMp = 20+5*ork.getLevel();
        if (checkMp(ork, comsumeMp)) {
            ork.setPowerBySkill((int) (ork.getPower()*0.5));
            ork.setDefenseBySkill((int) (ork.getDefense()*(-0.1)));
            ork.setMp(ork.getMp()-comsumeMp);
            System.out.println("Anger 시전");
        }
    }

    public static void frenzy(Ork ork) {
        if (ork.getLevel() < 99) {
            System.out.println("99레벨에 도달하지 않아 사용할 수 없습니다.");
            return;
        }
        int consumeMp = 220;
        if (checkMp(ork, consumeMp)) {
            ork.setPowerBySkill((ork.getPower()+ ork.getPowerByWeapon())*4);
            ork.setMp(ork.getMp()-consumeMp);
            System.out.println("궁극스킬 Frenzy 시전");
        }
    }
}

package com.example.game.biginsight.skill;

import com.example.game.biginsight.tribe.Elf;
import com.example.game.biginsight.tribe.Human;
import com.example.game.biginsight.tribe.Ork;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SkillTests {
    @Test
    @DisplayName("엘프_스킬 사용 후 변화값 확인_성공")
    void elfSkill() {
        Elf elf = new Elf();
        System.out.println("스킬 사용 전 : " + elf);
        ElfSkill.elusion(elf);
        ElfSkill.heal(elf);
        ElfSkill.steam(elf);
        System.out.println("스킬 사용 후 : " + elf);
    }

    @Test
    @DisplayName("휴먼_스킬 사용 후 변화값 확인_성공")
    void humanSkill() {
        Human human = new Human();
        System.out.println("스킬 사용 전 : " + human);
        HumanSkill.guard(human);
        HumanSkill.heal(human);
        HumanSkill.steam(human);
        System.out.println("스킬 사용 후 : " + human);
    }

    @Test
    @DisplayName("오크_스킬 사용 후 변화값 확인_성공")
    void orkSkill() {
        Ork ork = new Ork();
        System.out.println("스킬 사용 전 : " + ork);
        OrkSkill.frenzy(ork);
        OrkSkill.heal(ork);
        OrkSkill.steam(ork);
        System.out.println("스킬 사용 후 : " + ork);
    }

    @Test
    @DisplayName("엘프_스킬 지속시간 확인_성공")
    void elfSkillDuraion() {
        Elf elf = new Elf();
        Elf elf2 = new Elf();
        ElfSkill.steam(elf);
        ElfSkill.elusion(elf);
        System.out.println("스킬 사용 후 : " + elf);
//        long before = System.currentTimeMillis();
//        try {
//            Thread.sleep(8000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        long after = System.currentTimeMillis();
//
//        if (after - before >= ElfSkill.STEAM_DURATION) {
//            elf.setPowerBySkill(0);
//        }
//        if (after - before >= ElfSkill.ELUSION_DURATION) {
//            elf.setEvasionBySkill(0);
//        }
        System.out.println("스킬 지속 시간 끝난 후 : " + elf);
    }

    @Test
    @DisplayName("엘프_궁극 스킬 사용_성공")
    void elfUltiSkill() {
        Elf elf = new Elf();
        for (int i = 0; i < 98; i++) {
            elf.levelUp();
        }
        System.out.println("스킬 사용 전 : " + elf);
        ElfSkill.rapid(elf);
        System.out.println("스킬 사용 후 : " + elf);
    }

    @Test
    @DisplayName("오크_궁극 스킬 사용_성공")
    void orkUltiSkill() {
        Ork ork = new Ork();
        for (int i = 0; i < 98; i++) {
            ork.levelUp();
        }
        System.out.println("스킬 사용 전 : " + ork);
        OrkSkill.frenzy(ork);
        System.out.println("스킬 사용 후 : " + ork);
    }

}

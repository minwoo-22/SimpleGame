package com.example.game.biginsight.skill;

import com.example.game.biginsight.tribe.Elf;
import com.example.game.biginsight.tribe.Human;
import com.example.game.biginsight.tribe.Ork;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
public class SkillTests {
    @Test
    @DisplayName("엘프_스킬 사용 후 변화값 확인_성공")
    void elfSkill() {
        Elf elf = new Elf();
        log.info("스킬 사용 전 : " + elf);
        ElfSkill.elusion(elf);
        ElfSkill.heal(elf);
        ElfSkill.steam(elf);
        log.info("스킬 사용 후 : " + elf);
    }

    @Test
    @DisplayName("휴먼_스킬 사용 후 변화값 확인_성공")
    void humanSkill() {
        Human human = new Human();
        log.info("스킬 사용 전 : " + human);
        HumanSkill.guard(human);
        HumanSkill.heal(human);
        HumanSkill.steam(human);
        log.info("스킬 사용 후 : " + human);
    }

    @Test
    @DisplayName("오크_스킬 사용 후 변화값 확인_성공")
    void orkSkill() {
        Ork ork = new Ork();
        log.info("스킬 사용 전 : " + ork);
        OrkSkill.frenzy(ork);
        OrkSkill.heal(ork);
        OrkSkill.steam(ork);
        log.info("스킬 사용 후 : " + ork);
    }

    @Test
    @DisplayName("엘프_궁극 스킬 사용_성공")
    void elfUltiSkill() {
        Elf elf = new Elf();
        for (int i = 0; i < 98; i++) {
            elf.levelUp();
        }
        log.info("스킬 사용 전 : " + elf);
        ElfSkill.rapid(elf);
        log.info("스킬 사용 후 : " + elf);
    }

    @Test
    @DisplayName("오크_궁극 스킬 사용_성공")
    void orkUltiSkill() {
        Ork ork = new Ork();
        for (int i = 0; i < 98; i++) {
            ork.levelUp();
        }
        log.info("스킬 사용 전 : " + ork);
        OrkSkill.frenzy(ork);
        log.info("스킬 사용 후 : " + ork);
    }

    @Test
    @DisplayName("휴먼_마나 없을 때 스킬 시전 불가_성공")
    void mpTest() {
        Human human = new Human();
        int i = 0;
        while (i < 10) {
            HumanSkill.guard(human);
            int MP = human.getMp();
            log.info("MP : " + human.getMp());
            i++;
        }
        log.info(human.toString());
    }


}

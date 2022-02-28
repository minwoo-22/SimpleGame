package com.example.game.biginsight.weapon;

import com.example.game.biginsight.attribute.OrkAttribute;
import com.example.game.biginsight.tribe.Elf;
import com.example.game.biginsight.tribe.Human;
import com.example.game.biginsight.tribe.Ork;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class WeaponTests {
    @Test
    @DisplayName("종족_무기 착용 후 스탯 비교_성공")
    void putOnWeapon() {
        Elf elf = new Elf();
        log.info("엘프 무기 착용 전 : " + elf);
        elf.putOnWeapon(ElfWeaponTypes.SHORT_BOW.name());
        log.info("엘프 무기 착용 후 : " + elf);

        Human human = new Human();
        log.info("휴먼 무기 착용 전 : " + human);
        human.putOnWeapon(HumanWeaponTypes.LONG_SWORD.name());
        log.info("휴먼 무기 착용 후 : " + human);

        Ork ork = new Ork();
        log.info("오크 무기 착용 전 : " + ork);
        ork.putOnWeapon(OrkWeaponTypes.IRON_AXE.name());
        log.info("오크 무기 착용 후 : " + ork);
    }

    @Test
    @DisplayName("휴먼_오크 무기 착용_실패")
    void putOnWeaponFailure() {
        Human human = new Human();
        human.putOnWeapon(OrkWeaponTypes.SHORT_AXE.name());
    }

    @Test
    @DisplayName("엘프_무기 바꿔 착용하기_성공")
    void changeWeapon() {
        Elf elf = new Elf();
        elf.putOnWeapon(ElfWeaponTypes.SHORT_BOW.name());
        log.info(elf.toString());
        elf.putOnWeapon(ElfWeaponTypes.IRON_BOW.name());
        log.info(elf.toString());

        Human human = new Human();
        human.putOnWeapon(HumanWeaponTypes.SHORT_SWORD.name());
        log.info(human.toString());
        human.putOnWeapon(HumanWeaponTypes.LONG_SWORD.name());
        log.info(human.toString());

        Ork ork = new Ork();
        ork.putOnWeapon(OrkWeaponTypes.SHORT_AXE.name());
        log.info(ork.toString());
        ork.putOnWeapon(OrkWeaponTypes.IRON_AXE.name());
        log.info(ork.toString());
    }

    @Test
    @DisplayName("엘프_무기 착용 후 레벨업 스탯 비교_성공")
    void putOnWeaponAndLevelUp() {
        Elf elf = new Elf();
        log.info("무기 착용 전 : " + elf);
        elf.putOnWeapon(ElfWeaponTypes.IRON_BOW.name());
        log.info("무기 착용 후 : " + elf);
        for (int i = 0; i < 19; i++) {   //20레벨
            elf.levelUp();
        }
        log.info("레벨업 후 : " + elf);
    }



}

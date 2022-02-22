package com.example.game.biginsight.weapon;

import com.example.game.biginsight.attribute.OrkAttribute;
import com.example.game.biginsight.tribe.Elf;
import com.example.game.biginsight.tribe.Human;
import com.example.game.biginsight.tribe.Ork;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class WeaponTests {
    @Test
    @DisplayName("무기 착용 후 스탯 비교_성공")
    void putOnWeapon() {
        Elf elf = new Elf();
        System.out.println("엘프 무기 착용 전 : " + elf);
        elf.putOnWeapon(ElfWeaponTypes.SHORT_BOW.name());
        System.out.println("엘프 무기 착용 후 : " + elf);

        Human human = new Human();
        System.out.println("휴먼 무기 착용 전 : " + human);
        human.putOnWeapon(HumanWeaponTypes.LONG_SWORD.name());
        System.out.println("휴먼 무기 착용 후 : " + human);

        Ork ork = new Ork();
        System.out.println("오크 무기 착용 전 : " + ork);
        ork.putOnWeapon(OrkWeaponTypes.IRON_AXE.name());
        System.out.println("오크 무기 착용 후 : " + ork);
    }

    @Test
    @DisplayName("휴먼이 오크 무기 착용_실패")
    void putOnWeaponFailure() {
        Human human = new Human();
        human.putOnWeapon(OrkWeaponTypes.SHORT_AXE.name());
    }

    @Test
    @DisplayName("무기 바꿔 착용하기_성공")
    void changeWeapon() {
        Elf elf = new Elf();
        elf.putOnWeapon(ElfWeaponTypes.SHORT_BOW.name());
        System.out.println(elf);
        elf.putOnWeapon(ElfWeaponTypes.IRON_BOW.name());
        System.out.println(elf);

        Human human = new Human();
        human.putOnWeapon(HumanWeaponTypes.SHORT_SWORD.name());
        System.out.println(human);
        human.putOnWeapon(HumanWeaponTypes.LONG_SWORD.name());
        System.out.println(human);

        Ork ork = new Ork();
        ork.putOnWeapon(OrkWeaponTypes.SHORT_AXE.name());
        System.out.println(ork);
        ork.putOnWeapon(OrkWeaponTypes.IRON_AXE.name());
        System.out.println(ork);
    }

    @Test
    @DisplayName("무기 착용 후 레벨업 스탯 비교_성공")
    void putOnWeaponAndLevelUp() {
        Elf elf = new Elf();
        System.out.println("무기 착용 전 : " + elf);
        elf.putOnWeapon(ElfWeaponTypes.IRON_BOW.name());
        System.out.println("무기 착용 후 : " + elf);
        for (int i = 0; i < 19; i++) {   //20레벨
            elf.levelUp();
        }
        System.out.println("레벨업 후 : " + elf);
    }

    @Test
    void numTest() {
        Elf elf = new Elf();
        int num = (int) Math.round(elf.getEvasion());
        System.out.println(num);
    }

}

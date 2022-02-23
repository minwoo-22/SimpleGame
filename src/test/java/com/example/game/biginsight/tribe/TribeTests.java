package com.example.game.biginsight.tribe;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TribeTests {
    @Test
    @DisplayName("엘프_생성_성공")
    void createElf() {
        Elf elf = new Elf();
        System.out.println(elf);
    }

    @Test
    @DisplayName("휴먼_생성_성공")
    void createHuman() {
        Human human = new Human();
        System.out.println(human);
    }

    @Test
    @DisplayName("오크_생성_성공")
    void createOrk() {
        Ork ork = new Ork();
        System.out.println(ork);
    }

    @Test
    @DisplayName("엘프_레벨업_성공")
    void levelUpSuccess() {
        Elf elf = new Elf();
        elf.levelUp();
        System.out.println(elf);

        Human human = new Human();
        human.levelUp();
        System.out.println(human);

        Ork ork = new Ork();
        ork.levelUp();
        System.out.println(ork);
    }

}

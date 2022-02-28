package com.example.game.biginsight.tribe;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class TribeTests {
    @Test
    @DisplayName("엘프_생성_성공")
    void createElf() {
        Elf elf = new Elf();
        log.info(elf.toString());
    }

    @Test
    @DisplayName("휴먼_생성_성공")
    void createHuman() {
        Human human = new Human();
        log.info(human.toString());
    }

    @Test
    @DisplayName("오크_생성_성공")
    void createOrk() {
        Ork ork = new Ork();
        log.info(ork.toString());
    }

    @Test
    @DisplayName("엘프_레벨업_성공")
    void levelUpSuccess() {
        Elf elf = new Elf();
        elf.levelUp();
        log.info(elf.toString());

        Human human = new Human();
        human.levelUp();
        log.info(human.toString());

        Ork ork = new Ork();
        ork.levelUp();
        log.info(ork.toString());
    }

}

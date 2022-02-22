package com.example.game.biginsight.monster;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MonsterTests {
    @Test
    @DisplayName("몬스터 생성")
    void createMonster() {
        Monster monster = new Monster().builder()
                .hp(200)
                .power(25)
                .attackSpeed(0.4)
                .defense(5)
                .build();

        Monster monster1 = new Monster();
        System.out.println(monster.toString());
        assertThat(monster.toString()).isEqualTo(monster1.toString());
    }
}

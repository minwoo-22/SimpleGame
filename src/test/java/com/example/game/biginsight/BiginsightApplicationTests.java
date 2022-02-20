package com.example.game.biginsight;

import com.example.game.biginsight.monster.Monster;
import com.example.game.biginsight.skill.character.ElfSkill;
import com.example.game.biginsight.tribe.Elf;
import com.example.game.biginsight.tribe.common.Character;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BiginsightApplicationTests {

	@Test
	void createMonster() {
		Monster monster = new Monster().builder()
				.hp(200)
				.power(25)
				.attack_speed(0.4)
				.defense(5)
				.build();
		System.out.println(monster.toString());
	}

	@Test
	void createCharacter() {
		Elf elf = new Elf();
		System.out.println(elf.toString());
	}

	@Test
	void levelUp() {
		Elf elf = new Elf();
		System.out.println("레벨업 전 : " + elf.toString());
		elf.levelUp();
		System.out.println("레벨업 후 : " + elf.toString());
	}

	@Test
	void skill() {
		Elf elf = new Elf();
		System.out.println("스킬 사용 전 : " + elf);
		ElfSkill.elusion(elf);
		ElfSkill.heal(elf);
		ElfSkill.steam(elf);
		System.out.println("스킬 사용 후 : " + elf);
	}

}

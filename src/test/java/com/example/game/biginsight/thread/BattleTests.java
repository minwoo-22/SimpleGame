package com.example.game.biginsight.thread;

import com.example.game.biginsight.monster.Monster;
import com.example.game.biginsight.skill.ElfSkill;
import com.example.game.biginsight.skill.HumanSkill;
import com.example.game.biginsight.skill.OrkSkill;
import com.example.game.biginsight.tribe.Elf;
import com.example.game.biginsight.tribe.Human;
import com.example.game.biginsight.tribe.Ork;
import com.example.game.biginsight.weapon.HumanWeaponTypes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BattleTests {
    @Test
    @DisplayName("공격 테스트_성공")
    void attack() {
        Elf elf = new Elf();
        Monster monster = new Monster().builder()
                .hp(200)
                .power(25)
                .attackSpeed(0.4)
                .defense(5)
                .build();

        elf.attack(monster);
        monster.attack(elf);

        System.out.println(elf);
        System.out.println(monster);
    }

    @Test
    @DisplayName("[휴먼] 회피 테스트_성공")
    void evasion() {
        Human human = new Human();
        Monster monster = new Monster();

        while (true) {
            if (!monster.attack(human)) break;
        }
    }

    @Test
    @DisplayName("[몬스터] 반격 테스트_성공")
    void counterAttack() {
        Ork ork = new Ork();
        Monster monster = new Monster();

        while (true) {
            if (!ork.attack(monster)) break;
        }
        System.out.println(ork);
    }

    @Test
    @DisplayName("[엘프] 전투 도중 힐 사용(쿨타임 기능)_성공")
    void elfHeal() {
        long healStartTime = 0L;
        Elf elf = new Elf();
        Monster monster = new Monster();

        MonsterAttack monsterAttack = new MonsterAttack(monster, elf);
        Thread t1 = new Thread(monsterAttack);
        t1.start();

        while (t1.isAlive()) {
            int random = (int) (Math.random()*99+1);
            elf.attack(monster);
            System.out.println("몬스터 현재 체력 : " + monster.getHp());
            try {
                Thread.sleep((long) ((1/elf.getAttackSpeed())*1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            long checkTime = System.currentTimeMillis();

            if (elf.getHp() <= (elf.getMax_hp()/2)) {   //체력이 최대 체력의 절반 이하로 내려가면
                if (random >= 1 && random <= 40) {      //40%의 확률로 heal 스킬 사용
                    if (healStartTime == 0) {
                        ElfSkill.heal(elf);
                        healStartTime = System.currentTimeMillis();
                    } else {
                        System.out.println("힐 쿨타임입니다.");
                    }
                }
            }

            if (healStartTime != 0 && checkTime - healStartTime >= ElfSkill.HEAL_COOL_TIME){
                healStartTime = 0L;
            }


            if (monster.getHp() <= 0) {
                break;
            }
        }
        monsterAttack.threadStop(true);
        if (elf.getHp() <= 0) {
            System.out.println("플레이어 패배");
        } else {
            elf.levelUp();
            System.out.println("플레이어 승리");
        }

        System.out.println(elf);
        System.out.println(monster);
    }

    @Test
    @DisplayName("[오크] 전투 도중 전용스킬 사용_성공")
    void orkAnger() {
        long angerStartTime = 0L;
        Ork ork = new Ork();
        Monster monster = new Monster().builder()
                .hp(400)
                .power(35)
                .attackSpeed(0.5)
                .defense(8)
                .build();

        MonsterAttack monsterAttack = new MonsterAttack(monster, ork);
        Thread t1 = new Thread(monsterAttack);
        t1.start();

        while (t1.isAlive()) {
            int random = (int) (Math.random()*99+1);
            ork.attack(monster);
            System.out.println("몬스터 현재 체력 : " + monster.getHp());
            try {
                Thread.sleep((long) ((1/ork.getAttackSpeed())*1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            long checkTime = System.currentTimeMillis();

            if (random >= 1 && random <= 40) {      //40%의 확률로 anger 스킬 사용
                if (angerStartTime == 0) {
                    OrkSkill.anger(ork);
                    angerStartTime = System.currentTimeMillis();
                } else {
                    System.out.println("Anger 쿨타임입니다.");
                }
            }

            // 스킬 지속시간 확인
            if (angerStartTime != 0 && (checkTime - angerStartTime) >= OrkSkill.ANGER_DURATION) {
                ork.setPowerBySkill(0);     // 능력치 원상태로 복구
                ork.setDefenseBySkill(0);
            }

            // 쿨타임 확인
            if (angerStartTime != 0 &&
                    (checkTime - angerStartTime) >= OrkSkill.ANGER_COOL_TIME){
                angerStartTime = 0L;
            }

            if (monster.getHp() <= 0) {
                break;
            }
        }
        monsterAttack.threadStop(true);
        if (ork.getHp() <= 0) {
            System.out.println("플레이어 패배");
        } else {
            ork.levelUp();
            System.out.println("플레이어 승리");
        }

        System.out.println(ork);
        System.out.println(monster);
    }

    @Test
    @DisplayName("[휴먼] 무기 착용 후 전투_성공")
    void humanPutOnWeapon() {
        Human human = new Human();
        human.putOnWeapon(HumanWeaponTypes.LONG_SWORD.name());

        Monster monster = new Monster().builder()
                .hp(400)
                .power(35)
                .attackSpeed(0.5)
                .defense(8)
                .build();

        MonsterAttack monsterAttack = new MonsterAttack(monster, human);
        Thread t1 = new Thread(monsterAttack);
        t1.start();

        while (t1.isAlive()) {
            human.attack(monster);
            System.out.println("몬스터 현재 체력 : " + monster.getHp());
            try {
                Thread.sleep((long) ((1/human.getAttackSpeed())*1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (monster.getHp() <= 0) {
                break;
            }
        }
        monsterAttack.threadStop(true);
        if (human.getHp() <= 0) {
            System.out.println("플레이어 패배");
        } else {
            human.levelUp();
            System.out.println("플레이어 승리");
        }

        System.out.println(human);
        System.out.println(monster);
    }


    @Test
    @DisplayName("[휴먼] 궁극스킬 테스트_성공")
    void humanUltiSkill() {
        long invincibleStartTime = 0L;
        Human human = new Human();
        human.putOnWeapon(HumanWeaponTypes.LONG_SWORD.name());
        for (int i = 0; i < 98; i++) {
            human.levelUp();    //99레벨
        }
        int maxHp = human.getMax_hp();  //스킬 지속 지간 후 원상태로 복구하기 위한 maxHp
        int hp = 0;

        Monster monster = new Monster().builder()
                .hp(100000)
                .power(600)
                .attackSpeed(0.8)
                .defense(90)
                .build();

        MonsterAttack monsterAttack = new MonsterAttack(monster, human);
        Thread t1 = new Thread(monsterAttack);
        t1.start();

        while (t1.isAlive()) {
            int random = (int) (Math.random()*99+1);
            human.attack(monster);
            System.out.println("몬스터 현재 체력 : " + monster.getHp());
            try {
                Thread.sleep((long) ((1/human.getAttackSpeed())*1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            long checkTime = System.currentTimeMillis();

            if (random >= 1 && random <= 50) {      //50%의 확률로 invincible 스킬 사용
                if (invincibleStartTime == 0) {
                    hp = human.getHp();
                    HumanSkill.invincible(human);
                    invincibleStartTime = System.currentTimeMillis();
                } else {
                    System.out.println("Invincible 쿨타임입니다.");
                }
            }

            // 스킬 지속시간 확인
            if (invincibleStartTime != 0 && (checkTime - invincibleStartTime) >= HumanSkill.INVINCIBLE_DURAION) {
                human.setMax_hp(maxHp);     // 능력치 원상태로 복구
                human.setHp(hp);
            }

            // 쿨타임 확인
            if (invincibleStartTime != 0 &&
                    (checkTime - invincibleStartTime) >= HumanSkill.INVINCIBLE_COOL_TIME){
                invincibleStartTime = 0L;
            }

            if (monster.getHp() <= 0) {
                human.setMax_hp(maxHp);
                human.setHp(hp);
                break;
            }
        }
        monsterAttack.threadStop(true);
        if (human.getHp() <= 0) {
            System.out.println("플레이어 패배");
        } else {
            System.out.println("플레이어 승리");
            human.levelUp();
        }

        System.out.println(human);
        System.out.println(monster);
    }

    @Test
    @DisplayName("[엘프] 모든 스킬 사용하여 전투_성공")
    void finalBattle() {

    }
}

package com.example.game.biginsight.thread;

import com.example.game.biginsight.monster.Monster;
import com.example.game.biginsight.thread.commonskill.HealThread;
import com.example.game.biginsight.thread.commonskill.SteamThread;
import com.example.game.biginsight.thread.elfskill.ElusionThread;
import com.example.game.biginsight.thread.elfskill.RapidThread;
import com.example.game.biginsight.thread.humanskill.GuardThread;
import com.example.game.biginsight.thread.humanskill.InvincibleThread;
import com.example.game.biginsight.thread.orkskill.AngerThread;
import com.example.game.biginsight.thread.orkskill.FrenzyThread;
import com.example.game.biginsight.tribe.Elf;
import com.example.game.biginsight.tribe.Human;
import com.example.game.biginsight.tribe.Ork;
import com.example.game.biginsight.weapon.ElfWeaponTypes;
import com.example.game.biginsight.weapon.HumanWeaponTypes;
import com.example.game.biginsight.weapon.OrkWeaponTypes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BattleTests {
    @Test
    @DisplayName("엘프,몬스터_공격 테스트_성공")
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
    @DisplayName("휴먼_회피 테스트_성공")
    void evasion() {
        Human human = new Human();
        Monster monster = new Monster();

        while (true) {
            if (!monster.attack(human)) break;
        }
    }

    @Test
    @DisplayName("몬스터_반격 테스트_성공")
    void counterAttack() {
        Ork ork = new Ork();
        Monster monster = new Monster();

        while (true) {
            if (!ork.attack(monster)) break;
        }
        System.out.println(ork);
    }

    @Test
    @DisplayName("엘프_전투 중 힐 사용(쿨타임 기능)_성공")
    void elfHeal() {
        Elf elf = new Elf();
        Monster monster = new Monster().builder()
                .hp(400)
                .power(35)
                .attackSpeed(0.7)
                .defense(8)
                .build();

        MonsterAttack monsterAttack = new MonsterAttack(monster, elf);
        Thread t1 = new Thread(monsterAttack);
        t1.start();

        Thread t2 = new Thread();
        while (t1.isAlive()) {
            elf.attack(monster);
            System.out.println("몬스터 현재 체력 : " + monster.getHp());
            try {
                Thread.sleep((long) ((1/elf.getTotalAttackSpeed())*1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (elf.getHp() <= 300) {   //체력이 300이하로 내려가면
                if (!t2.isAlive()){
                    int random = (int) (Math.random()*99+1);
                    if (random >= 1 && random <= 40) {      //40%의 확률로 heal 스킬 사용
                        t2 = new HealThread(elf);
                        t2.start();
                    }
                }

            }

            if (monster.getHp() <= 0) break;
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
    @DisplayName("오크_전투 중 Steam 사용_성공")
    void orkSteam() {
        Ork ork = new Ork();
        Monster monster = new Monster();

        MonsterAttack monsterAttack = new MonsterAttack(monster, ork);
        Thread t1 = new Thread(monsterAttack);
        Thread t2 = new Thread();

        t1.start();
        while (t1.isAlive()) {
            ork.attack(monster);
            System.out.println("몬스터 현재 체력 : " + monster.getHp());
            try {
                Thread.sleep((long) ((1/ork.getTotalAttackSpeed())*1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (!t2.isAlive()){
                int random = (int) (Math.random()*99+1);
                if (random >= 1 && random <= 40) {      //40%의 확률로 steam 스킬 사용
                    t2 = new SteamThread(ork);
                    t2.start();
                }
            }
            if (monster.getHp() <= 0) break;
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
    @DisplayName("오크_전투 도중 전용스킬 사용_성공")
    void orkAnger() {
        Ork ork = new Ork();
        Monster monster = new Monster().builder()
                .hp(400)
                .power(35)
                .attackSpeed(0.5)
                .defense(8)
                .build();

        MonsterAttack monsterAttack = new MonsterAttack(monster, ork);
        Thread t1 = new Thread(monsterAttack);
        Thread t2 = new Thread();
        t1.start();

        while (t1.isAlive()) {
            ork.attack(monster);
            System.out.println("몬스터 현재 체력 : " + monster.getHp());
            try {
                Thread.sleep((long) ((1/ork.getTotalAttackSpeed())*1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (!t2.isAlive()){
                int random = (int) (Math.random()*99+1);
                if (random >= 1 && random <= 50) {      //50%의 확률로 anger 스킬 사용
                    t2 = new AngerThread(ork);
                    t2.start();
                }
            }

            if (monster.getHp() <= 0) break;
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
    @DisplayName("휴먼_무기 착용 후 전투_성공")
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
                Thread.sleep((long) ((1/human.getTotalAttackSpeed())*1000));
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
    @DisplayName("휴먼_궁극스킬 테스트_성공")
    void humanUltiSkill() {
        Human human = new Human();
        human.putOnWeapon(HumanWeaponTypes.LONG_SWORD.name());
        for (int i = 0; i < 98; i++) {
            human.levelUp();    //99레벨
        }

        Monster monster = new Monster().builder()
                .hp(100000)
                .power(600)
                .attackSpeed(0.8)
                .defense(90)
                .build();

        MonsterAttack monsterAttack = new MonsterAttack(monster, human);
        Thread t1 = new Thread(monsterAttack);
        Thread t2 = new Thread();
        t1.start();

        while (t1.isAlive()) {
            human.attack(monster);
            System.out.println("몬스터 현재 체력 : " + monster.getHp());
            try {
                Thread.sleep((long) ((1/human.getTotalAttackSpeed())*1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (!t2.isAlive()) {
                int random = (int) (Math.random()*99+1);
                if (random >= 1 && random <= 50) {      //50%의 확률로 invincible 스킬 사용
                    t2 = new InvincibleThread(human);
                    t2.start();
                }
            }

            if (monster.getHp() <= 0) break;
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
    @DisplayName("엘프_궁극스킬_테스트_성공")
    void elfUltiSkill() {
        Elf elf = new Elf();
        elf.putOnWeapon(ElfWeaponTypes.IRON_BOW.name());
        for (int i = 0; i < 98; i++) {
            elf.levelUp();    //99레벨
        }

        Monster monster = new Monster().builder()
                .hp(50000)
                .power(600)
                .attackSpeed(0.8)
                .defense(90)
                .build();

        MonsterAttack monsterAttack = new MonsterAttack(monster, elf);
        Thread t1 = new Thread(monsterAttack);
        Thread t2 = new Thread();
        t1.start();

        while (t1.isAlive()) {
            elf.attack(monster);
            System.out.println("몬스터 현재 체력 : " + monster.getHp());
            try {
                Thread.sleep((long) ((1/elf.getTotalAttackSpeed())*1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (!t2.isAlive()) {
                int random = (int) (Math.random()*99+1);
                if (random >= 1 && random <= 50) {      //50%의 확률로 invincible 스킬 사용
                    t2 = new RapidThread(elf);
                    t2.start();
                }
            }

            if (monster.getHp() <= 0) break;
        }
        monsterAttack.threadStop(true);
        if (elf.getHp() <= 0) {
            System.out.println("플레이어 패배");
        } else {
            System.out.println("플레이어 승리");
        }

        System.out.println(elf);
        System.out.println(monster);
    }

    @Test
    @DisplayName("엘프_모든 스킬 사용하여 실제 전투 테스트_성공")
    void elfRealBattle() {
        Elf elf = new Elf();
        for (int i = 0; i < 98; i++) {      // 레벨 99
            elf.levelUp();
        }
        elf.putOnWeapon(ElfWeaponTypes.IRON_BOW.name());    // 무기장착

        Monster monster = new Monster().builder()   // 보스급 몬스터
                .hp(50000)
                .power(500)
                .attackSpeed(0.8)
                .defense(90)
                .build();

        MonsterAttack monsterAttack = new MonsterAttack(monster, elf);
        Thread t1 = new Thread(monsterAttack);
        Thread t2 = new Thread();   //Heal
        Thread t3 = new Thread();   //Steam
        Thread t4 = new Thread();   //Elusion
        Thread t5 = new Thread();   //Rapid

        t1.start();
        while (t1.isAlive()) {
            elf.attack(monster);
            System.out.println("몬스터 현재 체력 : " + monster.getHp());
            try {
                Thread.sleep((long) ((1/ elf.getTotalAttackSpeed())*1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (elf.getHp() <= (elf.getMax_hp()*0.6)) {   //체력이 전체 체력의 60% 이하로 내려가면
                if (!t2.isAlive()){
                    int random = (int) (Math.random()*99+1);
                    if (random >= 1 && random <= 50) {      //50% 확률로 heal 시전
                        t2 = new HealThread(elf);
                        t2.start();
                    }
                }
            }

            if (!t3.isAlive()) {    //40% 확률로 steam 시전
                int random = (int) (Math.random()*99+1);
                if (random >= 1 && random <= 40) {
                    t3 = new SteamThread(elf);
                    t3.start();
                }
            }

            if (!t4.isAlive()) {    //30%의 확률로 elusion 시전
                int random = (int) (Math.random()*99+1);
                if (random >= 1 && random <= 30) {
                    t4 = new ElusionThread(elf);
                    t4.start();
                }
            }

            if (!t5.isAlive()) {    //20%의 확률로 rapid 시전
                int random = (int) (Math.random()*99+1);
                if (random >= 1 && random <= 20) {
                    t5 = new RapidThread(elf);
                    t5.start();
                }
            }

            if (monster.getHp() <= 0) break;
        }
        monsterAttack.threadStop(true);
        if (elf.getHp() <= 0) {
            System.out.println("플레이어 패배");
        } else {
            System.out.println("플레이어 승리");
        }

        System.out.println(elf);
        System.out.println(monster);
    }

    @Test
    @DisplayName("휴먼_모든 스킬 사용하여 실제 전투 테스트_성공")
    void humanRealBattle() {
        Human human = new Human();
        for (int i = 0; i < 98; i++) {      // 레벨 99
            human.levelUp();
        }
        human.putOnWeapon(HumanWeaponTypes.LONG_SWORD.name());    // 무기장착

        Monster monster = new Monster().builder()   // 보스급 몬스터
                .hp(50000)
                .power(500)
                .attackSpeed(0.8)
                .defense(90)
                .build();

        MonsterAttack monsterAttack = new MonsterAttack(monster, human);
        Thread t1 = new Thread(monsterAttack);
        Thread t2 = new Thread();   //Heal
        Thread t3 = new Thread();   //Steam
        Thread t4 = new Thread();   //Guard
        Thread t5 = new Thread();   //Invincible

        t1.start();
        while (t1.isAlive()) {
            human.attack(monster);
            System.out.println("몬스터 현재 체력 : " + monster.getHp());
            try {
                Thread.sleep((long) ((1/ human.getTotalAttackSpeed())*1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (human.getHp() <= (human.getMax_hp()*0.6)) {   //체력이 전체 체력의 60% 이하로 내려가면
                if (!t2.isAlive()){
                    int random = (int) (Math.random()*99+1);
                    if (random >= 1 && random <= 50) {      //50% 확률로 heal 시전
                        t2 = new HealThread(human);
                        t2.start();
                    }
                }
            }

            if (!t3.isAlive()) {    //40% 확률로 steam 시전
                int random = (int) (Math.random()*99+1);
                if (random >= 1 && random <= 40) {
                    t3 = new SteamThread(human);
                    t3.start();
                }
            }

            if (!t4.isAlive()) {    //30%의 확률로 guard 시전
                int random = (int) (Math.random()*99+1);
                if (random >= 1 && random <= 30) {
                    t4 = new GuardThread(human);
                    t4.start();
                }
            }

            if (!t5.isAlive()) {    //20%의 확률로 invincible 시전
                int random = (int) (Math.random()*99+1);
                if (random >= 1 && random <= 20) {
                    t5 = new InvincibleThread(human);
                    t5.start();
                }
            }

            if (monster.getHp() <= 0) break;
        }
        monsterAttack.threadStop(true);
        if (human.getHp() <= 0) {
            System.out.println("플레이어 패배");
        } else {
            System.out.println("플레이어 승리");
        }

        System.out.println(human);
        System.out.println(monster);
    }

    @Test
    @DisplayName("오크_모든 스킬 사용하여 실제 전투 테스트_성공")
    void orkRealBattle() {
        Ork ork = new Ork();
        for (int i = 0; i < 98; i++) {      // 레벨 99
            ork.levelUp();
        }
        ork.putOnWeapon(OrkWeaponTypes.IRON_AXE.name());    // 무기장착

        Monster monster = new Monster().builder()   // 보스급 몬스터
                .hp(100000)
                .power(500)
                .attackSpeed(0.8)
                .defense(90)
                .build();

        MonsterAttack monsterAttack = new MonsterAttack(monster, ork);
        Thread t1 = new Thread(monsterAttack);
        Thread t2 = new Thread();   //Heal
        Thread t3 = new Thread();   //Steam
        Thread t4 = new Thread();   //Anger
        Thread t5 = new Thread();   //Frenzy

        t1.start();
        while (t1.isAlive()) {
            ork.attack(monster);
            System.out.println("몬스터 현재 체력 : " + monster.getHp());
            try {
                Thread.sleep((long) ((1/ork.getTotalAttackSpeed())*1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (ork.getHp() <= (ork.getMax_hp()*0.6)) {   //체력이 전체 체력의 60% 이하로 내려가면
                if (!t2.isAlive()){
                    int random = (int) (Math.random()*99+1);
                    if (random >= 1 && random <= 40) {      //40% 확률로 Heal 시전
                        t2 = new HealThread(ork);
                        t2.start();
                    }
                }
            }

            if (!t3.isAlive()) {    //40% 확률로 Steam 시전
                int random = (int) (Math.random()*99+1);
                if (random >= 1 && random <= 40) {
                    t3 = new SteamThread(ork);
                    t3.start();
                }
            }

            if (!t4.isAlive()) {    //30%의 확률로 Anger 시전
                int random = (int) (Math.random()*99+1);
                if (random >= 1 && random <= 30) {
                    t4 = new AngerThread(ork);
                    t4.start();
                }
            }

            if (!t5.isAlive()) {    //20%의 확률로 Frenzy 시전
                int random = (int) (Math.random()*99+1);
                if (random >= 1 && random <= 20) {
                    t5 = new FrenzyThread(ork);
                    t5.start();
                }
            }

            if (monster.getHp() <= 0) break;
        }
        monsterAttack.threadStop(true);
        if (ork.getHp() <= 0) {
            System.out.println("플레이어 패배");
        } else {
            System.out.println("플레이어 승리");
        }

        System.out.println(ork);
        System.out.println(monster);
    }
}

package com.example.game.biginsight.parent;

public abstract class Skill {
    public static final int HEAL_COOL_TIME = 20000;
    public static final int STEAM_COOL_TIME = 20000;
    public static final int STEAM_DURATION = 6000;

    public static void heal(PlayerUnit playerUnit) {
        int consumeMp = 20+5*playerUnit.getLevel();
        if (checkMp(playerUnit, consumeMp)) {
            playerUnit.setHp((int) (playerUnit.getHp()+ playerUnit.getMax_hp()*0.3));   // 최대 체력의 30% 만큼 회복
            playerUnit.setMp(playerUnit.getMp()-consumeMp);
            System.out.println("heal 시전");
        }

    }

    public static void steam(PlayerUnit playerUnit) {
        int consumeMp = 30+5*playerUnit.getLevel();
        if (checkMp(playerUnit, consumeMp)) {
            playerUnit.setPowerBySkill((int) (playerUnit.getPower()*0.2));
            playerUnit.setMp(playerUnit.getMp()-consumeMp);
            System.out.println("steam 시전");
        }
    }

    public static boolean checkMp(PlayerUnit playerUnit, int consumeMp) {
        boolean check = true;
        if (playerUnit.getMp() <= 0 || consumeMp > playerUnit.getMp()) {
            System.out.println("[MP 부족] 스킬 사용 불가");
            check = false;
        }
        return check;
    }

}

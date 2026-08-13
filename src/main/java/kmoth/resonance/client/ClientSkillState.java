package kmoth.resonance.client;

import kmoth.resonance.skill.ResonanceSkill;

public class ClientSkillState {

    private static int skillPoints = 0;

    private static boolean bladeResonanceUnlocked = false;
    private static boolean pulseStepUnlocked = false;
    private static boolean resonantGuardUnlocked = false;

    private static ResonanceSkill equippedSkill =
            ResonanceSkill.NONE;


    public static int getSkillPoints() {
        return skillPoints;
    }

    public static void setSkillPoints(int points) {
        skillPoints = points;
    }


    public static boolean isBladeResonanceUnlocked() {
        return bladeResonanceUnlocked;
    }

    public static void setBladeResonanceUnlocked(
            boolean unlocked
    ) {
        bladeResonanceUnlocked = unlocked;
    }


    public static boolean isPulseStepUnlocked() {
        return pulseStepUnlocked;
    }

    public static void setPulseStepUnlocked(
            boolean unlocked
    ) {
        pulseStepUnlocked = unlocked;
    }


    public static boolean isResonantGuardUnlocked() {
        return resonantGuardUnlocked;
    }

    public static void setResonantGuardUnlocked(
            boolean unlocked
    ) {
        resonantGuardUnlocked = unlocked;
    }


    public static ResonanceSkill getEquippedSkill() {
        return equippedSkill;
    }

    public static void setEquippedSkill(
            ResonanceSkill skill
    ) {
        equippedSkill = skill;
    }


    private ClientSkillState() {
    }
}
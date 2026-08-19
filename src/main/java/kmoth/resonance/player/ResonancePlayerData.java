package kmoth.resonance.player;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import kmoth.resonance.skill.ResonanceSkill;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

public class ResonancePlayerData {

    public static final Codec<ResonancePlayerData> CODEC =
            RecordCodecBuilder.create(instance ->
                    instance.group(

                            Codec.INT
                                    .optionalFieldOf("xp", 0)
                                    .forGetter(ResonancePlayerData::getXp),

                            Codec.INT
                                    .optionalFieldOf("level", 1)
                                    .forGetter(ResonancePlayerData::getLevel),

                            Codec.INT
                                    .optionalFieldOf("skill_points", 0)
                                    .forGetter(ResonancePlayerData::getSkillPoints),

                            Codec.STRING
                                    .listOf()
                                    .optionalFieldOf(
                                            "unlocked_skills",
                                            List.of()
                                    )
                                    .forGetter(
                                            ResonancePlayerData::getUnlockedSkillNames
                                    ),

                            Codec.STRING
                                    .optionalFieldOf(
                                            "equipped_skill",
                                            "NONE"
                                    )
                                    .forGetter(
                                            ResonancePlayerData::getEquippedSkillName
                                    )

                    ).apply(
                            instance,
                            ResonancePlayerData::new
                    )
            );


    private int xp;
    private int level;
    private int skillPoints;

    private final Set<ResonanceSkill> unlockedSkills;

    private ResonanceSkill equippedSkill;


    // ==========================================
    // DEFAULT NEW PLAYER DATA
    // ==========================================

    public ResonancePlayerData() {

        this(
                0,
                1,
                0,
                List.of(),
                "NONE"
        );
    }


    // ==========================================
    // CODEC CONSTRUCTOR
    // ==========================================

    public ResonancePlayerData(
            int xp,
            int level,
            int skillPoints,
            List<String> unlockedSkillNames,
            String equippedSkillName
    ) {

        this.xp =
                Math.max(
                        0,
                        xp
                );

        this.level =
                Math.max(
                        1,
                        level
                );

        this.skillPoints =
                Math.max(
                        0,
                        skillPoints
                );

        this.unlockedSkills =
                EnumSet.noneOf(
                        ResonanceSkill.class
                );


        // ==========================================
        // LOAD UNLOCKED SKILLS
        // ==========================================

        for (String skillName : unlockedSkillNames) {

            ResonanceSkill skill =
                    parseSkill(skillName);

            if (skill != ResonanceSkill.NONE) {
                unlockedSkills.add(skill);
            }
        }


        // ==========================================
        // LOAD EQUIPPED SKILL
        // ==========================================

        ResonanceSkill loadedEquipped =
                parseSkill(
                        equippedSkillName
                );

        if (loadedEquipped != ResonanceSkill.NONE
                && unlockedSkills.contains(
                loadedEquipped
        )) {

            this.equippedSkill =
                    loadedEquipped;

        } else {

            this.equippedSkill =
                    ResonanceSkill.NONE;
        }
    }


    // ==========================================
    // XP
    // ==========================================

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {

        this.xp =
                Math.max(
                        0,
                        xp
                );
    }


    // ==========================================
    // LEVEL
    // ==========================================

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {

        this.level =
                Math.max(
                        1,
                        level
                );
    }


    // ==========================================
    // SKILL POINTS
    // ==========================================

    public int getSkillPoints() {
        return skillPoints;
    }

    public void setSkillPoints(
            int skillPoints
    ) {

        this.skillPoints =
                Math.max(
                        0,
                        skillPoints
                );
    }

    public void addSkillPoints(
            int amount
    ) {

        skillPoints =
                Math.max(
                        0,
                        skillPoints + amount
                );
    }


    // ==========================================
    // UNLOCKED SKILLS
    // ==========================================

    public boolean isSkillUnlocked(
            ResonanceSkill skill
    ) {

        return unlockedSkills.contains(skill);
    }


    public void unlockSkill(
            ResonanceSkill skill
    ) {

        if (skill == ResonanceSkill.NONE) {
            return;
        }

        unlockedSkills.add(skill);
    }


    public List<String> getUnlockedSkillNames() {

        List<String> names =
                new ArrayList<>();

        for (ResonanceSkill skill : unlockedSkills) {

            names.add(
                    skill.name()
            );
        }

        return names;
    }


    // ==========================================
    // EQUIPPED SKILL
    // ==========================================

    public ResonanceSkill getEquippedSkill() {

        return equippedSkill;
    }


    public void equipSkill(
            ResonanceSkill skill
    ) {

        if (skill == ResonanceSkill.NONE) {

            equippedSkill =
                    ResonanceSkill.NONE;

            return;
        }

        if (!isSkillUnlocked(skill)) {
            return;
        }

        equippedSkill = skill;
    }


    public String getEquippedSkillName() {

        return equippedSkill.name();
    }


    // ==========================================
    // SAFE ENUM LOADING
    // ==========================================

    private static ResonanceSkill parseSkill(
            String name
    ) {

        if (name == null) {
            return ResonanceSkill.NONE;
        }

        try {

            return ResonanceSkill.valueOf(
                    name
            );

        } catch (IllegalArgumentException exception) {

            return ResonanceSkill.NONE;
        }
    }
}
package kmoth.resonance.skill;

import net.minecraft.server.level.ServerPlayer;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class PlayerSkillState {

    private static final Map<UUID, Set<ResonanceSkill>> UNLOCKED =
            new HashMap<>();

    private static final Map<UUID, ResonanceSkill> EQUIPPED =
            new HashMap<>();

    public static boolean isUnlocked(
            ServerPlayer player,
            ResonanceSkill skill
    ) {

        return UNLOCKED
                .getOrDefault(
                        player.getUUID(),
                        EnumSet.noneOf(ResonanceSkill.class)
                )
                .contains(skill);
    }

    public static void unlock(
            ServerPlayer player,
            ResonanceSkill skill
    ) {

        UNLOCKED
                .computeIfAbsent(
                        player.getUUID(),
                        id -> EnumSet.noneOf(ResonanceSkill.class)
                )
                .add(skill);
    }

    public static void equip(
            ServerPlayer player,
            ResonanceSkill skill
    ) {

        if (!isUnlocked(player, skill)) {
            return;
        }

        EQUIPPED.put(
                player.getUUID(),
                skill
        );
    }

    public static ResonanceSkill getEquipped(
            ServerPlayer player
    ) {

        return EQUIPPED.getOrDefault(
                player.getUUID(),
                ResonanceSkill.NONE
        );
    }

    private PlayerSkillState() {
    }
}
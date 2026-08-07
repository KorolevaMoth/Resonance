package kmoth.resonance.progression;

import kmoth.resonance.data.BalanceDataLoader;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ResonanceProgression {

    private static final Map<UUID, Integer> XP = new HashMap<>();
    private static final Map<UUID, Integer> LEVEL = new HashMap<>();
    private static final Map<UUID, Integer> SKILL_POINTS = new HashMap<>();

    public static void addXp(ServerPlayer player, int amount) {

        UUID id = player.getUUID();

        int currentXp = XP.getOrDefault(id, 0);
        int currentLevel = LEVEL.getOrDefault(id, 1);

        currentXp += amount;

        if (currentLevel == 1
                && currentXp >= BalanceDataLoader.progression.xp_to_level_2) {

            currentLevel = 2;

            int currentSkillPoints =
                    SKILL_POINTS.getOrDefault(id, 0);

            SKILL_POINTS.put(
                    id,
                    currentSkillPoints + 1
            );

            player.sendSystemMessage(
                    Component.literal(
                            "Resonance Level Up! Level 2 reached."
                    )
            );

            player.sendSystemMessage(
                    Component.literal(
                            "You gained 1 Skill Point."
                    )
            );
        }

        XP.put(id, currentXp);
        LEVEL.put(id, currentLevel);

        player.sendSystemMessage(
                Component.literal(
                        "Resonance XP: "
                                + currentXp
                                + " / "
                                + BalanceDataLoader.progression.xp_to_level_2
                )
        );
    }

    public static int getSkillPoints(ServerPlayer player) {

        return SKILL_POINTS.getOrDefault(
                player.getUUID(),
                0
        );
    }

    public static boolean spendSkillPoints(
            ServerPlayer player,
            int amount
    ) {

        UUID id = player.getUUID();

        int current =
                SKILL_POINTS.getOrDefault(id, 0);

        if (current < amount) {
            return false;
        }

        SKILL_POINTS.put(
                id,
                current - amount
        );

        return true;
    }
}
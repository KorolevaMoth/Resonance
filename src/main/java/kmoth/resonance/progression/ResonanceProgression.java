package kmoth.resonance.progression;

import kmoth.resonance.data.BalanceDataLoader;
import kmoth.resonance.network.SkillStateSync;
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

        int currentXp =
                XP.getOrDefault(id, 0);

        int currentLevel =
                LEVEL.getOrDefault(id, 1);

        currentXp += amount;


        // ==========================================
        // CHECK FOR MULTIPLE LEVEL UPS
        // ==========================================

        int[] requirements =
                BalanceDataLoader.progression.xp_requirements;

        while (
                currentLevel < requirements.length
                        && currentXp >= requirements[currentLevel]
        ) {

            currentLevel++;

            int currentSkillPoints =
                    SKILL_POINTS.getOrDefault(id, 0);

            SKILL_POINTS.put(
                    id,
                    currentSkillPoints
                            + BalanceDataLoader
                            .progression
                            .skill_points_per_level
            );

            player.sendSystemMessage(
                    Component.literal(
                            "Resonance Level Up! Level "
                                    + currentLevel
                                    + " reached."
                    )
            );

            player.sendSystemMessage(
                    Component.literal(
                            "+"
                                    + BalanceDataLoader
                                    .progression
                                    .skill_points_per_level
                                    + " Skill Point!"
                    )
            );
        }


        XP.put(id, currentXp);
        LEVEL.put(id, currentLevel);


        // ==========================================
        // DISPLAY PROGRESS
        // ==========================================

        if (currentLevel < requirements.length) {

            player.sendSystemMessage(
                    Component.literal(
                            "Resonance XP: "
                                    + currentXp
                                    + " / "
                                    + requirements[currentLevel]
                                    + " | Level "
                                    + currentLevel
                    )
            );

        } else {

            player.sendSystemMessage(
                    Component.literal(
                            "Resonance XP: "
                                    + currentXp
                                    + " | Level "
                                    + currentLevel
                                    + " (MAX)"
                    )
            );
        }


        SkillStateSync.syncToClient(player);
    }


    public static int getSkillPoints(ServerPlayer player) {

        return SKILL_POINTS.getOrDefault(
                player.getUUID(),
                0
        );
    }


    public static int getLevel(ServerPlayer player) {

        return LEVEL.getOrDefault(
                player.getUUID(),
                1
        );
    }


    public static int getXp(ServerPlayer player) {

        return XP.getOrDefault(
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

        if (amount < 0) {
            return false;
        }

        if (current < amount) {
            return false;
        }

        SKILL_POINTS.put(
                id,
                current - amount
        );

        SkillStateSync.syncToClient(player);

        return true;
    }


    private ResonanceProgression() {
    }
}
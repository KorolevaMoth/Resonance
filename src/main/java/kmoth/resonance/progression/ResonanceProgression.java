package kmoth.resonance.progression;

import kmoth.resonance.data.BalanceDataLoader;
import kmoth.resonance.network.SkillStateSync;
import kmoth.resonance.player.ModAttachments;
import kmoth.resonance.player.ResonancePlayerData;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public class ResonanceProgression {

    private static ResonancePlayerData getData(
            ServerPlayer player
    ) {
        return player.getData(
                ModAttachments.RESONANCE_PLAYER_DATA.get()
        );
    }


    public static void addXp(
            ServerPlayer player,
            int amount
    ) {

        ResonancePlayerData data =
                getData(player);

        int currentXp =
                data.getXp();

        int currentLevel =
                data.getLevel();

        currentXp += amount;


        // ==========================================
        // CHECK FOR MULTIPLE LEVEL UPS
        // ==========================================

        int[] requirements =
                BalanceDataLoader
                        .progression
                        .xp_requirements;

        while (
                currentLevel < requirements.length
                        && currentXp
                        >= requirements[currentLevel]
        ) {

            currentLevel++;

            data.addSkillPoints(
                    BalanceDataLoader
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


        data.setXp(currentXp);
        data.setLevel(currentLevel);


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


    public static int getSkillPoints(
            ServerPlayer player
    ) {

        return getData(player)
                .getSkillPoints();
    }


    public static int getLevel(
            ServerPlayer player
    ) {

        return getData(player)
                .getLevel();
    }


    public static int getXp(
            ServerPlayer player
    ) {

        return getData(player)
                .getXp();
    }


    public static boolean spendSkillPoints(
            ServerPlayer player,
            int amount
    ) {

        if (amount < 0) {
            return false;
        }

        ResonancePlayerData data =
                getData(player);

        int current =
                data.getSkillPoints();

        if (current < amount) {
            return false;
        }

        data.setSkillPoints(
                current - amount
        );

        SkillStateSync.syncToClient(player);

        return true;
    }


    private ResonanceProgression() {
    }
}
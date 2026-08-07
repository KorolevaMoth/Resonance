package kmoth.resonance.skill;

import kmoth.resonance.data.BalanceDataLoader;
import kmoth.resonance.progression.ResonanceProgression;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class BladeResonanceUnlocks {

    private static final Set<UUID> UNLOCKED = new HashSet<>();

    public static boolean isUnlocked(ServerPlayer player) {
        return UNLOCKED.contains(player.getUUID());
    }

    public static void tryUnlock(ServerPlayer player) {

        if (isUnlocked(player)) {
            player.sendSystemMessage(
                    Component.literal("Blade Resonance is already unlocked.")
            );
            return;
        }

        int cost =
                BalanceDataLoader.bladeResonance.skill_point_cost;

        boolean success =
                ResonanceProgression.spendSkillPoints(player, cost);

        if (!success) {
            player.sendSystemMessage(
                    Component.literal(
                            "Not enough Skill Points. Blade Resonance costs "
                                    + cost
                                    + "."
                    )
            );
            return;
        }

        UNLOCKED.add(player.getUUID());

        player.sendSystemMessage(
                Component.literal(
                        "Blade Resonance unlocked for "
                                + cost
                                + " Skill Point(s)!"
                )
        );
    }
}
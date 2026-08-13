package kmoth.resonance.skill;

import kmoth.resonance.data.BalanceDataLoader;
import kmoth.resonance.network.SkillStateSync;
import kmoth.resonance.progression.ResonanceProgression;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public class PulseStepUnlocks {

    public static boolean isUnlocked(ServerPlayer player) {

        return PlayerSkillState.isUnlocked(
                player,
                ResonanceSkill.PULSE_STEP
        );
    }

    public static void tryUnlock(ServerPlayer player) {

        // Already unlocked -> equip it.
        if (isUnlocked(player)) {

            PlayerSkillState.equip(
                    player,
                    ResonanceSkill.PULSE_STEP
            );

            SkillStateSync.syncToClient(player);

            player.sendSystemMessage(
                    Component.literal(
                            "Pulse Step equipped."
                    )
            );

            return;
        }

        // Blade Resonance is the prerequisite.
        if (!PlayerSkillState.isUnlocked(
                player,
                ResonanceSkill.BLADE_RESONANCE
        )) {

            player.sendSystemMessage(
                    Component.literal(
                            "Blade Resonance must be unlocked first."
                    )
            );

            return;
        }

        int cost =
                BalanceDataLoader
                        .pulseStep
                        .skill_point_cost;

        // Protect against invalid balance data.
        if (cost < 0) {

            player.sendSystemMessage(
                    Component.literal(
                            "Pulse Step has an invalid skill-point cost."
                    )
            );

            return;
        }

        boolean success =
                ResonanceProgression.spendSkillPoints(
                        player,
                        cost
                );

        if (!success) {

            player.sendSystemMessage(
                    Component.literal(
                            "Not enough Skill Points. "
                                    + "Pulse Step costs "
                                    + cost
                                    + "."
                    )
            );

            return;
        }

        PlayerSkillState.unlock(
                player,
                ResonanceSkill.PULSE_STEP
        );

        PlayerSkillState.equip(
                player,
                ResonanceSkill.PULSE_STEP
        );

        SkillStateSync.syncToClient(player);

        player.sendSystemMessage(
                Component.literal(
                        "Pulse Step unlocked and equipped for "
                                + cost
                                + " Skill Point(s)!"
                )
        );
    }

    private PulseStepUnlocks() {
    }
}
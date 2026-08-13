package kmoth.resonance.skill;

import kmoth.resonance.data.BalanceDataLoader;
import kmoth.resonance.network.SkillStateSync;
import kmoth.resonance.progression.ResonanceProgression;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public class ResonantGuardUnlocks {

    public static boolean isUnlocked(ServerPlayer player) {
        return PlayerSkillState.isUnlocked(
                player,
                ResonanceSkill.RESONANT_GUARD
        );
    }

    public static void tryUnlock(ServerPlayer player) {

        if (isUnlocked(player)) {

            PlayerSkillState.equip(
                    player,
                    ResonanceSkill.RESONANT_GUARD
            );

            SkillStateSync.syncToClient(player);

            player.sendSystemMessage(
                    Component.literal(
                            "Resonant Guard equipped."
                    )
            );

            return;
        }

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
                        .resonantGuard
                        .skill_point_cost;

        if (cost < 0) {

            player.sendSystemMessage(
                    Component.literal(
                            "Resonant Guard has an invalid skill-point cost."
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
                                    + "Resonant Guard costs "
                                    + cost
                                    + "."
                    )
            );

            return;
        }

        PlayerSkillState.unlock(
                player,
                ResonanceSkill.RESONANT_GUARD
        );

        PlayerSkillState.equip(
                player,
                ResonanceSkill.RESONANT_GUARD
        );

        SkillStateSync.syncToClient(player);

        player.sendSystemMessage(
                Component.literal(
                        "Resonant Guard unlocked and equipped for "
                                + cost
                                + " Skill Point(s)!"
                )
        );
    }

    private ResonantGuardUnlocks() {
    }
}
package kmoth.resonance.skill;

import kmoth.resonance.data.BalanceDataLoader;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ResonantGuardSkill {

    private static final Map<UUID, Long> COOLDOWNS =
            new HashMap<>();

    private static final Map<UUID, Long> ACTIVE_UNTIL =
            new HashMap<>();


    public static void activate(ServerPlayer player) {

        if (!PlayerSkillState.isUnlocked(
                player,
                ResonanceSkill.RESONANT_GUARD
        )) {

            player.sendSystemMessage(
                    Component.literal(
                            "Resonant Guard is locked."
                    )
            );

            return;
        }

        if (PlayerSkillState.getEquipped(player)
                != ResonanceSkill.RESONANT_GUARD) {

            player.sendSystemMessage(
                    Component.literal(
                            "Resonant Guard is not equipped."
                    )
            );

            return;
        }


        long currentTime =
                player.level().getGameTime();

        long cooldownTicks =
                BalanceDataLoader
                        .resonantGuard
                        .cooldown_seconds
                        * 20L;

        long lastUse =
                COOLDOWNS.getOrDefault(
                        player.getUUID(),
                        Long.MIN_VALUE / 2
                );

        if (currentTime - lastUse < cooldownTicks) {

            long remainingTicks =
                    cooldownTicks - (currentTime - lastUse);

            long remainingSeconds =
                    (remainingTicks + 19) / 20;

            player.sendSystemMessage(
                    Component.literal(
                            "Resonant Guard cooldown: "
                                    + remainingSeconds
                                    + "s"
                    )
            );

            return;
        }


        long durationTicks =
                BalanceDataLoader
                        .resonantGuard
                        .duration_seconds
                        * 20L;

        ACTIVE_UNTIL.put(
                player.getUUID(),
                currentTime + durationTicks
        );

        COOLDOWNS.put(
                player.getUUID(),
                currentTime
        );


        int reductionPercent =
                (int) Math.round(
                        BalanceDataLoader
                                .resonantGuard
                                .damage_reduction
                                * 100
                );

        player.sendSystemMessage(
                Component.literal(
                        "Resonant Guard activated! "
                                + reductionPercent
                                + "% damage reduction."
                )
        );
    }


    public static boolean isActive(
            ServerPlayer player
    ) {

        Long activeUntil =
                ACTIVE_UNTIL.get(
                        player.getUUID()
                );

        if (activeUntil == null) {
            return false;
        }

        return player.level().getGameTime()
                < activeUntil;
    }


    private ResonantGuardSkill() {
    }
}
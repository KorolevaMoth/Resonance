package kmoth.resonance.skill;

import kmoth.resonance.data.BalanceDataLoader;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BladeResonanceSkill {

    private static final Map<UUID, Long> COOLDOWNS = new HashMap<>();
    private static final Map<UUID, Long> ACTIVE_UNTIL = new HashMap<>();

    public static void activate(ServerPlayer player) {

        long currentTime = player.level().getGameTime();

        long cooldownTicks =
                BalanceDataLoader.bladeResonance.cooldown_seconds * 20L;

        Long previousUse = COOLDOWNS.get(player.getUUID());

        // Check whether Blade Resonance is still on cooldown.
        if (previousUse != null &&
                currentTime - previousUse < cooldownTicks) {

            long remainingTicks =
                    cooldownTicks - (currentTime - previousUse);

            long remainingSeconds =
                    (remainingTicks + 19) / 20;

            player.sendSystemMessage(
                    Component.literal(
                            "Blade Resonance cooldown: "
                                    + remainingSeconds
                                    + "s"
                    )
            );

            return;
        }

        // Start the cooldown.
        COOLDOWNS.put(player.getUUID(), currentTime);

        // Blade Resonance remains active for 5 seconds.
        ACTIVE_UNTIL.put(
                player.getUUID(),
                currentTime + (5 * 20L)
        );

        player.sendSystemMessage(
                Component.literal(
                        "Blade Resonance activated! +"
                                + BalanceDataLoader.bladeResonance.damage_bonus
                                + " damage"
                )
        );
    }

    public static boolean isActive(ServerPlayer player) {

        Long activeUntil =
                ACTIVE_UNTIL.get(player.getUUID());

        if (activeUntil == null) {
            return false;
        }

        return player.level().getGameTime() < activeUntil;
    }
}
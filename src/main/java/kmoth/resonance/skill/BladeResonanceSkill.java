package kmoth.resonance.skill;

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
        long cooldownTicks = BladeResonanceData.cooldownSeconds * 20L;

        Long previousUse = COOLDOWNS.get(player.getUUID());

        if (previousUse != null && currentTime - previousUse < cooldownTicks) {

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

        COOLDOWNS.put(player.getUUID(), currentTime);

        // For our prototype, the buff lasts 5 seconds.
        ACTIVE_UNTIL.put(
                player.getUUID(),
                currentTime + (5 * 20L)
        );

        player.sendSystemMessage(
                Component.literal(
                        "Blade Resonance activated! +"
                                + BladeResonanceData.damageBonus
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

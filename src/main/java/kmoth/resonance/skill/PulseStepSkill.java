package kmoth.resonance.skill;

import kmoth.resonance.data.BalanceDataLoader;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PulseStepSkill {

    private static final Map<UUID, Long> COOLDOWNS = new HashMap<>();

    public static void activate(ServerPlayer player) {

        if (!PlayerSkillState.isUnlocked(
                player,
                ResonanceSkill.PULSE_STEP
        )) {
            player.sendSystemMessage(
                    Component.literal("Pulse Step is locked.")
            );
            return;
        }

        if (PlayerSkillState.getEquipped(player)
                != ResonanceSkill.PULSE_STEP) {
            player.sendSystemMessage(
                    Component.literal("Pulse Step is not equipped.")
            );
            return;
        }

        long currentTime = player.level().getGameTime();

        long cooldownTicks =
                BalanceDataLoader.pulseStep.cooldown_seconds * 20L;

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
                            "Pulse Step cooldown: "
                                    + remainingSeconds
                                    + "s"
                    )
            );

            return;
        }

        int durationTicks =
                BalanceDataLoader.pulseStep.duration_seconds * 20;

        double multiplier =
                BalanceDataLoader.pulseStep.speed_multiplier;

        int amplifier =
                Math.max(
                        0,
                        (int) Math.round(
                                (multiplier - 1.0) / 0.2
                        ) - 1
                );

        player.addEffect(
                new MobEffectInstance(
                        MobEffects.MOVEMENT_SPEED,
                        durationTicks,
                        amplifier,
                        false,
                        true,
                        true
                )
        );

        COOLDOWNS.put(
                player.getUUID(),
                currentTime
        );

        player.sendSystemMessage(
                Component.literal("Pulse Step activated!")
        );
    }

    private PulseStepSkill() {
    }
}
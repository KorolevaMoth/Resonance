package kmoth.resonance.network;

import kmoth.resonance.skill.BladeResonanceSkill;
import kmoth.resonance.skill.BladeResonanceUnlocks;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import kmoth.resonance.client.ClientSkillState;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import kmoth.resonance.skill.PlayerSkillState;
import kmoth.resonance.skill.PulseStepSkill;
import kmoth.resonance.skill.ResonanceSkill;
import net.minecraft.network.chat.Component;
import kmoth.resonance.skill.PulseStepUnlocks;
import kmoth.resonance.skill.ResonantGuardSkill;
import kmoth.resonance.skill.ResonantGuardUnlocks;

@EventBusSubscriber(
        modid = "resonance",
        bus = EventBusSubscriber.Bus.MOD
)
public class ModNetworking {

    @SubscribeEvent
    public static void registerPayloads(RegisterPayloadHandlersEvent event) {

        var registrar = event.registrar("1");

        registrar.playToServer(
                BladeResonancePayload.TYPE,
                BladeResonancePayload.STREAM_CODEC,
                (payload, context) -> context.enqueueWork(() -> {

                    if (context.player() instanceof ServerPlayer player) {

                        ResonanceSkill equipped =
                                PlayerSkillState.getEquipped(player);

                        switch (equipped) {

                            case BLADE_RESONANCE ->
                                    BladeResonanceSkill.activate(player);

                            case PULSE_STEP ->
                                    PulseStepSkill.activate(player);

                            case RESONANT_GUARD ->
                                    ResonantGuardSkill.activate(player);

                            case NONE ->
                                    player.sendSystemMessage(
                                            Component.literal(
                                                    "No Resonance skill equipped."
                                            )
                                    );
                        }
                    }
                })

        );

        registrar.playToServer(
                UnlockBladeResonancePayload.TYPE,
                UnlockBladeResonancePayload.STREAM_CODEC,
                (payload, context) -> context.enqueueWork(() -> {

                    if (context.player() instanceof ServerPlayer player) {
                        BladeResonanceUnlocks.tryUnlock(player);
                    }
                })
        );

        registrar.playToClient(
                SkillStatePayload.TYPE,
                SkillStatePayload.STREAM_CODEC,
                ModNetworking::handleSkillState
        );

        registrar.playToServer(
                RequestSkillStatePayload.TYPE,
                RequestSkillStatePayload.STREAM_CODEC,
                (payload, context) -> context.enqueueWork(() -> {

                    if (context.player()
                            instanceof ServerPlayer player) {

                        SkillStateSync.syncToClient(player);
                    }
                })
        );

        registrar.playToServer(
                UnlockPulseStepPayload.TYPE,
                UnlockPulseStepPayload.STREAM_CODEC,
                (payload, context) -> context.enqueueWork(() -> {

                    if (context.player() instanceof ServerPlayer player) {
                        PulseStepUnlocks.tryUnlock(player);
                    }
                })
        );

        registrar.playToServer(
                UnlockResonantGuardPayload.TYPE,
                UnlockResonantGuardPayload.STREAM_CODEC,
                (payload, context) -> context.enqueueWork(() -> {

                    if (context.player() instanceof ServerPlayer player) {
                        ResonantGuardUnlocks.tryUnlock(player);
                    }
                })
        );
    }
    private static void handleSkillState(
            SkillStatePayload payload,
            IPayloadContext context
    ) {

        context.enqueueWork(() -> {

            ClientSkillState.setSkillPoints(
                    payload.skillPoints()
            );

            ClientSkillState.setBladeResonanceUnlocked(
                    payload.bladeResonanceUnlocked()
            );

            ClientSkillState.setPulseStepUnlocked(
                    payload.pulseStepUnlocked()
            );

            ClientSkillState.setResonantGuardUnlocked(
                    payload.resonantGuardUnlocked()
            );

            ClientSkillState.setEquippedSkill(
                    payload.equippedSkill()
            );
        });
    }
}
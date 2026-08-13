package kmoth.resonance.network;

import kmoth.resonance.progression.ResonanceProgression;
import kmoth.resonance.skill.PlayerSkillState;
import kmoth.resonance.skill.ResonanceSkill;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.PacketDistributor;

public class SkillStateSync {

    public static void syncToClient(ServerPlayer player) {

        int skillPoints =
                ResonanceProgression.getSkillPoints(player);

        boolean bladeUnlocked =
                PlayerSkillState.isUnlocked(
                        player,
                        ResonanceSkill.BLADE_RESONANCE
                );

        boolean pulseUnlocked =
                PlayerSkillState.isUnlocked(
                        player,
                        ResonanceSkill.PULSE_STEP
                );

        boolean guardUnlocked =
                PlayerSkillState.isUnlocked(
                        player,
                        ResonanceSkill.RESONANT_GUARD
                );

        ResonanceSkill equipped =
                PlayerSkillState.getEquipped(player);

        SkillStatePayload payload =
                new SkillStatePayload(
                        skillPoints,
                        bladeUnlocked,
                        pulseUnlocked,
                        guardUnlocked,
                        equipped
                );

        PacketDistributor.sendToPlayer(
                player,
                payload
        );
    }

    private SkillStateSync() {
    }
}
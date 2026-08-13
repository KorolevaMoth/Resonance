package kmoth.resonance.network;

import io.netty.buffer.ByteBuf;
import kmoth.resonance.skill.ResonanceSkill;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record SkillStatePayload(
        int skillPoints,
        boolean bladeResonanceUnlocked,
        boolean pulseStepUnlocked,
        boolean resonantGuardUnlocked,
        ResonanceSkill equippedSkill
) implements CustomPacketPayload {

    public static final Type<SkillStatePayload> TYPE =
            new Type<>(
                    ResourceLocation.fromNamespaceAndPath(
                            "resonance",
                            "skill_state"
                    )
            );

    public static final StreamCodec<ByteBuf, SkillStatePayload>
            STREAM_CODEC =
            StreamCodec.composite(

                    ByteBufCodecs.INT,
                    SkillStatePayload::skillPoints,

                    ByteBufCodecs.BOOL,
                    SkillStatePayload::bladeResonanceUnlocked,

                    ByteBufCodecs.BOOL,
                    SkillStatePayload::pulseStepUnlocked,

                    ByteBufCodecs.BOOL,
                    SkillStatePayload::resonantGuardUnlocked,

                    ByteBufCodecs.INT,
                    payload -> payload.equippedSkill().ordinal(),

                    (skillPoints,
                     bladeUnlocked,
                     pulseUnlocked,
                     guardUnlocked,
                     equippedOrdinal) ->

                            new SkillStatePayload(
                                    skillPoints,
                                    bladeUnlocked,
                                    pulseUnlocked,
                                    guardUnlocked,
                                    ResonanceSkill.values()[
                                            equippedOrdinal
                                            ]
                            )
            );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
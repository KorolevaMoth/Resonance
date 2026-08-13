package kmoth.resonance.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record RequestSkillStatePayload()
        implements CustomPacketPayload {

    public static final Type<RequestSkillStatePayload> TYPE =
            new Type<>(
                    ResourceLocation.fromNamespaceAndPath(
                            "resonance",
                            "request_skill_state"
                    )
            );

    public static final StreamCodec<ByteBuf, RequestSkillStatePayload>
            STREAM_CODEC =
            StreamCodec.unit(
                    new RequestSkillStatePayload()
            );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
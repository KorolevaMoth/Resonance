package kmoth.resonance.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record UnlockResonantGuardPayload()
        implements CustomPacketPayload {

    public static final Type<UnlockResonantGuardPayload> TYPE =
            new Type<>(
                    ResourceLocation.fromNamespaceAndPath(
                            "resonance",
                            "unlock_resonant_guard"
                    )
            );

    public static final StreamCodec<ByteBuf, UnlockResonantGuardPayload>
            STREAM_CODEC =
            StreamCodec.unit(
                    new UnlockResonantGuardPayload()
            );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
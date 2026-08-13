package kmoth.resonance.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record UnlockPulseStepPayload()
        implements CustomPacketPayload {

    public static final Type<UnlockPulseStepPayload> TYPE =
            new Type<>(
                    ResourceLocation.fromNamespaceAndPath(
                            "resonance",
                            "unlock_pulse_step"
                    )
            );

    public static final StreamCodec<ByteBuf, UnlockPulseStepPayload>
            STREAM_CODEC =
            StreamCodec.unit(
                    new UnlockPulseStepPayload()
            );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
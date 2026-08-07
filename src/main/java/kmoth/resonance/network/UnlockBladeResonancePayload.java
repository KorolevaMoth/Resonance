package kmoth.resonance.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record UnlockBladeResonancePayload()
        implements CustomPacketPayload {

    public static final Type<UnlockBladeResonancePayload> TYPE =
            new Type<>(
                    ResourceLocation.fromNamespaceAndPath(
                            "resonance",
                            "unlock_blade_resonance"
                    )
            );

    public static final StreamCodec<ByteBuf, UnlockBladeResonancePayload>
            STREAM_CODEC =
            StreamCodec.unit(
                    new UnlockBladeResonancePayload()
            );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
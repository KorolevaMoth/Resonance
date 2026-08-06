package kmoth.resonance.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record BladeResonancePayload() implements CustomPacketPayload {

    public static final Type<BladeResonancePayload> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath(
                    "resonance",
                    "blade_resonance"
            ));

    public static final StreamCodec<ByteBuf, BladeResonancePayload> STREAM_CODEC =
            StreamCodec.unit(new BladeResonancePayload());

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
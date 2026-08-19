package kmoth.resonance.player;

import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class ModAttachments {

    public static final DeferredRegister<AttachmentType<?>>
            ATTACHMENT_TYPES =
            DeferredRegister.create(
                    NeoForgeRegistries.ATTACHMENT_TYPES,
                    "resonance"
            );


    public static final Supplier<AttachmentType<ResonancePlayerData>>
            RESONANCE_PLAYER_DATA =
            ATTACHMENT_TYPES.register(
                    "resonance_player_data",
                    () -> AttachmentType
                            .builder(ResonancePlayerData::new)
                            .serialize(
                                    ResonancePlayerData.CODEC
                            )
                            .copyOnDeath()
                            .build()
            );


    private ModAttachments() {
    }
}
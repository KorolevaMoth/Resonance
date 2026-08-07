package kmoth.resonance.entity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(
                    BuiltInRegistries.ENTITY_TYPE,
                    "resonance"
            );

    public static final DeferredHolder<EntityType<?>, EntityType<CorruptedBioSlime>>
            CORRUPTED_BIO_SLIME =
            ENTITY_TYPES.register(
                    "corrupted_bio_slime",
                    () -> EntityType.Builder
                            .of(
                                    CorruptedBioSlime::new,
                                    MobCategory.MONSTER
                            )
                            .sized(2.04F, 2.04F)
                            .build(
                                    ResourceLocation
                                            .fromNamespaceAndPath(
                                                    "resonance",
                                                    "corrupted_bio_slime"
                                            )
                                            .toString()
                            )
            );

    private ModEntities() {
    }
}
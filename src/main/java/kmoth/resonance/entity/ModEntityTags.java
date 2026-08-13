package kmoth.resonance.entity;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;

public class ModEntityTags {

    public static final TagKey<EntityType<?>> WEAK =
            create("weak");

    public static final TagKey<EntityType<?>> STANDARD =
            create("standard");

    public static final TagKey<EntityType<?>> ELITE =
            create("elite");

    public static final TagKey<EntityType<?>> BOSS =
            create("boss");


    private static TagKey<EntityType<?>> create(String name) {

        return TagKey.create(
                Registries.ENTITY_TYPE,
                ResourceLocation.fromNamespaceAndPath(
                        "resonance",
                        name
                )
        );
    }


    private ModEntityTags() {
    }
}
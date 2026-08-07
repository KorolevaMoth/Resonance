package kmoth.resonance.item;

import kmoth.resonance.entity.ModEntities;
import net.minecraft.world.item.SpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {

    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems("resonance");

    public static final DeferredItem<SpawnEggItem>
            CORRUPTED_BIO_SLIME_SPAWN_EGG =
            ITEMS.register(
                    "corrupted_bio_slime_spawn_egg",
                    () -> new SpawnEggItem(
                            ModEntities.CORRUPTED_BIO_SLIME.get(),
                            0x31543B,
                            0x9B59B6,
                            new net.minecraft.world.item.Item.Properties()
                    )
            );

    private ModItems() {
    }
}
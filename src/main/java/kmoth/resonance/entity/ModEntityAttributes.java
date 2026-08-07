package kmoth.resonance.entity;

import kmoth.resonance.data.BalanceDataLoader;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

@EventBusSubscriber(
        modid = "resonance",
        bus = EventBusSubscriber.Bus.MOD
)
public class ModEntityAttributes {

    @SubscribeEvent
    public static void createAttributes(EntityAttributeCreationEvent event) {

        event.put(
                ModEntities.CORRUPTED_BIO_SLIME.get(),

                Monster.createMonsterAttributes()
                        .add(
                                Attributes.MAX_HEALTH,
                                BalanceDataLoader.corruptedBioSlime.getMaxHealth()
                        )
                        .add(
                                Attributes.MOVEMENT_SPEED,
                                0.2
                        )
                        .add(
                                Attributes.ATTACK_DAMAGE,
                                2.0
                        )
                        .build()
        );
    }
}
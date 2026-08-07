package kmoth.resonance.client;

import kmoth.resonance.entity.ModEntities;
import net.minecraft.client.renderer.entity.SlimeRenderer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(
        modid = "resonance",
        bus = EventBusSubscriber.Bus.MOD
)
public class ModEntityRenderers {

    @SubscribeEvent
    public static void registerRenderers(
            EntityRenderersEvent.RegisterRenderers event
    ) {

        event.registerEntityRenderer(
                ModEntities.CORRUPTED_BIO_SLIME.get(),
                SlimeRenderer::new
        );
    }
}
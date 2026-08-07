package kmoth.resonance.progression;

import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;

@EventBusSubscriber(modid = "resonance")
public class ResonanceXpHandler {

    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {

        if (!(event.getSource().getEntity() instanceof ServerPlayer player)) {
            return;
        }

        // Temporary prototype reward:
        // Every defeated mob gives 25 Resonance XP.
        ResonanceProgression.addXp(player, 25);
    }
}
package kmoth.resonance.progression;

import kmoth.resonance.data.BalanceDataLoader;
import kmoth.resonance.entity.CorruptedBioSlime;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;

@EventBusSubscriber(modid = "resonance")
public class ResonanceXpHandler {

    @SubscribeEvent
    public static void onLivingDeath(
            LivingDeathEvent event
    ) {

        if (!(event.getSource().getEntity()
                instanceof ServerPlayer player)) {
            return;
        }

        // ==========================================
        // CORRUPTED BIO-SLIME
        // ==========================================

        if (event.getEntity()
                instanceof CorruptedBioSlime) {

            int xpReward =
                    BalanceDataLoader
                            .corruptedBioSlime
                            .getXpReward();

            ResonanceProgression.addXp(
                    player,
                    xpReward
            );

            return;
        }

        // Other enemies will be added to
        // the tier system later.
    }
}
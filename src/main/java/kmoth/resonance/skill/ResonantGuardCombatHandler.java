package kmoth.resonance.skill;

import kmoth.resonance.data.BalanceDataLoader;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

@EventBusSubscriber(modid = "resonance")
public class ResonantGuardCombatHandler {

    @SubscribeEvent
    public static void onLivingDamage(
            LivingDamageEvent.Pre event
    ) {

        if (!(event.getEntity()
                instanceof ServerPlayer player)) {
            return;
        }

        if (!ResonantGuardSkill.isActive(player)) {
            return;
        }


        double reduction =
                BalanceDataLoader
                        .resonantGuard
                        .damage_reduction;

        // Safety clamp:
        // 0.0 = no reduction
        // 1.0 = 100% reduction
        reduction =
                Math.max(
                        0.0,
                        Math.min(
                                1.0,
                                reduction
                        )
                );


        float originalDamage =
                event.getNewDamage();

        float reducedDamage =
                originalDamage
                        * (float) (1.0 - reduction);


        event.getContainer().setNewDamage(
                reducedDamage
        );
    }
}
package kmoth.resonance.skill;

import kmoth.resonance.data.BalanceDataLoader;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

@EventBusSubscriber(modid = "resonance")
public class BladeResonanceCombatHandler {

    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent.Pre event) {

        if (!(event.getSource().getEntity() instanceof ServerPlayer player)) {
            return;
        }

        if (!BladeResonanceSkill.isActive(player)) {
            return;
        }

        event.getContainer().setNewDamage(
                event.getNewDamage()
                        + (float) BalanceDataLoader.bladeResonance.damage_bonus
        );
    }
}
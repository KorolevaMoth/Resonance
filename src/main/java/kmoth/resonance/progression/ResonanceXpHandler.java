package kmoth.resonance.progression;

import kmoth.resonance.data.BalanceDataLoader;
import kmoth.resonance.entity.CorruptedBioSlime;
import kmoth.resonance.entity.EntityTierData;
import kmoth.resonance.entity.ModEntityTags;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;

@EventBusSubscriber(modid = "resonance")
public class ResonanceXpHandler {

    @SubscribeEvent
    public static void onLivingDeath(
            LivingDeathEvent event
    ) {

        // Only award Resonance XP when a player gets the kill.
        if (!(event.getSource().getEntity()
                instanceof ServerPlayer player)) {
            return;
        }

        LivingEntity defeatedEntity =
                event.getEntity();


        // ==========================================
        // DETERMINE ENTITY TIER
        // ==========================================

        EntityTierData.Tier tierData;

        var entityTypeHolder =
                defeatedEntity
                        .getType()
                        .builtInRegistryHolder();

        if (entityTypeHolder.is(ModEntityTags.WEAK)) {

            tierData =
                    BalanceDataLoader.entityTiers.weak;

        } else if (entityTypeHolder.is(ModEntityTags.STANDARD)) {

            tierData =
                    BalanceDataLoader.entityTiers.standard;

        } else if (entityTypeHolder.is(ModEntityTags.ELITE)) {

            tierData =
                    BalanceDataLoader.entityTiers.elite;

        } else if (entityTypeHolder.is(ModEntityTags.BOSS)) {

            tierData =
                    BalanceDataLoader.entityTiers.boss;

        } else {

            player.sendSystemMessage(
                    net.minecraft.network.chat.Component.literal(
                            "DEBUG: Enemy has no Resonance tier."
                    )
            );

            return;
        }

        player.sendSystemMessage(
                net.minecraft.network.chat.Component.literal(
                        "DEBUG: Tier found! XP = "
                                + tierData.xp
                )
        );


        // ==========================================
        // BASE XP FROM TIER
        // ==========================================

        int xpReward = tierData.xp;


        // ==========================================
        // ENTITY-SPECIFIC MODIFIER
        // ==========================================

        if (defeatedEntity instanceof CorruptedBioSlime) {

            xpReward =
                    BalanceDataLoader
                            .corruptedBioSlime
                            .getXpReward();
        }


        // ==========================================
        // AWARD XP
        // ==========================================

        ResonanceProgression.addXp(
                player,
                xpReward
        );
    }
}
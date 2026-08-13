package kmoth.resonance.entity;

import kmoth.resonance.data.BalanceDataLoader;

public class CorruptedBioSlimeData {

    public String tier;

    public double health_multiplier;

    public double xp_multiplier;


    public double getMaxHealth() {

        EntityTierData.Tier tierData =
                BalanceDataLoader
                        .entityTiers
                        .getTier(tier);

        return tierData.health
                * health_multiplier;
    }


    public int getXpReward() {

        EntityTierData.Tier tierData =
                BalanceDataLoader
                        .entityTiers
                        .getTier(tier);

        return (int) Math.round(
                tierData.xp
                        * xp_multiplier
        );
    }
}
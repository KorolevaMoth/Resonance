package kmoth.resonance.entity;

import kmoth.resonance.data.BalanceDataLoader;

public class CorruptedBioSlimeData {

    public double health_multiplier;

    public double getMaxHealth() {
        return BalanceDataLoader.entityHealth.standard_health
                * health_multiplier;
    }
}
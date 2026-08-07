package kmoth.resonance.entity;

import kmoth.resonance.data.BalanceDataLoader;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.level.Level;

public class CorruptedBioSlime extends Slime {

    public CorruptedBioSlime(
            EntityType<? extends Slime> entityType,
            Level level
    ) {
        super(entityType, level);

        applyResonanceHealth();
    }

    @Override
    public void setSize(int size, boolean resetHealth) {

        super.setSize(size, resetHealth);

        applyResonanceHealth();
    }

    private void applyResonanceHealth() {

        if (BalanceDataLoader.corruptedBioSlime == null) {
            return;
        }

        if (BalanceDataLoader.entityHealth == null) {
            return;
        }

        double maxHealth =
                BalanceDataLoader.corruptedBioSlime.getMaxHealth();

        var healthAttribute =
                this.getAttribute(Attributes.MAX_HEALTH);

        if (healthAttribute != null) {
            healthAttribute.setBaseValue(maxHealth);
            this.setHealth((float) maxHealth);
        }
    }
}
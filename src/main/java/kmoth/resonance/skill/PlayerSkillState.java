package kmoth.resonance.skill;

import kmoth.resonance.player.ModAttachments;
import kmoth.resonance.player.ResonancePlayerData;
import net.minecraft.server.level.ServerPlayer;

public class PlayerSkillState {

    private static ResonancePlayerData getData(
            ServerPlayer player
    ) {

        return player.getData(
                ModAttachments
                        .RESONANCE_PLAYER_DATA
                        .get()
        );
    }


    public static boolean isUnlocked(
            ServerPlayer player,
            ResonanceSkill skill
    ) {

        return getData(player)
                .isSkillUnlocked(skill);
    }


    public static void unlock(
            ServerPlayer player,
            ResonanceSkill skill
    ) {

        ResonancePlayerData data =
                getData(player);

        data.unlockSkill(skill);

        player.setData(
                ModAttachments
                        .RESONANCE_PLAYER_DATA
                        .get(),
                data
        );
    }


    public static void equip(
            ServerPlayer player,
            ResonanceSkill skill
    ) {

        ResonancePlayerData data =
                getData(player);

        data.equipSkill(skill);

        player.setData(
                ModAttachments
                        .RESONANCE_PLAYER_DATA
                        .get(),
                data
        );
    }


    public static ResonanceSkill getEquipped(
            ServerPlayer player
    ) {

        return getData(player)
                .getEquippedSkill();
    }


    private PlayerSkillState() {
    }
}
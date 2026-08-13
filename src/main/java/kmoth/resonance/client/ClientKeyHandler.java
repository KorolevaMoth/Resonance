package kmoth.resonance.client;

import kmoth.resonance.network.BladeResonancePayload;
import net.minecraft.client.Minecraft;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import kmoth.resonance.network.RequestSkillStatePayload;

@EventBusSubscriber(modid = "resonance")
public class ClientKeyHandler {

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {

        Minecraft minecraft = Minecraft.getInstance();

        if (minecraft.player == null) {
            return;
        }

        // ==========================================
        // R = USE ACTIVE SKILL
        // ==========================================

        while (ModKeyMappings.BLADE_RESONANCE.consumeClick()) {

            PacketDistributor.sendToServer(
                    new BladeResonancePayload()
            );
        }


        // ==========================================
        // U = OPEN SKILL TREE
        // ==========================================

        while (ModKeyMappings.OPEN_SKILL_TREE.consumeClick()) {

            // Ask the server for the player's latest
            // skill points, unlocked skills, and equipped skill.
            PacketDistributor.sendToServer(
                    new RequestSkillStatePayload()
            );

            minecraft.setScreen(
                    new SkillTreeScreen()
            );
        }
    }
}
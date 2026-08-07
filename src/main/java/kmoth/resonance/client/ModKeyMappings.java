package kmoth.resonance.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import org.lwjgl.glfw.GLFW;

@EventBusSubscriber(
        modid = "resonance",
        bus = EventBusSubscriber.Bus.MOD
)
public class ModKeyMappings {

    public static final KeyMapping BLADE_RESONANCE =
            new KeyMapping(
                    "key.resonance.blade_resonance",
                    InputConstants.Type.KEYSYM,
                    GLFW.GLFW_KEY_R,
                    "key.categories.resonance"
            );
    public static final KeyMapping UNLOCK_BLADE_RESONANCE =
            new KeyMapping(
                    "key.resonance.unlock_blade_resonance",
                    InputConstants.Type.KEYSYM,
                    GLFW.GLFW_KEY_U,
                    "key.categories.resonance"
            );

    @SubscribeEvent
    public static void registerKeyMappings(
            RegisterKeyMappingsEvent event
    ) {
        event.register(BLADE_RESONANCE);
        event.register(UNLOCK_BLADE_RESONANCE);
    }
}
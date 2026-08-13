package kmoth.resonance.client;

import kmoth.resonance.network.UnlockBladeResonancePayload;
import kmoth.resonance.skill.ResonanceSkill;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.network.PacketDistributor;
import kmoth.resonance.network.UnlockPulseStepPayload;
import kmoth.resonance.network.UnlockResonantGuardPayload;

public class SkillTreeScreen extends Screen {

    private static final int PANEL_WIDTH = 360;
    private static final int PANEL_HEIGHT = 250;

    public SkillTreeScreen() {
        super(Component.literal("Resonance Skill Tree"));
    }

    @Override
    protected void init() {

        int centerX = this.width / 2;
        int centerY = this.height / 2;

        // ==========================================
        // BLADE RESONANCE
        // ==========================================

        String bladeText;

        if (ClientSkillState.getEquippedSkill()
                == ResonanceSkill.BLADE_RESONANCE) {

            bladeText = "Blade Resonance - EQUIPPED";

        } else if (ClientSkillState.isBladeResonanceUnlocked()) {

            bladeText = "Blade Resonance - UNLOCKED";

        } else {

            bladeText = "Blade Resonance - LOCKED";
        }

        this.addRenderableWidget(
                Button.builder(
                                Component.literal(bladeText),
                                button -> {

                                    PacketDistributor.sendToServer(
                                            new UnlockBladeResonancePayload()
                                    );

                                    this.onClose();
                                }
                        )
                        .bounds(
                                centerX - 90,
                                centerY - 55,
                                180,
                                20
                        )
                        .build()
        );


        // ==========================================
        // PULSE STEP
        // ==========================================

        String pulseText;

        if (ClientSkillState.getEquippedSkill()
                == ResonanceSkill.PULSE_STEP) {

            pulseText = "Pulse Step - EQUIPPED";

        } else if (ClientSkillState.isPulseStepUnlocked()) {

            pulseText = "Pulse Step - UNLOCKED";

        } else {

            pulseText = "Pulse Step - LOCKED";
        }

        Button pulseButton =
                Button.builder(
                                Component.literal(pulseText),
                                button -> {

                                    PacketDistributor.sendToServer(
                                            new UnlockPulseStepPayload()
                                    );

                                    this.onClose();
                                }
                        )
                        .bounds(
                                centerX - 155,
                                centerY + 20,
                                135,
                                20
                        )
                        .build();

        pulseButton.active =
                ClientSkillState.isBladeResonanceUnlocked();

        this.addRenderableWidget(pulseButton);


        // ==========================================
        // RESONANT GUARD
        // ==========================================

        String guardText;

        if (ClientSkillState.getEquippedSkill()
                == ResonanceSkill.RESONANT_GUARD) {

            guardText = "Resonant Guard - EQUIPPED";

        } else if (ClientSkillState.isPulseStepUnlocked()) {

            guardText = "Resonant Guard - UNLOCKED";

        } else {

            guardText = "Resonant Guard - LOCKED";
        }

        Button guardButton =
                Button.builder(
                                Component.literal(guardText),
                                button -> {
                                    PacketDistributor.sendToServer(
                                            new UnlockResonantGuardPayload()
                                    );

                                    this.onClose();
                                }
                        )
                        .bounds(
                                centerX + 20,
                                centerY + 20,
                                135,
                                20
                        )
                        .build();

        pulseButton.active =
                ClientSkillState.isBladeResonanceUnlocked();

        this.addRenderableWidget(guardButton);
    }

    @Override
    public void renderBackground(
            GuiGraphics graphics,
            int mouseX,
            int mouseY,
            float partialTick
    ) {
        // Intentionally empty.
        // Prevents Minecraft's default blurred screen background.
    }

    @Override
    public void render(
            GuiGraphics graphics,
            int mouseX,
            int mouseY,
            float partialTick
    ) {

        int centerX = this.width / 2;
        int centerY = this.height / 2;

        // ==========================================
        // BACKGROUND PANEL
        // ==========================================

        graphics.fill(
                centerX - PANEL_WIDTH / 2,
                centerY - PANEL_HEIGHT / 2,
                centerX + PANEL_WIDTH / 2,
                centerY + PANEL_HEIGHT / 2,
                0xCC101018
        );


        // ==========================================
        // TITLE
        // ==========================================

        graphics.drawCenteredString(
                this.font,
                "Resonance Skill Tree",
                centerX,
                centerY - 110,
                0xFFFFFF
        );


        // ==========================================
        // SKILL POINT DISPLAY
        // ==========================================

        graphics.drawCenteredString(
                this.font,
                "Skill Points: "
                        + ClientSkillState.getSkillPoints(),
                centerX,
                centerY - 92,
                0xFFFF55
        );


        // ==========================================
        // BLADE RESONANCE INFO
        // ==========================================

        graphics.drawCenteredString(
                this.font,
                "Cost: 1 Skill Point",
                centerX,
                centerY - 30,
                0xAAAAAA
        );


        // ==========================================
        // TREE CONNECTION LINES
        // ==========================================

        // Vertical line from Blade Resonance.
        graphics.fill(
                centerX - 1,
                centerY - 10,
                centerX + 1,
                centerY + 5,
                0xFF888888
        );

        // Horizontal branch.
        graphics.fill(
                centerX - 88,
                centerY + 4,
                centerX + 88,
                centerY + 6,
                0xFF888888
        );

        // Left branch down.
        graphics.fill(
                centerX - 88,
                centerY + 5,
                centerX - 86,
                centerY + 20,
                0xFF888888
        );

        // Right branch down.
        graphics.fill(
                centerX + 86,
                centerY + 5,
                centerX + 88,
                centerY + 20,
                0xFF888888
        );


        // ==========================================
        // FOOTER
        // ==========================================

        graphics.drawCenteredString(
                this.font,
                "Click an unlocked skill to equip it.",
                centerX,
                centerY + 75,
                0xAAAAAA
        );

        graphics.drawCenteredString(
                this.font,
                "Locked skills require Skill Points.",
                centerX,
                centerY + 90,
                0x777777
        );

        super.render(
                graphics,
                mouseX,
                mouseY,
                partialTick
        );
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
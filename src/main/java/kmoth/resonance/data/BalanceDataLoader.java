package kmoth.resonance.data;

import com.google.gson.Gson;
import kmoth.resonance.entity.CorruptedBioSlimeData;
import kmoth.resonance.entity.EntityTierData;
import kmoth.resonance.progression.ProgressionData;
import kmoth.resonance.skill.BladeResonanceData;
import kmoth.resonance.skill.PulseStepData;
import kmoth.resonance.skill.ResonantGuardData;

import java.io.InputStream;
import java.io.InputStreamReader;

public class BalanceDataLoader {

    private static final Gson GSON = new Gson();

    public static BladeResonanceData bladeResonance;
    public static ProgressionData progression;
    public static EntityTierData entityTiers;
    public static CorruptedBioSlimeData corruptedBioSlime;
    public static PulseStepData pulseStep;
    public static ResonantGuardData resonantGuard;

    public static void load() {

        try {

            // ==========================================
            // BLADE RESONANCE
            // ==========================================

            InputStream bladeStream =
                    BalanceDataLoader.class.getResourceAsStream(
                            "/data/resonance/balance/blade_resonance.json"
                    );

            if (bladeStream == null) {
                throw new RuntimeException(
                        "Could not find blade_resonance.json"
                );
            }

            InputStreamReader bladeReader =
                    new InputStreamReader(bladeStream);

            bladeResonance =
                    GSON.fromJson(
                            bladeReader,
                            BladeResonanceData.class
                    );

            bladeReader.close();

            System.out.println(
                    "[Resonance] Blade Resonance data loaded."
            );

            // ==========================================
            // PULSE STEP
            // ==========================================

            InputStream pulseStepStream =
                    BalanceDataLoader.class.getResourceAsStream(
                            "/data/resonance/balance/pulse_step.json"
                    );

            if (pulseStepStream == null) {
                throw new RuntimeException(
                        "Could not find pulse_step.json"
                );
            }

            InputStreamReader pulseStepReader =
                    new InputStreamReader(pulseStepStream);

            pulseStep =
                    GSON.fromJson(
                            pulseStepReader,
                            PulseStepData.class
                    );

            pulseStepReader.close();

            System.out.println(
                    "[Resonance] Pulse Step data loaded."
            );

            // ==========================================
            // RESONANT GUARD
            // ==========================================

            InputStream resonantGuardStream =
                    BalanceDataLoader.class.getResourceAsStream(
                            "/data/resonance/balance/resonant_guard.json"
                    );

            if (resonantGuardStream == null) {
                throw new RuntimeException(
                        "Could not find resonant_guard.json"
                );
            }

            InputStreamReader resonantGuardReader =
                    new InputStreamReader(resonantGuardStream);

            resonantGuard =
                    GSON.fromJson(
                            resonantGuardReader,
                            ResonantGuardData.class
                    );

            resonantGuardReader.close();

            System.out.println(
                    "[Resonance] Resonant Guard data loaded."
            );

            // ==========================================
            // PROGRESSION
            // ==========================================

            InputStream progressionStream =
                    BalanceDataLoader.class.getResourceAsStream(
                            "/data/resonance/balance/progression.json"
                    );

            if (progressionStream == null) {
                throw new RuntimeException(
                        "Could not find progression.json"
                );
            }

            InputStreamReader progressionReader =
                    new InputStreamReader(progressionStream);

            progression =
                    GSON.fromJson(
                            progressionReader,
                            ProgressionData.class
                    );

            progressionReader.close();

            System.out.println(
                    "[Resonance] Progression data loaded."
            );


            // ==========================================
            // ENTITY TIERS
            // ==========================================

            InputStream entityTierStream =
                    BalanceDataLoader.class.getResourceAsStream(
                            "/data/resonance/balance/entity_tiers.json"
                    );

            if (entityTierStream == null) {
                throw new RuntimeException(
                        "Could not find entity_tiers.json"
                );
            }

            InputStreamReader entityTierReader =
                    new InputStreamReader(entityTierStream);

            entityTiers =
                    GSON.fromJson(
                            entityTierReader,
                            EntityTierData.class
                    );

            entityTierReader.close();

            System.out.println(
                    "[Resonance] Entity tier data loaded."
            );


            // ==========================================
            // CORRUPTED BIO-SLIME
            // ==========================================

            InputStream slimeStream =
                    BalanceDataLoader.class.getResourceAsStream(
                            "/data/resonance/balance/corrupted_bio_slime.json"
                    );

            if (slimeStream == null) {
                throw new RuntimeException(
                        "Could not find corrupted_bio_slime.json"
                );
            }

            InputStreamReader slimeReader =
                    new InputStreamReader(slimeStream);

            corruptedBioSlime =
                    GSON.fromJson(
                            slimeReader,
                            CorruptedBioSlimeData.class
                    );

            slimeReader.close();

            System.out.println(
                    "[Resonance] Corrupted Bio-Slime data loaded."
            );


            // ==========================================
            // DEBUG OUTPUT
            // ==========================================

            System.out.println(
                    "[Resonance] Blade Resonance cooldown: "
                            + bladeResonance.cooldown_seconds
            );

            System.out.println(
                    "[Resonance] XP to Level 2: "
                            + progression.xp_requirements[1]
            );

            System.out.println(
                    "[Resonance] Weak tier health: "
                            + entityTiers.weak.health
            );

            System.out.println(
                    "[Resonance] Weak tier XP: "
                            + entityTiers.weak.xp
            );

            System.out.println(
                    "[Resonance] Corrupted Bio-Slime health: "
                            + corruptedBioSlime.getMaxHealth()
            );

            System.out.println(
                    "[Resonance] Corrupted Bio-Slime XP: "
                            + corruptedBioSlime.getXpReward()
            );

            System.out.println(
                    "[Resonance] Corrupted Bio-Slime health: "
                            + corruptedBioSlime.getMaxHealth()
            );

        } catch (Exception e) {

            throw new RuntimeException(
                    "Failed to load Resonance balance data",
                    e
            );
        }
    }
}
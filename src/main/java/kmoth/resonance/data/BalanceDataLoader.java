package kmoth.resonance.data;

import com.google.gson.Gson;
import kmoth.resonance.entity.CorruptedBioSlimeData;
import kmoth.resonance.entity.EntityHealthData;
import kmoth.resonance.progression.ProgressionData;
import kmoth.resonance.skill.BladeResonanceData;

import java.io.InputStream;
import java.io.InputStreamReader;

public class BalanceDataLoader {

    private static final Gson GSON = new Gson();

    public static BladeResonanceData bladeResonance;
    public static ProgressionData progression;
    public static EntityHealthData entityHealth;
    public static CorruptedBioSlimeData corruptedBioSlime;

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
            // ENTITY HEALTH TIERS
            // ==========================================

            InputStream entityHealthStream =
                    BalanceDataLoader.class.getResourceAsStream(
                            "/data/resonance/balance/entity_health.json"
                    );

            if (entityHealthStream == null) {
                throw new RuntimeException(
                        "Could not find entity_health.json"
                );
            }

            InputStreamReader entityHealthReader =
                    new InputStreamReader(entityHealthStream);

            entityHealth =
                    GSON.fromJson(
                            entityHealthReader,
                            EntityHealthData.class
                    );

            entityHealthReader.close();

            System.out.println(
                    "[Resonance] Entity health data loaded."
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
                            + progression.xp_to_level_2
            );

            System.out.println(
                    "[Resonance] Standard entity health: "
                            + entityHealth.standard_health
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
package kmoth.resonance.data;

import com.google.gson.Gson;
import kmoth.resonance.progression.ProgressionData;
import kmoth.resonance.skill.BladeResonanceData;

import java.io.InputStream;
import java.io.InputStreamReader;

public class BalanceDataLoader {

    private static final Gson GSON = new Gson();

    // Data loaded from our JSON files
    public static BladeResonanceData bladeResonance;
    public static ProgressionData progression;

    public static void load() {

        try {

            // ==========================================
            // LOAD BLADE RESONANCE DATA
            // ==========================================

            InputStream stream =
                    BalanceDataLoader.class.getResourceAsStream(
                            "/data/resonance/balance/blade_resonance.json"
                    );

            if (stream == null) {
                throw new RuntimeException(
                        "Could not find blade_resonance.json"
                );
            }

            InputStreamReader reader =
                    new InputStreamReader(stream);

            bladeResonance =
                    GSON.fromJson(
                            reader,
                            BladeResonanceData.class
                    );

            reader.close();

            System.out.println(
                    "[Resonance] Blade Resonance data loaded."
            );


            // ==========================================
            // LOAD PROGRESSION DATA
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


        } catch (Exception e) {

            throw new RuntimeException(
                    "Failed to load Resonance balance data",
                    e
            );
        }
    }
}
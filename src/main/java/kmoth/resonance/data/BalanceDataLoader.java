package kmoth.resonance.data;

import com.google.gson.Gson;
import kmoth.resonance.skill.BladeResonanceData;

import java.io.InputStream;
import java.io.InputStreamReader;

public class BalanceDataLoader {

    private static final Gson GSON = new Gson();

    public static BladeResonanceData bladeResonance;

    public static void load() {

        try {

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

        } catch (Exception e) {

            throw new RuntimeException(
                    "Failed to load Blade Resonance data",
                    e
            );
        }
    }
}
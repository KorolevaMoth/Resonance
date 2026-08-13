package kmoth.resonance.entity;

public class EntityTierData {

    public Tier weak;
    public Tier standard;
    public Tier elite;
    public Tier boss;

    public static class Tier {

        public double health;
        public int xp;
    }

    public Tier getTier(String tierName) {

        if (tierName == null) {
            return standard;
        }

        return switch (tierName.toLowerCase()) {

            case "weak" -> weak;

            case "elite" -> elite;

            case "boss" -> boss;

            case "standard" -> standard;

            default -> standard;
        };
    }
}
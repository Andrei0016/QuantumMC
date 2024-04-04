package mc.andrei0016.me.quantummc.config;

import com.mojang.datafixers.util.Pair;

public class ModConfigs {
    public static SimpleConfig CONFIG;
    private static ModConfigProvider configs;

    public static boolean warnCrash;

    public static void registerConfigs() {
        configs = new ModConfigProvider();
        createConfigs();

        CONFIG = SimpleConfig.of( "QuantumMcConfig").provider(configs).request();

        assignConfigs();
    }

    private static void createConfigs() {
        configs.addKeyValuePair(new Pair<>("quantumMC.rules.warnCrash", false), "boolean");
    }

    private static void assignConfigs() {
        warnCrash = CONFIG.getOrDefault("quantumMC.rules.warnCrash", false);
        System.out.println("All " + configs.getConfigsList().size() + " have been set properly");
    }
}

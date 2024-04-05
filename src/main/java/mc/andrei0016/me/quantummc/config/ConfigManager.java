package mc.andrei0016.me.quantummc.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.*;

public class ConfigManager {
    private static final String CONFIG_FILE_NAME = "QuantumMcConfig.json";
    private boolean warnCrash = false;

    public boolean isWarnCrash() {
        return warnCrash;
    }

    public void setWarnCrash(boolean warnCrash) {
        this.warnCrash = warnCrash;
    }

    public void saveConfig() {
        File configFile = new File(FabricLoader.getInstance().getConfigDir().toFile(), CONFIG_FILE_NAME);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (Writer writer = new FileWriter(configFile)) {
            gson.toJson(this, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ConfigManager loadConfig() {
        File configFile = new File(FabricLoader.getInstance().getConfigDir().toFile(), CONFIG_FILE_NAME);
        Gson gson = new Gson();
        ConfigManager config = new ConfigManager();

        if (configFile.exists()) {
            try (Reader reader = new FileReader(configFile)) {
                config = gson.fromJson(reader, ConfigManager.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            config = createDefaultConfig();
            config.saveConfig();
        }

        return config;
    }
    public static ConfigManager createDefaultConfig() {
        return new ConfigManager();
    }
}

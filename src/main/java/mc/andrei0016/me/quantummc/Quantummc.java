package mc.andrei0016.me.quantummc;

import mc.andrei0016.me.quantummc.config.ConfigManager;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Quantummc implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("quantummc");
	public static ConfigManager config;

	@Override
	public void onInitialize() {
		config = ConfigManager.loadConfig();
		LOGGER.info(String.valueOf(config.isWarnCrash()));
		LOGGER.info("QuantumMC initialized successfully!");
	}
}
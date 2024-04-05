package mc.andrei0016.me.quantummc;

import mc.andrei0016.me.quantummc.config.ConfigManager;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Quantummc implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("quantummc");

	@Override
	public void onInitialize() {
		ConfigManager.loadConfig();
		LOGGER.info("QuantumMC initialized successfully!");
	}
}
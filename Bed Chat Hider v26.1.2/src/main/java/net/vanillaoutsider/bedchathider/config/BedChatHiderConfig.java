// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.vanillaoutsider.bedchathider.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import net.dasik.social.api.config.ConfigOption;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Configuration options and persistence manager for Vanilla Outsider: Bed Chat Hider.
 * Exposes fields annotated with @ConfigOption for UniversalConfigScreen (YACL / ModMenu).
 */
public final class BedChatHiderConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(BedChatHiderConfig.class);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private static Path customConfigPath = null;

    @ConfigOption(category = "general", nameKey = "config.vanilla-outsider-bed-chat-hider.suppress_sleep_vignette", descriptionKey = "config.vanilla-outsider-bed-chat-hider.suppress_sleep_vignette.desc")
    public static boolean suppressSleepVignette = false;

    @ConfigOption(category = "general", nameKey = "config.vanilla-outsider-bed-chat-hider.auto_hide_chat", descriptionKey = "config.vanilla-outsider-bed-chat-hider.auto_hide_chat.desc")
    public static boolean autoHideChat = false;

    @ConfigOption(category = "general", nameKey = "config.vanilla-outsider-bed-chat-hider.enable_cinematic_sleep_fade", descriptionKey = "config.vanilla-outsider-bed-chat-hider.enable_cinematic_sleep_fade.desc")
    public static boolean enableCinematicSleepFade = true;

    @ConfigOption(category = "general", nameKey = "config.vanilla-outsider-bed-chat-hider.cinematic_fade_delay_seconds", descriptionKey = "config.vanilla-outsider-bed-chat-hider.cinematic_fade_delay_seconds.desc")
    public static float cinematicFadeDelaySeconds = 2.5f;

    @ConfigOption(category = "general", nameKey = "config.vanilla-outsider-bed-chat-hider.fade_crosshair_when_sleeping", descriptionKey = "config.vanilla-outsider-bed-chat-hider.fade_crosshair_when_sleeping.desc")
    public static boolean fadeCrosshairWhenSleeping = true;

    private BedChatHiderConfig() {
    }

    public static void resetDefaults() {
        suppressSleepVignette = false;
        autoHideChat = false;
        enableCinematicSleepFade = true;
        cinematicFadeDelaySeconds = 2.5f;
        fadeCrosshairWhenSleeping = true;
    }

    public static Path getConfigPath() {
        if (customConfigPath != null) {
            return customConfigPath;
        }
        try {
            if (FabricLoader.getInstance() != null && FabricLoader.getInstance().getConfigDir() != null) {
                return FabricLoader.getInstance().getConfigDir().resolve("bedchathider.json");
            }
        } catch (Throwable ignored) {
        }
        return Path.of("config", "bedchathider.json");
    }

    public static void setCustomConfigPath(Path path) {
        customConfigPath = path;
    }

    public static void load() {
        loadFromPath(getConfigPath());
    }

    public static void save() {
        saveToPath(getConfigPath());
    }

    public static void loadFromPath(Path path) {
        if (path == null || !Files.exists(path)) {
            resetDefaults();
            return;
        }

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();
            if (json.has("suppressSleepVignette")) {
                suppressSleepVignette = json.get("suppressSleepVignette").getAsBoolean();
            } else {
                suppressSleepVignette = false;
            }
            if (json.has("autoHideChat")) {
                autoHideChat = json.get("autoHideChat").getAsBoolean();
            } else {
                autoHideChat = false;
            }
            if (json.has("enableCinematicSleepFade")) {
                enableCinematicSleepFade = json.get("enableCinematicSleepFade").getAsBoolean();
            } else {
                enableCinematicSleepFade = true;
            }
            if (json.has("cinematicFadeDelaySeconds")) {
                cinematicFadeDelaySeconds = json.get("cinematicFadeDelaySeconds").getAsFloat();
            } else {
                cinematicFadeDelaySeconds = 2.5f;
            }
            if (json.has("fadeCrosshairWhenSleeping")) {
                fadeCrosshairWhenSleeping = json.get("fadeCrosshairWhenSleeping").getAsBoolean();
            } else {
                fadeCrosshairWhenSleeping = true;
            }
        } catch (Exception e) {
            LOGGER.warn("Failed to parse Bed Chat Hider configuration at {}, resetting to defaults: {}", path.getFileName(), e.getMessage());
            resetDefaults();
        }
    }

    public static void saveToPath(Path path) {
        if (path == null) {
            return;
        }

        try {
            if (path.getParent() != null) {
                Files.createDirectories(path.getParent());
            }

            JsonObject json = new JsonObject();
            json.addProperty("suppressSleepVignette", suppressSleepVignette);
            json.addProperty("autoHideChat", autoHideChat);
            json.addProperty("enableCinematicSleepFade", enableCinematicSleepFade);
            json.addProperty("cinematicFadeDelaySeconds", cinematicFadeDelaySeconds);
            json.addProperty("fadeCrosshairWhenSleeping", fadeCrosshairWhenSleeping);

            try (BufferedWriter writer = Files.newBufferedWriter(path)) {
                GSON.toJson(json, writer);
            }
        } catch (IOException e) {
            LOGGER.error("Failed to save Bed Chat Hider configuration to {}", path.getFileName(), e);
        }
    }
}

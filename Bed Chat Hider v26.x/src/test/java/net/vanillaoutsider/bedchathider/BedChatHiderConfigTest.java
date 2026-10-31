// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.vanillaoutsider.bedchathider;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import net.vanillaoutsider.bedchathider.config.BedChatHiderConfig;
import net.vanillaoutsider.bedchathider.util.BedChatHiderSupport;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import static org.junit.jupiter.api.Assertions.*;

class BedChatHiderConfigTest {

    @TempDir
    Path tempDir;

    private Path testConfigPath;

    @BeforeEach
    void setUp() {
        testConfigPath = tempDir.resolve("bedchathider.json");
        BedChatHiderConfig.setCustomConfigPath(testConfigPath);
        BedChatHiderConfig.resetDefaults();
        BedChatHiderSupport.setTrueSleepLoadedOverride(null);
    }

    @AfterEach
    void tearDown() {
        BedChatHiderConfig.setCustomConfigPath(null);
        BedChatHiderConfig.resetDefaults();
        BedChatHiderSupport.setTrueSleepLoadedOverride(null);
    }

    @Test
    @DisplayName("Verify default configuration values")
    void testDefaultConfigValues() {
        assertFalse(BedChatHiderConfig.suppressSleepVignette, "suppressSleepVignette should default to false");
        assertFalse(BedChatHiderConfig.autoHideChat, "autoHideChat should default to false");
        assertTrue(BedChatHiderConfig.enableCinematicSleepFade, "enableCinematicSleepFade should default to true");
        assertEquals(2.5f, BedChatHiderConfig.cinematicFadeDelaySeconds, 0.001f, "cinematicFadeDelaySeconds should default to 2.5f");
        assertTrue(BedChatHiderConfig.fadeCrosshairWhenSleeping, "fadeCrosshairWhenSleeping should default to true");
    }

    @Test
    @DisplayName("Verify save and load round-trip persistence")
    void testSaveAndLoadRoundTrip() {
        BedChatHiderConfig.suppressSleepVignette = true;
        BedChatHiderConfig.autoHideChat = true;
        BedChatHiderConfig.enableCinematicSleepFade = false;
        BedChatHiderConfig.cinematicFadeDelaySeconds = 5.0f;
        BedChatHiderConfig.fadeCrosshairWhenSleeping = false;

        BedChatHiderConfig.save();
        assertTrue(Files.exists(testConfigPath), "Config file should exist on disk after save()");

        BedChatHiderConfig.resetDefaults();
        assertFalse(BedChatHiderConfig.suppressSleepVignette);
        assertFalse(BedChatHiderConfig.autoHideChat);
        assertTrue(BedChatHiderConfig.enableCinematicSleepFade);

        BedChatHiderConfig.load();
        assertTrue(BedChatHiderConfig.suppressSleepVignette, "suppressSleepVignette should be loaded as true");
        assertTrue(BedChatHiderConfig.autoHideChat, "autoHideChat should be loaded as true");
        assertFalse(BedChatHiderConfig.enableCinematicSleepFade, "enableCinematicSleepFade should be loaded as false");
        assertEquals(5.0f, BedChatHiderConfig.cinematicFadeDelaySeconds, 0.001f);
        assertFalse(BedChatHiderConfig.fadeCrosshairWhenSleeping, "fadeCrosshairWhenSleeping should be loaded as false");
    }

    @Test
    @DisplayName("Verify True Sleep collision avoidance logic")
    void testCollisionLogicWithTrueSleep() {
        // Case 1: True Sleep is NOT loaded
        BedChatHiderSupport.setTrueSleepLoadedOverride(false);
        assertFalse(BedChatHiderSupport.isTrueSleepLoaded());

        BedChatHiderConfig.suppressSleepVignette = false;
        assertFalse(BedChatHiderSupport.shouldSuppressSleepVignette(), "Should follow config when True Sleep absent");

        BedChatHiderConfig.suppressSleepVignette = true;
        assertTrue(BedChatHiderSupport.shouldSuppressSleepVignette(), "Should follow config when True Sleep absent");

        // Case 2: True Sleep IS loaded -> True Sleep takes ownership, suppression MUST be false
        BedChatHiderSupport.setTrueSleepLoadedOverride(true);
        assertTrue(BedChatHiderSupport.isTrueSleepLoaded());

        BedChatHiderConfig.suppressSleepVignette = false;
        assertFalse(BedChatHiderSupport.shouldSuppressSleepVignette(), "Should be false when True Sleep loaded");

        BedChatHiderConfig.suppressSleepVignette = true;
        assertFalse(BedChatHiderSupport.shouldSuppressSleepVignette(), "Must yield to True Sleep even if config is true");
    }

    @Test
    @DisplayName("Verify resetDefaults() restores initial states")
    void testResetDefaults() {
        BedChatHiderConfig.suppressSleepVignette = true;
        BedChatHiderConfig.autoHideChat = true;
        BedChatHiderConfig.enableCinematicSleepFade = false;
        BedChatHiderConfig.cinematicFadeDelaySeconds = 10.0f;
        BedChatHiderConfig.fadeCrosshairWhenSleeping = false;

        BedChatHiderConfig.resetDefaults();
        assertFalse(BedChatHiderConfig.suppressSleepVignette);
        assertFalse(BedChatHiderConfig.autoHideChat);
        assertTrue(BedChatHiderConfig.enableCinematicSleepFade);
        assertEquals(2.5f, BedChatHiderConfig.cinematicFadeDelaySeconds, 0.001f);
        assertTrue(BedChatHiderConfig.fadeCrosshairWhenSleeping);
    }

    @Test
    @DisplayName("Verify corrupted JSON gracefully falls back to defaults")
    void testCorruptedJsonFallback() throws IOException {
        Files.writeString(testConfigPath, "{ corrupt json syntax [[[[");

        BedChatHiderConfig.suppressSleepVignette = true;
        BedChatHiderConfig.autoHideChat = true;
        BedChatHiderConfig.enableCinematicSleepFade = false;

        BedChatHiderConfig.load();
        assertFalse(BedChatHiderConfig.suppressSleepVignette, "Corrupted config must reset to default false");
        assertFalse(BedChatHiderConfig.autoHideChat, "Corrupted config must reset to default false");
        assertTrue(BedChatHiderConfig.enableCinematicSleepFade, "Corrupted config must reset to default true");
        assertEquals(2.5f, BedChatHiderConfig.cinematicFadeDelaySeconds, 0.001f);
        assertTrue(BedChatHiderConfig.fadeCrosshairWhenSleeping);
    }

    @Test
    @DisplayName("Verify missing file safely resets to defaults")
    void testMissingFileHandling() {
        assertFalse(Files.exists(testConfigPath));
        BedChatHiderConfig.suppressSleepVignette = true;
        BedChatHiderConfig.autoHideChat = true;
        BedChatHiderConfig.enableCinematicSleepFade = false;

        BedChatHiderConfig.load();
        assertFalse(BedChatHiderConfig.suppressSleepVignette);
        assertFalse(BedChatHiderConfig.autoHideChat);
        assertTrue(BedChatHiderConfig.enableCinematicSleepFade);
        assertEquals(2.5f, BedChatHiderConfig.cinematicFadeDelaySeconds, 0.001f);
        assertTrue(BedChatHiderConfig.fadeCrosshairWhenSleeping);
    }
}

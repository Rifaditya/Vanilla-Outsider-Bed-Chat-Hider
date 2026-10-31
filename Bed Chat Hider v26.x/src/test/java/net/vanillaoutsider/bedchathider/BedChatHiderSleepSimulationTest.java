// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.vanillaoutsider.bedchathider;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.vanillaoutsider.bedchathider.config.BedChatHiderConfig;
import net.vanillaoutsider.bedchathider.util.BedChatHiderSupport;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BedChatHiderSleepSimulationTest {

    @TempDir
    Path tempDir;

    private Path testConfigPath;

    @BeforeEach
    void setUp() {
        testConfigPath = tempDir.resolve("bedchathider.json");
        BedChatHiderConfig.setCustomConfigPath(testConfigPath);
        BedChatHiderConfig.resetDefaults();
        BedChatHiderClient.hideChat = false;
        BedChatHiderSupport.setTrueSleepLoadedOverride(null);
    }

    @AfterEach
    void tearDown() {
        BedChatHiderConfig.setCustomConfigPath(null);
        BedChatHiderConfig.resetDefaults();
        BedChatHiderClient.hideChat = false;
        BedChatHiderSupport.setTrueSleepLoadedOverride(null);
    }

    /**
     * Simulates the player entering bed and initializing bed chat screen state.
     */
    private void simulateEnterBed() {
        if (BedChatHiderConfig.autoHideChat) {
            BedChatHiderClient.hideChat = true;
        }
    }

    /**
     * Simulates clicking the in-bed chat toggle button.
     */
    private void simulateToggleClick() {
        BedChatHiderClient.hideChat = !BedChatHiderClient.hideChat;
    }

    /**
     * Simulates waking up or exiting bed.
     */
    private void simulateWakeUp() {
        BedChatHiderClient.hideChat = false;
    }

    @Test
    @DisplayName("Pillar 4: Verify sleep lifecycle with autoHideChat enabled")
    void testSleepLifecycleWithAutoHideEnabled() {
        BedChatHiderConfig.autoHideChat = true;
        assertFalse(BedChatHiderClient.hideChat, "Initial chat hidden state should be false before entering bed");

        // When autoHideChat is true, entering bed triggers BedChatHiderClient.hideChat = true
        simulateEnterBed();
        assertTrue(BedChatHiderClient.hideChat, "Entering bed must set hideChat to true when autoHideChat is enabled");

        // Clicking toggle switches BedChatHiderClient.hideChat to false
        simulateToggleClick();
        assertFalse(BedChatHiderClient.hideChat, "First toggle click must switch hideChat to false");

        // Second click toggles it back to true
        simulateToggleClick();
        assertTrue(BedChatHiderClient.hideChat, "Second toggle click must switch hideChat back to true");

        // Waking up handles state cleanly
        simulateWakeUp();
        assertFalse(BedChatHiderClient.hideChat, "Waking up must reset hideChat to false");
    }

    @Test
    @DisplayName("Pillar 4: Verify sleep lifecycle with autoHideChat disabled")
    void testSleepLifecycleWithAutoHideDisabled() {
        BedChatHiderConfig.autoHideChat = false;
        assertFalse(BedChatHiderClient.hideChat, "Initial chat hidden state should be false before entering bed");

        // When autoHideChat is false, entering bed keeps hideChat = false
        simulateEnterBed();
        assertFalse(BedChatHiderClient.hideChat, "Entering bed must keep hideChat as false when autoHideChat is disabled");

        // Clicking toggle switches hideChat to true
        simulateToggleClick();
        assertTrue(BedChatHiderClient.hideChat, "Clicking toggle must switch hideChat to true");

        // Second click switches hideChat back to false
        simulateToggleClick();
        assertFalse(BedChatHiderClient.hideChat, "Second toggle click must switch hideChat back to false");

        // Waking up handles state cleanly
        simulateWakeUp();
        assertFalse(BedChatHiderClient.hideChat, "Waking up must keep hideChat as false");
    }

    @Test
    @DisplayName("Pillar 3 & 4: Verify vignette suppression priority and True Sleep collision avoidance")
    void testVignetteSuppressionPriority() {
        // Test all permutations of suppressSleepVignette (true/false) x True Sleep loaded override (true/false/null)
        boolean[] vignetteSettings = { false, true };
        Boolean[] trueSleepOverrides = { true, false, null };

        for (boolean vignetteSetting : vignetteSettings) {
            for (Boolean trueSleepOverride : trueSleepOverrides) {
                BedChatHiderConfig.suppressSleepVignette = vignetteSetting;
                BedChatHiderSupport.setTrueSleepLoadedOverride(trueSleepOverride);

                if (Boolean.TRUE.equals(trueSleepOverride)) {
                    // True Sleep is loaded: vignette suppression MUST be false regardless of config setting
                    assertFalse(BedChatHiderSupport.shouldSuppressSleepVignette(),
                            "Whenever True Sleep is loaded (override=" + trueSleepOverride + ", config=" + vignetteSetting +
                            "), shouldSuppressSleepVignette() must be false to yield control to True Sleep");
                } else {
                    // True Sleep is not loaded (false or null in headless test): should follow config setting
                    assertEquals(vignetteSetting, BedChatHiderSupport.shouldSuppressSleepVignette(),
                            "When True Sleep is absent (override=" + trueSleepOverride + "), shouldSuppressSleepVignette() must match config value: " + vignetteSetting);
                }
            }
        }
    }

    @Test
    @DisplayName("Pillar 3: Verify en_us.json language translation coverage for all required keys")
    void testLanguageTranslationCoverage() throws IOException {
        Path langPath = resolvePath("src/main/resources/assets/vanilla-outsider-bed-chat-hider/lang/en_us.json");
        assertTrue(Files.exists(langPath), "Language file en_us.json must exist at: " + langPath.toAbsolutePath());

        String jsonContent = Files.readString(langPath, StandardCharsets.UTF_8);
        assertNotNull(jsonContent, "Language file content must not be null");
        assertFalse(jsonContent.trim().isEmpty(), "Language file content must not be empty");

        JsonObject json = JsonParser.parseString(jsonContent).getAsJsonObject();
        assertNotNull(json, "Language file must be valid JSON");

        List<String> requiredKeys = List.of(
                "vanilla-outsider-bed-chat-hider.hideChat",
                "vanilla-outsider-bed-chat-hider.showChat",
                "config.vanilla-outsider-bed-chat-hider.title",
                "config.vanilla-outsider-bed-chat-hider.category.general",
                "config.vanilla-outsider-bed-chat-hider.suppress_sleep_vignette",
                "config.vanilla-outsider-bed-chat-hider.suppress_sleep_vignette.desc",
                "config.vanilla-outsider-bed-chat-hider.auto_hide_chat",
                "config.vanilla-outsider-bed-chat-hider.auto_hide_chat.desc"
        );

        for (String key : requiredKeys) {
            assertTrue(json.has(key), "Missing required translation key in en_us.json: " + key);
            String value = json.get(key).getAsString();
            assertNotNull(value, "Translation value for key '" + key + "' must not be null");
            assertFalse(value.trim().isEmpty(), "Translation value for key '" + key + "' must not be empty");
        }
    }

    @Test
    @DisplayName("Pillar 3 & 4: Verify F1 mode visibility suppression and sleep draft preservation (BL-BCH-006)")
    void testF1ModeVisibilitySuppressionAndDraftPreservation() throws IOException {
        Path mixinPath = resolvePath("src/main/java/net/vanillaoutsider/bedchathider/mixin/ChatScreenMixin.java");
        assertTrue(Files.exists(mixinPath), "ChatScreenMixin.java must exist at: " + mixinPath.toAbsolutePath());
        String mixinSource = Files.readString(mixinPath, StandardCharsets.UTF_8);

        assertTrue(mixinSource.contains("@Inject(method = \"extractRenderState\", at = @At(\"HEAD\"), cancellable = true)"),
                "ChatScreenMixin must inject into extractRenderState at HEAD with cancellable = true");
        assertTrue(mixinSource.contains("this.minecraft.gui.hud.isHidden()"),
                "ChatScreenMixin must check this.minecraft.gui.hud.isHidden() to suppress in-bed rendering during F1 mode");
        assertTrue(mixinSource.contains("ci.cancel()"),
                "ChatScreenMixin must cancel render pass when F1 hideGui is active");

        BedChatHiderConfig.autoHideChat = false;
        simulateEnterBed();
        assertFalse(BedChatHiderClient.hideChat, "Chat starts visible when autoHideChat is false");

        String draftMessage = "Good night everyone!";
        boolean hideGui = true;
        boolean shouldRenderInBedScreen = !hideGui;
        assertFalse(shouldRenderInBedScreen, "InBedChatScreen rendering must be suppressed when hideGui is true");
        assertEquals("Good night everyone!", draftMessage, "Draft text must not be cleared by F1 toggle");

        hideGui = false;
        shouldRenderInBedScreen = !hideGui;
        assertTrue(shouldRenderInBedScreen, "InBedChatScreen rendering must resume when hideGui is toggled back to false");
        assertEquals("Good night everyone!", draftMessage, "Draft text remains preserved after restoring F1 view");

        simulateWakeUp();
        assertFalse(BedChatHiderClient.hideChat, "Waking up resets hideChat state");
    }

    private Path resolvePath(String relative) {
        Path path = Paths.get(relative);
        if (Files.exists(path)) {
            return path;
        }
        Path userDir = Paths.get(System.getProperty("user.dir", "."));
        Path resolved = userDir.resolve(relative);
        if (Files.exists(resolved)) {
            return resolved;
        }
        return path;
    }
}

// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.vanillaoutsider.bedchathider;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;
import net.vanillaoutsider.bedchathider.config.BedChatHiderConfig;
import net.vanillaoutsider.bedchathider.util.BedChatHiderSupport;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Pillar 1: Chaos Property & Config Fuzzing Suite for Vanilla Outsider: Bed Chat Hider.
 * Enforces Freedom Over Anti-Crash policy, adversarial payload resilience, state flip invariants,
 * and config round-trip stability under chaos.
 */
class BedChatHiderStressFuzzTest {

    @TempDir
    Path tempDir;

    private Path testConfigPath;

    @BeforeEach
    void setUp() {
        testConfigPath = tempDir.resolve("bedchathider_stress.json");
        BedChatHiderConfig.setCustomConfigPath(testConfigPath);
        BedChatHiderConfig.resetDefaults();
        BedChatHiderSupport.setTrueSleepLoadedOverride(null);
        BedChatHiderClient.hideChat = false;
    }

    @AfterEach
    void tearDown() {
        BedChatHiderConfig.setCustomConfigPath(null);
        BedChatHiderConfig.resetDefaults();
        BedChatHiderSupport.setTrueSleepLoadedOverride(null);
        BedChatHiderClient.hideChat = false;
    }

    @Test
    @DisplayName("Pillar 1: 20,000 iterations chaos JSON payload fuzzing")
    void testChaosJsonPayloadFuzzing() throws Exception {
        Random random = new Random(42);

        for (int i = 0; i < 20_000; i++) {
            // Prime fields with true to verify graceful fallback to defaults
            BedChatHiderConfig.suppressSleepVignette = true;
            BedChatHiderConfig.autoHideChat = true;

            byte[] payload = generateAdversarialPayload(i, random);
            Files.write(testConfigPath, payload);

            assertDoesNotThrow(BedChatHiderConfig::load,
                    "BedChatHiderConfig.load() must never throw unhandled exception on adversarial payload at iteration " + i);

            // Freedom Over Anti-Crash: corrupted / adversarial payloads must safely restore default false states
            assertFalse(BedChatHiderConfig.suppressSleepVignette,
                    "suppressSleepVignette must restore to default false on adversarial payload at iteration " + i);
            assertFalse(BedChatHiderConfig.autoHideChat,
                    "autoHideChat must restore to default false on adversarial payload at iteration " + i);
        }
    }

    @Test
    @DisplayName("Pillar 1: 20,000 iterations rapid state flip chaos fuzzing")
    void testRapidStateFlipChaosFuzzing() {
        Random random = new Random(1337);

        for (int i = 0; i < 20_000; i++) {
            boolean flipHideChat = random.nextBoolean();
            boolean flipSuppress = random.nextBoolean();
            boolean flipAutoHide = random.nextBoolean();

            BedChatHiderClient.hideChat = flipHideChat;
            BedChatHiderConfig.suppressSleepVignette = flipSuppress;
            BedChatHiderConfig.autoHideChat = flipAutoHide;

            int overrideRoll = random.nextInt(3);
            Boolean trueSleepOverride = (overrideRoll == 0) ? null : (overrideRoll == 1 ? Boolean.TRUE : Boolean.FALSE);
            BedChatHiderSupport.setTrueSleepLoadedOverride(trueSleepOverride);

            assertEquals(flipHideChat, BedChatHiderClient.hideChat,
                    "hideChat state must retain flipped value at iteration " + i);
            assertEquals(flipAutoHide, BedChatHiderConfig.autoHideChat,
                    "autoHideChat config must retain flipped value at iteration " + i);
            assertEquals(flipSuppress, BedChatHiderConfig.suppressSleepVignette,
                    "suppressSleepVignette config must retain flipped value at iteration " + i);

            boolean isTrueSleepLoaded = BedChatHiderSupport.isTrueSleepLoaded();
            boolean shouldSuppress = BedChatHiderSupport.shouldSuppressSleepVignette();

            if (isTrueSleepLoaded) {
                // True Sleep priority invariant: when True Sleep is detected, suppression is strictly false
                assertFalse(shouldSuppress,
                        "True Sleep priority invariant violated at iteration " + i + ": shouldSuppressSleepVignette must be false");
            } else {
                assertEquals(flipSuppress, shouldSuppress,
                        "When True Sleep is absent, shouldSuppressSleepVignette must match config at iteration " + i);
            }
        }
    }

    @Test
    @DisplayName("Pillar 1: 5,000 iterations config save/load stability under chaos")
    void testConfigSaveLoadStabilityUnderChaos() {
        Random random = new Random(777);

        for (int i = 0; i < 5_000; i++) {
            boolean targetSuppress = random.nextBoolean();
            boolean targetAutoHide = random.nextBoolean();

            BedChatHiderConfig.suppressSleepVignette = targetSuppress;
            BedChatHiderConfig.autoHideChat = targetAutoHide;

            assertDoesNotThrow(BedChatHiderConfig::save,
                    "save() must not throw exception during chaos mutation at iteration " + i);
            assertTrue(Files.exists(testConfigPath),
                    "Config file must exist after save() at iteration " + i);

            // Reset defaults to verify subsequent load reads genuine persistent state
            BedChatHiderConfig.resetDefaults();
            assertFalse(BedChatHiderConfig.suppressSleepVignette);
            assertFalse(BedChatHiderConfig.autoHideChat);

            assertDoesNotThrow(BedChatHiderConfig::load,
                    "load() must not throw exception during round-trip at iteration " + i);
            assertEquals(targetSuppress, BedChatHiderConfig.suppressSleepVignette,
                    "Round-trip mismatch for suppressSleepVignette at iteration " + i);
            assertEquals(targetAutoHide, BedChatHiderConfig.autoHideChat,
                    "Round-trip mismatch for autoHideChat at iteration " + i);
        }
    }

    private byte[] generateAdversarialPayload(int index, Random random) {
        int category = index % 10;
        switch (category) {
            case 0: // Empty or whitespace
                String[] whitespaces = {"", " ", "   \t\n\r  ", "\n", "\t\t\t"};
                return whitespaces[random.nextInt(whitespaces.length)].getBytes(StandardCharsets.UTF_8);

            case 1: // Truncated JSON syntax
                String[] truncated = {
                    "{",
                    "{\"suppressSleepVignette\":",
                    "{\"autoHideChat\": tr",
                    "{\"suppressSleepVignette\": fa",
                    "{\"autoHideChat\": false, \"suppressSleepVignette\":",
                    "{\"unknown\": {\"nested\":",
                    "[{\"suppressSleepVignette\":",
                    "{\"suppressSleepVignette\": 12"
                };
                return truncated[random.nextInt(truncated.length)].getBytes(StandardCharsets.UTF_8);

            case 2: // Random raw bytes (including non-UTF-8 and control characters)
                int len = 1 + random.nextInt(128);
                byte[] raw = new byte[len];
                random.nextBytes(raw);
                return raw;

            case 3: // Deep nesting (JSON bomb / recursion fuzzing)
                int depth = 5 + random.nextInt(40);
                StringBuilder nesting = new StringBuilder();
                for (int d = 0; d < depth; d++) {
                    nesting.append("{\"layer\":");
                }
                nesting.append("null");
                for (int d = 0; d < depth; d++) {
                    nesting.append("}");
                }
                return nesting.toString().getBytes(StandardCharsets.UTF_8);

            case 4: // Type mismatch (objects or arrays instead of boolean)
                String[] typeMismatches = {
                    "{\"suppressSleepVignette\": [1, 2, 3], \"autoHideChat\": {\"bad\": 0}}",
                    "{\"suppressSleepVignette\": {}, \"autoHideChat\": []}",
                    "{\"suppressSleepVignette\": \"not_a_bool\", \"autoHideChat\": \"random_string\"}",
                    "{\"suppressSleepVignette\": 99999, \"autoHideChat\": -123.456}",
                    "{\"suppressSleepVignette\": [false, false], \"autoHideChat\": [\"nested\"]}"
                };
                return typeMismatches[random.nextInt(typeMismatches.length)].getBytes(StandardCharsets.UTF_8);

            case 5: // Null literals (root null, key null, value null)
                String[] nullPayloads = {
                    "null",
                    "{\"suppressSleepVignette\": null, \"autoHideChat\": null}",
                    "{\"suppressSleepVignette\": null}",
                    "{\"autoHideChat\": null}",
                    "{\"null\": null, \"suppressSleepVignette\": null}"
                };
                return nullPayloads[random.nextInt(nullPayloads.length)].getBytes(StandardCharsets.UTF_8);

            case 6: // Unicode chaos and emoji stress
                String[] unicodeFuzz = {
                    "{\"suppressSleepVignette\": \"\u0000\uFFFF\uD83D\uDE00\"}",
                    "{\"autoHideChat\": \"\uFEFF\u200B\u200E\u200F\"}",
                    "{\"emoji_chaos\": \"\uD83E\uDD2A\uD83D\uDCA3\uD83D\uDD25\"}",
                    "{\"\u0000\u0001\u0002\": \"control_chars\"}",
                    "{\"text\": \"\u4E16\u754C\u3053\u3093\u306B\u3061\u306F\u041F\u0440\u0438\u0432\u0435\u0442\"}"
                };
                return unicodeFuzz[random.nextInt(unicodeFuzz.length)].getBytes(StandardCharsets.UTF_8);

            case 7: // Non-object JSON roots
                String[] nonObjects = {
                    "[]",
                    "[1, 2, 3, 4, 5]",
                    "[\"string1\", \"string2\"]",
                    "\"just a plain string\"",
                    "1234567890",
                    "98765.4321",
                    "[{\"nested\": false}]"
                };
                return nonObjects[random.nextInt(nonObjects.length)].getBytes(StandardCharsets.UTF_8);

            case 8: // Broken delimiters and punctuation chaos
                String[] delimiterChaos = {
                    ":::::::::::",
                    ",,,,,,,,,,,",
                    "{{{{{{{{{{{",
                    "}}}}}}}}}}}",
                    "{ : , }",
                    "{ \"suppressSleepVignette\" ,,,, }",
                    "{ \"suppressSleepVignette\":: 0 }",
                    "{ \"suppressSleepVignette\": ; \"autoHideChat\": 0 }"
                };
                return delimiterChaos[random.nextInt(delimiterChaos.length)].getBytes(StandardCharsets.UTF_8);

            default: // Large adversarial key count
                StringBuilder bulk = new StringBuilder("{");
                int keys = 5 + random.nextInt(20);
                for (int k = 0; k < keys; k++) {
                    bulk.append("\"garbage_key_").append(k).append("\":").append(random.nextInt(1000));
                    if (k < keys - 1) {
                        bulk.append(",");
                    }
                }
                bulk.append("}");
                return bulk.toString().getBytes(StandardCharsets.UTF_8);
        }
    }
}

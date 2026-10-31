// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.vanillaoutsider.bedchathider;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import net.vanillaoutsider.bedchathider.client.CinematicSleepController;
import net.vanillaoutsider.bedchathider.config.BedChatHiderConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 5-Pillar Headless Verification Suite for Cinematic Sleep HUD Inactivity Auto-Fade.
 * Verifies mathematical bounds, drift deadzone filtering, blind click protection,
 * concurrency resilience, and config side-safety.
 */
class BedChatHiderCinematicFadeTest {

    @BeforeEach
    void setUp() {
        BedChatHiderConfig.resetDefaults();
        BedChatHiderConfig.enableCinematicSleepFade = true;
        BedChatHiderConfig.cinematicFadeDelaySeconds = 2.5f;
        BedChatHiderConfig.fadeCrosshairWhenSleeping = true;
        CinematicSleepController.reset();
    }

    @AfterEach
    void tearDown() {
        BedChatHiderConfig.resetDefaults();
        CinematicSleepController.reset();
    }

    @Test
    @DisplayName("Pillar 1: 50,000 iterations chaos fuzzing of delta ticking, mouse input, and alpha bounds")
    void testChaosFuzzingAlphaBounds() {
        Random random = new Random(4242);
        float[] adversarialDeltas = {
            Float.NaN, Float.POSITIVE_INFINITY, Float.NEGATIVE_INFINITY,
            -1000.0f, -0.001f, 0.0f, 0.0001f, 0.016f, 0.033f, 0.1f, 1.0f, 10.0f, 10000.0f
        };

        for (int i = 0; i < 50_000; i++) {
            int op = random.nextInt(4);
            switch (op) {
                case 0 -> {
                    float dt = (random.nextInt(10) == 0)
                        ? adversarialDeltas[random.nextInt(adversarialDeltas.length)]
                        : random.nextFloat() * 5.0f;
                    CinematicSleepController.update(dt);
                }
                case 1 -> {
                    double mx = (random.nextInt(20) == 0) ? Double.NaN : (random.nextDouble() * 2000.0 - 500.0);
                    double my = (random.nextInt(20) == 0) ? Double.POSITIVE_INFINITY : (random.nextDouble() * 2000.0 - 500.0);
                    CinematicSleepController.onMouseMove(mx, my);
                }
                case 2 -> CinematicSleepController.onInteraction();
                case 3 -> {
                    if (random.nextBoolean()) {
                        CinematicSleepController.setLastInteractionTimeMs(System.currentTimeMillis() - random.nextInt(10000));
                    }
                }
            }

            float alpha = CinematicSleepController.getAlpha();
            assertFalse(Float.isNaN(alpha), "Alpha must never be NaN at iteration " + i);
            assertFalse(Float.isInfinite(alpha), "Alpha must never be Infinite at iteration " + i);
            assertTrue(alpha >= 0.0f && alpha <= 1.0f, "Alpha out of bounds [0.0, 1.0]: " + alpha + " at iteration " + i);

            boolean fullyHidden = CinematicSleepController.isFullyHidden();
            if (fullyHidden) {
                assertTrue(alpha <= 0.01f, "isFullyHidden() is true but alpha > 0.01: " + alpha);
            }
        }
    }

    @Test
    @DisplayName("Pillar 2: 50 virtual worker threads concurrency stress load")
    void testConcurrencyLoad() throws Exception {
        int threads = 50;
        int operationsPerThread = 2_000;
        ExecutorService executor = Executors.newFixedThreadPool(threads);
        List<Callable<Void>> tasks = new ArrayList<>();

        for (int t = 0; t < threads; t++) {
            final int threadId = t;
            tasks.add(() -> {
                Random random = new Random(threadId * 31L);
                for (int i = 0; i < operationsPerThread; i++) {
                    int action = random.nextInt(5);
                    switch (action) {
                        case 0 -> CinematicSleepController.onMouseMove(random.nextDouble() * 1000.0, random.nextDouble() * 1000.0);
                        case 1 -> CinematicSleepController.onInteraction();
                        case 2 -> CinematicSleepController.update(0.016f);
                        case 3 -> {
                            float alpha = CinematicSleepController.getAlpha();
                            assertTrue(alpha >= 0.0f && alpha <= 1.0f);
                        }
                        case 4 -> {
                            CinematicSleepController.isFullyHidden();
                            CinematicSleepController.isCrosshairSuppressed();
                        }
                    }
                }
                return null;
            });
        }

        List<Future<Void>> futures = executor.invokeAll(tasks);
        executor.shutdown();
        assertTrue(executor.awaitTermination(30, TimeUnit.SECONDS), "Executor did not terminate in time; possible deadlock!");

        for (Future<Void> future : futures) {
            assertDoesNotThrow(() -> future.get(), "Worker thread threw an unhandled exception!");
        }
    }

    @Test
    @DisplayName("Pillar 3: Deadzone drift filtering: <= 2.0px sensor jitter does not trigger interaction")
    void testDeadzoneDriftFiltering() {
        // Initial anchor at (100.0, 100.0)
        CinematicSleepController.onMouseMove(100.0, 100.0);

        // Advance time past delay and decay alpha
        CinematicSleepController.setLastInteractionTimeMs(System.currentTimeMillis() - 5000);
        CinematicSleepController.update(0.3f); // Half fade
        float halfAlpha = CinematicSleepController.getAlpha();
        assertTrue(halfAlpha < 1.0f, "Alpha should have decayed past delay");

        // Sub-pixel / sensor noise jitter under 2.0px threshold: dx=1.0, dy=1.0 -> distSq=2.0 <= 4.0
        CinematicSleepController.onMouseMove(101.0, 101.0);
        assertEquals(halfAlpha, CinematicSleepController.getAlpha(), 0.001f,
            "Mouse movement <= 2.0px deadzone must NOT reset alpha or trigger interaction");

        // Jitter dx=1.4, dy=1.4 -> distSq=3.92 <= 4.0
        CinematicSleepController.onMouseMove(101.4, 101.4);
        assertEquals(halfAlpha, CinematicSleepController.getAlpha(), 0.001f,
            "Mouse movement <= 2.0px deadzone must NOT reset alpha");

        // Intentional movement > 2.0px: dx=3.0, dy=0.0 -> distSq=9.0 > 4.0
        CinematicSleepController.onMouseMove(104.0, 100.0);
        assertEquals(1.0f, CinematicSleepController.getAlpha(), 0.001f,
            "Mouse movement > 2.0px threshold must immediately wake controller and reset alpha to 1.0f");
    }

    @Test
    @DisplayName("Pillar 4: Inactivity auto-fade decay and blind click protection guarantee")
    void testInactivityDecayAndBlindClickProtection() {
        CinematicSleepController.reset();
        assertEquals(1.0f, CinematicSleepController.getAlpha());
        assertFalse(CinematicSleepController.isFullyHidden());

        // While elapsed < delay (2.5s), alpha stays 1.0f
        CinematicSleepController.setLastInteractionTimeMs(System.currentTimeMillis() - 1000);
        CinematicSleepController.update(0.1f);
        assertEquals(1.0f, CinematicSleepController.getAlpha());
        assertFalse(CinematicSleepController.isFullyHidden());

        // Once elapsed > 2.5s (e.g. 3.0s ago), alpha decays at rate delta / 0.6s
        CinematicSleepController.setLastInteractionTimeMs(System.currentTimeMillis() - 3000);
        CinematicSleepController.update(0.3f); // 0.3 / 0.6 = 0.5 decay
        assertEquals(0.5f, CinematicSleepController.getAlpha(), 0.01f);
        assertFalse(CinematicSleepController.isFullyHidden());

        // Another 0.3s decay brings alpha to 0.0f
        CinematicSleepController.update(0.3f);
        assertEquals(0.0f, CinematicSleepController.getAlpha(), 0.001f);
        assertTrue(CinematicSleepController.isFullyHidden(), "Controller must be fully hidden when alpha is 0.0f");
        assertTrue(CinematicSleepController.isCrosshairSuppressed(), "Crosshair must be suppressed when fully hidden");

        // Blind Click Protection: player clicks while fully hidden
        // Inactivity controller wakes up and resets alpha to 1.0f immediately
        CinematicSleepController.onInteraction();
        assertEquals(1.0f, CinematicSleepController.getAlpha(), 0.001f);
        assertFalse(CinematicSleepController.isFullyHidden());
        assertFalse(CinematicSleepController.isCrosshairSuppressed());
    }

    @Test
    @DisplayName("Pillar 5: Config switches, crosshair toggle, and state safety")
    void testConfigDisablingAndReset() {
        // Disable cinematic fade in config
        BedChatHiderConfig.enableCinematicSleepFade = false;
        CinematicSleepController.setLastInteractionTimeMs(System.currentTimeMillis() - 10000);
        CinematicSleepController.update(1.0f);

        assertEquals(1.0f, CinematicSleepController.getAlpha(),
            "When enableCinematicSleepFade is false, alpha must remain locked to 1.0f");
        assertFalse(CinematicSleepController.isFullyHidden());
        assertFalse(CinematicSleepController.isCrosshairSuppressed());

        // Re-enable cinematic fade but disable crosshair suppression
        BedChatHiderConfig.enableCinematicSleepFade = true;
        BedChatHiderConfig.fadeCrosshairWhenSleeping = false;
        CinematicSleepController.setLastInteractionTimeMs(System.currentTimeMillis() - 10000);
        CinematicSleepController.update(1.0f); // fully decayed

        assertTrue(CinematicSleepController.isFullyHidden());
        assertFalse(CinematicSleepController.isCrosshairSuppressed(),
            "When fadeCrosshairWhenSleeping is false, crosshair must NOT be suppressed even when fully hidden");

        // Reset restores initial full visibility
        CinematicSleepController.reset();
        assertEquals(1.0f, CinematicSleepController.getAlpha());
        assertFalse(CinematicSleepController.isFullyHidden());
    }
}

// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.vanillaoutsider.bedchathider.client;

import net.vanillaoutsider.bedchathider.config.BedChatHiderConfig;

/**
 * Controller managing idle mouse/keyboard inactivity and smooth cinematic alpha auto-fading
 * for sleep HUD elements (bed buttons, chat input, crosshair).
 */
public final class CinematicSleepController {
    public static final double DEADZONE_THRESHOLD_SQ = 4.0; // 2.0 px delta squared
    public static final float FADE_DURATION_SECONDS = 0.6f;

    private static volatile long lastInteractionTimeMs = System.currentTimeMillis();
    private static volatile float currentAlpha = 1.0f;
    private static volatile double lastMouseX = -1.0;
    private static volatile double lastMouseY = -1.0;
    private static volatile long lastUpdateTimeNs = System.nanoTime();

    private CinematicSleepController() {
    }

    public static synchronized void onInteraction() {
        lastInteractionTimeMs = System.currentTimeMillis();
        currentAlpha = 1.0f;
    }

    public static synchronized void onMouseMove(double mouseX, double mouseY) {
        if (Double.isNaN(mouseX) || Double.isNaN(mouseY) || Double.isInfinite(mouseX) || Double.isInfinite(mouseY)) {
            return;
        }

        if (lastMouseX < 0.0 || lastMouseY < 0.0) {
            lastMouseX = mouseX;
            lastMouseY = mouseY;
            return;
        }

        double dx = mouseX - lastMouseX;
        double dy = mouseY - lastMouseY;
        double distSq = dx * dx + dy * dy;

        if (distSq > DEADZONE_THRESHOLD_SQ) {
            lastMouseX = mouseX;
            lastMouseY = mouseY;
            onInteraction();
        }
    }

    public static synchronized void update() {
        long nowNs = System.nanoTime();
        float dt = (nowNs - lastUpdateTimeNs) / 1_000_000_000.0f;
        lastUpdateTimeNs = nowNs;
        if (dt < 0.0f || dt > 1.0f) {
            dt = 0.016f;
        }
        update(dt);
    }

    public static synchronized void update(float deltaSeconds) {
        if (Float.isNaN(deltaSeconds) || Float.isInfinite(deltaSeconds) || deltaSeconds < 0.0f) {
            deltaSeconds = 0.0f;
        }

        if (!BedChatHiderConfig.enableCinematicSleepFade) {
            currentAlpha = 1.0f;
            return;
        }

        long now = System.currentTimeMillis();
        float elapsed = (now - lastInteractionTimeMs) / 1000.0f;

        if (elapsed > BedChatHiderConfig.cinematicFadeDelaySeconds) {
            currentAlpha = Math.max(0.0f, Math.min(1.0f, currentAlpha - (deltaSeconds / FADE_DURATION_SECONDS)));
        } else {
            currentAlpha = 1.0f;
        }
    }

    public static synchronized float getAlpha() {
        return currentAlpha;
    }

    public static synchronized boolean isFullyHidden() {
        return BedChatHiderConfig.enableCinematicSleepFade && currentAlpha <= 0.01f;
    }

    public static synchronized boolean isCrosshairSuppressed() {
        return BedChatHiderConfig.fadeCrosshairWhenSleeping && isFullyHidden();
    }

    public static synchronized void reset() {
        lastInteractionTimeMs = System.currentTimeMillis();
        lastUpdateTimeNs = System.nanoTime();
        currentAlpha = 1.0f;
        lastMouseX = -1.0;
        lastMouseY = -1.0;
    }

    public static synchronized void setLastInteractionTimeMs(long timeMs) {
        lastInteractionTimeMs = timeMs;
    }

    public static synchronized long getLastInteractionTimeMs() {
        return lastInteractionTimeMs;
    }

    public static synchronized void setAlpha(float alpha) {
        if (Float.isNaN(alpha) || Float.isInfinite(alpha)) {
            currentAlpha = 1.0f;
            return;
        }
        currentAlpha = Math.max(0.0f, Math.min(1.0f, alpha));
    }
}

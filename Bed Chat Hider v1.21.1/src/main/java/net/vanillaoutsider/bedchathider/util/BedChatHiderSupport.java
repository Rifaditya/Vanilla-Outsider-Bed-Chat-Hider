// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.vanillaoutsider.bedchathider.util;

import java.net.URI;
import net.dasik.social.api.SocialLinks;
import net.dasik.social.api.config.DasikSupportHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.vanillaoutsider.bedchathider.config.BedChatHiderConfig;

/**
 * Utility helper connecting Bed Chat Hider with Dasik Library community & creator support,
 * and managing inter-mod collision detection (e.g. Vanilla Outsider: True Sleep).
 */
public final class BedChatHiderSupport {

    private static Boolean trueSleepLoadedOverride = null;

    private BedChatHiderSupport() {
    }

    public static void setTrueSleepLoadedOverride(Boolean override) {
        trueSleepLoadedOverride = override;
    }

    public static boolean isTrueSleepLoaded() {
        if (trueSleepLoadedOverride != null) {
            return trueSleepLoadedOverride;
        }
        try {
            return FabricLoader.getInstance().isModLoaded("vanilla-outsider-true-sleep");
        } catch (Throwable ignored) {
            return false;
        }
    }

    public static boolean shouldSuppressSleepVignette() {
        if (isTrueSleepLoaded()) {
            return false; // True Sleep takes ownership of sleep darkening
        }
        return BedChatHiderConfig.suppressSleepVignette;
    }

    public static String getDiscordUrl() {
        return SocialLinks.DISCORD_INVITE_URL;
    }

    public static String getKofiUrl() {
        return DasikSupportHelper.KOFI_URL;
    }

    public static URI getDiscordUri() {
        return SocialLinks.getDiscordUri();
    }

    public static URI getKofiUri() {
        return SocialLinks.getKofiUri();
    }

    public static URI getGithubUri() {
        return SocialLinks.getGithubUri();
    }

    public static String getFormattedSupportInfo() {
        return "Discord: " + SocialLinks.DISCORD_INVITE_URL + " | Ko-fi: " + DasikSupportHelper.KOFI_URL;
    }
}

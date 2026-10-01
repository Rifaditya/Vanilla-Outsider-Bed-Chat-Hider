// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.vanillaoutsider.bedchathider.util;

import net.dasik.social.api.SocialLinks;

import java.net.URI;

/**
 * Utility helper connecting Bed Chat Hider with Dasik Library community & creator support.
 */
public final class BedChatHiderSupport {

    private BedChatHiderSupport() {
    }

    public static String getDiscordUrl() {
        return SocialLinks.DISCORD_INVITE_URL;
    }

    public static String getKofiUrl() {
        return SocialLinks.KOFI_URL;
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
        return "Discord: " + SocialLinks.DISCORD_INVITE_URL + " | Ko-fi: " + SocialLinks.KOFI_URL;
    }
}

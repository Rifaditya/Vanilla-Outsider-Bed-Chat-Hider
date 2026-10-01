// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.vanillaoutsider.bedchathider;

import net.fabricmc.api.ClientModInitializer;
import net.dasik.social.api.SocialLinks;
import net.dasik.social.util.ModVersionGuard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BedChatHiderClient implements ClientModInitializer {
    public static final String MOD_ID = "vanilla-outsider-bed-chat-hider";
    public static final Logger LOGGER = LoggerFactory.getLogger("Bed Chat Hider");

    // In-memory static field to track the session toggle state
    public static boolean hideChat = false;

    @Override
    public void onInitializeClient() {
        ModVersionGuard.verifyEnvironment();
        LOGGER.info("Vanilla Outsider: Bed Chat Hider 1.21.11 initialized! Community: {}, Support: {}", SocialLinks.DISCORD_INVITE_URL, SocialLinks.KOFI_URL);
    }
}

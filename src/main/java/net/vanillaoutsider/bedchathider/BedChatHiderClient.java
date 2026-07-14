package net.vanillaoutsider.bedchathider;

import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BedChatHiderClient implements ClientModInitializer {
    public static final String MOD_ID = "vanilla-outsider-bed-chat-hider";
    public static final Logger LOGGER = LoggerFactory.getLogger("Bed Chat Hider");

    // In-memory static field to track the session toggle state
    public static boolean hideChat = false;

    @Override
    public void onInitializeClient() {
        LOGGER.info("Vanilla Outsider: Bed Chat Hider initialized!");
    }
}

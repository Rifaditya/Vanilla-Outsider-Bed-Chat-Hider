// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.vanillaoutsider.bedchathider;

import net.dasik.social.api.SocialLinks;
import net.dasik.social.util.ModVersionGuard;
import net.vanillaoutsider.bedchathider.util.BedChatHiderSupport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;

class BedChatHiderStateTest {

    @BeforeEach
    void setUp() {
        BedChatHiderClient.hideChat = false;
    }

    @Test
    @DisplayName("Verify default state: chat is not hidden by default")
    void testInitialState() {
        assertFalse(BedChatHiderClient.hideChat, "Initial chat hidden state must be false");
    }

    @Test
    @DisplayName("Verify toggle state logic: flipping hideChat toggles between true and false")
    void testToggleStateLogic() {
        assertFalse(BedChatHiderClient.hideChat);

        // Simulate user toggle
        BedChatHiderClient.hideChat = !BedChatHiderClient.hideChat;
        assertTrue(BedChatHiderClient.hideChat, "After first toggle, hideChat must be true");

        // Simulate second toggle
        BedChatHiderClient.hideChat = !BedChatHiderClient.hideChat;
        assertFalse(BedChatHiderClient.hideChat, "After second toggle, hideChat must be false again");
    }

    @Test
    @DisplayName("Verify Mod ID integrity")
    void testModIdIntegrity() {
        assertEquals("vanilla-outsider-bed-chat-hider", BedChatHiderClient.MOD_ID);
        assertNotNull(BedChatHiderClient.LOGGER);
    }

    @Test
    @DisplayName("Verify BedChatHiderSupport URL integration with Dasik Library")
    void testSupportUrlIntegration() {
        assertEquals(SocialLinks.DISCORD_INVITE_URL, BedChatHiderSupport.getDiscordUrl());
        assertEquals(SocialLinks.KOFI_URL, BedChatHiderSupport.getKofiUrl());

        assertNotNull(BedChatHiderSupport.getDiscordUrl());
        assertTrue(BedChatHiderSupport.getDiscordUrl().startsWith("https://discord.gg/"));

        assertNotNull(BedChatHiderSupport.getKofiUrl());
        assertTrue(BedChatHiderSupport.getKofiUrl().startsWith("https://ko-fi.com/"));

        URI discordUri = BedChatHiderSupport.getDiscordUri();
        assertNotNull(discordUri);
        assertEquals(SocialLinks.getDiscordUri(), discordUri);

        URI kofiUri = BedChatHiderSupport.getKofiUri();
        assertNotNull(kofiUri);
        assertEquals(SocialLinks.getKofiUri(), kofiUri);

        URI githubUri = BedChatHiderSupport.getGithubUri();
        assertNotNull(githubUri);
        assertEquals(SocialLinks.getGithubUri(), githubUri);

        String formatted = BedChatHiderSupport.getFormattedSupportInfo();
        assertNotNull(formatted);
        assertTrue(formatted.contains(SocialLinks.DISCORD_INVITE_URL));
        assertTrue(formatted.contains(SocialLinks.KOFI_URL));
    }

    @Test
    @DisplayName("Verify ModVersionGuard safety: existing classes resolve without exception")
    void testModVersionGuardExistingClass() {
        assertDoesNotThrow(() -> {
            ModVersionGuard.checkClass("Bed Chat Hider Test", "java.lang.String");
        });
    }

    @Test
    @DisplayName("Verify ModVersionGuard safety: missing class throws RuntimeException with guard banner")
    void testModVersionGuardMissingClass() {
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            ModVersionGuard.checkClass("Bed Chat Hider Test", "net.vanillaoutsider.nonexistent.FakeClass");
        });
        assertTrue(exception.getMessage().contains("[PRE-RELEASE / VERSION GUARD WARNING]"));
    }
}

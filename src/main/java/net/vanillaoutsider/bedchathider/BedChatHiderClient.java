/*
 * This file is part of Bed Chat Hider.
 *
 * Bed Chat Hider is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Bed Chat Hider is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Bed Chat Hider.  If not, see <https://www.gnu.org/licenses/>.
 */
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

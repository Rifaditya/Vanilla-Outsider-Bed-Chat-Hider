// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.vanillaoutsider.bedchathider;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import net.vanillaoutsider.bedchathider.config.BedChatHiderModMenu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class BedChatHiderModMenuTest {

    @Test
    @DisplayName("Verify BedChatHiderModMenu can be directly instantiated and provides non-null factory")
    void testDirectInstantiationAndFactory() {
        BedChatHiderModMenu modMenu = new BedChatHiderModMenu();
        assertNotNull(modMenu, "BedChatHiderModMenu instance should not be null");

        ConfigScreenFactory<?> factory = modMenu.getModConfigScreenFactory();
        assertNotNull(factory, "ConfigScreenFactory must not be null even when YACL is absent");
    }

    @Test
    @DisplayName("Verify BedChatHiderModMenu can be loaded and invoked via reflection")
    void testReflectionInstantiationAndInvocation() throws Exception {
        Class<?> clazz = Class.forName("net.vanillaoutsider.bedchathider.config.BedChatHiderModMenu");
        assertNotNull(clazz, "BedChatHiderModMenu class must be discoverable");

        Object instance = clazz.getDeclaredConstructor().newInstance();
        assertNotNull(instance, "BedChatHiderModMenu reflection instance must not be null");

        Method factoryMethod = clazz.getMethod("getModConfigScreenFactory");
        assertNotNull(factoryMethod, "getModConfigScreenFactory method must exist");

        Object factory = factoryMethod.invoke(instance);
        assertNotNull(factory, "Invoking getModConfigScreenFactory must return non-null factory");
    }
}

// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.vanillaoutsider.bedchathider;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * BedChatHiderSidednessContractTest: Pillar 5 Sidedness, Manifest & License Hygiene Contract Test Suite.
 *
 * Verifies across all Minecraft version anchors:
 * - Exact single-line GPLv3 license header compliance on line 1 of all .java sources.
 * - Client-only manifest contract in fabric.mod.json (client environment, only client/modmenu entrypoints).
 * - Structural integrity of vanilla-outsider-bed-chat-hider.mixins.json and valid package/class registration.
 * - Reflection hygiene & Anti-Unmapped Reflection Law enforcement (zero raw Class.forName in production sources/bytecode).
 */
public class BedChatHiderSidednessContractTest {

    private static final String EXPECTED_LICENSE_HEADER = "// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3";

    // Testing Policy §4: Dynamic Bytecode Scanner Constant Pool Inlining Guard
    // Search tokens constructed dynamically from byte arrays prevent compiler constant-pool inlining & self-matching
    private static final String FORBIDDEN_REFLECTION_TOKEN = new String(new byte[]{
            67, 108, 97, 115, 115, 46, 102, 111, 114, 78, 97, 109, 101 // "Class.forName"
    }, StandardCharsets.UTF_8);

    private static final String FORBIDDEN_BYTECODE_REFLECTION = new String(new byte[]{
            106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 67, 108, 97, 115, 115, 47, 102, 111, 114, 78, 97, 109, 101 // "java/lang/Class/forName"
    }, StandardCharsets.ISO_8859_1);

    private Path resolvePath(String relative) {
        Path path = Paths.get(relative);
        if (Files.exists(path)) {
            return path;
        }
        Path userDir = Paths.get(System.getProperty("user.dir", "."));
        Path resolved = userDir.resolve(relative);
        if (Files.exists(resolved)) {
            return resolved;
        }
        return path;
    }

    @Test
    @DisplayName("Pillar 5: Exact Single-Line GPLv3 License Header Present On Line 1 Across All Sources")
    void testLicenseHeaderCompliance() throws IOException {
        Path[] roots = new Path[] {
                resolvePath("src/main/java"),
                resolvePath("src/test/java")
        };

        for (Path root : roots) {
            assertTrue(Files.exists(root), "Source root must exist: " + root.toAbsolutePath());
            try (Stream<Path> stream = Files.walk(root)) {
                List<Path> javaFiles = stream.filter(p -> p.toString().endsWith(".java")).toList();
                assertFalse(javaFiles.isEmpty(), "Java files must exist in: " + root);

                for (Path file : javaFiles) {
                    List<String> lines = Files.readAllLines(file, StandardCharsets.UTF_8);
                    assertFalse(lines.isEmpty(), "Java source file must not be empty: " + file);
                    String line1 = lines.get(0).trim();
                    assertEquals(EXPECTED_LICENSE_HEADER, line1,
                            "Missing or invalid GPLv3 license header on line 1 in: " + file.toString().replace('\\', '/'));
                }
            }
        }
    }

    @Test
    @DisplayName("Pillar 5: Manifest Contract Enforces Strict Client-Only Environment and Entrypoints")
    void testClientOnlyManifestContract() throws IOException {
        Path fabricModPath = resolvePath("src/main/resources/fabric.mod.json");
        assertTrue(Files.exists(fabricModPath), "fabric.mod.json must exist at: " + fabricModPath.toAbsolutePath());

        String content = Files.readString(fabricModPath, StandardCharsets.UTF_8);
        JsonObject json = JsonParser.parseString(content).getAsJsonObject();

        // Environment must be strictly client
        assertTrue(json.has("environment"), "fabric.mod.json must declare 'environment'");
        assertEquals("client", json.get("environment").getAsString(),
                "fabric.mod.json 'environment' must be strictly 'client'");

        // Entrypoints must contain only client and modmenu
        assertTrue(json.has("entrypoints"), "fabric.mod.json must declare 'entrypoints'");
        JsonObject entrypoints = json.getAsJsonObject("entrypoints");
        Set<String> entrypointKeys = entrypoints.keySet();

        assertTrue(entrypointKeys.contains("client"), "entrypoints must contain 'client'");
        assertTrue(entrypointKeys.contains("modmenu"), "entrypoints must contain 'modmenu'");
        assertEquals(Set.of("client", "modmenu"), entrypointKeys,
                "entrypoints must only contain 'client' and 'modmenu' (no server or common entrypoints)");

        // Assert client entrypoint targets BedChatHiderClient
        JsonArray clientArray = entrypoints.getAsJsonArray("client");
        assertNotNull(clientArray, "'client' entrypoint must be an array");
        assertEquals(1, clientArray.size(), "'client' entrypoint must contain exactly 1 entrypoint");
        assertEquals("net.vanillaoutsider.bedchathider.BedChatHiderClient", clientArray.get(0).getAsString());

        // Assert modmenu entrypoint targets BedChatHiderModMenu
        JsonArray modmenuArray = entrypoints.getAsJsonArray("modmenu");
        assertNotNull(modmenuArray, "'modmenu' entrypoint must be an array");
        assertEquals(1, modmenuArray.size(), "'modmenu' entrypoint must contain exactly 1 entrypoint");
        assertEquals("net.vanillaoutsider.bedchathider.config.BedChatHiderModMenu", modmenuArray.get(0).getAsString());
    }

    @Test
    @DisplayName("Pillar 5: Mixin Registration Hygiene and Configuration Integrity")
    void testMixinConfigContract() throws IOException {
        Path mixinConfigPath = resolvePath("src/main/resources/vanilla-outsider-bed-chat-hider.mixins.json");
        assertTrue(Files.exists(mixinConfigPath),
                "vanilla-outsider-bed-chat-hider.mixins.json must exist at: " + mixinConfigPath.toAbsolutePath());

        String content = Files.readString(mixinConfigPath, StandardCharsets.UTF_8);
        JsonObject json = JsonParser.parseString(content).getAsJsonObject();

        assertTrue(json.has("required"), "Mixin config must declare 'required'");
        assertTrue(json.get("required").getAsBoolean(), "Mixin config 'required' flag must be true");

        assertTrue(json.has("package"), "Mixin config must declare 'package'");
        assertEquals("net.vanillaoutsider.bedchathider.mixin", json.get("package").getAsString(),
                "Mixin package must be 'net.vanillaoutsider.bedchathider.mixin'");

        // In 1.20.1-26.1.2 mixins are under "client", in 26.2+ client mod mixins may be under "mixins" or "client"
        JsonArray mixinArray = json.has("client") ? json.getAsJsonArray("client") : json.getAsJsonArray("mixins");
        assertNotNull(mixinArray, "Mixin config must define 'client' or 'mixins' array");

        List<String> mixinNames = new ArrayList<>();
        for (int i = 0; i < mixinArray.size(); i++) {
            mixinNames.add(mixinArray.get(i).getAsString());
        }

        assertTrue(mixinNames.contains("ChatScreenMixin"), "Mixins must include ChatScreenMixin");
        assertTrue(mixinNames.contains("InBedChatScreenMixin"), "Mixins must include InBedChatScreenMixin");
        assertTrue(mixinNames.contains("HudMixin"), "Mixins must include HudMixin");

        // Verify each mixin class exists in source
        Path mixinSourceDir = resolvePath("src/main/java/net/vanillaoutsider/bedchathider/mixin");
        assertTrue(Files.exists(mixinSourceDir), "Mixin source directory must exist: " + mixinSourceDir.toAbsolutePath());

        for (String mixinName : mixinNames) {
            Path mixinSourceFile = mixinSourceDir.resolve(mixinName + ".java");
            assertTrue(Files.exists(mixinSourceFile),
                    "Mixin source file must exist: " + mixinSourceFile.toAbsolutePath());
        }
    }

    @Test
    @DisplayName("Pillar 5: Mixin Registration Hygiene Contract (Alias)")
    void testMixinRegistrationHygiene() throws IOException {
        testMixinConfigContract();
    }

    @Test
    @DisplayName("Pillar 5: Reflection Hygiene and Anti-Unmapped Reflection Law Enforcement")
    void testReflectionHygiene() throws IOException {
        Path srcMain = resolvePath("src/main/java");
        assertTrue(Files.exists(srcMain), "src/main/java must exist at: " + srcMain.toAbsolutePath());

        try (Stream<Path> stream = Files.walk(srcMain)) {
            List<Path> javaFiles = stream.filter(p -> p.toString().endsWith(".java")).toList();
            assertFalse(javaFiles.isEmpty(), "Must find Java source files in src/main/java");

            for (Path file : javaFiles) {
                String normalized = file.toString().replace('\\', '/');
                String content = Files.readString(file, StandardCharsets.UTF_8);

                // Anti-Unmapped-Reflection Law (Testing Policy §1): Knot loads intermediary bytecode in production.
                // Raw Class.forName is forbidden in production sources.
                assertFalse(content.contains(FORBIDDEN_REFLECTION_TOKEN),
                        "Forbidden raw Class.forName invocation found in production source: " + normalized
                                + " (Use FabricLoader.getInstance().isModLoaded() or accessor mixins)");
            }
        }

        Path classesRoot = resolvePath("build/classes/java/main");
        if (Files.exists(classesRoot)) {
            try (Stream<Path> stream = Files.walk(classesRoot)) {
                List<Path> classFiles = stream.filter(p -> p.toString().endsWith(".class")).toList();
                for (Path classFile : classFiles) {
                    String rawBytes = new String(Files.readAllBytes(classFile), StandardCharsets.ISO_8859_1);
                    assertFalse(rawBytes.contains(FORBIDDEN_BYTECODE_REFLECTION),
                            "Forbidden java/lang/Class/forName constant pool reference in bytecode: "
                                    + classFile.toString().replace('\\', '/'));
                }
            }
        }
    }

    @Test
    @DisplayName("Pillar 5: Zero Forbidden Reflection in Production Sources (Alias)")
    void testNoForbiddenReflection() throws IOException {
        testReflectionHygiene();
    }
}

# 🛠️ 開發者環境與編譯建置

| Parameter | Technical Details |
| :--- | :--- |
| **Target Java Version** | `Java 25` (Hotspot JDK 25) |
| **Build Automation** | Gradle 9.3+ with Loom 1.15+ (`id 'net.fabricmc.fabric-loom'`) |
| **Minecraft Target** | `26.2` |
| **Fabric Loader** | `0.19.3` |
| **Fabric API (Build)** | `0.152.2+26.2` |
| **Build Flag** | `--no-daemon` |

---

## 🚀 Step-by-Step Environment Setup

### 1. Prerequisites
* Install **JDK 25** (e.g. Eclipse Adoptium Temurin 25).
* Configure `gradle.properties`:
  ```properties
  org.gradle.java.home=C:/Program Files/Eclipse Adoptium/jdk-25.0.3.9-hotspot
  ```

### 2. Cloning the Repository
```bash
git clone https://github.com/Rifaditya/Vanilla-Outsider-Bed-Chat-Hider.git
cd Vanilla-Outsider-Bed-Chat-Hider
```

### 3. Compiling the Release JAR
```bash
./gradlew build --no-daemon
```
The compiled output JAR will be generated at `build/libs/vanilla-outsider-bed-chat-hider-1.0.4+26.2.jar`.

### 4. Running the Development Client
```bash
./gradlew runClient --no-daemon
```

---

## 📁 Source Code Directory Structure

```
Vanilla-Outsider-Bed-Chat-Hider/
├── gradle/
├── src/
│   └── main/
│       ├── java/
│       │   └── net/vanillaoutsider/bedchathider/
│       │       ├── BedChatHiderClient.java
│       │       ├── mixin/
│       │       │   ├── ChatScreenMixin.java
│       │       │   └── InBedChatScreenMixin.java
│       │       └── util/
│       │           └── ModVersionGuard.java
│       └── resources/
│           ├── assets/vanilla-outsider-bed-chat-hider/
│           │   ├── icon.png
│           │   └── lang/en_us.json
│           ├── fabric.mod.json
│           └── vanilla-outsider-bed-chat-hider.mixins.json
├── build.gradle
└── gradle.properties
```

---

*Related Pages*: [[首頁面板|zh_tw-Home]] | [[程式碼架構與 Mixin 注入剖析|zh_tw-Architecture-and-Mixins]] | [[版本相容性與生命週期|zh_tw-Version-Compatibility]]

plugins {
    id("java")
    id("io.papermc.paperweight.userdev") version "2.0.0-beta.17"
}

group = "com.m3z0id.gunGame"
version = "1.0.2"
description = "A GunGame minigame plugin for Paper 1.19+"

java {
    toolchain.languageVersion = JavaLanguageVersion.of(21)
}

repositories {
    mavenCentral()
    maven {
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
    maven {
        url = uri("https://oss.sonatype.org/content/groups/public/")
    }
    maven {
        url = uri("https://jitpack.io")
    }
    maven {
        url = uri("https://repo.codemc.io/repository/maven-public/")
    }
    maven {
        url = uri("https://repo.extendedclip.com/content/repositories/placeholderapi/")
    }
}

dependencies {
    implementation("com.google.code.gson:gson:2.13.1")
    compileOnly("me.clip:placeholderapi:2.11.6")
    compileOnly("com.github.MilkBowl:VaultAPI:1.7") {
        exclude(group = "org.bukkit", module = "bukkit")
    }
    paperweight.paperDevBundle("1.21.5-R0.1-SNAPSHOT")
}

tasks {
    compileJava {
        options.release = 21
    }

    processResources {
        filteringCharset = Charsets.UTF_8.name()
        val props =
            mapOf(
                "name" to project.name,
                "version" to project.version,
                "description" to project.description,
            )
        inputs.properties(props)
        filesMatching("paper-plugin.yml") { expand(props) }
    }

    build {
        dependsOn(reobfJar)
    }
}

tasks.assemble {
    paperweight.reobfArtifactConfiguration = io.papermc.paperweight.userdev.ReobfArtifactConfiguration.MOJANG_PRODUCTION
}
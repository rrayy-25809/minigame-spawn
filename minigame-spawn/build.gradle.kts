plugins {
    java
}

group = "com.rrayy"
version =" 0.1-SNAPSHOT"

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
    maven {
        url = uri("https://hub.spigotmc.org/nexus/content/repositories/snapshots/") // Spigot
    }
}

dependencies {
//JUnit Jupiter 사용.
    testImplementation("org.junit.jupiter:junit-jupiter:5.7.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    implementation("org.spigotmc:spigot-api:1.19.3-R0.1-SNAPSHOT") //Spigot-API
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}
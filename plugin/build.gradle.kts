plugins {
    id("xyz.jpenilla.run-paper") version "2.0.0"
    id("net.minecrell.plugin-yml.bukkit") version "0.5.3"
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.20.1-R0.1-SNAPSHOT")
    implementation("cloud.commandframework:cloud-paper:1.8.4")
    implementation("cloud.commandframework:cloud-annotations:1.8.4")
    implementation(project(":api"))
    annotationProcessor("cloud.commandframework:cloud-annotations:1.8.4")
}

tasks {
    runServer {
        minecraftVersion("1.20.1")
    }
}

bukkit {
    name = "BlockShufflePlugin"
    main = "me.wckq.plugin.BlockShufflePlugin"
}
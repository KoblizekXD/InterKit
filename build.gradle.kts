plugins {
    `java-library`
    id("io.papermc.paperweight.userdev") version "1.7.1"
}

group = "lol.koblizek"
version = "1.0.0"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    paperweight.paperDevBundle("1.20.6-R0.1-SNAPSHOT")
}

tasks.assemble {
    dependsOn(tasks.getByName("reobfJar"))
}
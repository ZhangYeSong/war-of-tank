import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val kotlin_version: String by extra
buildscript {
    var kotlin_version: String by extra
    kotlin_version = "1.1.4-3"
    repositories {
        mavenCentral()
        maven { setUrl("https://jitpack.io") }
    }
    dependencies {
        classpath(kotlin("gradle-plugin", kotlin_version))
    }
}
plugins {
    kotlin("jvm","1.1.4-3")
    application
}

application {
    mainClassName = "GameWindow"
}

dependencies {
    compile(kotlin("stdlib-jre8", kotlin_version))
    compile("com.github.shaunxiao:kotlinGameEngine:v0.0.2")
}
repositories {
    mavenCentral()
    maven { setUrl("https://jitpack.io") }
}
val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
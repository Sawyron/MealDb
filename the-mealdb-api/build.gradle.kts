plugins {
    id("java-library")
    alias(libs.plugins.jetbrainsKotlinJvm)
    alias(libs.plugins.kotlinxSerialization)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(libs.retrofit2)
    implementation(libs.converter.kotlinx.serialization)
    api(libs.okhttp)
    api(libs.kotlinx.serialization.json)
}
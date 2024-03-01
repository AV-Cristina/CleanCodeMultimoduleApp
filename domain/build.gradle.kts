plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation("org.mockito:mockito-core:5.10.0")
    implementation("org.mockito.kotlin:mockito-kotlin:5.2.1")
    testImplementation("junit:junit:4.13.2")
}
plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    id("au.com.dius.pact")

    kotlin("jvm")
    kotlin("plugin.spring")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("au.com.dius.pact.provider:junit5")
    testImplementation("io.mockk:mockk")
}

tasks {
    withType<Test> {
        systemProperty("pact.verifier.publishResults", "true")
        systemProperty("pact.provider.version", "0.1.0-SNAPSHOT")
        systemProperty("pactbroker.auth.username", "pact-user")
        systemProperty("pactbroker.auth.password", "123")
    }
}

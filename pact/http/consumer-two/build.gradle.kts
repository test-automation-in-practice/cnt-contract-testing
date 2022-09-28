plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    id("au.com.dius.pact")

    kotlin("jvm")
    kotlin("plugin.spring")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("au.com.dius.pact.consumer:junit5")
}

pact {
    publish {
        pactBrokerUrl = "http://localhost:9292"
        consumerVersion = "0.1.0-SNAPSHOT"
        pactBrokerUsername = "pact-user"
        pactBrokerPassword = "123"
    }
}


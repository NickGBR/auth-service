import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val springBootVersion = "3.2.2"
val jsonWebTokenVersion = "0.12.5"
plugins {
    kotlin("jvm") version "1.9.0"
    kotlin("plugin.spring") version "1.9.22"
    id("org.jetbrains.kotlin.plugin.jpa") version "2.0.0-Beta4"
    id("org.liquibase.gradle") version "2.2.1"
    application
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict", "-java-parameters=true")
        jvmTarget = "17"
    }
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot:${springBootVersion}")
    implementation("org.springframework.boot:spring-boot-starter-web:${springBootVersion}")
    implementation("org.springframework.boot:spring-boot-starter-security:${springBootVersion}")
    implementation("org.springframework.boot:spring-boot-starter-graphql:${springBootVersion}")
    implementation("org.springframework:spring-orm:6.1.3")

    implementation("io.jsonwebtoken:jjwt-api:${jsonWebTokenVersion}")
    implementation("io.jsonwebtoken:jjwt-impl:${jsonWebTokenVersion}")
    implementation("io.jsonwebtoken:jjwt-jackson:${jsonWebTokenVersion}")
    implementation("org.postgresql:postgresql:42.2.27")
    implementation("org.hibernate.orm:hibernate-core:6.3.0.Final")
    runtimeOnly("org.jetbrains.kotlin:kotlin-reflect:1.9.22")

    testImplementation("org.springframework.security:spring-security-test:6.2.1")
    testImplementation(kotlin("test"))

    liquibaseRuntime ("org.liquibase:liquibase-core:4.24.0")
    liquibaseRuntime ("info.picocli:picocli:4.7.5")
    liquibaseRuntime ("org.postgresql:postgresql:42.2.27")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}


application {
    mainClass.set("Application")
}

liquibase {
    activities.register("development") {
        this.arguments = mapOf(
            "changeLogFile" to "src/main/resources/db.yaml",
            "url" to "",
            "username" to "",
            "password" to "",
            "driver" to ""
        )
    }
}
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.7.10"
	id("io.spring.dependency-management") version "1.0.15.RELEASE"
	kotlin("jvm") version "1.6.21"
	kotlin("plugin.spring") version "1.6.21"
	kotlin("plugin.jpa") version "1.6.10"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
	mavenCentral()
}

extra["mysqlVersion"] = "8.0.16"
extra["jwtVersion"] = "3.14.0"

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-security")

	implementation("org.springframework.cloud:spring-cloud-starter-openfeign:3.1.4")

	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("com.auth0:java-jwt:${property("jwtVersion")}")

	implementation("org.jetbrains.kotlin:kotlin-reflect")

	implementation("com.squareup.okhttp3:okhttp:4.9.1")
	implementation("com.google.code.gson:gson:2.8.8")

	implementation("mysql:mysql-connector-java:${property("mysqlVersion")}")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

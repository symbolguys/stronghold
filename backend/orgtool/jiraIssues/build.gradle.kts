plugins {
	java
	id("org.springframework.boot") version "2.7.9"
	id("io.spring.dependency-management") version "1.0.15.RELEASE"
}

group = "com.hackathon.orgroup"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.apache.httpcomponents:httpclient:4.5.13")
	implementation("oauth.signpost:signpost-core:2.1.1")
	implementation("org.springframework.boot:spring-boot-starter-amqp")
	implementation("com.fasterxml.jackson.core:jackson-databind")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.amqp:spring-rabbit-test")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

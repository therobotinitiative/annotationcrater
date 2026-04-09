group = "com.orbital3d"
version = "1.0"

plugins {
    `java-library`
    `checkstyle`
    `jacoco`
  	`maven-publish`
	id("com.diffplug.spotless") version("8.4.0")
	id("org.owasp.dependencycheck") version "12.2.0"
}

checkstyle {
	configFile = File("${project.rootDir}/checkstyle.xml")
}

spotless {
	java {
		googleJavaFormat()
		target("src/main/java/**/*.java", "src/test/java/**/*.java")
		trimTrailingWhitespace()
		endWithNewline()
	}
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
    mavenLocal()
}

dependencies {
    implementation("com.orbital3d:domain:1.0-RELEASE")

    // Use JUnit Jupiter for testing.
	testImplementation("org.junit.jupiter:junit-jupiter:5.10.2")
	testImplementation("org.mockito:mockito-core:5.5.0")
	testImplementation("com.google.testing.compile:compile-testing:0.23.0")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher:1.10.2")
}

// Apply a specific Java toolchain to ease working on different environments.
java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
	withSourcesJar()
	withJavadocJar()
}

// Ensure code is formatted before build
tasks.named("build") {
	dependsOn("spotlessApply")
}

tasks.named<Test>("test") {
    // Use JUnit Platform for unit tests.
    useJUnitPlatform()
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }
}

// Configure OWASP Dependency Check tasks with different CVSS thresholds for different build types
tasks.register("dependencyCheckRelease", org.owasp.dependencycheck.gradle.tasks.Analyze::class.java) {
    dependencyCheck {
        failBuildOnCVSS.set(9.0f)
    }
}

tasks.register("dependencyCheckCI", org.owasp.dependencycheck.gradle.tasks.Analyze::class.java) {
    dependencyCheck {
        failBuildOnCVSS.set(3.0f)
    }
}